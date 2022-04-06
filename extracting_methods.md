# Eliminate large accumulation by extractiong methods and collecting parameter

This code further refactors the code from composite_builder.md

The code below is an accumulation method which accumulates information into itemInformation. In this document, we split this big method into smaller methods. A collecting parameter, passed from one method to another, will be used instead to collect the required information.

//original code
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

//refactored code
public String toString(){
    StringBuffer itemInfo = new StringBuffer();
    addItemInfoAndChildren(itemInfo);
    return itemInfo.toString();
}

private void addItemInfoAndChildren(StringBuffer itemInfo){
    addItemInformation(itemInfo);
    addChildrenInformation(itemInfo);
}

private void addItemInformation(StringBuffer itemInfo){
    itemInfo.append("\n" + itemName + " ");
    //if itme info is available get it
    if (!itemInfoHm.isEmpty()){
        itemInfo.append(displayProductInfo());
    }
}

private void addChildrenInformation(StringBuffer itemInfo){
    Iterator<Item> it =children.iterator();
    while(it.hasNext()){
        Item node = (Item)it.next();
        itemInfo.append(node.toString());
    }
}

