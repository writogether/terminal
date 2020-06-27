package writo.terminal.util;

import lombok.Data;

/**
 * Boxing type for BE and FE communication.
 */
@Data
public class Res {

    private Boolean success = true;
    private String message = "";
    private Object data = null;

    private Res() {}

    public static Res ok() {
        return new Res();
    }

    public static Res ok(Object data) {
        Res res = new Res();
        res.data = data;
        return res;
    }

    public static Res oops() {
        Res res = new Res();
        res.success = false;
        return res;
    }

    public static Res oops(String message) {
        Res res = new Res();
        res.success = false;
        res.setMessage(message);
        return res;
    }

    public Res setMessage(String message) {
        this.message = message;
        return this;
    }

}
