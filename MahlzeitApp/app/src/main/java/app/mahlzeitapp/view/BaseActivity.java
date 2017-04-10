package app.mahlzeitapp.view;
/**
 * Created by Bianca on 10.04.2017.
 */

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

import app.mahlzeitapp.R;


public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.home:
                showHomeView();
                return true;
            case R.id.favorite:
                showFavoritePersonsView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showHomeView() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void showFavoritePersonsView() {
        Intent intent = new Intent(this, FavoritePersonsActivity.class);
        startActivity(intent);
    }
}
