# Building Composites Using Builder Pattern
~~~Java
//just code i guess
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Item{
    // using HashMap to store key value pairs
    private HashMap<String, String> itemInfoHM;
    private String itemName = "";

    //will store children of this Item in a tree
    private ArrayList<Item> children = new ArrayList<Item>();
    
    public String getItemName(){ return itemName;}
    public void setItemName(String itemName){ this.itemName = itemName;}

    public Item(String itemName){
        super();

        setItemName(itemName);
        //create Item HashMap that stores Item info
        itemInfoHM = new HashMap<String, String>(20);
    }
~~~
~~~Java
    //Used to add children nodes
    public void add(Item childNote){ children.add(childNode);}
    public void addItemInformation(String infoName, String info){
        itemInfoHM.put(infoName, info);
    }

    public String getItemInformation(String infoName){
        return itemInfoHM.get(infoName);
    }

    public String toString(){
        String itemInformation = "\n" + itemName + " ";
        //if itme info is available get it
        if (!itemInfoHm.isEmpty()){
            itemInformation += displayProductInfo();
        }
        Iterator<Item> it = children.iterator();
        //attach all children for this item
        while (it.hasNext()){
            Item node = (Item)it.next();
            itemInformation += node.toString();
        }
        return itemInformation;
    }
~~~
~~~Java
    public String displayProductInfo(){
        String productInfo = "";
        //cycle through every key, value pair and return them
        //entrySet() returns a Set that contains the Map entries
        for (Map.Entry<String, String> entry : itemInfoHM.entrySet()){
            productInfo += entry.getKey() + ": " + entry.getValue() + " ";
        }
        return productInfo;
    }

    public static void main(String[] args){
        //create an item that stores all others, or the Root
        ItemBuilder products = new ItemBuilder("Products");

        //Add children and their info
        products.addChild("Produce");
        products.addChild("Orange");
        products.addItemInformation("Price", "$1.00");
        products.addItemInformation("In Stock", "100");

        //add siblings
        products.addSibling("Apple");
        products.addSibling("Grapes");

        //change the current item to the root of the tree
        products.editThisItem("Products");

        products.addChild("Cereal");
        products.addChild("Special K");
        products.addItemInformation("Price", "$4.00");
        products.addSibling("Raisin Bran");
        products.addItemInformation("Price", "5.00");

        product.editThisItem("Apple");
        products.addItemInformation("Price", "$.25");
        products.addSibling("Peach");
        products.editThisItem("Cereal");
        products.addChild("Fiber One");
        products.addItemInformation("Price", "$3.99");

        products.displayAllItems();
        //print information on just the cereal item and its children
        System.out.println("\n" + products.getItemByName("Cereal"));
    }
}
~~~
~~~Java
class ItemBuilder{
    //holds all of the items created
    ArrayList<Item> items = new ArrayList<Item>();

    //storesthe root and parent item objects for the 
    //current item you are workingwith so you can
    //add siblings and children based on location in 
    //the tree structure

    private Item root;
    private Item current;
    private Item parent;

    public ItemBuilder(String rootName){
        root = new Item(rootName);

        addItemToArrayList(root);

        current = root;
        parent = root;

        //store the parent for the item object
        root.addItemInformation("Parent", parent.getItemName());
    }

    //allows me to store item information
    public void addItemInformation(String name, String value){
        current.addItemInformation(name, value);
    }

    //adds a cild item to the current parent item
    public void addChild(String child){
        Item childNode = new Item(child);
        addItemToArrayList(childNode);
        current.add(childNode);
        parent = current;
        current = childNode;

        //store the parent for the item object
        childNode.addItemInformation("Parent", parent.getItemName());
    }

    //adds a sibling item to the store item stored in current
    public void addSibling(String sibling){
        Item siblingNode = new Item(sibling);
        addItemToArrayList(siblingNode);
        
        //adding a child node to the parent item
        parrent.add(siblingNode);
        current =  sibling.Node;

        //store the parent for the item object
        siblingNode.addItemInformation("Parent", parent.getItemName());
    }

    //adds item objects to ArrayList
    public void addItemToArrayList(Item newItem){
        items.add(newItem);
    }

    public String toString(){
        return root.toString();
    }

    public void displayAllItems(){
        for(Item item : items){
            System.our.println(item.getItemName() + ": " + item.displayProductInfo());
        }
    }

    //changes the current item object that is being used
    public void editThisItem(String itemName){
        for(Item item : items){
            if(item.getItemName().equals(itemName)){
                current = item;

                //gets the name of the stored parent object
                //and passes it so that parent can be set
                //as the parent in the ItemBuilder
                setItemsParent(current.getItemInformation("Parent"));
            }
        }
    }

    //sets the parent item for itembuilder
    public void setItemsParent(String parentItem){
        for(Item item : items){
            if(item.getItemName().equals(parentItem)){
                parent = item;
            }
        }
    }

    //returns the item based on the name passed into the method

    public Item getItemByNam(String itemToGet){
        Item itemToReturn = null;
        for(Item item : items){
            if(item.getItemName().equals(itemToGet)){
                itemToReturn = item;
            }
        }
        return itemToReturn;
    }
}
~~~
