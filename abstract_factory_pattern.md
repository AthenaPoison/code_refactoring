# Abstract factory pattern
The basic of the Abstract Design Pattern(according to the example code):
1. There is a Monster abstract class
2. The difference between Monsters is the Objects stored in Attack and Range
3. An interface is used to represent both options available for Attack and Range
4. 2 classes implement that Attack and Range interface
5. There is a MonsterFactory interface. It represents monsters with different Attack and Range types
6. If I want a Vampire with a MediumAttack & MediumRange, a Vampire Factory is made that assigns those classes to the Vampire objects Attack and Range attributes
7. The only thing left is the builder that receives a string. If the String is a Vampire we pass the Vampire Factory into the Vampire class Object
8. The Vampire class object assigns the Attack and Range Objects as the attributes for Attack and Range for every Vampire Object that is created.

~~~Java
//This class defines attributes and capabilities
//for each monster
public abstract class Monster{
    private String name;

    MonsterAttackPower attackPower;
    MonsterAttackRange attackRange;

    abstract void makeMonster();

    public void checkIfVictimIsInRange(){
        System.out.println(getName() + " checks if victim is " + attackRange);
    }

    public void attackTheVictim(){
        System.out.println(getName() + " attacks the victim for " + attackPower);
    }

    public String toString(){
        String infoOnMonster = getName() + " attacks anything " + attackRange + " for " +attackPower;
        return infoOnMonster;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
~~~

~~~Java
//Define interface to be used 
interface MonsterAttackPower{
    public String toString();
}

//These replace toString for MonsterAttackPower
class BasicAttack implements MonsterAttackPower{
    public String toString(){
        return "10 in damage";
    }
}

class MediumAttack implements MonsterAttackPower{
    public String toString(){
        return "20 in damage";
    }
}

//for MonsterAttackRange
interface MonsterAttackRange{
    public String toString();
}

class BasicRange implements MonsterAttackRange{
    public String toString(){
        return "5 away";
    }
}

class MediumRange implements MonsterAttackRange{
    public String toString(){
        return "10 away";
    }
}
~~~

~~~Java
//Define attributes for each monster and the methods that will define them
interface MonsterFactory{
    public MonsterAttackPower assignAttackPower();
    public MonsterAttackRange assignAttackRange();
}

//This defines the attack and range to assign to each zombie created
class ZombieFactory implements MonsterFactory{
    public MonsterAttackPower assignAttackPower(){
        return new BasicAttack();
    }

    public MonsterAttackRange assignAttackRange(){
        return new BasicRange();
    }
}

//Define attack and range assign to vampire
class VampireFactory implements MonsterFactory{
    public MonsterAttackPower assignAttackPower(){
        return new Mediumttack();
    }

    public MonsterAttackRange assignAttackRange(){
        return new MediumRange();
    }
}
~~~

~~~Java
//A factory is sent into this class that will assign the right objects for attack and range to the zombie
class Zombie extends Monster{
    //the type of attack & range to assign are past into the constructor
    MonsterFactory monsterFactory;

    public Zombie(MonsterFactory monsterFactory){
        this.MonsterFactory = monsterFactory;
    }

    void makeMonster(){
        System.out.println("Raising a Zombie");
        attackPower = monsterFactory.assignAttackPower();
        attackRange = monsterFactory.assignAttackRange();
    }
}

//Same for vampire
class Vampire extends Monster{
    //the type of attack & range to assign are past into the constructor
    MonsterFactory monsterFactory;

    public Vampire(MonsterFactory monsterFactory){
        this.MonsterFactory = monsterFactory;
    }

    void makeMonster(){
        System.out.println("Turning Human to Vampire");
        attackPower = monsterFactory.assignAttackPower();
        attackRange = monsterFactory.assignAttackRange();
    }
}
~~~

Now that the Monsters are defined, their individual attacks & ranges and I have a factory for making them, now we need to create a way to order them
~~~Java
abstract class MonsterBuilder{
    protected abstract Monster makeMonster(String typeOfMonster);

    public Monster orderAMonster(String typeOfMonster){
        Monster theMonster = makeMonster(typeOfMonster);

        //Test
        theMonster.makeMonster();
        theMosnter.checkIfVictimIsInRange();
        theMonster.attackTheVictim();

        return theMonster;
    }
}

class OrderAMonster extends MonsterBuilder{
    protected Monster makeMonster(String typeOfMonster){
        Monster theMonster = null;

        if(typeOfMonster.equals(Zombie)){
            //create the factory that assigns the right attributes to each zombie
            MonsterFactory monsterFactory = new ZombieFactory();

            //create a zombie monster that stores the objects specific for each zombie so they can be assigned to this monster

            theMonster = new Zombie(monsterFactory);
            theMonster.setName("Zombie Bob");
        }else if (typeOfMonster.equals("Vampire")){
            MonsterFactory monsterFactory = new VampireFactory();

            theMonster = new Vampire(monsterFactory);
            theMonster.setName("Vampire Paul");
        }
        return theMonster;
    }
}
~~~

~~~Java
//Test code
class MonsterMakerTest{
    MonsterBuilder monsterBuilder = new OrderAMonster();
    Monster zombie = monsterBuilder.orderAMonster("Zombie");
    System.out.println(zombie + "\n");

    Monster vampire = monsterBuilder.orderAMonster("Vampire");
    System.out.println(vampire + "\n");
}
~~~