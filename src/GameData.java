import java.io.Serializable;

public class GameData implements Serializable {
    Player[] players_list;
    String winner;

    public GameData(Player[] players_list, String winner) {
        this.players_list = players_list;
        this.winner = winner;
    }

    public String getter(){
        String pl = "Players - ";
        for(int i=0 ; i<players_list.length; i++){
            pl += players_list[i].name + " ";
        }
        if(winner.equals("draw")){
            String w = "\nResult is: " + winner;
        }
        String w = "\nWinner is: " + winner;
        return pl + w;
    }
}