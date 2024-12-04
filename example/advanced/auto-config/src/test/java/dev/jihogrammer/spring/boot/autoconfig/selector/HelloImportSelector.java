package dev.jihogrammer.spring.boot.autoconfig.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.NonNull;

class HelloImportSelector implements ImportSelector {

    @Override
    @NonNull
    public String[] selectImports(@NonNull final AnnotationMetadata importingClassMetadata) {
        return new String[] {
                "dev.jihogrammer.spring.boot.autoconfig.selector.HelloConfig",
        };
    }

}
