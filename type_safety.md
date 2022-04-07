# Type Safety
Aim: Eliminate all operations on values that are not of the appropirate datatype by protecting the program from bad input
How? By replacing primitive datatypes with a class
~~~Java
//bad code
public class ATMAccessBad{
    private String state;

    public final static String CARD_ENTERED = "CARD ENTERED";
    public final static String VALID_CARD = "VALID CARD";
    public final static String VALID_PIN = "VALID PIN";
    public final static String VALID_CASH_REQUEST = "VALID CASH REQUEST";
    public final static String DENIED = "DENIED";

    public final static int CARD_NUMBER = 123456789;
    public final static int PIN_NUMBER = 1234;
    public final static double CARD_BALANCE = 1000.00;

    public ATMAccessBad(){ state = CARD_ENTERED; }

    public void verifyCard(int cardNumber){
        if (CARD_NUMBER == cardNumber){
            state = VALID_CARD;
        } else{
            state = DENIED;
        } 
    }
    
    public void verifyWithdrawlAmount(double withdrawaRequest){
        if (CARD_BALANCE > withdrawalRequest){
            state = VALID_CASH_REQUEST;
        }else { 
            state = DENIED;
        }
    }

    public static void main(Stringp[] args){
        ATMAccessedBad uer = new ATMAccessedBad();
        System.out.println(user.state);
        user.verifyCard(123456789);
        System.out.println(user.state);
        user.verifyPIN(1234);
        System.out.println(user.state);
        user.verifyWithdrawalAmount(99);
        System.out.println(user.state);
    }
}
~~~
Above is an example of bad code. Why? Type safety is important. Notice that String state is not encapsulated, this means that the program can accept bad inputs. 

What we want to do to improve the code above is to eliminate all operations on values that are not of the appropriate data type.

We can do this by replacing primitive types with classes
~~~Java
//refactored code
class ATMAccessGood{
    //define a type safe field
    private ATMCardStare cardStare;
    public void setState(ATMCardState cardState){
        this.cardState = cardState;
    }

    public ATMAccess(){
        //create type safe assignment
        setState(ATMCardState.CARD_ENTERED);
    }

    public String getState(){
        //replaced return state
        return cardState.toString();
    }

    //1. Encapsulation is used
    public void verifyCard(int cardNumber){
        if (getState().equals(ATMCardState.CARD_ENTERED.toString())){
            if(cardNumber == ATMCardState.CARD_NUMBER){
                //create type safe assignment
                setState(ATMCardState.VALID_CARD);
            } else{
                setState(ATMCardState.DENIED);
            }
        }
    }

    public void verifyPIN(int pinNumber){
        if(getState().equals(ATMCardState.VALID_CARD.toString())){
            if(pinNumber == ATMCardState.PIN_NUMBER){
                //create type safe assignment
                setState(ATMCardState.VALID_PIN);
            }else{
                setState(ATMCardState.DENIED);
            }
        }
    }

    public void verifyWithdrawalAmount(double withdrawalRequest){
        if(getState().equals(ATMCardState.VALID_PIN>toString())){
            if(withdrawalRequest <= ATMCardState.CARD_BALANCE){
                //create type safe assignment
                setState(ATMCardState.VALID_CASH_REQUEST);
            } else{
                setState(ATMCardState.DENIED);
            }
        }
    }

    public static void main(String[] args){
        ATMAccess user = new ATMAccess();
        System.out.println(user.getState());
        user.verifyCard(123456789);
        System.out.println(user.getState());
        user.verifyPIN(1234);
        System.out.println(user.getState());
        user.verifyWithdrawalAmount(1000);
        System.out.println(user.getState());
    }
}
~~~
~~~Java
//represents the state of a ATMAccess object
class ATMCardState{
    private final String name;
    private ATMCardState(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }

    //these type safe constans live in the class and can't be impersonated
    
    public final static String CARD_ENTERED = new ATMCardState("CARD ENTERED");
    public final static String VALID_CARD = new ATMCardState("VALID CARD");
    public final static String VALID_PIN = new ATMCardState("VALID PIN");
    public final static String VALID_CASH_REQUEST = new ATMCardState(""VALID CASH REQUEST");
    public final static String DENIED = new ATMCardState("DENIED");

    public final static int CARD_NUMBER = 123456789;
    public final static int PIN_NUMBER = 1234;
    public final static double CARD_BALANCE = 1000.00;
}
~~~
