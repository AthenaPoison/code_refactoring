# Descriptive variables for understandable code
Do this for a less stressful future

// Product.java
public class Product{
    private String name = "";
    private double price = 0.0;
    private double shippingCost = 0.0;
    private int quantity = 0;

    public String getName(){ return name; }
    public double getPrice(){ return price; }
    public double getShippingCost(){ return shippingCoset; }
    public int getQuantity(){ return quantity; }

    Product(String name, double price, double shippingCost, int quatity){
        this.name = name;
        this.price = price;
        this.shippingCost = shippingCost;
        this.quantity = quantity;
    }

    public double getTotalCost(){
        double quantityDiscount = 0.0;

        if ((quantity > 50) || (quantity * price) > 500){
            quantityDiscount = .10;
        } else if ((quantity >25) || (quantity * price) > 100){
            quantityDiscount = .07;
        } else if ((quantity >= 10) || (quantity * price) > 50){
            quantityDiscount = .05;
        }

        double discount = ((quantity - 1) * quantityDiscount) * price;
        return (quantity * price) + (quantity * shippingCost) - discount;
    }
}

Study the getTotalCost method in the code above. The conditional statements read as follows: if the quantity of an object is more than 50 and costs more than 500, than the discount is at 10%. If it's more than 10 and costs more than 100, discount is 7% and so on. 

The expressions are a little hard to read and can be made more understandable by saving them into temporary variables that can be referred to.

//Using variables 
public double getTotalCost(){
    double quantityDiscount = 0.0;

    final boolean over50products = (quantity > 50) || ((quantity * price) > 500);
    final boolean over25products = (quantity > 25) || ((quantity * price) > 100);
    final boolean over10products = (quantity >= 10) || ((quantity * price) >50);

    if(over50products){
        quantityDiscount = .10;
    } else if(over25products){
        quantityDiscount = .07;
    } else if(over10products){
        quantityDiscount = .05;
    }

    double discount = ((quantity -1) * quantityDiscount) * price;
    return (quantity * price) + (quantity * shippingCost) - discount;
}

Another application for temporary variables is to use them for complicated calculations that cannot be extracted into methods.

//Store.java - original
import java.util.ArrayList;
public class Store{
    ppublic ArrayList<Product> theProducts = new ArrayList<Product>();

    public void addProduct(Product newProduct){
        theProducts.add(newProduct);
    }

    public void getCostOfProducts(){
        System.out.println("Total cost for " + product.getQuantity() + " " + product.getName() + "s is $" + product.getTotalCost());
        System.out.println("Cost per product " + product.getTotalCost() / product.getQuantity());
        System.out.println("Savings per product " + ((product.getPrice() + product.getShippingCost()) - (product.getTotalCost() / prodyct.getQuantity())) + "\n");
    }

    public static void main(String[] args){
        Store cornerStore = new Store();
        cornerStore.addProduct(new Product("Pizza", 10.00, 1.00, 52));
        cornerStore.addProduct(new Product("Pizza", 10.00, 1.00, 26));
        cornerStore.addProduct(new Product("Pizza", 10.00, 1.00, 10));
        cornerStore.getCostOfProducts();
    }
}

We can refactor the code above to be shorter and more understandable

//Store.java -refactored; getCostOfProduct
public void getCostOfProducts(){
    for (Product product: theProducts){
        final int numOfProducts = product.getQuantity();
        final String prodNmae = product.getName();
        final double cost = product.getTotalCost();

        final double costWithDiscount = cost/numOfProducts;
        final double costWIthoutDiscount = product.getPrice() + product.getShippingCost();

        System.out.println("Total cost for " + numOfProducts + " " + prodName + "s is $" + cost);
        System.out.println("Cost per product " + cost / numOfProducts);
        System.out.println("Savings per product " + (costWithoutDiscount - costWithDiscount) + "\n");
    }
}

# Do not assign many values to a temp

Things can get confussing very quickly. 

//bad code 
double temp = totalCost/numberOfProducts; //Individual Product Cost

temp = temp + shipping; //Individual Product Cost + shipping
temp = temp - discount //Individual Product Cost + shipping - discount

Also don't assign values to paramters passed in methods

//bad code
public double getTotPrice(double quantity, double price, doubel shippingCost, double discount){
    price = price + shippingCost;
    price = price * quantity;
    return price - discount;
}

Instead use more descriptive variable names

//good code for both examples above
double indivProductCost = totalCost/numberOfProducts;
double prodCostAndShipping = indivProductCost + shipping;
double discountedProductCost = prodCostAndShipping - discount;

public double getTotPrice (doube quantity, double price, double shippingCost, double discount){
    double prodCostAndShipping = price + shippingCost;
    double totalProdCostAndShipping = prodCostAndShipping * quantity;
    return totalProdCostAndShipping - discount;
}