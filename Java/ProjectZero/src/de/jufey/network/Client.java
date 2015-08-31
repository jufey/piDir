package de.jufey.network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by jufey on 31/08/15.
 */
public class Client{
    public static void main( String[] args )
    {
        Socket server = null;

        try
        {
//            server = new Socket( "192.168.2.100", 3141 );
//            Scanner in  = new Scanner( server.getInputStream() );
//            PrintWriter out = new PrintWriter( server.getOutputStream(), true );
//
//            printName(out);
//
//            System.out.println( in.nextLine() );

            server = new Socket( "localhost", 3141 );
            Scanner in  = new Scanner( server.getInputStream() );
            PrintWriter out = new PrintWriter( server.getOutputStream(), true );

            out.println( "23895737895" );
            out.println( "434589358935857" );
            System.out.println( in.nextLine() );
        }
        catch ( UnknownHostException e ) {
            e.printStackTrace();
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
        finally {
            if ( server != null )
                try { server.close(); } catch ( IOException e ) { }
        }
    }
    public static void printName(PrintWriter out){
        Scanner scanner = new Scanner(System.in);

        //  prompt for the user's name
        System.out.print("Enter your first name: ");
        out.println(scanner.next());
        System.out.print("Enter your second name: ");
        out.println(scanner.next());


    }
}