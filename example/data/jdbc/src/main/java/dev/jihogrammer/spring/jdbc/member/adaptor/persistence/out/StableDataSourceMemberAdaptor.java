package dev.jihogrammer.spring.jdbc.member.adaptor.persistence.out;

import dev.jihogrammer.spring.jdbc.member.application.port.out.MemberPort;
import dev.jihogrammer.spring.jdbc.member.domain.Member;
import dev.jihogrammer.spring.jdbc.member.domain.MemberId;
import dev.jihogrammer.spring.jdbc.member.domain.exception.MemberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Objects;

@Slf4j
class StableDataSourceMemberAdaptor implements MemberPort {

    private static final String SAVE_SQL = "INSERT INTO MEMBER (MEMBER_ID, MONEY) VALUES (?, ?)";

    private static final String FIND_BY_ID_SQL = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";

    private static final String UPDATE_SQL = "UPDATE MEMBER SET MONEY = ? WHERE MEMBER_ID = ?";

    private static final String DELETE_SQL = "DELETE FROM MEMBER WHERE MEMBER_ID = ?";

    private final DataSource dataSource;

    private final SQLExceptionTranslator sqlExceptionTranslator;

    public StableDataSourceMemberAdaptor(final DataSource dataSource) {
        this.dataSource = dataSource;
        this.sqlExceptionTranslator = new SQLErrorCodeSQLExceptionTranslator(dataSource);
    }

    @Override
    public Member save(final Member member) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.getConnection();

            preparedStatement = connection.prepareStatement(SAVE_SQL);
            preparedStatement.setString(1, member.id().value());
            preparedStatement.setInt(2, member.money());

            preparedStatement.executeUpdate();

            connection.commit();
            return member;
        } catch (SQLException e) {
            throw this.translate("save", SAVE_SQL, e);
        } finally {
            this.close(null, preparedStatement, connection);
        }
    }

    @Override
    public Member findById(MemberId memberId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.getConnection();

            preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL);
            preparedStatement.setString(1, memberId.value());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Member.of(resultSet.getString("MEMBER_ID"), resultSet.getInt("MONEY"));
            } else {
                throw new MemberException("Could not found member");
            }
        } catch (SQLException e) {
            throw this.translate("findById", FIND_BY_ID_SQL, e);
        } finally {
            close(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public Member update(Member member) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.getConnection();

            preparedStatement = connection.prepareStatement(UPDATE_SQL);
            preparedStatement.setInt(1, member.money());
            preparedStatement.setString(2, member.id().value());

            preparedStatement.executeUpdate();

            connection.commit();
            return member;
        } catch (SQLException e) {
            throw this.translate("update", UPDATE_SQL, e);
        } finally {
            close(null, preparedStatement, connection);
        }
    }

    @Override
    public boolean delete(MemberId memberId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.getConnection();

            preparedStatement = connection.prepareStatement(DELETE_SQL);
            preparedStatement.setString(1, memberId.value());

            if (1 == preparedStatement.executeUpdate()) {
                connection.commit();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw this.translate("delete", DELETE_SQL, e);
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

    private RuntimeException translate(final String task, final String sql, final SQLException cause) {
        return Objects.requireNonNullElseGet(
                this.sqlExceptionTranslator.translate(task, sql, cause),
                () -> new MemberException(cause));
    }

    protected void close(final ResultSet resultSet, final Statement statement, final Connection connection) {
        JdbcUtils.closeResultSet(resultSet);
        JdbcUtils.closeStatement(statement);
        JdbcUtils.closeConnection(connection);
    }

}
