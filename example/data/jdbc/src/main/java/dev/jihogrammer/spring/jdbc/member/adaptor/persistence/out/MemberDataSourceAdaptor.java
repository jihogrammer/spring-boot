package dev.jihogrammer.spring.jdbc.member.adaptor.persistence.out;

import dev.jihogrammer.spring.jdbc.member.application.port.out.MemberPort;
import dev.jihogrammer.spring.jdbc.member.domain.Member;
import dev.jihogrammer.spring.jdbc.member.domain.MemberId;
import dev.jihogrammer.spring.jdbc.member.domain.exception.MemberException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.*;

@Slf4j
@RequiredArgsConstructor
class MemberDataSourceAdaptor implements MemberPort {

    protected final DataSource dataSource;

    @Override
    public Member save(final Member member) {
        final var sql = "INSERT INTO MEMBER (MEMBER_ID, MONEY) VALUES (?, ?)";
        final var connection = this.getConnection();
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
            this.close(null, preparedStatement, connection);
        }
    }

    @Override
    public Member findById(MemberId memberId) {
        final var sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";
        final var connection = this.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberId.value());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Member.of(resultSet.getString("MEMBER_ID"), resultSet.getInt("MONEY"));
            } else {
                throw new MemberException("Could not found member");
            }
        } catch (SQLException e) {
            log.error("Failed to find member by {}.", memberId, e);
            throw new MemberException(e);
        } finally {
            close(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public Member update(Member member) {
        final var sql = "UPDATE MEMBER SET MONEY = ? WHERE MEMBER_ID = ?";
        final var connection = this.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, member.money());
            preparedStatement.setString(2, member.id().value());

            preparedStatement.executeUpdate();

            connection.commit();
            return member;
        } catch (SQLException e) {
            log.error("Failed to save member.", e);
            throw new MemberException(e);
        } finally {
            close(null, preparedStatement, connection);
        }
    }

    @Override
    public boolean delete(MemberId memberId) {
        final var sql = "DELETE FROM MEMBER WHERE MEMBER_ID = ?";
        final var connection = this.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberId.value());

            if (1 == preparedStatement.executeUpdate()) {
                connection.commit();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            log.error("Failed to find member by {}.", memberId, e);
            throw new MemberException(e);
        } finally {
            close(null, preparedStatement, connection);
        }
    }

    protected Connection getConnection() {
        try {
            var connection = this.dataSource.getConnection();
            log.trace("get connection => {}; {};", connection, connection.getClass());
            return connection;
        } catch (final SQLException e) {
            throw new MemberException(e);
        }
    }

    protected void close(final ResultSet resultSet, final Statement statement, final Connection connection) {
        JdbcUtils.closeResultSet(resultSet);
        JdbcUtils.closeStatement(statement);
        JdbcUtils.closeConnection(connection);
    }

}
