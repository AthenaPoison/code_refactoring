# Extrac Methods

Extracting methods are used to make code as readable as possible. When methods become too long, readability might reduce. In order to combat this, we can turn code fragments into methods with descriptive names.

//we're gonna need a lot of footballplayer classes
public class FootballPlayer{
    private String name = "";
    private double[] fortyYardDashTime = null;

    public String getName(){
        return name;
    }

    public double[] get40YardDashTimes(){
        return fortyYardDashTimes;
    }

    FootballPlayer(String name, double[] fortyYardDashTimes){
        this.name = name;
        this.fortyYardDashTimes = fortyYardDashTimes;
    }
}

//main class
import java.util.ArrayList;
public class FootballPlayer40YardDashInfo{
    ArrayList<FootballPlayer> players = new ArrayList<FootballPlayer>();

    public void addFootballPlayer(FootballPlayer player){
        players.add(player);
    }

    public void printPlayerInfo(){
        double eavg40YdTime = 0.0;
        System.out.printf("%-15s%15s", "Name", "Avg 40 Times\n");

        //print dashes under titles
        for (int i = 0; i <30; i++>){ System.out.print("_");}

        System.out.println();

        for (FootballPlayer player : player){
            System.out.printf("%-19s", player.getName());

            double total40YdDashTimes = 0.0;
            doublep[] fortyYardDashTimes = player.get40YdDashTimes();

            for (int i = 0; i < player.get40YardDashTimes().length; i++){
                total40YardDashTimes += fortyYardDashTimes[i];
            }

            avg40YdTime = total40YdDashTimes / player.get40YardDashTimes().length;
            System.out.println("%1$.2f", avg40YdTime);
            System.out.println();
        }
    }
}

In the example above, the printPlayerInfo method is quite long and certain parts perform different functions. For easier readability and understanding, we can extract these parts into separate methods with separate names.

//using extraction
public void printPlayerInfo(){
    printTitles();
    printPlayersWith40Avg();
}
public void printTitles(){
    System.out.println("%-15s %15s", "Name", "Avg 40 Time\n");
    printDashes('-', 30);
}
public void printDashes(char charToPrint, int howManyTimes){
    for (int 1 = 0; i < howManyTimes; i++){
        System.out.print(charToPrint);
    }
    System.out.println();
}
public void printPlayerWith40Avg(){
    for (FootballPlayer player : players){
        System.out.printf("%-19s", player.getName());
        double total40YdDashTimes = 0.0;
        double[] fortyYardDashTimes = player.get40YardDashTimes();
        for (int i = 0; i < player.get40YardDashTimes().length; i++){
            total40YdDashTimes += fortyYardDashTimes[i];
        }

        double avg40YdDashTimes = getAvgDashTimes(total40YdDashTimes, player.get40YardDashTimes().length);

        System.out.printf("%1$.2f", avg40YdTime);
        System.out.println():
    }
}
public static double getAvgDashTimes(double totalDashTimes, int numberDashTimes){
    return totalDashTimes / numberDashTimes;
}

# When not to extract methods

If the code is clear, extracting is not needed. Consider the code below:

//without extraction
public static void main(String[] args){
    String inTop15 = (avg40YdTime < 4.41) ? " *Top 15\n" : "\n";
}

and compare it to:

// with extraction
public static void main(String[] args){
    String inTop15 = checkIfInTop15(avg40YdTime) ? " *Top 15\n" : "\n"
}
public static boolean checkIfInTop15(double avg40YdTime){
    return avg40YdTime < 4.41;
}

which uses extraction. In this case, the first code reads simpler than the second. When it comes to conditionals in expressions, it might be better to leave it in one code instead of extracting it for better understandability.

# Temps

Temps are used once and doesn't add to the understanding of the code. Consider the code below:

//original
public static void main(String[] args){
    double dashTime = 4.50;

    final double avg40YdDash = getAVGDashTime();

    String dashGrade = ((dashTime <= avg40YdDash) ? "Good" : "Bad");

    System.out.println("That was a " + dashGrade + " time");
}

The variable avg40YDash is a temp used to hold the value of an expression. Since it is only used once, we can directly call on the expression holding the value instead of storing it in a temp

//refactored
public static void main(String[] args){
    double dashTime = 4.50;
    String dashGrade = ((dashTime <= getAvgDashTime()) ? "Good" : "Bad");
    System.out.println("That was a " + dashGrade + " time");
}

Replacing a temp variable with a query is also something to consider if temp is only used once and storing the value of an expression. While it does not make the code less understandable, it does have the advantage of being able to bring the expression elsewhere, reducing repeated code and overall understandability of the code.

//original
public static void main(String[] args){
    double avgDashTime = totalDashTime / totalDashes;

    if (avgDashTime > 4.41){
        System.out.println("Average Time");
    }
}

//refactored 
public static void main(String[] args){
    if (avgDashTime() > 4.41){
        System.out.println("Average Time");
    }
}
public static double avgDashTime(){
    return totalDashTime / totalDashes;
}