import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Board {

    private final int boardOrder;
    public String[][] board;
    Map<String, Integer> winCombo;


    public Board(int boardOrder) {
        this.boardOrder = boardOrder;
        this.board = new String[boardOrder][boardOrder];

    }

    public void initialize(Player[] players, int numberOfPlayers) {
        for(int i=0;i<boardOrder;i++) {
            for(int j=0;j<boardOrder;j++) {
                this.board[i][j] = ".";
            }
        }

        winCombo = new HashMap<>();
        for(int i=0;i<numberOfPlayers;i++){
            String combo="";
            for(int j=0;j<this.boardOrder;j++) {
                combo+=players[i].symbol;
            }
            //String combo = players[i].symbol.repeat(this.boardOrder);
            winCombo.put(combo, i+1);
        }

    }

    public boolean isValidMove(int row, int column){
        return ((row>=1) && (row<=boardOrder)) && ((column>=1) && (column<=boardOrder));
    }

    public int playMove(int row, int column, String symbol) {
        if(!isValidMove(row, column)) {
            System.out.println("Invalid Move. Please Try again");
            return -1;
        } else if(!board[row-1][column-1].equals(".")){
            System.out.println("The place is occupied by another player. Try again.");
            return -1;
        } else {
            board[row-1][column-1] = symbol;
            System.out.println("---Move recorded Successfully---");
            return 1;
        }
    }

    public void displayBoard() {
        System.out.print("|");
        for(int i=0;i<(3*boardOrder+(boardOrder-1));i++){
            System.out.print("-");
        }
        System.out.print("|");
        System.out.println();

        for(int i=0;i<boardOrder;i++) {
            for(int j=0;j<boardOrder;j++) {
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
            if(i!=boardOrder-1) {
                System.out.print("|");
                for (int k = 0; k < (3*boardOrder+(boardOrder-1)); k++) {
                    System.out.print("-");
                }
                System.out.print("|");
                System.out.println();
            }
        }

        System.out.print("|");
        for(int i=0;i<(3*boardOrder+(boardOrder-1));i++){
            System.out.print("-");
        }
        System.out.print("|");
    }

    public boolean checkForDraw(){
        for(int i=0;i<boardOrder;i++){
            for (int j=0;j<boardOrder;j++){
                if(board[i][j].equals(".")) return false;
            }
        }
        return true;
    }

    public int checkWinner(){

        // checking rows
        for(int i=0;i<boardOrder;i++) {
            String checkCombo = "";
            for(int j=0;j<boardOrder;j++){
                checkCombo = checkCombo + board[i][j];
            }
            if(winCombo.containsKey(checkCombo)) {
                return winCombo.get(checkCombo);
            }
        }

        //checking columns
        for(int i=0;i<boardOrder;i++) {
            String checkCombo = "";
            for(int j=0;j<boardOrder;j++){
                checkCombo = checkCombo + board[j][i];
            }
            if(winCombo.containsKey(checkCombo)) {
                return winCombo.get(checkCombo);
            }
        }

        //checking diagonals
        String checkComboMainDiagonal = "";
        String checkComboSecondDiagonal = "";
        for(int i=0;i<boardOrder;i++) {
            checkComboMainDiagonal = checkComboMainDiagonal + board[i][i];
            checkComboSecondDiagonal = checkComboSecondDiagonal +
                    board[boardOrder - i - 1][boardOrder - i - 1];
        }
        if(winCombo.containsKey(checkComboMainDiagonal)) {
            return winCombo.get(checkComboMainDiagonal);
        } else return winCombo.getOrDefault(checkComboSecondDiagonal, -1);
    }



}
