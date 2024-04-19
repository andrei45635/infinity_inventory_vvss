
package inventory.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    
    // Declare fields
    private ObservableList<Product> products;
    private ObservableList<Part> parts;
    private int autoPartId;
    private int autoProductId;
    public Inventory(){
        this.products = FXCollections.observableArrayList();
        this.parts = FXCollections.observableArrayList();
        this.autoProductId=0;
        this.autoPartId=0;
    }

    // Declare methods
    /**
     * Add new product to observable list products
     * @param product 
     */
    public void addProduct(Product product) {
        products.add(product);
    }
    
    /**
     * Remove product from observable list products
     * @param product 
     */
    public void removeProduct(Product product) {
        products.remove(product);
    }

    /**
     * Accepts search parameter and if an ID or name matches input, that product is returned
     * @param searchItem
     * @return
     */
    public Product lookupProduct(String searchItem) {
        boolean isFound = false;
        boolean isIdMatch = false;
        Product foundProduct = null;

        for (Product p : products) {
            if (p.getName().contains(searchItem)) {
                if (p.getInStock() > 0) {
                    foundProduct = p;
                    isFound = true;
                    break;
                }
            }
            if ((p.getProductId() + "").equals(searchItem)) {
                isIdMatch = true;
                if (!isFound) {
                    foundProduct = p;
                }
            }
        }

        if (!isFound && !isIdMatch) {
            return new Product(0, null, 0.0, 0, 0, 0, null);
        } else if (isFound && foundProduct.getInStock() <= 0) {
            return new Product(foundProduct.getProductId(), foundProduct.getName(), 0.0, 0, 0, 0, foundProduct.getAssociatedParts());
        }

        return foundProduct;
    }


    /**
     * Update product at given index
     * @param index
     * @param product 
     */
    public void updateProduct(int index, Product product) {
        products.set(index, product);
    }
    
    /**
     * Getter for Product Observable List
     * @return 
     */
    public ObservableList<Product> getProducts() {
        return products;
    }

    public void setProducts(ObservableList<Product> list) {
        products=list;
    }
    
    /**
     * Add new part to observable list allParts
     * @param part 
     */
    public void addPart(Part part) {
        parts.add(part);
    }
    
    /**
     * Removes part passed as parameter from allParts
     * @param part 
     */
    public void deletePart(Part part) {
        parts.remove(part);
    }
    
    /**
     * Accepts search parameter and if an ID or name matches input, that part is returned
     * @param searchItem
     * @return 
     */
    public Part lookupPart(String searchItem) {
        for(Part p: parts) {
            if(p.getName().contains(searchItem) || (p.getPartId()+"").equals(searchItem)) {
                return p;
            }
        }
        return null;
    }
    
    /**
     * Update part at given index
     * @param index
     * @param part 
     */
    public void updatePart(int index, Part part) {
        parts.set(index, part);
    }
    
    /**
     * Getter for allParts Observable List
     * @return 
     */
    public ObservableList<Part> getParts() {
        return parts;
    }

    /**
     *
     * @param list
     */
    public void setParts(ObservableList<Part> list) {
        parts =list;
    }
    
    /**
     * Method for incrementing part ID to be used to automatically
     * assign ID numbers to parts
     * @return 
     */
    public int getAutoPartId() {
        autoPartId++;
        return autoPartId;
    }
    
    /**
     * Method for incrementing product ID to be used to automatically
     * assign ID numbers to products
     * @return 
     */
    public int getAutoProductId() {
        autoProductId++;
        return autoProductId;
    }


    public void setAutoPartId(int id){
        autoPartId=id;
    }

    public void setAutoProductId(int id){
        autoProductId=id;
    }
    
}
