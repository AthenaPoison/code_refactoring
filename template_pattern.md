# Eliminating duplicate code using template pattern

The template pattern is part of generalization. It transforms specific purpose code into general purpose code which in doing so, simplifies and removes duplicate code

You apply this pattern when Objecs perform similar steps in the same order and it allows subclasses to override the behavior that varies
~~~Java
//original code
public class Hamburger{
    private boolean customerWantsCondiments = true;

    Hamburger(boolean wantsCondiments){
        customerWantsCondiments = wantsCondiments;
    }

    public void makeSandwich(){
        cutBun();
        addMeat();
        addVegetables();
        if(customerWantsCondiments()){
            addCondiments();
        }

        wrapSandwich();
    }
}
~~~
~~~Java
public class VeggieSub.java{
    //this class will have the same methods as Hamburger above with the only difference being whether meat is added or not

    //...
    public void makeSandwich(){
        cutBun();
        addVegetables();
        if(customerWantsCondiments()){
            addCondiments();
        }

        wrapSandwich();
    }
}
~~~
This is where the template method comes in.
~~~Java
//refactored code:
public abstract class Sandwich(){
    final void makeSandwich(){
        System.out.println("\n-----New Order-----\n");
        
        cutBun();
        if(customerWantsMeat()){
            addMeat();
        }
        addVegetables();
        if(customerWantsCondiments){
            addCondiments();
        }
        wrapSandwich();
    }
    //methods must be overriden by subclasses
    abstract void addMeat();
    abstract void addCondiments();

    //methods used for every sandwich
    public void cutBun(){
        System.out.println("Bun cut");
    }

    public void addVegetables(){
        System.out.println("No tomatoes");
    }
    public void wrapSandwich(){
        System.out.println("Sandwich done");
    }

    //Next are hooks. These can be overridden.
    //Use abstract methods when you want to force the user to override and a hook when it is optional
    boolean customerWantsMeat(){return true;}
    boolean customerWantsCondiments(){return true;}
}
~~~
And here is what the Hamburger and VeggieSub will look like in the template method.
~~~Java
public class Hamburger extends Sandwich{
    @Override
    void addMeat(){
        System.out.println("It's a chicken patty");
    }

    @Override
    void addCondiments(){
        System.out.println("Why put tomatoes when you already have ketchup?");
    }
}

public class VeggieSub extends Sandwich{
    //remember no meat here
    boolean customerWantsMeat(){return false;}
    @Override
    void addMeat(){
    }

    @Override
    void addCondiments(){
        System.out.println("Added speacial sauce");
    }
}
~~~
~~~Java
//test code using Cook.java
public class Cook{
    public static void main(String[] args){
        Sandwich customer1 = new Hamburger();

        customer1.makeSandwich();

        Sandwich customer2 = new VeggieSub();
        customer2.makeSandwich();
    }
}
~~~
