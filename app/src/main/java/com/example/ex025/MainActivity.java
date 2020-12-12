package com.example.ex025;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author Tahel Hazan <th8887@bs.amalnet.k12.il>
 * @version 1.1.6
 * @since 11.12.2020
 * The user can put information in it will be saved in the data, the user will
 * be able to see the history too.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * The History that was in the file.
     */
    TextView history;
    EditText type;
    String add="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        type=(EditText)findViewById(R.id.type);
        history=(TextView)findViewById(R.id.history);

        try {
            FileInputStream fis= openFileInput("ltm.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            String line = br.readLine();
            while (line != null) {
                sb.append(line+'\n');
                line = br.readLine();
            }
            add=sb.toString();
            history.setText(sb.toString());
            isr.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the information and adds it to the previous data.
     *ltm- Long Term Memory
     * @param view the view
     */
    public void save(View view) {
        add=add.concat(type.getText().toString());
        add= add.replace("\n"," ");
        try {
            FileOutputStream fos= openFileOutput("ltm.txt",MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(add);
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        history.setText(add);
        type.setText("");
    }

    /**
     * Deletes the information from the TextView and from the EditText.
     * NOT FROM THE FILE HIMSELF!
     *
     * @param view the view
     */
    public void reset(View view) {
        type.setText("");
        history.setText("");
    }

    /**
     * Exits the app and saves the data in a file.
     *
     * @param view the view
     */
    public void exit(View view) {
        add=add.concat(type.getText().toString());
        add= add.replace("\n"," ");
        try {
            FileOutputStream fos= openFileOutput("ltm.txt",MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(add);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finish();
    }

    /**
     * Btnp.
     *
     * @param item
     * The item Sends a Toast to let the user know he is in the current page
     * he chose from the OptionMenu.
     */
    public void btnp(MenuItem item) {
        Toast.makeText(this, "You are already here :)", Toast.LENGTH_SHORT).show();
    }

    /**
     * Cred.
     *
     * @param item
     * The item Moves from main activity to Credits.
     */
    public void cred(MenuItem item) {
        Intent c= new Intent(this,Credits.class);
        startActivity(c);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}