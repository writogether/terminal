package writo.terminal.core;

import lombok.Value;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

@Component
@Value
@Accessors(fluent = true)
public class Core {

    Mapper mapper;
    Service service;

    public Core(Mapper mapper, Service service) {
        this.mapper = mapper;
        this.service = service;
    }

}

