public class Player {
    String name;
    String symbol;

    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public void getDetails(){
        System.out.println("Player name- " + this.name + " || Player Symbol- " + this.symbol);
    }
}
