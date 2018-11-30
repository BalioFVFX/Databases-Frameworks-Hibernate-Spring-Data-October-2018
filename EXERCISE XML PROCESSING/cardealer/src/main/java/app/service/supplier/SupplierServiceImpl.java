package app.service.supplier;

import app.domain.dto.input.query1.SupplierDto;
import app.domain.dto.input.query1.SupplierListDto;
import app.domain.dto.output.query3.Q3SupplierDto;
import app.domain.dto.output.query3.Q3SupplierListDto;
import app.domain.entity.PartSupplier;
import app.modelmappercfg.CMapper;
import app.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void saveAll(SupplierListDto supplierListDto) {
        for (SupplierDto supplier: supplierListDto.getSuppliers()){
            PartSupplier entity = CMapper.mapper().map(supplier, PartSupplier.class);
            this.supplierRepository.saveAndFlush(entity);
        }
    }

    @Override
    public Q3SupplierListDto query3() {
        Q3SupplierListDto result = new Q3SupplierListDto();
        List<PartSupplier> supplierEntities = this.supplierRepository.findAllByImporterFalse();

        for (PartSupplier entity: supplierEntities){
            Q3SupplierDto q3SupplierDto = CMapper.mapper().map(entity, Q3SupplierDto.class);
            result.getSuppliers().add(q3SupplierDto);
        }

        return result;
    }

}
