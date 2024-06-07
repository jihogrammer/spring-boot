package dev.jihogrammer.item;

import dev.jihogrammer.item.application.port.in.ItemQuery;
import dev.jihogrammer.item.application.port.in.ItemUpdatePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public abstract class IntegrationTest {

    @Autowired
    protected ItemQuery itemQuery;

    @Autowired
    protected ItemUpdatePort itemUpdatePort;

    // @Transactional 대체
//    @Autowired
//    private PlatformTransactionManager transactionManager;
//
//    private TransactionStatus transaction;
//
//    @BeforeEach
//    void setUp() {
//        this.transaction = this.transactionManager.getTransaction(new DefaultTransactionDefinition());
//    }
//
//    @AfterEach
//    void tearDown() {
//        this.transactionManager.rollback(this.transaction);
//    }

}
