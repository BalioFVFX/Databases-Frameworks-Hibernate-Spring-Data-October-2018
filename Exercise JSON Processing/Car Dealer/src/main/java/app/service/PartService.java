package app.service;

import app.domain.dto.importDto.PartImportDto;
import app.domain.entity.Part;

import java.util.List;

public interface PartService {
    void saveAll(List<PartImportDto> parts);
}
