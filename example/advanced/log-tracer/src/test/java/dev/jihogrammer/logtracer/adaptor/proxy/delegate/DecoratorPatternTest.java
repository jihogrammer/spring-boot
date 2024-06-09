package dev.jihogrammer.logtracer.adaptor.proxy.delegate;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DecoratorPatternTest {

    @Test
    void beforeDecorate() {
        // given
        var component = new RealComponent();
        var client = new DecoratorPatternClient(component);

        // when
        var result = client.execute();

        // then
        assertThat(result).isNotNull();
    }

    @Test
    void decorateUpperCase() {
        // given
        var realComponent = new RealComponent();
        var decorator = new UpperCaseComponentDecorator(realComponent);
        var client = new DecoratorPatternClient(decorator);

        // when
        var result = client.execute();

        // then
        assertThat(result).isUpperCase();
    }

    @Test
    void decorateElapsed() {
        // given
        var realComponent = new RealComponent();
        var upperCaseDecorator = new UpperCaseComponentDecorator(realComponent);
        var elapsedDecorator = new ElapsedComponentDecorator(upperCaseDecorator);
        var client = new DecoratorPatternClient(elapsedDecorator);

        // when
        var result = client.execute();

        // then
        assertThat(result).isUpperCase();
    }

}
