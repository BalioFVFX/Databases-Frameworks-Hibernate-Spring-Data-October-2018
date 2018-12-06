package app.service.contest;

import app.domain.dto.importjson.contest.ContestJsonDto;

import java.util.List;

public interface ContestService {
    void saveAll(List<ContestJsonDto> contestJsonDtos);
}
