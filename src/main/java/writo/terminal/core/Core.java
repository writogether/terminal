package writo.terminal.core;

import lombok.Value;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend core, with auto injected services and mappers, which provide business logic impl and database mappings.
 */
@Component
@Value
@Accessors(fluent = true)
public class Core {

    Mapper mapper;
    Service service;

    @Autowired
    public Core(Mapper mapper, Service service) {
        this.mapper = mapper;
        this.service = service;
    }

}

