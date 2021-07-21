// Java implementation for multithreaded chat client
// Save file as Client.java

import java.io.*;
import java.net.*;
import java.util.*;

public class TicTacToeClient
{
    final static int ServerPort = 1234;

    public static void main(String args[]) throws UnknownHostException, IOException
    {
        Scanner scn = new Scanner(System.in);

        // getting localhost ip
        InetAddress ip = InetAddress.getByName("localhost");

        // establish the connection
        Socket s = new Socket(ip, ServerPort);

        // obtaining input and out streams
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        Thread game = new Thread(new Runnable(){
            @Override
            public void run(){
                game newGame = new game();
                newGame.startGame();

                GameData gd = new GameData(newGame.players, newGame.winner);
                String msg = gd.getter();

                try {
                    // write on the output stream
                    dos.writeUTF(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String msg1 = "GameOver";

                try {
                    // write on the output stream
                    dos.writeUTF(msg1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        game.start();
    }
}

