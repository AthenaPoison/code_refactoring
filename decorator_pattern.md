# Decorator Pattern

When new features are needed , it is a bad idea to add new code to older classes. This would make compact easy to understand classes complicated because they would break the Single responsibility principle.

The decorator pattern instead places each special case behavior (Embellishments) into its own classes.

By using the decorator pattern we will also satisfy another SOLID principle: the interface segregation principle, by using many specific interfaces rather than one general purpose interface.
~~~Java
//Bad way to add functionality
class CalculateHairCut{
    enum HairCutOptions{ BASIC_CUT, PERM, HAIR_FROSTING};
    HairCutOptions optionPicked;
    CalculateHairCut(HairCutOptions options){
        optionPicked = options;
    }

    public String getDescription(){
        if(optionPicked == HairCutOptions.PERM){
            return "\nAdd Chemicals and Put Hair in Rollers";
        }else if (optionPicked == HairCutOptions.HAIR_FROSTING){
            return "\nAdd Chemicals and Put Hair in Foil";
        } else{
            return "\nTrim the Hair";
        }
    }

    public double getCost(){
        if(optionPicked == HairCutOptions.PERM){
            return 75.00;
        }else if (optionPicked == HairCutOptions.HAIR_FROSTING){
            return 100.00";
        } else{
            return 10.00;
        }
    }
}
~~~
~~~Java
//The decorator pattern method
public interface HairCut{
    public String getDescription();
    public double getCost();
}

//The decorator will allow you to add features while keeping them completely separated in their own class
abstract class HairCutDecorator implements HairCut{
    protected HairCut hairCut;

    //the decorator will be able to dynamically customize HairCuts
    HairCutDecorator(HairCut hairCut){
        this.hairCut = hairCut;
    }

    public String getDescription(){
        return hairCut.getDescription();
    }

    public double getCost(){
        return hairCut.getCost();
    }
}
~~~
~~~Java
//This represents the basic HairCut that was originally used all of the time before the upgrade
class RegularHairCut implements HairCut{
    public String getDescription(){
        return "Trim the Hair";
    }
    public double getCost(){
        return 10.00;
    }
}
~~~
~~~Java
//With the decorator, we can easily add additional features and calculations without changing the code that already exists
class Perm extends HairCutDecorator{
    Perm(HairCur hairCut){
        super(hairCit);
    }

    public String getDescription(){
        return hairCut.getDescription() + "\nAdd chemicals and put hair in rollers";
    }

    public double getCost(){
        return hairCut.getCost() + 75.00;
    }
}
~~~
~~~Java
class TestHairCut{
    public static void main(String[] args){
        HairCut perAndCut = new Perm(new RegularHairCut());
        System.out.println("Services");
        System.out.println(permAndCut.getDescription());
        System.out.println("Price: $" + permAndCut.getCost());
    }
}
~~~
