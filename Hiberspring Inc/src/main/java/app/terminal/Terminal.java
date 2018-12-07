package app.terminal;

import app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Terminal implements CommandLineRunner {

    private final TownService townService;
    private final BranchService branchService;
    private final EmployeeCardService employeeCardService;
    private final ProductService productService;
    private final EmployeeService employeeService;

    @Autowired
    public Terminal(TownService townService, BranchService branchService, EmployeeCardService employeeCardService, ProductService productService, EmployeeService employeeService) {
        this.townService = townService;
        this.branchService = branchService;
        this.employeeCardService = employeeCardService;
        this.productService = productService;
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {

        // Seed
        this.townService.saveAll();
        this.branchService.saveAll();
        this.employeeCardService.saveAll();
        this.productService.saveAll();
        this.employeeService.saveAll();

        // Export
        this.employeeCardService.saveFreeCards();
        this.employeeService.saveProductiveEmployees();
        this.townService.saveTowns();
        this.branchService.saveAllTopBranches();
    }
}
