package dev.jihogrammer.exception;

/**
 * path 설정 시 {@code "/error/*"}와 같이 설정할 경우 정상 동작하지 않는다.
 * {@code "/error/*"} 경로는 약속된 사용 경로이기 때문에 {@code "/error-page/*"} 형식으로 변경했다.
 * @see org.springframework.boot.web.server.ErrorPage
 * @see WebServerCustomizer
 */
public interface ErrorPagePath {
    String NOT_FOUND = "/error-page/404";
    String INTERNAL_SERVER_ERROR = "/error-page/500";
}
