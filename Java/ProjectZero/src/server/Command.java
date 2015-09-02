package server;

/**
 * Created by jufey on 02/09/15.
 */
public enum Command {
    ROOM("room"),
    ADD("add"),
    DEVICE("device"),
    EXIT("exit"),
    STATUS("status"),
    ALL("all"),
    SHUTDOWN("shutdown"),
    SERVER("server");

    String name;

    Command(String name) {
        this.name = name;
    }

    public String getString() {
        return name;
    }
}
