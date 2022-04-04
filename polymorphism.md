# Simplifying Conditionals and Replacing them with Polymorphism

Example 1: Simplifying Conditionals

//original code
public class TurnConditionalsIntoMethods{
    public static void main(String[] args){
        int[] bagWeight = new int[]{25,55,75};
        int numberOfBags = bagWeight.length;
        int bagFees = 0;

        for (int i = 0; i < numberOfBags; i++){
            if (i<=1){
                if (bagWeight[i]< 50){
                    if (i==0){
                        bagFees += 25;
                    } else{
                        bagFees +=35;
                    }
                } else if (bagWeight[i] <70){
                    if (i==0){
                        bagFees += 100;
                    } else if (i ==1){
                        bagFees += 150;
                    } else{ 
                        bagFees += 200;}
                } else if (i >= 0 && bagWeight[i]<70){
                    if (i==1){
                        bagFeeds += 100;
                    } else{
                        bagFees += 150;
                    }
                } else{
                    bagFees += 200;
                }
            }
        }
    }
}

What the conditionals in the code mean:
    bag weigh < 50 lbs and there is 1 bag charge $25
    bag weigh < 50 lbs and htere is 2 bag charge $35
    bag weigh >= 70 lbs charge $200
    bag weigh between 50 to 70 lbs charge $100 if first bag and $150 for each additional bag

We simplify this conditional statement into methods to make it more readable.

public class TurnConditionalIntoMethods{
    static int bagOver7-lbs(){
        return 200;
    }
    static int bagUnder50lbs(int bagNumber){
        return (bagNumber < 1) ? 25:35;
    }
    static int bag50To70lbs(int bagNumber){
        return (bagNumber < 2) ? 100:150;
    }

    public static void main(String[] args){
        bagFees = 0;
        for (int theBag = 0; theBag< numberOfBags; theBag++){
            if (bagWeight[theBag] < 50){
                bagFees += bagUnder50lbs(theBag);
            }else if (bagWeight[theBag] < 70){
                bagFees += bag50To70lbs(theBag);
            }else{
                bagFees += 200;
            }
        }
        System.out.println("Bag Fees: $" + bagFees);
    }
}

The above solution operates on the assumption that every option is just as likely to occur however in proper coding if and else statements should not be used if things are not just as likely to occur. Instead, something called a "guard clause" should be used.

//continuing from the code above: edit
public static boud main(String[] args){
    bagFees = 0;
    for(int theBag=0; theBag < numberOfBags; theBag++){
        if (bagWeight[theBag] < 50){ bagFees += bagUnder50lbs(theBag); }
        if (bagWeigth[theBag] <70 && bagWeight[heBag] >=){
            bagFees += bag50To70lbs(theBag);
        }
        if (bagWeight[theBag] > 70){ bagFees += 200;}
    }
    System.out.println("Bag Fees: $" + bagFees);
}

# Example 2: Replace Conditionals with Polymorphism

//without polymorphism
public class ReplaceConditionalWithPoly{
    public static void main(String[] args){
        String doggy = "Dog";
        String kitty = "Cat";

        makeSound(doggy);
    }

    static void makeSound(String animal){
        switch(animal){

            case "Dog":
                System.out.println("Woof");
                break;
            case "Cat":
                System.out.println("Meow");
                break;
            default:
                throw new RuntimeException("Don't know that animal");
        }
    }
}

To make the code more dynamic:

class Animal{
    private String sound = "";
    
    public String getSOund(){
        return sound;
    }
    
    public void setSOund(String sound){
        this.sound = sound;
    }

    public Animal(String sound){
        super();
        this.sound = sound;
    }
}

class Dod extends Animal{
    public Dog(String sound){
        super(sound);
    }
}

Class Cat extends Animal{
    public Cat(String sound){
        super(sound);
    }
}

//in public void main
Animal rex = new Dog("Woof");
Animal sophie = new Cat("Meow");
System.out.println(sophie.getSound());