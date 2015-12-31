package com.hfad.starbuzz;

/**
 * Created by Hong on 2015/12/23.
 */
public class Drink {
    /*
        Each drink has a name, description, and image resource ID. The image resource ID refers to
        drink images we'll add to the project on the next page.
     */
    private String name;
    private String description;
    private int imageResourceId;

    // drinks is an array of drinks
    public static final Drink[] drinks = {
            /*
                drinks is an array of three Drinks
             */
            new Drink("Latte", "A couple of espresso shots with steamed milk", R.drawable.latte),
            new Drink("Cappuccino", "Espresso, hot milk, and a teamed milk foam", R.drawable.cappuccino),
            new Drink("Filter", "Highest quality beans roasted and brewed fresh", R.drawable.filter)
    };

    // Each drink has a name, description, and an image resource
    // The Drink Constructor
    private Drink(String name, String description, int imageResourceId) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    /*
        These are getters for the private variables.
     */
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    /*
        The String representation of a drink is its name
     */
    @Override
    public String toString() {
        return this.name;
    }
}
