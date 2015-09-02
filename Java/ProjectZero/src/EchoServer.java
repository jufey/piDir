import java.io.*;
import java.net.*;
import java.util.*;

public class EchoServer extends Thread {
    public static final int DEFAULT_PORT = 61509;
    ServerSocket server_socket;
    Vector<Connection> connections;
    int num_connections;
    private boolean isRunning;

    public static void fail(Exception e, String msg) {
        System.err.println(msg + ": " + e);
        System.exit(1);
    }

    public EchoServer() {
        connections = new Vector<Connection>();
        try {
            server_socket = new ServerSocket(DEFAULT_PORT);
            System.out.println(server_socket +
                    "\n" +
                    "Server is listening on " + DEFAULT_PORT);
            isRunning = true;
        } catch (IOException e) {
            fail(e, "Error while starting the Server");
        }
        this.start();
    }

    public void run() {
        try {
            while (isRunning) {
                Socket client_socket = server_socket.accept();
                Connection c = new Connection(client_socket, this);
                addConnection(c);
            }
        } catch (IOException e) {
            fail(e, "While waiting for a connection an error occurred");
        }
    }

    public synchronized void addConnection(Connection c) {
        connections.addElement(c);
        num_connections++;
    }

    public synchronized void receive(String line, Connection c) {
        c.send(line);
        System.out.println(line);
        if(line.equalsIgnoreCase("Server shutdown")){
            shutdown();
        }


    }
    public synchronized void shutdown(){
        connections.forEach(Connection::close);
    }

    public static void main(String[] args) {
        new EchoServer();
    }

}
