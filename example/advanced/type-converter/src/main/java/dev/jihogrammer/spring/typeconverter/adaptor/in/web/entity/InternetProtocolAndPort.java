package dev.jihogrammer.spring.typeconverter.adaptor.in.web.entity;

public record InternetProtocolAndPort(String ip, Integer port) {
    public static final char DELIMITER_COLON = ':';
}
