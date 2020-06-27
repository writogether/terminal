package writo.terminal.controller;

import lombok.experimental.Delegate;
import org.springframework.beans.factory.annotation.Autowired;
import writo.terminal.core.Core;

/**
 * Controller Base, delegate all functions from core.
 */
abstract public class Base {

    @Delegate
    @Autowired
    protected Core core;

}
