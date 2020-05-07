package writo.terminal.util;

public class Res {

    public boolean success = true;
    public String message = "Too lazy to write message";
    public Object data;

    public Res(String message, boolean is_success) {
        None data = new None();
        data.setHint("Failed to return data");
        this.success = is_success;
        this.message = message;
    }

    public Res(Object data, String successMessage) {
        this.message = successMessage;
        this.data = data;
    }

    public Res(Object data) {
        this.data = data;
    }

    public Res() {
        this.data = new None();
    }

}
