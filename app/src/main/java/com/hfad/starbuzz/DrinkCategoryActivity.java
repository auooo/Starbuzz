package com.hfad.starbuzz;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DrinkCategoryActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<Drink> listAdapter = new ArrayAdapter<Drink>(this,
                android.R.layout.simple_list_item_1, Drink.drinks);
        ListView listView = getListView();
        listView.setAdapter(listAdapter);
    }

    /*
        These are the same arguments that the onItemClick() method above has: the list view,
        the item view that was clicked, it's position in the list, and the row ID of the underlying
        data
      */
    @Override
    protected void onListItemClick(ListView listView, View itemView, int position, long id) {
        super.onListItemClick(listView, itemView, position, id);
        Intent intent = new Intent(DrinkCategoryActivity.this, DrinkActivity.class);
        // We're using a constant for the name of the extra information in the intent so that
        // we know DrinkCategoryActivity and DrinkActivity are using the same String. We'll add
        // the constant to DrinkActivity when we create the activity.
        intent.putExtra(DrinkActivity.EXTRA_DRINKNO, (int) id);
        startActivity(intent);
    }
}