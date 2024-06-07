package dev.jihogrammer.transaction.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@SpringBootTest
class BasicTransactionTest {

    @Autowired
    PlatformTransactionManager transactionManager;

    @Test
    void commit() {
        // given
        log.info(">>> Start transaction");
        var transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

        // when
        log.info(">>> Start to commit");
        transactionManager.commit(transactionStatus);

        // then
        log.info(">>> Completed to commit");
        assertThat(transactionStatus.isCompleted()).isTrue();
    }

    @Test
    void rollback() {
        // given
        log.info(">>> Start transaction");
        var transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

        // when
        log.info(">>> Start to rollback");
        transactionManager.rollback(transactionStatus);

        // then
        log.info(">>> Completed to rollback");
        assertThat(transactionStatus.isCompleted()).isTrue();
    }

    @Test
    void commitAndCommit() {
        transaction1:
        {
            log.info(">>> Start transaction 1");
            var tx1 = transactionManager.getTransaction(new DefaultTransactionDefinition());
            log.info(">>> Start to commit transaction 1");
            transactionManager.commit(tx1);
            assertThat(tx1.isCompleted()).isTrue();
        }

        transaction2:
        {
            log.info(">>> Start transaction 2");
            var tx2 = transactionManager.getTransaction(new DefaultTransactionDefinition());
            log.info(">>> Start to commit transaction 2");
            transactionManager.commit(tx2);
            assertThat(tx2.isCompleted()).isTrue();
        }
    }

    @Test
    void commitAndRollback() {
        transaction1:
        {
            log.info(">>> Start transaction 1");
            var tx1 = transactionManager.getTransaction(new DefaultTransactionDefinition());
            log.info(">>> Start to commit transaction 1");
            transactionManager.commit(tx1);
            assertThat(tx1.isCompleted()).isTrue();
        }

        transaction2:
        {
            log.info(">>> Start transaction 2");
            var tx2 = transactionManager.getTransaction(new DefaultTransactionDefinition());
            log.info(">>> Start to rollback transaction 2");
            transactionManager.rollback(tx2);
            assertThat(tx2.isCompleted()).isTrue();
        }
    }

    @Test
    void innerCommitAndOuterCommit() {
        outerTransaction:
        {
            log.info(">>> Start outer transaction");
            var outer = transactionManager.getTransaction(new DefaultTransactionDefinition());

            assertThat(outer.isNewTransaction()).isTrue();

            innerTransaction:
            {
                log.info(">>> Start inner transaction");
                var inner = transactionManager.getTransaction(new DefaultTransactionDefinition());

                assertThat(inner.isNewTransaction()).isFalse();

                log.info(">>> Start to commit inner transaction");
                transactionManager.commit(inner);

                assertThat(inner.isCompleted()).isTrue();
            }

            log.info(">>> Start to commit outer transaction");
            transactionManager.commit(outer);

            assertThat(outer.isCompleted()).isTrue();
        }
    }

    @Test
    void innerCommitAndOuterRollback() {
        outerTransaction:
        {
            log.info(">>> Start outer transaction");
            var outer = transactionManager.getTransaction(new DefaultTransactionDefinition());

            assertThat(outer.isNewTransaction()).isTrue();

            innerTransaction:
            {
                log.info(">>> Start inner transaction");
                var inner = transactionManager.getTransaction(new DefaultTransactionDefinition());

                assertThat(inner.isNewTransaction()).isFalse();

                log.info(">>> Start to commit inner transaction");
                transactionManager.commit(inner);

                assertThat(inner.isCompleted()).isTrue();
            }

            log.info(">>> Start to rollback outer transaction");
            transactionManager.rollback(outer);

            assertThat(outer.isCompleted()).isTrue();
        }
    }

    @Test
    void innerRollbackAndOuterCommit() {
        outerTransaction:
        {
            log.info(">>> Start outer transaction");
            var outer = transactionManager.getTransaction(new DefaultTransactionDefinition());

            assertThat(outer.isNewTransaction()).isTrue();

            innerTransaction:
            {
                log.info(">>> Start inner transaction");
                var inner = transactionManager.getTransaction(new DefaultTransactionDefinition());

                assertThat(inner.isNewTransaction()).isFalse();

                log.info(">>> Start to rollback inner transaction");
                transactionManager.rollback(inner);

                // innerTransaction 내부에서 롤백이 일어나면, 트랜잭션에서 rollbackOnly 값이 활성화 된다.
                assertThat(outer.isRollbackOnly()).isTrue();
            }

            log.info(">>> Start to commit outer transaction");
            assertThatThrownBy(() -> transactionManager.commit(outer))
                    .isInstanceOf(UnexpectedRollbackException.class);
            assertThat(outer.isCompleted()).isTrue();
        }
    }

    /**
     * @see TransactionDefinition
     * @see TransactionDefinition#PROPAGATION_REQUIRED
     * @see TransactionDefinition#PROPAGATION_REQUIRES_NEW
     */
    @Test
    void innerRollbackAndOuterCommitUsingEachTransaction() {
        outerTransaction:
        {
            log.info(">>> Start outer transaction");
            var outer = transactionManager.getTransaction(new DefaultTransactionDefinition());

            assertThat(outer.isNewTransaction()).isTrue();

            innerTransaction:
            {
                var transactionDefinition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
                log.info(">>> Start inner transaction - transactionDefinition={}", transactionDefinition);
                var inner = transactionManager.getTransaction(transactionDefinition);

                // 외부 트랜잭션 커넥션과 다른 새로운 트랜잭션 커넥션을 받는다.
                assertThat(inner.isNewTransaction()).isTrue();

                log.info(">>> Start to rollback inner transaction");
                transactionManager.rollback(inner);

                // 내부와 외부 트랜잭션이 서로 다른 커넥션을 사용하므로, 내부에서 롤백되더라도 외부 트랜잭션의 rollbackOnly 값이 활성화 되지 않는다.
                assertThat(outer.isRollbackOnly()).isFalse();
            }

            log.info(">>> Start to commit outer transaction");
            transactionManager.commit(outer);
            assertThat(outer.isCompleted()).isTrue();
        }
    }

    @TestConfiguration
    static class Config {

        @Bean
        PlatformTransactionManager transactionManager(DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }

    }

}
