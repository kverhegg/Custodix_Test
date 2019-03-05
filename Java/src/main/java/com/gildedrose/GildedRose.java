package com.gildedrose;

import java.util.ArrayList;


/*
NOTE 

I decided to replace the item array and instead make the GildedRose class extend an arraylist
This is mostly for conceptual reasons, after analyzing the code I came to the conclusion that
this is intended to be an inventory of sorts, which should be expandible... An Item[] is not
suited for that purpose, unless the exact dimensions are known beforehand. I found this 
solution a little bit more elegant, as it is trivial to implement a " dynamic inventory size".

NOTE : In a real production situation, this would need a proper datastructure and a database
backend to store item identifiers, parameters, etc... In that event the arraylist could be
swapped out with a HashSet of items, avoiding duplicate item entries (stacked inventory)

 */
public class GildedRose extends ArrayList<Item> {

    //This class didn't have a serial version UID
    private static final long serialVersionUID = 4L;
    
    
    public GildedRose() {

    }

    /**
     * A convenience method that acccepts the array from the initial situation
     *
     * @param items the input items
     * @return a boolean indicating if the items were succesfully added.
     *
     */
    public boolean addAll(Item[] items) {
        if (items != null && items.length > 0) {
            for (Item item : items) {
                if (!add(item)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * NOTE
     * 
     * Updates the quality of the inventory (GildedRose) object, based on what
     * was happening in the original method. There was no further information on
     * a bug or anything, so I basically refactored this method and cleaned it up
     * instead...
     */
    public void updateQuality() {
        for (Item item : this) {

            //at this point there are no other options so we could just check if this item has a "new name" (which could be fitted in a database structure)
            if (!item.hasName(ItemVariables.BRIE, ItemVariables.PASSES, ItemVariables.SULFURAS)) {
                if (item.quality > 0) {
                    item.quality--;
                }

                //so if there is a new name and its quality is above 50
            } else if (item.quality < 50) {

                /*
                NOTE
                
                theoretically you should try to minimize the amount of nested if loops the code will run through
                in this case, if the value is larger than 11 there's no point in checking the second 
                condition... Depending on how many checks you would need to do, this should be refactored in 
                a proper datastructure
                for example, if many conditions might come in play, a "condition pojo" could be made to hold 2 integers : a check and an effect
                which could be iterated in a loop and applied where necessary. This takes care of redundant if statements and makes for very
                clean code.
                
                Alternatively without the above : 
             
                 */
                int qualityDelta = 1; //add one either way

                qualityDelta += item.sellIn < 11 ? 1 : 0;
                qualityDelta += item.sellIn < 6 ? 1 : 0;

                item.quality += qualityDelta;
            }

            //this statement can be checked again if neccesary
            if (!item.hasName(ItemVariables.SULFURAS)) {
                item.sellIn--;
            }

            /*
            
            NOTE : I've noticed in the current setup that the above condition is the only
            place where the sellIn value gets decreased... This is indicative that only 
            the SULFURAS item can decrease in sellIn, meaning the next codeblock only 
            would apply to that. However, perhaps new items can be initialized with a 
            negative value, so I decided to keep it like this.
            
             */
            if (item.sellIn < 0) {

                if (!item.hasName(ItemVariables.BRIE)) {
                    if (!item.hasName(ItemVariables.PASSES)) {
                        if (!item.hasName(ItemVariables.SULFURAS) && item.quality > 0) {
                            item.quality--;
                        }
                    } else {
                        item.quality = 0;
                    }
                } else if (item.quality < 50) {
                    item.quality++;
                }

            }
        }
    }

    /**
     * Just for convenience sake while I'm awaiting more instructions ^-^
     *
     * @return a String representation of the GildedRose inventory
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("name\tsellIn\tquality").append(System.lineSeparator());
        if (size() > 0) {
            for (Item item : this) {
                builder.append(item).append(System.lineSeparator());
            }
        } else {
            builder.append("Empty");
        }
        builder.append(System.lineSeparator());
        return builder.toString();
    }

}
