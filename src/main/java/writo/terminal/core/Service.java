package writo.terminal.core;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import writo.terminal.core.ServiceI.AuthI;

/**
 * Service aggregate, contains business logic implementation.
 */
@Component
@Getter
@Accessors(fluent = true)
public class Service {

    private final AuthI auth;

    @Autowired
    public Service(AuthI auth) {this.auth = auth;}

}
