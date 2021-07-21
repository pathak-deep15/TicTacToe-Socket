import java.util.Scanner;

public class game {
    int boardSize;
    Board board;
    int numberOfPlayers;
    Player[] players;
    boolean gameOver = false;
    String winner;

    public void startGame() {

        Scanner sc = new Scanner(System.in);

        System.out.println("--------- Tic Tac Toe Game -------------");
        System.out.println("Enter the board size (n*n)");
        boardSize = sc.nextInt();
        sc.nextLine();

        board = new Board(boardSize);

        System.out.println("Enter the number of players");
        numberOfPlayers = sc.nextInt();
        sc.nextLine();
        players = new Player[numberOfPlayers];

        for(int i=0;i<numberOfPlayers;i++){
            System.out.println("Enter the name of player " + (i+1));
            String name = sc.nextLine();
            System.out.println("Enter the symbol of the player " + (i+1));
            String symbol = sc.nextLine();
            players[i] = new Player(name, symbol);
        }

        board.initialize(players, numberOfPlayers);
        System.out.println("The board initially looks like this");
        board.displayBoard();
        System.out.println();

        System.out.println("---The Game will Start now. Player 1 will play first and so on---");
        System.out.println();
        System.out.println("---To mark your symbol on the board, type in the row and column " +
                "number separated by space. (1 based indexing)---");
        System.out.println();

        while(!gameOver) {
            int i=0;
            while(i<numberOfPlayers) {
                System.out.println("Player " + (i+1) + ", enter the cell details of your move");
                System.out.println("Enter the row number");
                int row = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter the column number");
                int column = sc.nextInt();
                sc.nextLine();

                if(board.playMove(row, column, players[i].symbol) != -1) {
                    int result = board.checkWinner();
                    if(result==-1){
                        if(board.checkForDraw()){
                            System.out.println("There are no more moves possible. Game is drawn");
                            gameOver = true;
                            winner = "draw";
                            break;
                        } else {
                            System.out.println("After this move the board looks like");
                            System.out.println();
                            board.displayBoard();
                            System.out.println();
                            i = (i + 1) % numberOfPlayers;
                        }
                    } else{
                        System.out.println("Congratulations, Player " + (result) + " has won" +
                                " the game");
                        System.out.println();
                        System.out.println("The final board looks like");
                        System.out.println();
                        board.displayBoard();
                        System.out.println();
                        gameOver = true;
                        winner = players[result-1].name;
                        break;
                    }

                } else{
                    System.out.println("Player " + (i+1) + " will play again");
                }

            }
        }

        System.out.println("Thanks for playing");

    }
}
