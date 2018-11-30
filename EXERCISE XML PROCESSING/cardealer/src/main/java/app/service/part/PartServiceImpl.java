package app.service.part;

import app.domain.dto.input.query2.PartDto;
import app.domain.dto.input.query2.PartListDto;
import app.domain.entity.Part;
import app.domain.entity.PartSupplier;
import app.modelmappercfg.CMapper;
import app.repository.PartRepository;
import app.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PartServiceImpl implements PartService {

    private final SupplierRepository supplierRepository;
    private final PartRepository partRepository;

    @Autowired
    public PartServiceImpl(SupplierRepository supplierRepository, PartRepository partRepository) {
        this.supplierRepository = supplierRepository;
        this.partRepository = partRepository;
    }

    @Override
    public void saveAll(PartListDto partListDto) {
        List<PartSupplier> supplierEntities = this.supplierRepository.findAll();
        Random rnd = new Random();
        for (PartDto part: partListDto.getParts()){
            Part entity = CMapper.mapper().map(part, Part.class);

            int randomSupplierIndex = rnd.nextInt(supplierEntities.size() - 1);
            entity.setPartSupplier(supplierEntities.get(randomSupplierIndex));

            this.partRepository.saveAndFlush(entity);
        }
    }
}
