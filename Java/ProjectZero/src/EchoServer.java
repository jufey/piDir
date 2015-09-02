import java.io.*;
import java.net.*;
import java.util.*;

public class EchoServer extends Thread {
    public static final int DEFAULT_PORT = 33333;	// Default-Portnummer
    int port;					// aktuelle Portnummer
    ServerSocket server_socket;			// Der Socket des Servers
    Vector connections;				// Vektor aller aktiven Verbindungen
    int num_connections;

    public static void fail(Exception e, String msg) {
        System.err.println(msg + ": " + e);
        System.exit(1);
    }

    public EchoServer(int port) {
        this.port = port;			// Port initialisieren
        connections = new Vector();
        try {
            server_socket = new ServerSocket(port);
            System.out.println(server_socket +
                    "\nServer lauscht auf Port " + port);
        }
        catch(IOException e) {
            fail(e, "Eine Ausnahme trat beim Anlegen des Server Socket auf");
        }
        this.start();
    }

    public void run() {
        try {
            while(true) {
                Socket client_socket = server_socket.accept();
                Connection c = new Connection(client_socket, this);
                addConnection(c);
            }
        }
        catch(IOException e) {
            fail(e, "Beim Warten auf Verbindungen ist eine Ausnahme aufgetreten");
        }
    }

    public synchronized void addConnection(Connection c) {
        connections.addElement(c);
        num_connections++;
    }

    public synchronized void receive(String line, Connection c) {
        c.send(line);
    }

    public static void main(String[] args) {
        new EchoServer(DEFAULT_PORT);
    }
}
