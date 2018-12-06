package app.service.problem;

import app.domain.dto.importxml.problem.ProblemListXml;

import app.domain.dto.importxml.problem.ProblemlXml;
import app.domain.entity.Problem;
import app.mapperconfig.CMapper;
import app.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Service
public class ProblemServiceImpl implements ProblemService {

    private final ProblemRepository problemRepository;

    @Autowired
    public ProblemServiceImpl(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    @Override
    public void importProblems() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ProblemListXml.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ProblemListXml problemListXml = (ProblemListXml)unmarshaller.unmarshal(new File("C:\\Users\\Erik\\Desktop\\judgesystem\\src\\main\\resources\\input\\json\\problems.xml"));

        for (ProblemlXml problem : problemListXml.getProblems()) {
            Problem problemEntity = CMapper.mapper().map(problem, Problem.class);
            this.problemRepository.saveAndFlush(problemEntity);
        }

    }
}
