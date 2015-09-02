package client;
import java.io.*;
import java.net.*;
import server.*;

public class Echo {
    public static final String DEFAULT_ADDR = "192.168.2.100";
    public static final String DEFAULT_ADDR2 = "localhost";

    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in, sin;
        PrintWriter sout;


        try {
            socket = new Socket(DEFAULT_ADDR2, Server.DEFAULT_PORT);
            in = new BufferedReader(new InputStreamReader(System.in));
            sin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            sout = new PrintWriter(socket.getOutputStream());
            System.out.println("Connected: " + socket.getInetAddress() + ":" + socket.getPort());

            String line;
            while (true) {
                System.out.print(">");
                System.out.flush();
                //Read from Terminal
                line = in.readLine();
                if (line == null) break;
                //Send line to Server
                sout.println(line);
                sout.flush();
                //Read from Server
                line = sin.readLine();
                if(line==null){
                    System.out.println("NullLine");
                    break;
                }
                if (line.equalsIgnoreCase("exit")) {
                    System.out.println("Connection closed.");
                    return;
                }
                if (line.equalsIgnoreCase("Server shutdown")) {
                    System.out.println("Connection closed.");
                    System.out.println("Server will shutdown.");
                    return;
                }
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                if (socket != null) socket.close();
            } catch (IOException e2) {
            }
            ;
        }
    }
}
