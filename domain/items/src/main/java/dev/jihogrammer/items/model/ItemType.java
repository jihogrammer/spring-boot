package dev.jihogrammer.items.model;

public enum ItemType {

    BOOK("도서"),
    FOOD("음식"),
    ETC("기타");

    private final String description;

    ItemType(final String description) {
        this.description = description;
    }

    public String description() {
        return this.description;
    }

}
