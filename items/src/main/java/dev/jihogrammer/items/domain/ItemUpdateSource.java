package dev.jihogrammer.items.domain;

public record ItemUpdateSource(ItemId itemId, String name, Integer price, Integer quantity) {}
