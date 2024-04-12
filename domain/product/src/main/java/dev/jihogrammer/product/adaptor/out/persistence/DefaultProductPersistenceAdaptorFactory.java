package dev.jihogrammer.product.adaptor.out.persistence;

import dev.jihogrammer.product.application.port.out.ProductPort;

public final class DefaultProductPersistenceAdaptorFactory {

    public static ProductPort createProductPort() {
        return new InMemoryProductAdaptor();
    }

}
