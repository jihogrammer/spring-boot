package dev.jihogrammer.spring.jdbc.member.application.port.in;

public interface SendMoneyPort {

    void sendMoney(SendMoneyCommand command);

}
