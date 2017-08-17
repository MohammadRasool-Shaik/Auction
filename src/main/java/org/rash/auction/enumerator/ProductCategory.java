package org.rash.auction.enumerator;

public enum ProductCategory {

    CLOTHING("Clothing"), FURNITURE("Furniture"), STATIONARY("Stationary");

    private String key;

    ProductCategory(String key) {
        this.key = key;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }


}
