# Replacing tree structure of primitives with composite pattern

When you model a tree structure, it is either a list of info as primitives and then searched through for what you want or onjects that represent each level of the tree with the composite pattern.

//this is an interface for an abstract class. Abstract is used to not force subclasses to create methods inside of it

public abstract class ProductComponent{
    void add(ProductComponents newProductComponent){}

    void remove(ProductComponets newProductComponet){}

    ProductCompent getProductComponent(int componentIndex){ return null;}

    String getProductGroupName(){ return null;}

    //this is the one we want implemented in all subclasses
    abstract void displayProductInfo();
}

//ProductGroup.java
import java.util.ArrayList;
import java.util.Iterator;

public class ProductGroup extends ProducComponent{
    //each group and all the products contained in that group 
    //are stored in this ArrayList
    ArrayList<ProducComponent> productComponents = new ArrayList<ProductComponent>();

    private String prodyctGroupName;

    public ProductGroup(String productGroupName){
        this.productGroupName = productGroupName;
    }

    public void add(ProductComponent newProductComponent){
        productComponents.add(newProductComponent);
    }

    public void remove(ProductComponent newProductComponent){
        productComponents.remove(newProductComponent);
    }

    public ProductComponent getProductComponent(int componentIndex){
        return (ProductComponent) productComponents.get(componentIndex);
    }

    public String getProductGroupName(){
        return productGroupName;
    }

    @Override
    void displayProductInfo(){
        System.out.println(getProductGroupName());
        Iterator<ProductComponent> productIterator = productComponents.iterator();

        while(productIterator.hasNext()){
            ProductComponent productInfo = (ProductComponent) productIterator.next();
            productInfo.displayProductInfo();
        }
        System.out.println();
    }
}

This will represent a leaf in the product group

//Product.java
public class Produc extends ProductComponent{
    private String productName;
    private double productPrice;

    public Product(String productName, double productPrice){
        super();
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getProductName(){ return productName;}

    public void setProductName(String productName){
        this.productName = productName;
    }

    public double getProductPrice(){ return productPrice;}

    public void setProductPrice(double productPrice){
        this.productPrice = productPrice;
    }

    void displayProductInfo(){
        System.out.println(getProductName() + " $"+getProductPrice());
    }
}

//Test with ProductSystem.java
public class ProductSystem{
    public static void main(String[] args){
        ProductComponent produce = new ProductGroup("Produce);
        ProductComponent cereal = new ProductGroup("Cereal");

        //top level compenent that contains product and product groups
        ProductComponent everyProduct = new ProductGroup("All Products\n");

        everyProduct.add(produce);
        evryProduct.add(cereal);

        produce.add(new Product("Apple", 0.5));
        produce.add(new Produce("Watermelon", 5.00));
        product.add(new Product("Potato", .35));

        cereal.add(new Product("Special K", 12.20));
        cereal.add(new Produce("Sour Pop", 9.10));
        pcereal.add(new Product("Raisin Bran", 8.35));

        everyPorduct.displayProductInfo();
    }
}