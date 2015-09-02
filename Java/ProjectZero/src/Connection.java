import java.io.*;
import java.net.*;

public class Connection extends Thread {
    protected EchoServer server;
    protected Socket socket;
    protected BufferedReader sin;
    protected PrintWriter sout;

    public Connection(Socket socket, EchoServer server) {
        this.socket = socket;
        this.server = server;
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
            while (true) {
                line = sin.readLine();
                if (line == null) break;
                server.receive(line, this);
                if (line.equalsIgnoreCase("Close connection")) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if(line.equalsIgnoreCase("Server shutdown")){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }
        } catch (IOException e) {
            System.out.println("Error while receiving.");
        } finally {
            System.out.print(socket + ": Verbindung schließen...");
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
}
