import java.io.*;
import java.net.*;

public class Connection extends Thread {
    protected EchoServer server;			// Link zum Server
    protected Socket socket;			// Socket der Verbindung
    protected BufferedReader sin;			// Eingabestream für Tastatureingaben
    protected PrintWriter sout;			// Ausgabestream auf Socket

    public Connection(Socket socket, EchoServer server) {
        this.socket = socket;
        this.server = server;
        System.out.println(socket + ": Neue Verbindung");
        try {					// Streams erzeugen
            sin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            sout = new PrintWriter(socket.getOutputStream());
        }
        catch (IOException e) {
            try { socket.close(); } 	// Socket noch geöffnet? Dann schließen
            catch (IOException e2) {}
            System.err.println("Ausnahme beim Bereitstellen der Socketstreams: " + e);
            return;				// Bei Fehler Methode beenden
        }
        this.start();				// Thread starten
    }

    public void run () {
        String line;
        try {
            while (true) {
                line = sin.readLine();		// Auf eine Textzeile vom Client warten
                if (line == null) break;	// Wenn String leer, dann wurde Verbindung beendet
                server.receive(line, this);	// empfangene Daten an Server übergeben
            }
        }
        catch(IOException e) {}
        finally {
            System.out.print(socket + ": Verbindung schließen...");
            try { socket.close(); System.out.println("OK!");}
            catch(IOException e2) { System.out.println("ERR!"); }
        }
    }

    public void send(String line) {
        if (sout != null) {				// Wenn Ausgabestream noch aktiv
            sout.println(line);			// Daten an Client ausgeben
            sout.flush();				// und sofort senden
        }
    }
}
