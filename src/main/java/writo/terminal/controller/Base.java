package writo.terminal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import writo.terminal.core.Core;

abstract public class Base {

    @Autowired
    protected Core core;

}
