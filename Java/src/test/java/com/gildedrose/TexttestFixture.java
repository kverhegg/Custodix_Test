package com.gildedrose;

import java.util.Scanner;

public class TexttestFixture {

    public static void main(String[] args) {
        System.out.println("A refactored solution brought to you by Dr. Kenneth Verheggen !");

        Item[] items = new Item[]{
            new Item("+5 Dexterity Vest", 10, 20), //
            new Item("Aged Brie", 2, 0), //
            new Item("Elixir of the Mongoose", 5, 7), //
            new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            // this conjured item does not work properly yet

            /**
             * NOTE What exactly was "wrong" with this item? Need more
             * information to resolve "bugs", a description of the problem would
             * be helpful :D
             *
             */
            new Item("Conjured Mana Cake", 3, 6)};

        GildedRose app = new GildedRose();
        app.addAll(items);

        int days = 2;
        if (args.length > 0) {
            /**
             * NOTE I know this is probably out of scope of the test, but this
             * can crash if the user inputs anything but an integer. It also
             * requires you to restart the command every time, could replace it
             * with an input scanner but ... yeah I'm probably going overboard
             * already
             */
            try {
                days = Integer.parseInt(args[0]) + 1;
            } catch (NumberFormatException e) {
                //ignore the e is bad... it's basically saying you expect 
                //the user to mess up... But hey ! :D
                System.err.println("Please use only integer numbers");
            }
        }

        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println(app.toString());
            app.updateQuality();
        }

        
        /**
         * NOTE Decided to add this little pause at the end, might be useful
         * for anyone evaluating ;)
         */
        Scanner scan = new Scanner(System.in);
        System.out.println("Thank you for the fun exercise, have a nice day ! ");
        System.out.print("[ENTER TO CLOSE]");
        scan.nextLine();

    }

}
