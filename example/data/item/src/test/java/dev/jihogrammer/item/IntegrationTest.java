package dev.jihogrammer.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@SpringBootTest
public abstract class IntegrationTest {

    @Autowired
    private PlatformTransactionManager transactionManager;

    private TransactionStatus transaction;

    @BeforeEach
    void setUp() {
        this.transaction = this.transactionManager.getTransaction(new DefaultTransactionDefinition());
    }

    @AfterEach
    void tearDown() {
        this.transactionManager.rollback(this.transaction);
    }

}
