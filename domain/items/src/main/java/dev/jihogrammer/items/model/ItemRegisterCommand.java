package dev.jihogrammer.items.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Set;

@JsonDeserialize(builder = ItemRegisterCommand.Builder.class)
public record ItemRegisterCommand(
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

    @SuppressWarnings("unused")
    public static class Builder {

        private String name;

        private Integer price;

        private Integer quantity;

        private Boolean open;

        private Set<String> regions;

        private ItemType itemType;

        private String deliveryCode;

        private Builder() {}

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

        public Builder open(final Boolean open) {
            this.open = open;
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

        public ItemRegisterCommand build() {
            return new ItemRegisterCommand(
                this.name,
                this.price,
                this.quantity,
                this.open,
                this.regions,
                this.itemType,
                this.deliveryCode);
        }

    }

}
