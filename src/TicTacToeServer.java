// Java implementation of Server side
// It contains two classes : Server and ClientHandler
// Save file as Server.java

import java.io.*;
import java.util.*;
import java.net.*;

// Server class
public class TicTacToeServer
{

    // Vector to store active clients
    static Vector<GameHandler> ar = new Vector<>();
    static Vector<GameData> arr = new Vector<>();

    // counter for clients
    static int games = 0;

    public static void main(String[] args) throws IOException
    {
        // server is listening on port 1234
        ServerSocket ss = new ServerSocket(1234);

        Socket s;

        // running infinite loop for getting
        // client request
        while (true)
        {
            // Accept the incoming request
            s = ss.accept();

            System.out.println("New Game Request : " + s);

            // obtain input and output streams

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            System.out.println("Creating a new handler for this game...");

            // Create a new handler object for handling this request.
            GameHandler mtch = new GameHandler(s,"game_" + games, dis, dos);

            // Create a new Thread with this object.
            Thread t = new Thread(mtch);

            System.out.println("Adding this game to active games list");

            // add this client to active clients list
            ar.add(mtch);

            // start the thread.
            t.start();

            // increment games for new game.
            games++;
        }
    }
}



// ClientHandler class
class GameHandler implements Runnable
{
    Scanner scn = new Scanner(System.in);
    private String name;
    final DataInputStream dis;
    final DataOutputStream dos;
    Socket s;

    // constructor
    public GameHandler(Socket s, String name,DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
        this.name = name;
        this.s = s;
    }

    @Override
    public void run() {
        System.out.println();
        String received;
        while (true)
        {
            try
            {
                received = dis.readUTF();
                System.out.println(received);
                if(received.equals("GameOver")){
                    this.s.close();
                    break;
                }

            } catch (IOException e) {

                e.printStackTrace();
            }

        }
        try
        {
            // closing resources
            this.dis.close();
            this.dos.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
