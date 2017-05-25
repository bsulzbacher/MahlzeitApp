package app.mahlzeitapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.net.MalformedURLException;

import app.mahlzeitapp.R;
import app.mahlzeitapp.model.Person;
import app.mahlzeitapp.presenter.MahlzeitServiceAPI;

public class MenuActivity extends BaseActivity {

    MahlzeitServiceAPI service = new MahlzeitServiceAPI(MenuActivity.this);
    Person user;

    public MenuActivity() throws MalformedURLException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        //-------------------------------------------------------------------









        //-------------------------------------------------------------------
    }

}
