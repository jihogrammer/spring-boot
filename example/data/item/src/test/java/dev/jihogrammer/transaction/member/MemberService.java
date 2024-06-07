package dev.jihogrammer.transaction.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
class MemberService {

    private final MemberRepository memberRepository;

    private final LogRepository logRepository;

    void saveEachTransaction(String name) {
        var member = new Member(name);
        var aLog = new Log(member.getName());

        log.info(">>> Start to save using memberRepository");
        memberRepository.saveWithTransactional(member);
        log.info(">>> Completed to save using memberRepository");

        log.info(">>> Start to save using logRepository");
        logRepository.saveWithTransactional(aLog);
        log.info(">>> Completed to save using logRepository");
    }

    void saveEachTransactionAndIgnoreLogException(String name) {
        var member = new Member(name);
        var aLog = new Log(member.getName());

        log.info(">>> Start to save using memberRepository");
        memberRepository.saveWithTransactional(member);
        log.info(">>> Completed to save using memberRepository");

        log.info(">>> Start to save using logRepository");
        try {
            logRepository.saveWithTransactional(aLog);
        } catch (RuntimeException e) {
            log.error(">>> Failed to save log={}", aLog);
            log.error(">>> Ignore log exception. message={}", e.getMessage(), e);
        }
        log.info(">>> Completed to save using logRepository");
    }

    @Transactional
    void saveSingleTransaction(String name) {
        var member = new Member(name);
        var aLog = new Log(member.getName());

        log.info(">>> Start to save using memberRepository");
        memberRepository.saveWithoutTransactional(member);
        log.info(">>> Completed to save using memberRepository");

        log.info(">>> Start to save using logRepository");
        logRepository.saveWithoutTransactional(aLog);
        log.info(">>> Completed to save using logRepository");
    }

    @Transactional
    void saveGlobalTransaction(String name) {
        var member = new Member(name);
        var aLog = new Log(member.getName());

        log.info(">>> Start to save using memberRepository");
        memberRepository.saveWithTransactional(member);
        log.info(">>> Completed to save using memberRepository");

        log.info(">>> Start to save using logRepository");
        logRepository.saveWithTransactional(aLog);
        log.info(">>> Completed to save using logRepository");
    }

    @Transactional
    void saveGlobalTransactionAndIgnoreLogException(String name) {
        var member = new Member(name);
        var aLog = new Log(member.getName());

        log.info(">>> Start to save using memberRepository");
        memberRepository.saveWithTransactional(member);
        log.info(">>> Completed to save using memberRepository");

        log.info(">>> Start to save using logRepository");
        try {
            logRepository.saveWithTransactional(aLog);
        } catch (RuntimeException e) {
            log.error(">>> Failed to save log={}", aLog);
            log.error(">>> Ignore log exception. message={}", e.getMessage(), e);
        }
        log.info(">>> Completed to save using logRepository");
    }

    @Transactional
    void saveGlobalTransactionAndRequiresNewPropagationAndIgnoreLogException(String name) {
        var member = new Member(name);
        var aLog = new Log(member.getName());

        log.info(">>> Start to save using memberRepository");
        memberRepository.saveWithTransactional(member);
        log.info(">>> Completed to save using memberRepository");

        log.info(">>> Start to save using logRepository");
        try {
            logRepository.saveWithRequiresNewTransactional(aLog);
        } catch (RuntimeException e) {
            log.error(">>> Failed to save log={}", aLog);
            log.error(">>> Ignore log exception. message={}", e.getMessage(), e);
        }
        log.info(">>> Completed to save using logRepository");
    }

}
