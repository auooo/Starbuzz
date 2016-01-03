package com.hfad.starbuzz;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class DrinkCategoryActivity extends ListActivity {
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new UpdateDrinkCategory().execute();
//        ListView listDrinks = getListView();
//
//        try {
//            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
//            db = starbuzzDatabaseHelper.getReadableDatabase();
//
//            cursor = db.query("DRINK",
//                    new String[]{"_id", "NAME"},
//                    null, null, null, null, null);
//            CursorAdapter listAdapter = new SimpleCursorAdapter(this,
//                    android.R.layout.simple_list_item_1,
//                    cursor,
//                    new String[]{"NAME"},
//                    new int[]{android.R.id.text1},
//                    0);
//            listDrinks.setAdapter(listAdapter);
//        } catch (SQLiteException e) {
//            Toast toast = Toast.makeText(this, "Database unavailable in DrinkCategoryActivity",
//                    Toast.LENGTH_SHORT);
//            toast.show();
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }


    @Override
    protected void onListItemClick(ListView listView, View itemView, int position, long id) {
        // These are the same arguments that the onItemClick() method above has:
        // the list view,the item view that was clicked, it's position in the list,
        // and the row ID of the underlying data
        super.onListItemClick(listView, itemView, position, id);
        Intent intent = new Intent(DrinkCategoryActivity.this, DrinkActivity.class);
        // We're using a constant for the name of the extra information in the intent so that
        // we know DrinkCategoryActivity and DrinkActivity are using the same String. We'll add
        // the constant to DrinkActivity when we create the activity.
        intent.putExtra(DrinkActivity.EXTRA_DRINKNO, (int) id);
        startActivity(intent);
    }

    // add the async thread
    private class UpdateDrinkCategory extends AsyncTask<Void, Void, Boolean> {
        ListView listDrinks;
        ListAdapter listAdapter;

        @Override
        protected void onPreExecute() {
            Log.d("onPreExecute", "onPreExecute");
            listDrinks = getListView();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
//                int i = 10 / 0;
                SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(
                        DrinkCategoryActivity.this);
                db = starbuzzDatabaseHelper.getReadableDatabase();
                cursor = db.query("DRINK",
                        new String[]{"_id", "NAME"}, null, null, null, null, null);
                return true;
            } catch (/*SQLiteException*/Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                listAdapter = new SimpleCursorAdapter(DrinkCategoryActivity.this,
                        android.R.layout.simple_list_item_1,
                        cursor,
                        new String[]{"NAME"},
                        new int[]{android.R.id.text1}, 0);
                listDrinks.setAdapter(listAdapter);
            } else {
                Toast toast = Toast.makeText(DrinkCategoryActivity.this,
                        "Unavailable database", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}