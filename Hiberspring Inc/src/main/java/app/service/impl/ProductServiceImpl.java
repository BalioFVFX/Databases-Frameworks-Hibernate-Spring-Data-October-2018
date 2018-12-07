package app.service.impl;

import app.entity.Branch;
import app.entity.Product;
import app.entity.dto.importxml.ProductXmlDto;
import app.entity.dto.importxml.ProductsXmlListDto;
import app.mappers.modelmapper.CMapper;
import app.mappers.xml.XmlMapper;
import app.repository.BranchRepository;
import app.repository.ProductRepository;
import app.service.ProductService;
import app.validator.CValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.File;

@Service
public class ProductServiceImpl implements ProductService {
    private final static String PRODUCT_XML_FILE_PATH = "C:\\Users\\Erik\\Desktop\\Hiberspring Inc\\src\\main\\resources\\xml\\input\\products.xml";

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, BranchRepository branchRepository) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public void saveAll() throws JAXBException {
        StringBuilder output = new StringBuilder();
        ProductsXmlListDto productsXmlListDto = XmlMapper.unmarshall(new File(PRODUCT_XML_FILE_PATH), ProductsXmlListDto.class);

        for (ProductXmlDto productDto : productsXmlListDto.getProducts()) {
            if(!CValidator.isValid(productDto)){
                output.append("Error: Incorrect data.").append(System.lineSeparator());
                continue;
            }

            Branch branchEntity = this.branchRepository.findByName(productDto.getBranch()).orElse(null);

            if(branchEntity == null){
                output.append("Error: Incorrect data.").append(System.lineSeparator());
                continue;
            }

            Product productEntityToImport = CMapper.mapper().map(productDto, Product.class);
            productEntityToImport.setBranch(branchEntity);
            this.productRepository.saveAndFlush(productEntityToImport);
            output.append(String.format("Successfully imported %s %s", "Product", productEntityToImport.getName())).append(System.lineSeparator());
        }
        System.out.println(output.toString());
    }
}
