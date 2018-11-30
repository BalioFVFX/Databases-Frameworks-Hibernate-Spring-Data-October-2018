package app.terminal;

import app.domain.dto.input.query1.SupplierListDto;
import app.domain.dto.input.query2.PartListDto;
import app.domain.dto.input.query3.CarListDto;
import app.domain.dto.input.query4.CustomerListDto;
import app.domain.dto.output.query1.Q1CustomersList;
import app.domain.dto.output.query2.Q2ToyotaListDto;
import app.domain.dto.output.query3.Q3SupplierListDto;
import app.domain.dto.output.query4.Q4CarListDto;
import app.domain.dto.output.query5.Q5CustomerListDto;
import app.domain.dto.output.query6.Q6SalesListDto;
import app.service.supplier.SupplierService;
import app.service.car.CarService;
import app.service.customer.CustomerSerivce;
import app.service.part.PartService;
import app.service.sale.SaleService;
import app.xmlconfig.XmlTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class Terminal implements CommandLineRunner {

    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerSerivce customerSerivce;
    private final SaleService saleService;

    @Autowired
    public Terminal(SupplierService supplierService, PartService partService, CarService carService, CustomerSerivce customerSerivce, SaleService saleService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerSerivce = customerSerivce;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {

        // Seed the supplier
        File suppliersImportFile = new File("C:\\Users\\Erik\\Desktop\\cardealer\\src\\main\\resources\\input\\suppliers.xml");
        SupplierListDto supplierListDto = XmlTool.unmarshall(suppliersImportFile, SupplierListDto.class);
        this.supplierService.saveAll(supplierListDto);

        // Seed the parts
        File partsImportFile = new File("C:\\Users\\Erik\\Desktop\\cardealer\\src\\main\\resources\\input\\parts.xml");
        PartListDto partListDto = XmlTool.unmarshall(partsImportFile, PartListDto.class);
        this.partService.saveAll(partListDto);

        // Seed the cars
        File carsImportFile = new File("C:\\Users\\Erik\\Desktop\\cardealer\\src\\main\\resources\\input\\cars.xml");
        CarListDto carListDto = XmlTool.unmarshall(carsImportFile, CarListDto.class);
        this.carService.saveAll(carListDto);

        // Seed the customers
        File customerImportFile = new File("C:\\Users\\Erik\\Desktop\\cardealer\\src\\main\\resources\\input\\customers.xml");
        CustomerListDto customerListDto = XmlTool.unmarshall(customerImportFile, CustomerListDto.class);
        customerSerivce.saveAll(customerListDto);

        // Seed the sales
        this.saleService.saveRandomSales();


        // Query 1
        Q1CustomersList query1ObjectResult = this.customerSerivce.query1();
        File query1OutputFile = new File("C:\\Users\\Erik\\Desktop\\cardealer\\src\\main\\resources\\output\\ordered-customers.xml");
        XmlTool.marshall(query1OutputFile, query1ObjectResult);

        // Query 2
        Q2ToyotaListDto query2ObjectResult = this.carService.query2();
        File query2OutputFile = new File("C:\\Users\\Erik\\Desktop\\cardealer\\src\\main\\resources\\output\\toyota-cars.xml");
        XmlTool.marshall(query2OutputFile, query2ObjectResult);

        // Query 3
        Q3SupplierListDto query3ObjectResult = this.supplierService.query3();
        File query3OutputFile = new File("C:\\Users\\Erik\\Desktop\\cardealer\\src\\main\\resources\\output\\local-suppliers.xml");
        XmlTool.marshall(query3OutputFile, query3ObjectResult);

        // Query 4
        Q4CarListDto query4ObjectResult = this.carService.query4();
        File query4OutputFile = new File("C:\\Users\\Erik\\Desktop\\cardealer\\src\\main\\resources\\output\\cars-and-parts.xml");
        XmlTool.marshall(query4OutputFile, query4ObjectResult);

        // Query 5
        Q5CustomerListDto query5ObjectResult = this.customerSerivce.query5();
        File query5OutputFile = new File("C:\\Users\\Erik\\Desktop\\cardealer\\src\\main\\resources\\output\\customers-total-sales.xml");
        XmlTool.marshall(query5OutputFile, query5ObjectResult);

        // Query 6
        Q6SalesListDto query6ObjectResult = this.saleService.query6();
        File query6OutputFile = new File("C:\\Users\\Erik\\Desktop\\cardealer\\src\\main\\resources\\output\\sale-discounts.xml");
        XmlTool.marshall(query6OutputFile, query6ObjectResult);
    }
}
