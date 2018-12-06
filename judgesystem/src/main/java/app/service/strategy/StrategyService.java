package app.service.strategy;

import app.domain.dto.importjson.strategy.StrategyJsonDto;

import java.util.List;

public interface StrategyService {
    void saveAll(List<StrategyJsonDto> strategyJsonDtos);
}
