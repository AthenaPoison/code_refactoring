# Replacing Conditionals with Strategy Pattern
~~~Java
//Example Code: without strategy pattern
public class CalculateSalary{
    public static void main(String[] args){
        Employee salesman = new Employees(true, 1500.00);
        Employees secretary = new Employees(false, 20000.00);

        System.out.println("Salesman: " + salesman.getSalary());
        System.out.println("Secretary: " + secretary.getSalary());
    }
}

class Employees{
    private boolean bonus = false;
    private double salary = 0.0;

    public Employee(boolean bonus, double salary){
        this.bonus = bonus;
        this.salary = salary;
    }

    public Employees(){

    }

    public double getSalary(){
        if (bonus){
            return salary + (salary * .15);
        }
        return salary;
    }

    public void setSalary(double salary){
        this.salary = salary;
    }
}
~~~
The code above works, it is able to calculate and return an employees salary. However, it is not flexible and errors may occur when changes are made. One such example is the bonus rate which is set to .15 however a company may want it to be customizeable.

You could change the class code to suit the requirement but that in itself is a design flaw. You'll need to create new constructors for the changes.
~~~Java
class Employees{
    //...
    private double bonusAmount = .15;

    public Employees(boolean bonus, double salary, double bonusAmount){
        this.bonus = bonus;
        this.salary = salary;
        this.bonusAmount = bonusAmount;
    }

    public double getSalary(){
        if (bonus){
            return salary + (salary * bonusAmount);
        }
        return salary;
    }
}
~~~
With the strategy pattern, any changes can be made into a new class and still work. The strategy design pattern is used to dynamically change the algorithm that are used at runtime which makes it suitable to eliminate conditionals that decide which algorithm to use.

Here, we have a strategy pattern version of the code above. The different types of employees (salesman and secretary) are now their own class under Employee. We also have an interface to calculate pay.
~~~Java
public class ReplaceConditionalWithStrategy{
    public static void main(String[] args){
        Employee salesman = new Salesman(15000.00);
        Employee secretary = new Secretary(25000.00);

        System.out.println("Salesman: " + salesman.getPay());
        System.out.println("Secretary: " + secretary.getPay());

        salesman.setBonusOption(new getsBonus());
        System.out.println("Salesman: " + salesman.getPay());
    }
}
~~~
~~~Java
class Employee{
    super();
    protected double salary = 0.0;

    public Pay payType = new NoBonus();

    Employee(double salary){
        this.salary = salary;
    }
    Employee(double salary, Pay payType){
        this.salary;
        this.payType = payType;
    }

    public void setBonusUption(Pay newPayType){
        payType = newPayType;
    }
}

class Salesman extends Employee{
    Salesman(double salary){
        super(salary);
    }
    Salesman(double salary, Pay payType){
        super(salary);
        setBonusOption(payType);
    }
}

class Secretary extends Employee{
    Secretary(double salary){
        super(salary);
    }
    Secretary(double salary, Pay payType){
        super(salary);
        setBonusOption(payType);
    }
}

interface Pay{
    double getPay(double salary);
}
~~~
For each payType we create a class.
~~~Java
class GetsBonus implements Pay{
    @Override
    public double getPay(double Salary){
        return salary + (salary * .15);
    }
}

class NoBonus implements Pay{
    @Override
    public double getPay(double Salary){
        return salary;
    }
}

public double getPay(){
    return payType.getPay(this.salary);
}
~~~
And if there is any new bonus pays:
~~~Java
class Bonus20Per implements Pay{
    @Override
    public double getPay(double salary){
        return salary + (salary * .20);
    }
}
~~~
# Guard Claus Code
Guard clause eliminates the use of if-else statements and in doing so show clearly the path of execution. If we think a condition is just as likely to occur as another (think 50/50 odds) then we wouldn't use guard clause.
~~~Java
//from polymorphism.md
bagFees = 0;
for (int theBag = 0; theBag < numberOfBags; theBag++){
    if(bagWeight[theBag] < 50) { bagFees += bagUnder50lbs(theBag); }
    if(bagWeight[theBag] <70 && bagWeight[theBag] >= 50){ bagFees += bag50To70lbs(theBag); }
    if (bagWeight[theBag] >= 70) { bagFees += 200; }
}
~~~
This is what guard clause normally looks like
~~~Java
//guard clause example
int getBagPrice(double[] weight, int theBag){
    if (weight[theBag] < 50) return bagUnder50lbs(theBag);
    if (weight[theBag] < 70 && weight[theBag] >= 50) return bag50To70lbs(theBag);
    if (weight[theBag] >= 70) return 200;
    return 200;
}
~~~
