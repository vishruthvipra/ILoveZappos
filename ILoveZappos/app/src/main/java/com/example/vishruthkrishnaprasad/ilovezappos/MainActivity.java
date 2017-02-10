package com.example.vishruthkrishnaprasad.ilovezappos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.vishruthkrishnaprasad.ilovezappos.databinding.ActivityMainBinding;

/**
 * Created by vishruthkrishnaprasad on 31/1/17.
 */

// This is where the user enters a query in the search bar located on the toolbar


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    SearchView searchView;
    MenuItem myActionMenuItem;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // A custom styled toolbar is added at the top of the screen
        setSupportActionBar(activityMainBinding.toolbar);

        // No item searched to add to the cart
        activityMainBinding.fabPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "No product available", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        myActionMenuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                sharedPreferences.edit().putString("query", query).apply();

                Intent intent = new Intent(MainActivity.this, SearchResultsActivity.class);
                startActivity(intent);
                finish();
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }
}