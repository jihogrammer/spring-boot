package dev.jihogrammer.transaction.member;

import org.assertj.core.api.ThrowableAssert;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.UnexpectedRollbackException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class MemberServiceTest {

    static final String EXCEPTION_CAUSE = "exception";

    @Autowired MemberService service;

    @Autowired MemberRepository members;

    @Autowired LogRepository logs;

    /**
     *          transactional
     * ----------------------
     * service      OFF
     * ----------------------
     * members      ON
     * ----------------------
     * logs         ON
     * ----------------------
     */
    @Test
    void successAsServiceOffAndMembersOnAndLogsOn() {
        // given
        var name = "successAsServiceOffAndMembersOnAndLogsOn";
        assertThat(name).doesNotContain(EXCEPTION_CAUSE);

        // when
        service.saveEachTransaction(name);

        // then
        assertThat(members.findByName(name)).isPresent();
        assertThat(logs.findByMessage(name)).isPresent();
    }

    /**
     *          transactional
     * ----------------------
     * service      OFF
     * ----------------------
     * members      ON
     * ----------------------
     * logs         ON  <<< exception
     * ----------------------
     */
    @Test
    void exceptionAsServiceOffAndMembersOnAndLogsOn() {
        // given
        var name = "exceptionAsServiceOffAndMembersOnAndLogsOn";
        assertThat(name).contains(EXCEPTION_CAUSE);

        // when
        ThrowingCallable when = () -> service.saveEachTransaction(name);

        // then
        assertThatThrownBy(when).isInstanceOf(RuntimeException.class);
        assertThat(members.findByName(name)).isPresent();
        assertThat(logs.findByMessage(name)).isEmpty();
    }

    /**
     *          transactional
     * ----------------------
     * service      ON
     * ----------------------
     * members      OFF
     * ----------------------
     * logs         OFF
     * ----------------------
     */
    @Test
    void successAsServiceONAndMembersOffAndLogsOff() {
        // given
        var name = "successAsServiceONAndMembersOffAndLogsOff";
        assertThat(name).doesNotContain(EXCEPTION_CAUSE);

        // when
        service.saveSingleTransaction(name);

        // then
        assertThat(members.findByName(name)).isPresent();
        assertThat(logs.findByMessage(name)).isPresent();
    }

    /**
     *          transactional
     * ----------------------
     * service      ON
     * ----------------------
     * members      ON
     * ----------------------
     * logs         ON
     * ----------------------
     */
    @Test
    void successAsServiceONAndMembersOnAndLogsOn() {
        // given
        var name = "successAsServiceONAndMembersOnAndLogsOn";
        assertThat(name).doesNotContain(EXCEPTION_CAUSE);

        // when
        service.saveGlobalTransaction(name);

        // then
        assertThat(members.findByName(name)).isPresent();
        assertThat(logs.findByMessage(name)).isPresent();
    }

    /**
     *          transactional
     * ----------------------
     * service      ON
     * ----------------------
     * members      ON
     * ----------------------
     * logs         ON  <<< exception
     * ----------------------
     */
    @Test
    void exceptionAsServiceOnAndMembersOnAndLogsOn() {
        // given
        var name = "exceptionAsServiceOnAndMembersOnAndLogsOn";
        assertThat(name).contains(EXCEPTION_CAUSE);

        // when
        ThrowingCallable when = () -> service.saveGlobalTransaction(name);

        // then
        assertThatThrownBy(when).isInstanceOf(RuntimeException.class);
        assertThat(members.findByName(name)).isEmpty();
        assertThat(logs.findByMessage(name)).isEmpty();
    }

    /**
     *          transactional
     * ----------------------
     * service      ON
     * ----------------------
     * members      ON
     * ----------------------
     * logs         ON  <<< exception
     * ----------------------
     * !IMPORTANT!
     * logs 내부에서 예외가 발생하고, 서비스 로직에서 복구를 수행했음에도 불구하고 UnexpectedRollbackException 예외가 발생한다.
     * 로직을 짜면서 자연스럽다고 느낄 수 있지만, transactional rollback only 값이 활성화 된다.
     * 서비스 로직이 완료된 시점에서 transaction commit(flush) 작업을 수행하다가 rollback only 값 때문에 예외가 터진다.
     *
     * @see UnexpectedRollbackException
     * @see TransactionDefinition#isReadOnly()
     */
    @Test
    void recoverExceptionAsServiceOnAndMembersOnAndLogsOn() {
        // given
        var name = "recoverExceptionAsServiceOnAndMembersOnAndLogsOn";
        assertThat(name.toLowerCase()).contains(EXCEPTION_CAUSE);

        // when
        ThrowingCallable when = () -> service.saveGlobalTransactionAndIgnoreLogException(name);

        // then
        assertThatThrownBy(when).isInstanceOf(UnexpectedRollbackException.class);
        assertThat(members.findByName(name)).isEmpty();
        assertThat(logs.findByMessage(name)).isEmpty();
    }

    /**
     *              transactional
     * -------------------------------------------
     * service      ON
     * -------------------------------------------
     * members      ON
     * -------------------------------------------
     * logs         REQUIRES_NEW ON  <<< exception
     * -------------------------------------------
     * !IMPORTANT!
     * logs 내부에서 예외가 발생하지만, logs 내부는 새로운 트랜잭션을 사용하므로 rollback only 값이 전파되지 않는다.
     * 따라서 service 로직에서 예외만 잡아주면 정상 흐름이 가능하다.
     *
     * @see TransactionDefinition#PROPAGATION_REQUIRES_NEW
     */
    @Test
    void recoverExceptionAsServiceOnAndMembersOnAndLogsRequiresNewOn() {
        // given
        var name = "recoverExceptionAsServiceOnAndMembersOnAndLogsRequiresNewOn";
        assertThat(name.toLowerCase()).contains(EXCEPTION_CAUSE);

        // when
        service.saveGlobalTransactionAndRequiresNewPropagationAndIgnoreLogException(name);

        // then
        assertThat(members.findByName(name)).isPresent();
        assertThat(logs.findByMessage(name)).isEmpty();
    }

}
