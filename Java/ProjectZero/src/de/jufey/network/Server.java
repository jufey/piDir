package de.jufey.network;
/**
 * Created by jufey on 31/08/15.
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server
{
    private static void handleConnection( Socket client ) throws IOException
    {
        Scanner in  = new Scanner( client.getInputStream() );
        PrintWriter out = new PrintWriter( client.getOutputStream(), true );

        String name1 = in.nextLine();
        String name2 = in.nextLine();

        out.println("Your name is "+name1 +" "+name2);
    }

    public static void main( String[] args ) throws IOException
    {
        ServerSocket server = new ServerSocket( 3141 );

        while ( true )
        {
            Socket client = null;

            try
            {
                client = server.accept();
                handleConnection ( client );
            }
            catch ( IOException e ) {
                e.printStackTrace();
            }
            finally {
                if ( client != null )
                    try { client.close(); } catch ( IOException e ) { }
            }
        }
    }
}
