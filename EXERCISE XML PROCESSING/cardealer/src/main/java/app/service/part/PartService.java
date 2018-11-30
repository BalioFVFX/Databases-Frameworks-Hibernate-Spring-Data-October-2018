package app.service.part;

import app.domain.dto.input.query2.PartListDto;

public interface PartService {
    void saveAll(PartListDto partListDto);
}
