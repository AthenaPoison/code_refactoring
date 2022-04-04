# Builder Design Pattern
This pattern allows you to build complex objects in a series of steps.

Steps to implement: 
1. Define an object class of a specific type
2. Create an abstract class that contains all the methods that each class of type object must implement
3. A director then initializes the specified class type and provides it

//example code: Sandwich.java <- specific type of object
public class Sandwich{
    private String bread = "";
    private String vegetables = "";
    private String meat = "";
    private String cheese = "";
    private String condiments = "";

    public String getBread(){ return bread;}
    public void setBread(String bread){
        this.bread = bread;
    }

    public String getVegetables(){ return vegetables;}
    public void setVegetables(String bread){
        this.vegetables = vegetables;
    }

    public String getMeat(){ return meat;}
    public void setMeat(String meat){
        this.meat = meat;
    }

    public String getCheese(){ return cheese;}
    public void setCheese(String cheese){
        this.cheese = cheese;
    }

    public String getCondiments(){ return Condiments;}
    public void setCondiments(String condiments){
        this.condiments = condiments;
    }

    public String toString(){
        return getBread() + " " + getVegetables() + " " + getMeat() + " " + getCheese() + " " + getCondiments();
    }
}

Below will be the builder abstract class. It defines all the methods that each sandwich object must contain.

abstract class SandwichBuilder{
    Sandwich sandwich;

    public Sandwich getSandwich(){return sandwich;}

    public void makeSandwich(){ sandwich = new Sandwich();}

    public abstract void buildBread();
    public abstract void buildVegetables();
    public abstract void buildMeat();
    public abstract void buildCheese();
    public abstract void buildCondiments();
}

class BLTBuilder extends SandwichBuilder{
    @Override
    public void buildBread(){
        sandwich.setBread("white bread");
    }

    @Override
    public void buildVegetables(){
        sandwich.setBread("lettuce peppers");
    }

    @Override
    public void buildMeat(){
        sandwich.setBread("beef");
    }

    @Override
    public void buildCheese(){
        sandwich.setBread("pepperjack");
    }

    @Override
    public void buildCondiments(){
        sandwich.setBread("mayo");
    }
}

//This is the director which assigns the type of sandwich to build and then calls all of the initialization methods
class sandwichArtist{
    private SandwichBuilder sandwichBuilder;

    public void setSandwichBuilder(SandwichBuilder sandwichBuilder){
        this.sandwichBuilder = sandwichBuilder;
    }

    public Sandwich getSandwich(){ return sandwichBuilder.getSandwich();}

    //initializes the sandwich object
    public void takeSandwichOrder(){
        sandwichBuilder.makeSandwich();
        sandwichBuilder.buildBread();
        sandwichBuilder.buildVegetables();
        sandwichBuilder.buildMeat();
        sandwichBuilder.buildCheese();
        sandwichBuilder.buildCondiments();
    }
}

//To test the code
class TestBuilder{
    public static void main(String[] args){
        //The director has methods for assigning the sandwich to build, initializes it and provides the object to who asks for it

        SandwichArtist paul = new SandwichArtist();
        
        //Designate the specific sandwich object we want to build
        SandwichBuilder bltBuilder = new BLTBuilder();

        //Assign the specific sanwich to build
        paul.setSandwichBuilder(bltBuilder);

        //Initialize everything in the new object
        Sandwich bltSandwich = paul.getSandwich();

        System.out.println(bltSandwich);
    }
}