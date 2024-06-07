package dev.jihogrammer.transaction.member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
class MemberRepository {

    private final EntityManager em;

    private void save(Member member) {
        em.persist(member);
        log.info(">>> Saved a member={}", member);
    }

    @Transactional
    void saveWithTransactional(Member member) {
        save(member);
    }

    void saveWithoutTransactional(Member member) {
        save(member);
    }

    Optional<Member> findByName(String name) {
        return em.createQuery("SELECT m FROM Member m WHERE m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList()
                .stream()
                .findAny();
    }

}
