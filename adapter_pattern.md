# Adapter Pattern

This allows us to create a new classs wihtout disturbing any other code. The adapters make it easier to swap codes at runtime and allows for flexible use of method names, so we can choose names that makes sense to us.

//original code
public class EnemyShip{
    protected String name;
    private int attackPower;
    protected int spacesMovedPerTurn;

    public EnemyShip(int currentLevel){
        if(currentLvel <= 5){
            name = "Galax";
            this.attackPower = 5;
            this.spacesMovedPerTurn = 2;
        } else if (currentLvel>5 || currentLvel<10){
            name = "Galaxian";
            this.attackPower = 10;
            this.spacesMovedPerTurn = 3;
        } else if (currentLevel>10){
            name="Galaxian Prime";
            this.attackPower = 15;
            this.spacesMovedPerTurn = 4;
        }
    }

    public void moveShip(){
        System.out.println(name + " moves" + spacesMovedPerTurn + " spaces");
    }

    public void makeShipAttack(){
        System.out.println(name + " does" + attackPower+ " damage");
    }

    public static void main(String[] args){
        EnemyShip level1Ship = new EnemyShip(6);

        level1Ship.moveShip();
        level1Ship.makeShipAttack();

        GalaxianPrime primeTime = new GalaxianPrime(15);

        primeTime.moveShip();
        primeTime.makeShipAttack();
    }
}

class GalaxianPrime extends EnemyShip{
    public GalaxianPrime(int currentLevel){
        super(currentLevel);
    }

    public void moveShip(){
        System.out.println(name + " turns on forcefield and moves " + spacesMovedPerTurn+ " spaces");
    }
}

Now we implement the adapter pattern which starts with an interface. This makes sure that new classes are compatible with the adapter

//refactored code
interface Enemy{
    public void moveShip();
    public void makeShipAttack();
}

class Galax implements Enemy{
    private int attackPower=5;
    private int spacesMovedPerTurn = 2;

    public void moveShip(){
        System.out.println("Galax moves " + spacesMovedPerTurn + " spaces");
    }

    public void makeShipAttack(){
        System.out.println("Galax does " + attackPower + " damage");
    }
}

This is the adaptee. The adapter will call the right methods here when they are called on the Enemy interface

//refactored code
class GalaxPrime{
    protected String name = "Galaxian Prime";
    private int attacPower = 15;
    protected int spacesMovedPerTurn= 4;

    public void turnOnForceField(){
        System.out.println(name + " turns on force field");
    }

    public void warpToSpace(){
        System.out.println(name + " warps " + spacesMovedPerTurn + " spaces");
    }

    public voi chargePhasers(){
        System.out.println(name + " changes phasers");
    }

    public void firePhasers(){
        System.out.println(name + " fires phasers for " + attackPower + " damage");
    }
}

The adapter can provide completely different actions for the methods implemented
The adapter contains an object of the same type as adaptee, so all calls sent to the Enemy are sent to methods of the adaptee

//refactored code
class EnemyAdapter implements Enemy{
    GalaxPrime galaxPrime;
    public EnemyAdapter(GalaxPrime galaxPrime){
        super();
        this.galaxPrime = galaxPrime;
    }

    public void moveShip(){
        galaxPime.turnOnForceField();
        galaxPrime.warpToSpace();
    }

    public void makeShipAttack(){
        galaxPrime.chargePhasers();
        galaxPrime.firePhasers();
    }
}

//Test the code
class TestEnemyAdapter{
    public static void main(String[] args){
        Enemy galax = new Galax():
        GalaxPrime galaxPrimeAdaptee = new GalaxPrime();
        Enemy galaxPrime = new EnemyAdapter(galaxPrimeAdaptee);

        //Test a regular Enemy
        galax.moveShip();
        galax.makeShipAttack();

        //Test an adapted Enemy
        galaxPrime.moveShip();
        galaxPrime.makeShipAttack();
    }
}