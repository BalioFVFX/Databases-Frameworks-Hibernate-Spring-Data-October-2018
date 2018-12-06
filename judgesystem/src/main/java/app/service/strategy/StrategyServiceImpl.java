package app.service.strategy;

import app.domain.dto.importjson.strategy.StrategyJsonDto;
import app.domain.entity.Strategy;
import app.mapperconfig.CMapper;
import app.repository.StrategyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyServiceImpl implements StrategyService{
    private final StrategyRepository strategyRepository;

    public StrategyServiceImpl(StrategyRepository strategyRepository) {
        this.strategyRepository = strategyRepository;
    }

    @Override
    public void saveAll(List<StrategyJsonDto> strategyJsonDtos) {
        for (StrategyJsonDto strategyJsonDto : strategyJsonDtos) {
            Strategy strategyEntity = CMapper.mapper().map(strategyJsonDto, Strategy.class);
            this.strategyRepository.saveAndFlush(strategyEntity);
        }
    }
}
