# Command Pattern
3.  Has an invoker that stores the 

//common way-wrong way to implement command pattern
//also example of when to use command pattern
class Customer{
    //to add new customer type, have to change customer logic for returnFinalBill
    private int age;
    private boolean man;
    private double bill;

    public int getAge(){ return age;}
    public void setAge(int age){ this.age = age;}
    public boolean isMan(){ return man;}
    public void setMan(boolean man){ this.man = man;}
    public double getBill(){ return bill;}
    public void setBill(double bill){ this.bill = bill;}

    public Customer(int age, boolean man, double bill){
        this.age = age;
        this.man = man;
        this.bill = bill;
    }

    //adding a new discount requires editing the class
    public void returnFinalBill(){
        double percentOff = 0.0;
        if(age>0){ percentOff += .05;}
        if(!man){ percentOff += .05;}

        //can't implement a new calculation device
        System.out.println("Bill Amount: $" + (bill - (bill*percentOgg)));
    }

    public static void main(String[] args){
        Customer billSmith = new Customer(62,true, 12);
        billSmith.returnFinalBill();
    }
}

The first part of the command pattern is the interface. From the example above, this interface will be used to represent the different types of BillPayers and the methods used by them

import java.util.ArrayList;
public interface BillPayer{
    public void calculateBill(double amountDue);
}

The second is the command interface which will represent the methods that will change based off of different BillPayers

interface Command{
    public void executeCalculateBill(double amountDue);
}

The BillPayers:

class WomanOver60 implements BillPayer{
    public void calculateBill(double amountDue){
        System.out.println("Bill Amount for Woman Over 60: " + (amountDue - (amountDue* .10));
    }
}

class ManOver60 implements BillPayer{
    public void calculateBill(double amountDue){
        System.out.println("Bill Amount for Man Over 60: " + (amountDue - (amountDue* .05));
    }
}

class ManUnder60 implements BillPayer{
    public void calculateBill(double amountDue){
        System.out.println("Bill Amount for Man Under 60: " + amountDue;
    }
}

This will call the executeCalculateBill method based on the BillPayer type

class Waiter implements Command{
    BillPayer thePayer;

    Waiter(BillPayer thePayer){
        this.thePayer = thePayer;
    }

    public void executeCalculationBill(double amountDue){
        thePayer.calculateBill(amountDue);
    }
}

The invoker: Uses returnFinalBill and executes the right executeCalculateBill() based on the object stored in the Command. 
The Invoker can accept numerous command tyeps and then execute different methods based on the command type,

class CashRegister{
    Command theCommand;
    CashRegister(Command newCommand){
        theCommand = newCommand;
    }

    public void returnFinalBill(double amountDue){
        theCommand.executeCalculateBill(amountDue);
    }
}

Returns the right BillPayer object based on the method called
If a new BillPayer is added, just update this and create a new BillPayer class

class CustomerTypePicker{
    public static BillPayer getWomanOver60{
        return new WomanOver60();
    }

    public static BillPayer getManOver60(){
        return new ManOver60();
    }

    public static BillPayer getManUnder60(){
        return new ManUnder60();
    }
}

class UseCashRegister{
    public static void main(String[] args){
        //get the customer for bill calculation
        BillPayer sallyMay = CustomerTypePicker.getWomanOver60();

        //The waiter sets the customer tyype so that the right executeCalculateBill method is called
        Waiter theWaiter  = new Waiter(sallyMay);

        //the invoker makes sure the right method is called and 
        //stores the waiter so BillPayers assigned to Waiter are available

        CashRegister calculateBill = new CashRegister(theWaiter);

        //when returnFinalBill is called that signals that the Waiter
        //stored in CashRegister should execute method executeCalculateBill

        calculateBill.returnFinalBill(12.00);
    }
}

Group BillPayers into an arrayList so now you can use these billpayers as simple commands

class CustomerGroup{
    ArrayList<BillPayer> customers;

    CustomerGroup{
        customer = new ArrayList<BillPayer>(); 
    }

    public void add(BillPayer newPayer){
        customer.add(newPayer);
    }

    public BillPayer get(int customerIndex){
        return customers.get(customerIndex);
    }
}

//edit UseCashRegister
class UseCashRegister{
    public static void main(String[] args){
        //....

        CustomerGroup custGroup = new CustomerGroup();
        custGroup.add(CustomerTypePicker.getWomanOver0());
        custGroup.get(0).calculateBill(12.00);
    }
}