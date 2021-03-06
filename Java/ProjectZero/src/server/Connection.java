package server;
import java.io.*;
import java.net.*;

public class Connection extends Thread {
    protected Server server;
    protected Socket socket;
    protected BufferedReader sin;
    protected PrintWriter sout;
    protected boolean isRunning;

    public Connection(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        isRunning = true;
        System.out.println(socket + ": Connected");
        try {
            sin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            sout = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException e2) {
            }
            System.err.println("Error occurred while creating a Connection: " + e);
            return;
        }
        this.start();
    }

    public void run() {
        String line;
        try {
            while (isRunning) {
                line = sin.readLine();
                if (line == null) break;
                server.receive(line, this);

            }
        } catch (IOException e) {
            System.out.println("Error while receiving.");
        } finally {
            System.out.print(socket + ": Close connection...");
            try {
                socket.close();
                System.out.println("OK!");
            } catch (IOException e2) {
                System.out.println("ERR!");
            }
        }
    }

    public void send(String line) {
        if (sout != null) {                // Wenn Ausgabestream noch aktiv
            sout.println(line);            // Daten an Client ausgeben
            sout.flush();                // und sofort senden
        }
    }
    public void close(){
        isRunning=false;
    }
}
