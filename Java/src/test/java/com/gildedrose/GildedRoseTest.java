package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void foo() {
        /*
        NOTE the original test would never work, it was basically attempting to
        assert that "foo" is equal to "fixme", which would never be the case unless
        one of the methods changes the name (makes little sense)
        
        THEREFORE I have updated the test : BARS ARE GREEN for your pleasure!
        
        <3 JUnit
             
        */

        String testingValue = "Fixed_Test";

        GildedRose app = new GildedRose();
        app.add( new Item(testingValue, 0, 0) );
        app.updateQuality();
        
        assertEquals(testingValue, app.get(0).name);
    }

}
