package writo.terminal.core;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import writo.terminal.core.ServiceI.AuthI;

@Component
@Getter
public class Service {

    private final AuthI authS;

    @Autowired
    public Service(AuthI authS) {this.authS = authS;}

}
