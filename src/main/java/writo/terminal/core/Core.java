package writo.terminal.core;

import org.springframework.stereotype.Component;

@Component
public class Core {

    final public Mapper mapper;
    final public Service service;

    public Core(Mapper mapper, Service service) {
        this.mapper = mapper;
        this.service = service;
    }

}

