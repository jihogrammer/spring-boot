package dev.jihogrammer.items.port.out;

import dev.jihogrammer.items.model.vo.ItemType;

import java.util.Set;

public record ItemUpdateCommand(
    Long id,
    String name,
    Integer price,
    Integer quantity,
    Boolean open,
    Set<String> regions,
    ItemType itemType,
    String deliveryCode
) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;

        private String name;

        private Integer price;

        private Integer quantity;

        private Boolean isOpen;

        private Set<String> regions;

        private ItemType itemType;

        private String deliveryCode;

        private Builder() {}

        public Builder id(final Long id) {
            this.id = id;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder price(final Integer price) {
            this.price = price;
            return this;
        }

        public Builder quantity(final Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder isOpen(final Boolean isOpen) {
            this.isOpen = isOpen;
            return this;
        }

        public Builder regions(final Set<String> regions) {
            this.regions = regions;
            return this;
        }

        public Builder itemType(final ItemType itemType) {
            this.itemType = itemType;
            return this;
        }

        public Builder deliveryCode(final String deliveryCode) {
            this.deliveryCode = deliveryCode;
            return this;
        }

        public ItemUpdateCommand build() {
            return new ItemUpdateCommand(
                this.id,
                this.name,
                this.price,
                this.quantity,
                this.isOpen,
                this.regions,
                this.itemType,
                this.deliveryCode);
        }

    }

}
