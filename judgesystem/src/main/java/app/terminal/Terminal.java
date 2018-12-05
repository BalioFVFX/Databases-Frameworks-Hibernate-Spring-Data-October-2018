package app.terminal;

import app.domain.dto.importjson.category.CategoryJsonDto;
import app.domain.dto.importjson.contest.ContestJsonDto;
import app.domain.dto.importjson.strategy.StrategyJsonDto;
import app.domain.dto.importjson.user.UserJsonDto;
import app.service.category.CategoryService;
import app.service.contest.ContestService;
import app.service.problem.ProblemService;
import app.service.strategy.StrategyService;
import app.service.submission.SubmissionService;
import app.service.user.UserService;
import app.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Component
public class Terminal implements CommandLineRunner {

    private final CategoryService categoryService;
    private final StrategyService strategyService;
    private final ContestService contestService;
    private final UserService userService;
    private final ProblemService problemService;
    private final SubmissionService submissionService;
    @Autowired
    public Terminal(CategoryService categoryService, StrategyService strategyService, ContestService contestService, UserService userService, ProblemService problemService, SubmissionService submissionService) {
        this.categoryService = categoryService;
        this.strategyService = strategyService;
        this.contestService = contestService;
        this.userService = userService;
        this.problemService = problemService;
        this.submissionService = submissionService;
    }


    @Override
    public void run(String... args) throws IOException, JAXBException {
        // SEED CATEGORIES
        List<CategoryJsonDto> categoryJsonDtoList = FileUtil.parseJson("C:\\Users\\Erik\\Desktop\\judgesystem\\src\\main\\resources\\input\\json\\categories.json", CategoryJsonDto.class);
        this.categoryService.saveJson(categoryJsonDtoList);


        // SEED STRATEGIES
        List<StrategyJsonDto> strategies = FileUtil.parseJson("C:\\Users\\Erik\\Desktop\\judgesystem\\src\\main\\resources\\input\\json\\strategies.json", StrategyJsonDto.class);
        this.strategyService.saveAll(strategies);

        // Seed contests
        List<ContestJsonDto> contestJsonDtos = FileUtil.parseJson("C:\\Users\\Erik\\Desktop\\judgesystem\\src\\main\\resources\\input\\json\\contests.json", ContestJsonDto.class);
        this.contestService.saveAll(contestJsonDtos);

        // Seed users
        List<UserJsonDto> userJsonDtos = FileUtil.parseJson("C:\\Users\\Erik\\Desktop\\judgesystem\\src\\main\\resources\\input\\json\\users.json", UserJsonDto.class);
        this.userService.saveAll(userJsonDtos);

        // Seed problems

        this.problemService.importProblems();

        // Seed user participations
        this.userService.updateUserParticipations();

        this.submissionService.saveSubmissions();
    }
}
