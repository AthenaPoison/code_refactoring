package code_refactoring;

public class FootballPlayer {
    
    private double passerRating; //specific to QBs
    private int rushingYards; //specific to RBs & QBs
    private int receivingYards; //specific to RBs & WRs
    private int totalTackles; //specific to DEF
    private int interceptions; //specific to DEF
    private int fieldGoals; //specific to kickers
    private double avgPunt; //specific to punters
    private double avgKickoffReturn; //specific to special teams
    private double avgPuntReturn; //specific to special teams

    /*Creation method of replacement to contructors:
        In this example, some variables might only be used with certain football players of specific roles (QBS, RBs, etc)
        hence numerous constructors will be created based on the football player's role. 
        For a QB their constructor will look like this:
        
        FootballPlayer(double passerRating, int rushingYards){
            this.passerRating = passerRating;
            this.rushingYards = rushingYards;
        }

        a RB :
        
        FootballPlayer(int rushingYards){
            this.rushingYards = rushingYards;
        }

        and WR:
        FootballPlayer(int receivingYards){
            this.receivingYards = receivingYards;
        }

        and so on. However, errors will occur in the above code due to having the same attribute signature. 
        In this case RB and WR receiving int values.

        A solution to this problem is to use a creation method to create a catchall constructor that receives all the initiated variables.
    */
    
    private FootballPlayer(double passerRating, int rushingYards, int receivingYards, int totalTackles, int interceptions, int fieldGoals,
                            double avgPunt, double avgKickoffReturn, double avgPuntReturn){

                                this.passerRating = passerRating;
                                this.rushingYards = rushingYards;
                                this.receivingYards = receivingYards;
                                this.totalTackles = totalTackles;
                                this.interceptions = interceptions;
                                this.fieldGoals = fieldGoals;
                                this.avgPunt = avgPunt;
                                this.avgKickoffReturn = avgKickoffReturn;
                                this.avgPuntReturn = avgPuntReturn;
                            }
    
    public double getPasserRating(){
        return passerRating;
    }

    //and create creation methods that call on the constructor
    public static FootballPlayer createQB(double passerRating, int rushingYards){
        return new FootballPlayer(passerRating, rushingYards, 0, 0, 0, 0, 0.0, 0.0, 0.0);
    }
    
    public static FootballPlayer createRB(int rushingYards, int  receivingYards){
        return new FootballPlayer(0.0, rushingYards, receivingYards, 0, 0, 0, 0.0, 0.0, 0.0);
    }
    public static void main(String[] args){
        FootballPlayer aaronRogers = FootballPlayer.createQB(100.0, 259);
        System.out.println("Aaron Rodgers Passer Rating: " + aaronRogers.getPasserRating());
    }
}
