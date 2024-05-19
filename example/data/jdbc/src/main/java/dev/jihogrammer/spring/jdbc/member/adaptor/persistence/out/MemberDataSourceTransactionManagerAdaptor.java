package dev.jihogrammer.spring.jdbc.member.adaptor.persistence.out;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Slf4j
class MemberDataSourceTransactionManagerAdaptor extends MemberDataSourceAdaptor {

    public MemberDataSourceTransactionManagerAdaptor(final DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected Connection getConnection() {
        // Connection 객체를 DataSource 통해서 직접 받지 않고, 유틸을 통해 받는다.
        var connection = DataSourceUtils.getConnection(this.dataSource);
        log.trace("get connection => {}; {};", connection, connection.getClass());
        return connection;
    }

    @Override
    protected void close(final ResultSet resultSet, final Statement statement, final Connection connection) {
        JdbcUtils.closeResultSet(resultSet);
        JdbcUtils.closeStatement(statement);
        // Connection 객체를 직접 닫지 않고, 유틸을 통해 해제한다.
        DataSourceUtils.releaseConnection(connection, this.dataSource);
    }

}
