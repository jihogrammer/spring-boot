package dev.jihogrammer.spring.jdbc.member.adaptor.persistence.out;

import dev.jihogrammer.spring.jdbc.member.application.port.out.MemberPort;
import dev.jihogrammer.spring.jdbc.member.domain.Member;
import dev.jihogrammer.spring.jdbc.member.domain.MemberId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

@Slf4j
class MemberJDBCTemplateAdaptor implements MemberPort {

    private static final String MEMBER_TABLE = "MEMBER";

    private static final String MEMBER_ID_COLUMN = "MEMBER_ID";

    private static final String MONEY_COLUMN = "MONEY";

    private static final String SAVE_SQL = "INSERT INTO %s (%s, %s) VALUES (?, ?)".formatted(MEMBER_TABLE, MEMBER_ID_COLUMN, MONEY_COLUMN);

    private static final String FIND_BY_ID_SQL = "SELECT * FROM %s WHERE %s = ?".formatted(MEMBER_TABLE, MEMBER_ID_COLUMN);

    private static final String UPDATE_SQL = "UPDATE %s SET %s = ? WHERE %s = ?".formatted(MEMBER_TABLE, MONEY_COLUMN, MEMBER_ID_COLUMN);

    private static final String DELETE_SQL = "DELETE FROM %s WHERE %s = ?".formatted(MEMBER_TABLE, MEMBER_ID_COLUMN);

    private final JdbcTemplate jdbcTemplate;

    public MemberJDBCTemplateAdaptor(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(final Member member) {
        this.jdbcTemplate.update(SAVE_SQL, member.id().value(), member.money());
        return member;
    }

    @Override
    public Member findById(final MemberId memberId) {
        return this.jdbcTemplate.queryForObject(FIND_BY_ID_SQL, mapper(), memberId.value());
    }

    @Override
    public Member update(final Member member) {
        this.jdbcTemplate.update(UPDATE_SQL, member.money(), member.id().value());
        return member;
    }

    @Override
    public boolean delete(MemberId memberId) {
        return 1 == this.jdbcTemplate.update(DELETE_SQL, memberId);
    }

    private RowMapper<Member> mapper() {
        return (resultSet, i) -> Member.of(resultSet.getString(MEMBER_ID_COLUMN), resultSet.getInt(MONEY_COLUMN));
    }

}
