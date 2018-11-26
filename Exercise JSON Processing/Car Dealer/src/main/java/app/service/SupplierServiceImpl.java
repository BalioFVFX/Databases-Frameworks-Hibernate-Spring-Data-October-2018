package app.service;

import app.domain.dto.exportDto.Query3Dto;
import app.domain.dto.importDto.SupplierImportDto;
import app.domain.entity.Part;
import app.domain.entity.PartSupplier;
import app.repository.SupplierRepository;
import app.utils.CMapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void saveAll(List<SupplierImportDto> suppliers) {
        Type listType = new TypeToken<List<PartSupplier>>() {}.getType();
        List<PartSupplier> entities = CMapper.mapper().map(suppliers, listType);
        this.supplierRepository.saveAll(entities);
    }

    @Override
    public List<Query3Dto> query3() {
        List<PartSupplier> entities = this.supplierRepository.query3();
        List<Query3Dto> query3Dtos = new ArrayList<>();

        for (PartSupplier entity: entities){
            Query3Dto query3Dto = CMapper.mapper().map(entity, Query3Dto.class);
            query3Dto.setPartsCount(entity.getParts().size() - 1);
            query3Dtos.add(query3Dto);
        }
        return query3Dtos;
    }
}
