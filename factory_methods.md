# Replacing Constructors with Factory Methods

Example 1: The code below isa factory that is able to dynamically create subclasses based off of information being passed into it.
~~~Java
private abstract class Customer2{
    private String custRating;
    static final int PREMIER = 2;
    static final int VALUED = 1; 
    static final int DEADBEAT = 0;

    public String getCustRating(){ return custRating; }
    public static setCustRating(String cistRating){
        this.custRating = custRating;
    }

    public static void main(String[] args){
        CustomerFactory customerFactory = new CustomerFactory();
        
        Customer2 goodCustomer = customerFactory.getCustomer(PREMIER);

        System.out.print("Customer Rating: " + goodCustomer.getCutomerRating());
    }
}
~~~
~~~Java
class Premier extends Customer2{
    Premier(){
        setCustRating("Premier Customer");
    }
}
~~~
~~~Java
class Deadbeat extends Customer2{
    Deadbeat(){
        setCustRating("Deadbeat Customer");
    }
}
~~~
~~~Java
class CustomerFactory{
    public Customer2 getCustomer(int custType){
        switch(custType){
            case 2:
                return new Premier();
            case 0:
                return new Deadbeat();
            default:
                throw new IllegalArgumentException("Invalid Customer Type");
        }
    }
}
~~~
Alternatively, we can get rid of the conditional statments:
~~~Java
class CustomerFactory{
    public Customer2 getCustomer(String custName){
        try{
            return (Customer2) Class.forName(custName).newInstance();
        }
        catch(Exception e){
            throw new IllegalArgumentException("Invalid Customer Type");
        }
    }
}
~~~
and change the main method accordingly.
~~~Java
public static void main(String[] args){
    //...
    Customer2 goodCustomer = customerFactory.getCustomer("Premier");
}
~~~
# Singleton pattern
A factory that creates singletons(restricts a class to one instance)
~~~Java
//Athelete.java
import java.lanf.reflect.Method;

public class Athlete{
    private String athleteName = "";
    
    public String getAthleteName(){ return athleteName;}
    public void setAthleteName(String athleteName){
        this.athleteName = athleteName;
    }
    
    public static Athlete getInstance(){
        return null;
    }
}
~~~
~~~Java
class GoldWinner extends Athlete{
    //Set to null to signify that an instance f type GoldWinner doesn't exist
    private static GoldWinner goldAthlete = null;
    
    //Cinstructor is set to private to keep other classes from creating an instance of GoldWinner
    private GoldWinner(String athleteName){
        setAtheleteName(athleteName);
    }
    
    //Creates 1 instance of GoldWinner 
    public static GoldWinner getInstance(String athleteName){
        if (goldAthlete ==null){
            goldAthlete = new GoldWinner(athleteName);
        }
        return goldAthlete;
    }
}

class SilverWinner extends Athlete{
    private static SilverWinner silverAtlete = null;
    private SilverWinner(String athleteName){
        setAthleteName(athleteName);
    }

    public static SilverWinner getInstance(String athleteName){
        if (silverAthlete == null){
            silverAthlete= new SilverWinner(athleteName);
        }
        return silverAthlete;
    }
}

class BronzeWinner extends Athlete{
    private static BronzeWinner bronzeAthlete = null;
    private BronzeWinner(String athleteName){
        setAthleteName(athleteName);
    }

    public static BronzeWinner getInstance(String athleteName){
        if (bronzeAthlete == null){
            brozeAthlete = new BronzeWinnder(athleteName);
        }
        return bronzeAthlete;
    }
}
~~~
~~~Java
class MedalFactory{
    public Athlete getMedal(String medalType, String athleteName){
        try{
            //Define the type of the parameter that will be passed to the method created below
            
            Class[] athleteNameParameter = new Class[]{String class};

            //forNam returns a class object with the String that is passed to it.
            //getMethod returns the method provided the second parameter defines the type of parameter passed to the method

            Method getInstanceMethod = class.forName(medalType).getMethod("getInstance", athleteNameParameter);

            //create an array with the parameter values that will be passed to the method getInstance

            Object[] params = new Object[]{new String(athleteName)};

            //Pass the parameters to method getInstance and return a subclass of type Athlete

            return (Athlete) getInstanceMethod.invoke(null, params);
        }
        catch(Exception e){
            throw new IllegalArgumentException("Invalid Medal Type");
        }
    }
}
~~~
~~~Java
class TestMedalWinner{
    public static void main(String[] args){
        MedalFactory medalFactory = new MedalFactory();

        Athlete goldWinner = medalFactory.getMedal("GoldWinner", "Dave Thomas");
        Athlete silverWinner = medalFactory.getMedal("SilverWinner", "Mac McDonald");
        Athlete bronzeWinner = medalFactory.getMedal("BronzeWinnder", "Davi Edgerton");

        Athlete goldWinner2 = medalFactory.getMedal("GoldWinner", "Ray Kroc");

        System.out.println("Gold Medal Winner: " + goldWinnder.getAtheleteName());
        System.out.println("Silver Medal Winner: " + silverWinner.getAthleteName());
        System.out.println("Bronze Medal Winner: " + bronzeWinner.getAthleteName());

        System.out.println("Gold Medal Winner: " + goldWinner2.getAthleteName());
    }
}
~~~
Results: 
    Gold Medal Winner: Dave Thomas
    Silver Medal Winner: Mac McDonald
    Bronze Medal Winner: David Edgerton
 
 and even though we are referring to the second goldWinner
    Gold Medal Winner: Dave Thomas
