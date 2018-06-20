package com.example.razli.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    String languageSelected;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.razli.myapplication", Context.MODE_PRIVATE);

        textView = findViewById(R.id.textView);
        languageSelected = "";

        // Allow user to select language preference when first opening the app
        showLanguageSelectDialog();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbar_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.settings:
                showLanguageSelectDialog();
                break;
            case R.id.help:
                Toast.makeText(this, "No help is coming :(", Toast.LENGTH_SHORT).show();
                break;
            default: return false;
        }

        return true;
    }

    public void toggleLanguage(String languageSelected) {

        switch (languageSelected) {
            case "english": textView.setText("Welcome!");
                break;
            case "malay": textView.setText("Selamat Datang!");
                break;
            default: textView.setText("Error! No language selected");
                break;
        }
    }

    public void showLanguageSelectDialog() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Language preference")
                .setMessage("Which language would you prefer?")
                .setPositiveButton("English", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        languageSelected = "english";
                        toggleLanguage(languageSelected);
                    }
                })
                .setNegativeButton("Malay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        languageSelected = "malay";
                        toggleLanguage(languageSelected);
                    }
                })
                .show();
    }
}


