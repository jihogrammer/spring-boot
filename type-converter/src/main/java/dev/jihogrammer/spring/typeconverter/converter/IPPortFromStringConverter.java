package dev.jihogrammer.spring.typeconverter.converter;

import dev.jihogrammer.spring.typeconverter.model.InternetProtocolAndPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import static dev.jihogrammer.spring.typeconverter.model.InternetProtocolAndPort.DELIMITER_COLON;

@Slf4j
public class IPPortFromStringConverter implements Converter<String, InternetProtocolAndPort> {
    @Override
    @NonNull
    public InternetProtocolAndPort convert(@NonNull final String source) {
        log.info("converting String to InternetProtocolAndPort. source = {}", source);

        PairSplitHelper helper = new PairSplitHelper(source, DELIMITER_COLON);
        String ip = helper.left();
        int port = Integer.parseInt(helper.right());

        return new InternetProtocolAndPort(ip, port);
    }

    public static class PairSplitHelper {
        private final String left;
        private final String right;

        public PairSplitHelper(final String source, final char delimiter) {
            int delimiterIndex = source.indexOf(delimiter);
            this.left = source.substring(0, delimiterIndex);
            this.right = source.substring(delimiterIndex + 1);
        }

        public String left() {
            return this.left;
        }

        public String right() {
            return this.right;
        }
    }
}
