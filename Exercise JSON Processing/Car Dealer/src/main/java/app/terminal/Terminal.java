package app.terminal;

import app.domain.dto.exportDto.*;
import app.domain.dto.importDto.CarImportDto;
import app.domain.dto.importDto.CustomerImportDto;
import app.domain.dto.importDto.PartImportDto;
import app.domain.dto.importDto.SupplierImportDto;
import app.importexport.FileWriteExport;
import app.importexport.FilerReadImport;
import app.service.*;
import app.utils.GsonManager;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Terminal implements CommandLineRunner {

    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    @Autowired
    public Terminal(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {
        FilerReadImport reader = new FilerReadImport();
        FileWriteExport writter = new FileWriteExport();
        String supplierImportJsonStr = reader.readSuppliers();
        List<SupplierImportDto> suppliersImportDtos = GsonManager.importData(supplierImportJsonStr, SupplierImportDto.class);

        //Seed the suppliers
        supplierService.saveAll(suppliersImportDtos);

        // Seed the parts

        String partImportJsonStr = reader.readParts();
        List<PartImportDto> partImportDtos = GsonManager.importData(partImportJsonStr, PartImportDto.class);
        this.partService.saveAll(partImportDtos);

        // Seed the cars
        String carImportJsonStr = reader.readCars();
        List<CarImportDto> carImportDtos = GsonManager.importData(carImportJsonStr, CarImportDto.class);
        this.carService.saveAll(carImportDtos);

        // Seed the customers

        String customerImportJsonStr = reader.readCustomers();
        List<CustomerImportDto> customerImportDtos = GsonManager.importData(customerImportJsonStr, CustomerImportDto.class);
        this.customerService.saveAll(customerImportDtos);

        // Seed the sales
        this.saleService.saveRandomSales();


        // Query 1
        List<query1Dto> query1Dtos = customerService.query1();
        String query1JsonStr = GsonManager.toJsonFile(query1Dtos);
        writter.writeQuery1(query1JsonStr);


        // Query 2
        List<Query2Dto> query2Dtos = this.carService.query2("Toyota");
        String query2JsonStr = GsonManager.toJsonFile(query2Dtos);
        writter.writeQuery2(query2JsonStr);

        // Query 3
        List<Query3Dto> query3Dtos = this.supplierService.query3();
        String query3JsonStr = GsonManager.toJsonFile(query3Dtos);
        writter.writeQuery3(query3JsonStr);

         //Query 4
        List<Query4Dto> query4Dtos = this.carService.query4();
        String query4JsonStr = GsonManager.toJsonFile(query4Dtos);
        writter.writeQuery4(query4JsonStr);

         //Query 5
        List<Query5Dto> query5Dtos = this.customerService.query5();
        String query5JsonStr = GsonManager.toJsonFile(query5Dtos);
        writter.writeQuery5(query5JsonStr);


        // Query 6
        List<Query6Dto> query6Dtos = this.saleService.query6();
        String query6JsonStr = GsonManager.toJsonFile(query6Dtos);
        writter.writeQuery6(query6JsonStr);

    }
}
