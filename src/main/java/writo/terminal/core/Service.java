package writo.terminal.core;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;
import writo.terminal.core.ServiceI.AuthI;

@Component
@Getter
@Accessors(fluent = true)
public class Service {

    private final AuthI auth;

    public Service(AuthI auth) {this.auth = auth;}

}
