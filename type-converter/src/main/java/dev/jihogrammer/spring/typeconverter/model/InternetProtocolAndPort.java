package dev.jihogrammer.spring.typeconverter.model;

public record InternetProtocolAndPort(String ip, int port) {
    public static final char DELIMITER_COLON = ':';

    @Override
    public String toString() {
        return this.ip + DELIMITER_COLON + this.port;
    }
}
