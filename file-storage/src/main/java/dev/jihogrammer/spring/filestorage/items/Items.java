package dev.jihogrammer.spring.filestorage.items;

public interface Items {

    Item save(Item item);

    Item findById(Long id);

}
