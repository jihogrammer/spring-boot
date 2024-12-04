package dev.jihogrammer.spring.boot.autoconfig.member.adpator.h2;

import dev.jihogrammer.spring.boot.autoconfig.member.application.out.Members;
import dev.jihogrammer.spring.boot.autoconfig.member.domain.Member;
import dev.jihogrammer.spring.boot.autoconfig.member.domain.MemberId;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
class H2MemberAdaptor implements Members {

    private static final String[] INITIAL_COMMANDS = {
            "CREATE TABLE MEMBERS(MEMBER_ID VARCHAR PRIMARY KEY, NAME VARCHAR)"
    };

    private final JdbcTemplate jdbcTemplate;

    @PostConstruct
    void postConstruct() {
        for (final var command : INITIAL_COMMANDS) {
            this.jdbcTemplate.execute(command);
        }
    }

    @Override
    public void save(final Member member) {
        this.jdbcTemplate.update(
                "INSERT INTO MEMBERS(MEMBER_ID, NAME) VALUES (?, ?)",
                member.id().value(),
                member.name());
    }

    @Override
    public Member findById(final MemberId id) {
        return this.jdbcTemplate.queryForObject(
                "SELECT MEMBER_ID, NAME FROM MEMBERS WHERE MEMBER_ID = ?",
                (rs, rowNum) -> new Member(new MemberId(rs.getString(1)), rs.getString(2)),
                id.value());
    }

    @Override
    public List<Member> findAll() {
        return this.jdbcTemplate.query(
                "SELECT MEMBER_ID, NAME FROM MEMBERS",
                (rs, rowNum) -> new Member(new MemberId(rs.getString(1)), rs.getString(2)));
    }

}
