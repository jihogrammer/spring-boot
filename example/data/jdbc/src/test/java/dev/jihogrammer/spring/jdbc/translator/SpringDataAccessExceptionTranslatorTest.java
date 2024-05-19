package dev.jihogrammer.spring.jdbc.translator;

import dev.jihogrammer.spring.jdbc.member.MemberIntegrationTest;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Slf4j
class SpringDataAccessExceptionTranslatorTest extends MemberIntegrationTest {

    SQLExceptionTranslator translator;

    @BeforeEach
    void setUp() {
        this.translator = new SQLErrorCodeSQLExceptionTranslator(this.dataSource);
    }

    @Test
    void badGrammar() {
        // given
        var badSQL = "bad sql statement.";

        // when
        ThrowingCallable when = () -> {
            try {
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(badSQL);
                preparedStatement.executeQuery();
            } catch (SQLException e) {
                var dataAccessException = this.translator.translate("task", badSQL, e);
                requireNonNull(dataAccessException);

                log.error("translated exception. ({})", dataAccessException.getClass(), dataAccessException);
                throw dataAccessException;
            }
        };

        // then
        assertThatThrownBy(when).isInstanceOf(BadSqlGrammarException.class);
    }

}
