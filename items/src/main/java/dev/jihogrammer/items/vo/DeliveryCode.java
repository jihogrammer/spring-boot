package dev.jihogrammer.items.vo;

public record DeliveryCode(String code, String displayName) {
    public enum Type {
        FAST("빠른 배송"),
        NORMAL("일반 배송"),
        SLOW("느린 배송");

        private final String description;

        Type(final String description) {
            this.description = description;
        }

        public String description() {
            return this.description;
        }
    }
}
