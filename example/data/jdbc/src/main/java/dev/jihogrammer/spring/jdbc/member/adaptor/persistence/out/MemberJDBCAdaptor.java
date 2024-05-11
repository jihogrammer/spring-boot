package dev.jihogrammer.spring.jdbc.member.adaptor.persistence.out;

import dev.jihogrammer.spring.jdbc.connection.DatabaseConnectionUtils;
import dev.jihogrammer.spring.jdbc.member.application.port.out.MemberPort;
import dev.jihogrammer.spring.jdbc.member.domain.Member;
import dev.jihogrammer.spring.jdbc.member.domain.exception.MemberException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
@RequiredArgsConstructor
public class MemberJDBCAdaptor implements MemberPort {

    private final DatabaseConnectionUtils connectionUtils;

    @Override
    public Member save(final Member member) {
        final var sql = "INSERT INTO MEMBER (MEMBER_ID, MONEY) VALUES (?, ?)";
        final var connection = connectionUtils.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, member.id().value());
            preparedStatement.setInt(2, member.money());

            preparedStatement.executeUpdate();

            connection.commit();
            return member;
        } catch (SQLException e) {
            log.error("Failed to save member.", e);
            throw new MemberException(e);
        } finally {
            close(connection, preparedStatement, null);
        }
    }

    private void close(final Connection connection, final Statement statement, final ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.error("Failed to close resultSet.", e);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                log.error("Failed to close statement.", e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Failed to close connection.", e);
            }
        }
    }

}
