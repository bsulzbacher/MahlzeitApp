package app.mahlzeitapp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import app.mahlzeitapp.R;


public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button button = (Button) findViewById(R.id.btn_login);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText personenkennziffer = (EditText) findViewById(R.id.edit_username);
                String txt = personenkennziffer.getText().toString();
                goToHomeView(txt);
            }
        });
    }

    public void goToHomeView(String personenkennziffer) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
