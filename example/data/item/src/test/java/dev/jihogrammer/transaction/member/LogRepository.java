package dev.jihogrammer.transaction.member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
class LogRepository {

    private final EntityManager em;

    private void save(Log aLog) {
        em.persist(aLog);
        log.info(">>> saved log={}", aLog);

        if (aLog.getMessage().toLowerCase().contains("exception")) {
            log.error("A exception occurred.");
            throw new RuntimeException(aLog.getMessage());
        }
    }

    @Transactional
    void saveWithTransactional(Log aLog) {
        save(aLog);
    }

    void saveWithoutTransactional(Log aLog) {
        save(aLog);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void saveWithRequiresNewTransactional(Log aLog) {
        save(aLog);
    }

    Optional<Log> findByMessage(String message) {
        return em.createQuery("SELECT l FROM Log l WHERE l.message = :message", Log.class)
                .setParameter("message", message)
                .getResultList()
                .stream()
                .findAny();
    }

}
