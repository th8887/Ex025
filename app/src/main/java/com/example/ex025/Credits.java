package com.example.ex025;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Tahel Hazan <th8887@bs.amalnet.k12.il>
 * @version 1.1.6
 * @since 11.12.2020
 * The credit page shows the author and ways to contact him in case something doesn't work.
 */
public class Credits extends AppCompatActivity {
    TextView credits;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        credits=(TextView) findViewById(R.id.credits);

        SharedPreferences settings= getSharedPreferences("stm", MODE_PRIVATE);
        SharedPreferences.Editor editor=settings.edit();
        String s = "Author: Tahel Hazan            Contact= th8887@bs.amalnet.k12.il        " +
                "    version 1.1.4        since 25.11.2020   contact if something doesn't work!";
        editor.putString("contact",s);
        editor.commit();
        credits.setText(settings.getString("contact",null));
    }

    /**
     * Btnp.
     *
     * @param item the item moves back to the buttons page.
     */
    public void btnp(MenuItem item) {
        finish();
    }

    /**
     * Cred.
     *
     * @param item the item Sends a Toast to let the user know he is in the current page             he chose from the OptionMenu.
     */
    public void cred(MenuItem item) {
        Toast.makeText(this, "You are already here :)", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}