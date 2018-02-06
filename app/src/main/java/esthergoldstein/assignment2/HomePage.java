/** ***************************************************************************
 * Stock Information Program.
 *
 * This program displays stock information (Date, Open, High, Low, Close, Volume,
 * Adjusted close). It operates by providing a textbox for the user to enter a
 * a stock's ticker symbol. It then returns a listview of the stock information
 * for the user to view.
 *
 * If the user enters an invalid or an unavailable stock ticker symbol,
 * a 404 not found popup is displayed and then the program requests a different
 * symbol from the user.
 *
 * The files that contain the stock information are available at:
 * http://utdallas.edu/~John.Cole/2017Spring/
 *
 * Written by Esther Goldstein and Neel Jathanna for CS 4301.003,
 * Assignment 2, starting February 3, 2018.
 * NetID: emg140230 and nsj140030
 *
 * Esther Goldstein: HomePage.java, ResultsPage.onPostExecute,
 *                   ResultsPage.displayDialogAfterTimeout,
 * Neel Jathanna: StableArrayAdapter.java, ResultsPage.doInBackground
 ******************************************************************************/

package esthergoldstein.assignment2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomePage extends AppCompatActivity {

    private Button displayButton;
    private EditText userInput;

    /**************************************************************************
     * Creates button, views, and text box. Sets up conditions for 404 when
     * a file is not found and for timeout when searching for the file takes
     * too long.
     **************************************************************************/
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
        else if(iSent != null && iSent.hasExtra("Timeout")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Session Timeout")
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
        Intent iSend = new Intent(this, ResultsPage.class);
        iSend.putExtra("text", text.toUpperCase());
        startActivity(iSend);

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
