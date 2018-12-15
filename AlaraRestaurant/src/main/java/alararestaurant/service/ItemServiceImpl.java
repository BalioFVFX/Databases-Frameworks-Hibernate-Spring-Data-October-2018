package alararestaurant.service;

import alararestaurant.domain.dtos.importjson.ItemJsonDto;
import alararestaurant.domain.entities.Category;
import alararestaurant.domain.entities.Item;
import alararestaurant.repository.CategoryRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ItemServiceImpl implements ItemService {

    private final static String ITEMS_JSON_FILE_PATH = System.getProperty("user.dir") + ("\\src\\main\\resources\\files\\items.json");

    private final ItemRepository itemRepository;
    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final CategoryRepository categoryRepository;



    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, FileUtil fileUtil, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Boolean itemsAreImported() {
        return this.itemRepository.count() > 0;
    }

    @Override
    public String readItemsJsonFile() throws IOException {
        return this.fileUtil.readFile(ITEMS_JSON_FILE_PATH);
    }

    @Override
    public String importItems(String items) {
        StringBuilder output = new StringBuilder();
        ItemJsonDto[] itemJsonDtos = this.gson.fromJson(items, ItemJsonDto[].class);

        for (ItemJsonDto itemJsonDto : itemJsonDtos) {
            if(!this.validationUtil.isValid(itemJsonDto)){
                output.append("Invalid data format.").append(System.lineSeparator());
                continue;
            }

            Item itemEntity = this.itemRepository.findByName(itemJsonDto.getName()).orElse(null);

            if(itemEntity != null){
                output.append("Invalid data format.").append(System.lineSeparator());
                continue;
            }
            Category categoryToImport = this.categoryRepository.findByName(itemJsonDto.getCategory()).orElse(null);

            if(categoryToImport == null){
                categoryToImport = new Category();
                categoryToImport.setName(itemJsonDto.getCategory());
                this.categoryRepository.saveAndFlush(categoryToImport);
                categoryToImport = this.categoryRepository.findByName(categoryToImport.getName()).orElse(null);
            }

            Item itemToImport = this.modelMapper.map(itemJsonDto, Item.class);
            itemToImport.setCategory(categoryToImport);
            this.itemRepository.saveAndFlush(itemToImport);
            output.append(String.format("Record %s successfully imported.", itemToImport.getName())).append(System.lineSeparator());
        }
        return output.toString();
    }
}
