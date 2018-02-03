package esthergoldstein.assignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.net.Proxy.Type.HTTP;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    public void startAsyncTask(View sender){
        String text;
        EditText et = (EditText) findViewById(R.id.editText);
        text = et.getText().toString();
        RetrieveResultsTask resultRetriever = new RetrieveResultsTask();
        resultRetriever.execute(text.toUpperCase());
    }
}
