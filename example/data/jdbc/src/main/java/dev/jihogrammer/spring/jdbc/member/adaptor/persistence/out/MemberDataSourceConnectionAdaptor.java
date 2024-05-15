package dev.jihogrammer.spring.jdbc.member.adaptor.persistence.out;

import dev.jihogrammer.spring.jdbc.member.domain.Member;
import dev.jihogrammer.spring.jdbc.member.domain.MemberId;
import dev.jihogrammer.spring.jdbc.member.domain.exception.MemberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.NoSuchElementException;

@Slf4j
class MemberDataSourceConnectionAdaptor extends MemberDataSourceAdaptor {

    private static final String FIND_BY_ID_SQL = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";
    private static final String UPDATE_SQL = "UPDATE MEMBER SET MONEY = ? WHERE MEMBER_ID = ?";

    public MemberDataSourceConnectionAdaptor(final DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Member findById(final Connection connection, final MemberId memberId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL);
            preparedStatement.setString(1, memberId.value());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Member.of(resultSet.getString("MEMBER_ID"), resultSet.getInt("MONEY"));
            } else {
                throw new NoSuchElementException("Could not find a member.");
            }
        } catch (SQLException e) {
            log.error("Failed to find member by {}.", memberId);
            throw new MemberException(e);
        } finally {
            JdbcUtils.closeStatement(preparedStatement);
            JdbcUtils.closeResultSet(resultSet);
        }
    }

    @Override
    public Member update(final Connection connection, final Member member) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(UPDATE_SQL);
            preparedStatement.setInt(1, member.money());
            preparedStatement.setString(2, member.id().value());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to save member.");
            throw new MemberException(e);
        } finally {
            JdbcUtils.closeStatement(preparedStatement);
        }

        return member;
    }

}
