package dev.jihogrammer.items.vo;

public record DeliveryCode(Type type, String displayName) {
    public enum Type {
        FAST, NORMAL, SLOW
    }
}
