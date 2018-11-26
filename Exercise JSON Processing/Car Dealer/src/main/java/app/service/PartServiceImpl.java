package app.service;

import app.domain.dto.importDto.PartImportDto;
import app.domain.entity.Part;
import app.domain.entity.PartSupplier;
import app.repository.PartRepository;
import app.repository.SupplierRepository;
import app.utils.CMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;

@Service
public class PartServiceImpl implements PartService{

    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
    }


    @Override
    public void saveAll(List<PartImportDto> parts) {
        Type listType = new TypeToken<List<Part>>() {}.getType();
        List<Part> entities = CMapper.mapper().map(parts, listType);
        List<PartSupplier> supplierEntities = this.supplierRepository.findAll();
        Random rnd = new Random();
        for (Part entity: entities){
            int randomSupplierIndex = rnd.nextInt(supplierEntities.size() - 1);
            entity.setPartSupplier(supplierEntities.get(randomSupplierIndex));
        }
        this.partRepository.saveAll(entities);
    }
}
