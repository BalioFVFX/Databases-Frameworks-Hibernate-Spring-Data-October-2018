package app.service.submission;

import app.domain.dto.importxml.submission.SubmissionListXml;
import app.domain.dto.importxml.submission.SubmissionXml;
import app.domain.entity.*;
import app.mapperconfig.CMapper;
import app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubmissionServiceImpl implements SubmissionService{

    private final UserRepository userRepository;
    private final SubmissionRepository submissionRepository;
    private final ProblemRepository problemRepository;
    private final MaximumResultsForContestRepository maximumResultsForContestRepository;
    private final UserMaximumResultForProblemRepository userMaximumResultForProblemRepository;

    @Autowired
    public SubmissionServiceImpl(UserRepository userRepository, SubmissionRepository submissionRepository, ProblemRepository problemRepository, MaximumResultsForContestRepository maximumResultsForContestRepository, UserMaximumResultForProblemRepository userMaximumResultForProblemRepository) {
        this.userRepository = userRepository;
        this.submissionRepository = submissionRepository;
        this.problemRepository = problemRepository;
        this.maximumResultsForContestRepository = maximumResultsForContestRepository;
        this.userMaximumResultForProblemRepository = userMaximumResultForProblemRepository;
    }

    @Override
    public void saveSubmissions() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(SubmissionListXml.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        SubmissionListXml submissionListXml =  (SubmissionListXml)unmarshaller.unmarshal(new File("C:\\Users\\Erik\\Desktop\\judgesystem\\src\\main\\resources\\input\\json\\submissions.xml"));

        for (SubmissionXml o : submissionListXml.getSubmissions()) {
            Problem problemEntity = this.problemRepository.findById(o.getProblem().getId()).orElse(null);
            if(problemEntity == null){
                System.out.println("No problem with id " + o.getProblem().getId());
                continue;
            }
            User userEntity = this.userRepository.inContest(problemEntity.getContest().getId(), o.getUser().getId());
            if(userEntity == null){
                System.out.println("User is not contestant in contest!");
                continue;
            }

            Submission submissionEntity = CMapper.mapper().map(o, Submission.class);
            submissionEntity.setContestant(userEntity);
            submissionEntity.setProblem(problemEntity);
            userEntity.getProblems().add(problemEntity);
            this.submissionRepository.saveAndFlush(submissionEntity);
            MaximumResultsForContest maximumResultsForContest = userEntity.getMaximumResultsForContest().stream().filter(r -> {
                if (r.getContest().getId().equals(problemEntity.getContest().getId())) {
                    return true;
                };
                return false;
            }).findFirst().orElse(null);

            Submission submissionMaxUserProblem = this.submissionRepository.getMaxUserProblem(userEntity.getId(), problemEntity.getId()).orElse(null);

            UserMaximumResultsForProblem userMaximumResultsForProblemEntity = this.userMaximumResultForProblemRepository.bestProblemSolution(submissionMaxUserProblem.getContestant().getId(),
                    submissionMaxUserProblem.getProblem().getId()).orElse(null);

            if(userMaximumResultsForProblemEntity == null){
                userMaximumResultsForProblemEntity = new UserMaximumResultsForProblem();
                userMaximumResultsForProblemEntity.setContestant(userEntity);
                userMaximumResultsForProblemEntity.setProblem(problemEntity);
                userMaximumResultsForProblemEntity.setSubmission(submissionMaxUserProblem);
            }
            else{
                if(submissionMaxUserProblem.getPoints() > userMaximumResultsForProblemEntity.getSubmission().getPoints()){
                   userMaximumResultsForProblemEntity.setSubmission(submissionMaxUserProblem);
                }
                else if(submissionMaxUserProblem.getPoints().equals(userMaximumResultsForProblemEntity.getSubmission().getPoints())
                && submissionMaxUserProblem.getPerformanceMs() < userMaximumResultsForProblemEntity.getSubmission().getPerformanceMs()){
                    userMaximumResultsForProblemEntity.setSubmission(submissionMaxUserProblem);
                }
            }

            this.userMaximumResultForProblemRepository.saveAndFlush(userMaximumResultsForProblemEntity);

            if(maximumResultsForContest == null){
                MaximumResultsForContest maximumResultsForContest1 = new MaximumResultsForContest();
                maximumResultsForContest1.setAveragePerformance(submissionEntity.getPerformanceMs());
                maximumResultsForContest1.setContest(problemEntity.getContest());
                maximumResultsForContest1.setContestant(userEntity);
                maximumResultsForContest1.setOverallPoints(submissionEntity.getPoints());
                userEntity.getMaximumResultsForContest().add(maximumResultsForContest1);
                this.maximumResultsForContestRepository.saveAndFlush(maximumResultsForContest1);
                this.userRepository.saveAndFlush(userEntity);
                continue;
            }

            List<Submission> submissionsForUser = this.submissionRepository.contestMaxPonints(userEntity.getId(), problemEntity.getContest().getId());
            double maxPoints = 0;
            double performanceTime = 0;
            int problemsCount = 0;

            submissionsForUser = submissionsForUser.stream().filter(c -> c.getProblem().getContest().getId().equals(problemEntity.getContest().getId())).sorted((p1,p2) -> {
                int result = 0;
                result = p2.getPoints().compareTo(p1.getPoints());
                if(result == 0){
                    return p2.getPerformanceMs().compareTo(p1.getPerformanceMs());
                }
                return result;
            })
                    .collect(Collectors.toList());

            List<Long> prevProblemIds = new ArrayList<>();
            for (Submission submission : submissionsForUser) {
                if(prevProblemIds.contains(submission.getProblem().getId())){
                    continue;
                }

                maxPoints += submission.getPoints();
                performanceTime += submission.getPerformanceMs();
                problemsCount++;
                prevProblemIds.add(submission.getProblem().getId());
            }
            performanceTime /= problemsCount;

            if(maximumResultsForContest.getOverallPoints() < maxPoints){
                maximumResultsForContest.setOverallPoints(maxPoints);
                maximumResultsForContest.setAveragePerformance(performanceTime);

            }

            if(maximumResultsForContest.getOverallPoints().equals(maxPoints) &&
            maximumResultsForContest.getAveragePerformance() > performanceTime){
                maximumResultsForContest.setAveragePerformance(performanceTime);
            }

            this.maximumResultsForContestRepository.saveAndFlush(maximumResultsForContest);
            this.userRepository.saveAndFlush(userEntity);

        }
    }
}
