package writo.terminal.controller;

import lombok.experimental.Delegate;
import org.springframework.beans.factory.annotation.Autowired;
import writo.terminal.core.Core;

abstract public class Base {

    @Delegate
    @Autowired
    protected Core core;

}
