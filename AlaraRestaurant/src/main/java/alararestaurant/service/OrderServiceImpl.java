package alararestaurant.service;

import alararestaurant.domain.dtos.importxml.ItemXmlDto;
import alararestaurant.domain.dtos.importxml.OrderListXmlDto;
import alararestaurant.domain.dtos.importxml.OrderXmlDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Item;
import alararestaurant.domain.entities.Order;
import alararestaurant.domain.entities.OrderItem;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.repository.OrderRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import alararestaurant.util.XmlParser;
import alararestaurant.util.XmlParserImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final static String ORDERS_XML_FILE_PATH = System.getProperty("user.dir") + ("\\src\\main\\resources\\files\\orders.xml");
    private final OrderRepository orderRepository;
    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final EmployeeRepository employeeRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, FileUtil fileUtil, ModelMapper modelMapper, ValidationUtil validationUtil, EmployeeRepository employeeRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.employeeRepository = employeeRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public Boolean ordersAreImported() {
        return this.orderRepository.count() > 0;
    }

    @Override
    public String readOrdersXmlFile() throws IOException {
        return this.fileUtil.readFile(ORDERS_XML_FILE_PATH);
    }

    @Override
    public String importOrders() throws JAXBException {
        StringBuilder output = new StringBuilder();
        XmlParser xmlParser = new XmlParserImpl();
        OrderListXmlDto orderListXmlDto = xmlParser.parseXml(OrderListXmlDto.class, ORDERS_XML_FILE_PATH);

        for (OrderXmlDto orderDto : orderListXmlDto.getOrders()) {
            boolean isDataValid = true;
            if(!validationUtil.isValid(orderDto)){
                output.append("Invalid data format.").append(System.lineSeparator());
                continue;
            }

            Employee employeeEntity = this.employeeRepository.findByName(orderDto.getEmployee()).orElse(null);

            if(employeeEntity == null){
                output.append("Invalid data format.").append(System.lineSeparator());
                continue;
            }


            Order orderEntity = this.modelMapper.map(orderDto, Order.class);
            orderEntity.setEmployee(employeeEntity);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime formatDateTime = LocalDateTime.parse(orderDto.getDateTime(), formatter);
            orderEntity.setDateTime(formatDateTime);

            List<OrderItem> orderItemEntities = new ArrayList<>();
            for (ItemXmlDto item : orderDto.getItems().getItems()) {
                Item itemEntity = this.itemRepository.findByName(item.getName()).orElse(null);
                if(itemEntity == null){
                    isDataValid = false;
                    break;
                }
                OrderItem orderItemEntity = this.modelMapper.map(item, OrderItem.class);
                orderItemEntity.setItem(itemEntity);
                orderItemEntity.setOrder(orderEntity);
                orderItemEntities.add(orderItemEntity);
            }
            orderEntity.setOrderItems(orderItemEntities);

            if(!isDataValid){
                output.append("Invalid data format.").append(System.lineSeparator());
                continue;
            }

            if(!orderDto.getType().equals("ForHere") && !orderDto.getType().equals("ToGo")){
                output.append("Invalid data format.").append(System.lineSeparator());
                continue;
            }


            this.orderRepository.saveAndFlush(orderEntity);
            output.append(String.format("Order for %s on %s added", orderEntity.getCustomer(), orderDto.getDateTime())).append(System.lineSeparator());

        }
        return output.toString();
    }

    @Override
    public String exportOrdersFinishedByTheBurgerFlippers() {
        StringBuilder output = new StringBuilder();
        List<Order> query2 = this.orderRepository.query2();
        for (Order order : query2) {
            output.append("Name: " + order.getEmployee().getName()).append(System.lineSeparator());
            output.append("Orders:").append(System.lineSeparator());
            output.append(" Customer: " + order.getCustomer()).append(System.lineSeparator());
            output.append(" Items:").append(System.lineSeparator());
            for (OrderItem orderItem : order.getOrderItems()) {
                output.append("     Name: " + orderItem.getItem().getName()).append(System.lineSeparator());
                output.append("     Price: " + orderItem.getItem().getPrice()).append(System.lineSeparator());
                output.append("     Quantity: " + orderItem.getQuantity()).append(System.lineSeparator());
                output.append(System.lineSeparator());
            }
        }

        return output.toString();
    }
}
