package app.mahlzeitapp.view;
/**
 * Created by Bianca on 10.04.2017.
 */

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;

import app.mahlzeitapp.R;
import app.mahlzeitapp.model.Person;
import app.mahlzeitapp.presenter.MahlzeitServiceAPI;


public class BaseActivity extends AppCompatActivity {

    MahlzeitServiceAPI service = new MahlzeitServiceAPI(BaseActivity.this);
    Person user;

    public BaseActivity() throws MalformedURLException {
    }

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
            case R.id.launcher:
                try {
                    uploadContent();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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

    public void uploadContent() throws IOException, JSONException {
        user = MainActivity.user;
        service.addFavoritePerson(user, null, BaseActivity.this);
        service.updateGroupJoin(user, BaseActivity.this);
    }
}
