package hello.itemservice.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    private ItemRepository itemRepository = new ItemRepository();


    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }


    @Test
    void save() {
        Item item = new Item("itemA", 10000, 10);

        Item savedItem = itemRepository.save(item);

        Item findItem = itemRepository.findById(1L);

        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 10000, 10);

        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> items = itemRepository.findAll();

        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(item1, item2);
    }

    @Test
    void update() {
        Item item1 = new Item("item1", 20000, 20);
        Item item2 = new Item("item2", 10000, 10);

        itemRepository.save(item1);

        itemRepository.update(1L,item2);

        assertThat(itemRepository.findById(1L).getItemName()).isEqualTo(item2.getItemName());
        assertThat(itemRepository.findById(1L).getPrice()).isEqualTo(item2.getPrice());
        assertThat(itemRepository.findById(1L).getQuantity()).isEqualTo(item2.getQuantity());
    }
}