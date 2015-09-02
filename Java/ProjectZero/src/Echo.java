import java.io.*;
import java.net.*;

public class Echo {
    public static final String DEFAULT_ADDR = "192.168.2.100";	// Default-Adresse

    public static void main(String[] args) {
        Socket socket = null;				// Socket für die Kommunikation
        BufferedReader in, sin;				// Eingabestream: Lesen vom Socket / Tastatur
        PrintWriter sout;				// Ausgabestream: Schreiben auf Socket

        int port = EchoServer.DEFAULT_PORT;		// Standard-Porstnummer des Echo-Servers
        String addr = new String(DEFAULT_ADDR);

        if(args.length >= 1) addr = new String(args[0]);// Addresse in Parameter übergeben?
        if(args.length >= 2) {				// Portnummer auch übergeben?
            try { port = Integer.parseInt(args[1]); }
            catch (NumberFormatException e) { };
        }

        try {
            socket = new Socket(addr, port);	// Erzeuge den Socket, um mit dem angegebenen
            // Host und Port zu kommunizieren
            in = new BufferedReader(		// Stream, um Textzeilen von der Console zu lesen
                    new InputStreamReader(System.in));
            sin = new BufferedReader(		// Erzeuge Stream zum Lesen vom Socket
                    new InputStreamReader(		// (etwas umständlich!)
                            socket.getInputStream()));
            sout = new PrintWriter(			// Erzeuge Stream zum Schreiben auf Socket
                    socket.getOutputStream() );
            System.out.println(			// Information für Benutzer ausgeben
                    "Verbunden zu " +
                            socket.getInetAddress() + ":" +
                            socket.getPort());

            String line;
            while(true) {
                System.out.print(">");		// Drucke eine Eingabeaufforderung
                System.out.flush();
                line = in.readLine();		// Lies eine Zeile von der Console
                if (line == null) break;	// ...und prüfe sie auf EOF
                sout.println(line);		// Schicke sie zum Server
                sout.flush();			// und zwar gleich
                line = sin.readLine();		// Lies eine Zeile vom Server
                if (line.equalsIgnoreCase("exit")) {		// Kommunikation beendet? (EOF)
                    System.out.println("Verbindung vom Server geschlossen.");
                    break;
                }
                System.out.println(line);	// Schreibe die Zeile auf die Console
            }
        }
        catch (IOException e) {
            System.err.println(e);
        }
        finally { try { if (socket != null) socket.close(); }
        catch (IOException e2) { };
        }
    }
}
