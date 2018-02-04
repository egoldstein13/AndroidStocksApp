package esthergoldstein.assignment2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.net.Proxy.Type.HTTP;

public class HomePage extends AppCompatActivity {

    private Button displayButton;
    private EditText userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        displayButton = (Button)findViewById(R.id.button);
        userInput = (EditText)findViewById(R.id.editText);
        displayButton.setEnabled(false);
        userInput.addTextChangedListener(inputWatcher);
        Intent iSent = getIntent();
        if(iSent != null && iSent.hasExtra("404")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("404 Not Found")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //do things
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    public void startAsyncTask(View sender){
        String text;
        text = userInput.getText().toString();
        ResultsPage.RetrieveResultsTask resultRetriever = new ResultsPage.RetrieveResultsTask(this);
        resultRetriever.execute(text.toUpperCase());

    }
    private final TextWatcher inputWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public void afterTextChanged(Editable s) {
            displayButton.setEnabled(userInput.getText().length() > 0);
        }
    };
}
