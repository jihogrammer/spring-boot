package dev.jihogrammer.product.adaptor.out.persistence;

import dev.jihogrammer.member.model.MemberId;
import dev.jihogrammer.product.application.port.out.ProductPort;
import dev.jihogrammer.product.domain.Product;
import dev.jihogrammer.product.domain.model.ProductId;
import dev.jihogrammer.product.application.port.out.ProductSaveCommand;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.Objects.isNull;

@Slf4j
class InMemoryProductAdaptor implements ProductPort {

    private final Map<ProductId, Product> products = new ConcurrentHashMap<>();

    @Override
    public Product save(final ProductSaveCommand command) {
        final ProductId productId;
        if (isNull(command.id())) {
            productId = IdGenerator.next();
        } else {
            productId = command.id();
        }

        final LocalDateTime createdAt;
        final LocalDateTime updatedAt;
        var productOptional = this.findById(productId);
        if (productOptional.isEmpty()) {
            createdAt = LocalDateTime.now();
            updatedAt = null;
            log.trace("Create a product by command=[{}];", command);
        } else {
            createdAt = productOptional.get().createdAt();
            updatedAt = LocalDateTime.now();
            log.trace("Update a product by command=[{}];", command);
        }

        var product = new Product(
            productId,
            command.producerId(),
            command.name(),
            command.price(),
            command.quantity(),
            command.open(),
            command.descriptionFile(),
            command.imageFiles(),
            command.types(),
            command.regions(),
            command.deliveryTypes(),
            createdAt,
            updatedAt,
            null);

        this.products.put(product.id(), product);
        log.trace("Product is saved. product=[{}];", product);

        return product;
    }

    @Override
    public Collection<Product> findAll() {
        return this.products.values();
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        return Optional.ofNullable(this.products.get(id));
    }

    @Override
    public Collection<Product> findByProducerId(MemberId producerId) {
        return this.findAll().stream()
                .filter(product -> product.producerId().equals(producerId))
                .toList();
    }

    public void clear() {
        this.products.clear();
    }

    private static class IdGenerator {

        static final AtomicLong sequence = new AtomicLong();

        static ProductId next() {
            return new ProductId(sequence.incrementAndGet());
        }

    }

}
