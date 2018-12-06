package app.service.contest;

import app.domain.dto.importjson.contest.ContestJsonDto;
import app.domain.entity.Category;
import app.domain.entity.Contest;
import app.mapperconfig.CMapper;
import app.repository.CategoryRepository;
import app.repository.ContestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContestServiceImpl implements ContestService{

    private final ContestRepository contestRepository;
    private final CategoryRepository categoryRepository;

    public ContestServiceImpl(ContestRepository contestRepository, CategoryRepository categoryRepository) {
        this.contestRepository = contestRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void saveAll(List<ContestJsonDto> contestJsonDtos) {
        for (ContestJsonDto contestJsonDto : contestJsonDtos) {

            if(this.contestRepository.findByName(contestJsonDto.getName()).orElse(null) != null){
                System.out.println("Category must be unique!");
                continue;
            }
            Category categoryEntity = this.categoryRepository.findByName(contestJsonDto.getCategory().getName()).orElse(null);

            if(categoryEntity == null){
                System.out.println("No category with this name");
                continue;
            }

            Contest contestEntity = CMapper.mapper().map(contestJsonDto, Contest.class);

            contestEntity.setCategory(categoryEntity);
            this.contestRepository.saveAndFlush(contestEntity);
            System.out.println("Successfully imported contest - " + contestEntity.getName());
        }
    }
}
