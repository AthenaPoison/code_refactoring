package code_refactoring;

public class FootballPlayer2 {
    private String playerName = "";
    private String college = "";
    private double fortyYardDash = 0.0;
    private int repsBenchPress = 0;
    private double sixtyYardDash = 0.0;

    public String getPlayerName() {return playerName; }
    public String getCollege() {return college; }
    public double get40YdDash() {return fortyYardDash; }
    public int getRepsBenchPress() {return repsBenchPress; }
    public double get60YdDash() {return sixtyYardDash; }

    /*
    In the example below, we can see that there are many duplications within the constructors.
    This can prove a problem when updating the code and constructors, as one needs to update all the constructors in order to avoid errors in 
    the code. 

    public FootballPlayer2(String playerName, String college, double fortyYardDash, double sixtyYardDash){
        this.playerName = playerName;
        this.college = college;
        this.fortyYardDash = fortyYardDash;
        this.sixtyYardDash = sixtyYardDash;
    }

    public FootballPlayer2(String playerName, String college, double fortyYardDash, int repsBenchPress){
        this.playerName = playerName;
        this.college = college;
        this.fortyYardDash = fortyYardDash;
        this.repsBenchPress = repsBenchPress;
    }

    public FootballPlayer2(String playerName, String college, double fortyYardDash, int repsBenchPress, double sixtyYardDash){
        this.playerName = playerName;
        this.college = college;
        this.fortyYardDash = fortyYardDash;
        this.repsBenchPress = repsBenchPress;
        this.sixtyYardDash = sixtyYardDash;
    }

    A solution to the problem is by having as few constructors or chaining constructors so that they call on a general purpose constructor which
    eleminates duplicate constructors when it comes to assigning values.
    */
    public FootballPlayer2(String playerName, String college, double fortyYardDash, int repsBenchPress, double sixtyYardDash){
        this.playerName = playerName;
        this.college = college;
        this.fortyYardDash = fortyYardDash;
        this.repsBenchPress = repsBenchPress;
        this.sixtyYardDash = sixtyYardDash;
    }

    public FootballPlayer2(String playerName, String college, double fortyYardDash, int repsBenchPress){
        //call main constructor above
        this(playerName, college, fortyYardDash, repsBenchPress, 0.0);
    }

    public FootballPlayer2(String playerName, String college, double fortyYardDash, double sixtyYardDash){
        this(playerName, college, fortyYardDash, 0, sixtyYardDash);
    }

    public static void main(String[] args){
        FootballPlayer2 jamellFelming = new FootballPlayer2("Jamell Fleming", "Oklahoma", 4.53, 10.75);
        System.out.println(jamellFelming.getPlayerName());
        System.out.println(jamellFelming.getCollege());
        System.out.println(jamellFelming.get40YdDash());
        System.out.println(jamellFelming.getRepsBenchPress());
        System.out.println(jamellFelming.get60YdDash());

    }
}
