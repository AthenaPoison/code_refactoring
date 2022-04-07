# Extracting Methods, Fields, Classes
~~~Java
//original code
public class Customer{
    private String firstName = "";
    private String lastName = "";
    private String street = "";
    private String city = "";
    private String state = "";
    private int postalCode = 0;
    private String birthDay = "";

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public int getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
    public String getBirthDay() {
        return birthDay;
    }
    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }
    public Customer(String firstName, String lastName, String street, String city, String state, int postalCode, String birthDay) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.birthDay = birthDay;
    }
}
~~~
The code above shows the class getting too big. But on closer examination, some of the information can be extracted into a separate class such as:
~~~Java
class Address{
    private String street = "";
    private String city = "";
    private String state = "";
    private int postalCode = 0;
    
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public int getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
~~~
~~~Java
    public Address(String street, String city, String state, int postalCode){
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }
}
~~~
//And change Customer class accordingly
~~~Java
public class Customer{
    //... remove street, city, state, postalCode
    private Address address = null;

    //... get rid of corresponding getters and setters
    public Customer(String firstName, String lastName, Address address, String birthDay){
        //...
        this.address = address;
        //...
    }
}
~~~
Functionally, the code works the same as before..
~~~Java
//To test
public static void main(String[] args){
    Address sallySmithAddress = new Address("123 Main St", "Perry", "Iowa", 42343);

    Customer sallySmith = new Customer("Sally", "Smith", sallySmithAddress, "12/2/12");

    System.out.println("Customer name: " + sallySmith.getFirstName() + " " + sallySmith.getLastName());
}
~~~
However the code can be hard to read due to all the accessor methods as seen in the print functions. One method to prevent this is to define string methods in our classes such as:
~~~Java
class Address{
    //same code above

    public String toString(){
        return getStreet() + " " + getCity()+ " " getState() + " " + getPostalCode();
    }
}
~~~
Which can be applied to the main method:
~~~Java
public static void main(String[] args){
    //same code

    System.out.println("Custoemr address: " + sallySmith.address);
}
~~~
# Custom objects
Early in development many fields are represented as primitives or strings. However custom objects might make more sense later on in development. For example in the customer class above, birthday would make more sense as an object class than a string
~~~Java
//before
public class Customer{
//...
    private String birthDay = "";

//...
}
~~~
~~~Java
//after 
public class Customer{
    //...
    private Birthday birthday = null;
    
    //...
    public Customer(String firstName, String lastName, Address address, Birthday birthDay){
        //...
        this.birthday = birthday;
        //...
}

class Birthday{
    private int day;
    private int month;
    private int year;

    public int getDay(){ return day;}
    public int getMonth(){ return month;}
    public int getYear(){ return year;}

    public void setDay(int day){
        this.day = day;
    }
    public void setMonth(int month){
        this.month = month;
    }
    public void setYear(int year){
        this.year = year;
    }

    public Birthday(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }
}
~~~
Custom objects have their advantges. You can implement string methods in the classes and return the individual components of the class rather than the whole (ie: date, month year rather than birthday)
~~~Java
class Birthday{
    //...
    public String getBirthDate(){
        return getDay() + " / " + getMonth() + "/ " + getYear();
    }

    public String toString(){
        return getDay() + " / " getMonth() + " / " getYear();
    }
}
~~~
