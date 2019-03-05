package com.gildedrose;

import java.util.Objects;

/**
 * NOTE I added a convencience method to check for names, however depending on
 * the actual implementation this should become a proper data structure where an
 * item identifier is linked to a database and checks happen on those unique
 * keys. Checking Strings is not very efficient. I also generated a basic equals
 * and hashcode method. (See comment in the GildedRose class)
 *
 *
 * @author kenneth.verheggen
 */
public class Item {

    /**
     * The item name
     */
    public String name;

    /**
     * The sell in value (add javadoc)
     */
    public int sellIn;

    /**
     * The quality of the item
     */
    public int quality;

    /**
     * Basic item constructor
     *
     * @param name the name of the item
     * @param sellIn the sell in value of the item
     * @param quality the quality of the item
     * 
     * 
     */
    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    /**
     *
     * @param names a comma separated list of names to check
     * @return a boolean indicating this item matches one of the names (ignoring
     * the casing)
     */
    public boolean hasName(String... names) {
        for (String name : names) {
            if (this.name.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.name + "\t" + this.sellIn + "\t" + this.quality;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

}
