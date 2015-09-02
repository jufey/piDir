package server;

import java.util.Arrays;

/**
 * Created by jufey on 02/09/15.
 */
public class ServerCommandHandler {

    Database db;
    Server server;

    public ServerCommandHandler(Server server) {
        db = new Database();
        this.server = server;
    }

    public String command(String c) {
        String[] cmd = c.split("\\s+");

        System.out.println(Arrays.toString(cmd));

        int numCMD = cmd.length;
        for (int i = 0; i < numCMD; i++) {
            System.out.println(cmd[i]);
        }
        if (equalsCMD(cmd[0], Command.STATUS)) {
            return status(cmd, numCMD);
        }
        if (equalsCMD(cmd[0], Command.EXIT)) {
            return Command.EXIT.getString();
        }
        if (equalsCMD(cmd[0], Command.SHUTDOWN)) {

            return shutdown(cmd, numCMD);
        }

        return fail();
    }

    private String shutdown(String[] cmd, int num) {
        if (num == 1) {
            return fail();
        }
        if (!equalsCMD(cmd[1], Command.SERVER)) {
            return fail();
        }
        return server.shutdown();
    }

    public String status(String[] cmd, int num) {
        if (num == 1) {
            return fail();
        }
        if (equalsCMD(cmd[1], Command.ALL)) {
            return db.getStatusAll();
        }
        return fail();
    }

    public String fail() {
        return "ERROR wrong command";
    }

    private boolean equalsCMD(String s, Command cm) {
        return s.equalsIgnoreCase(cm.getString());
    }

}
