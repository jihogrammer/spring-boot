package dev.jihogrammer.items.vo;

public enum ItemType {
    BOOK("도서"), FOOD("음식"), ETC("기타");

    private final String description;

    ItemType(final String description) {
        this.description = description;
    }
}
