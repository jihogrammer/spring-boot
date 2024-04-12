package dev.jihogrammer.filestorage.application.service;

import dev.jihogrammer.filestorage.application.port.in.FileStorageRegisterCommand;
import dev.jihogrammer.filestorage.application.port.in.FileStorageUseCase;
import dev.jihogrammer.filestorage.application.port.out.ProductFilePort;
import dev.jihogrammer.member.model.MemberId;
import dev.jihogrammer.product.application.port.out.ProductPort;
import dev.jihogrammer.product.application.port.out.ProductSaveCommand;
import dev.jihogrammer.product.domain.Product;
import dev.jihogrammer.product.domain.model.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class FileStorageService implements FileStorageUseCase {

    private final ProductPort productPort;

    private final ProductFilePort productFilePort;

    @Override
    public Product register(final FileStorageRegisterCommand command) {
        final var commandBuilder = ProductSaveCommand.builder()
            .producerId(CommandSourceGenerator.nextId())
            .price(CommandSourceGenerator.nextPrice())
            .name(command.name());

        final var descriptionFile = this.productFilePort.save(command.file());
        commandBuilder.descriptionFile(descriptionFile);

        final var imageFiles = command.imageFiles().stream()
            .map(this.productFilePort::save)
            .collect(Collectors.toUnmodifiableSet());
        commandBuilder.imageFiles(imageFiles);

        return this.productPort.save(commandBuilder.build());
    }

    @Override
    public Product findById(Long id) {
        return this.productPort.findById(new ProductId(id))
            .orElseThrow(() -> new NoSuchElementException("Could not find product by [" + id + "]"));
    }

    @Override
    public Resource getResource(String filename) {
        return this.productFilePort.findByFilename(filename);
    }

    private static class CommandSourceGenerator {

        public static MemberId nextId() {
            return new MemberId((long) (Math.random() * 1000L));
        }

        public static int nextPrice() {
            return (int) (Math.random() * 1000);
        }

    }

}
