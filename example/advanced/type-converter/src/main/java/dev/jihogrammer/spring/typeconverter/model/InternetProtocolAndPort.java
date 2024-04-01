package dev.jihogrammer.spring.typeconverter.model;

public record InternetProtocolAndPort(String ip, Integer port) {
    public static final char DELIMITER_COLON = ':';
}
