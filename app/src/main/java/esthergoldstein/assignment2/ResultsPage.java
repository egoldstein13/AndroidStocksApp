package esthergoldstein.assignment2;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.transform.Result;

/**
 * Created by esthergoldstein on 2/3/18.
 */
public class ResultsPage extends AppCompatActivity {

    static ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page);
        listView = (ListView) findViewById(R.id.listview);
    }

    public static class RetrieveResultsTask extends AsyncTask<String, Integer, ArrayList<HashMap<String, String>>>{
        private Context context;
        private ProgressDialog dialog;

        public RetrieveResultsTask(Context context){
            this.context = context.getApplicationContext();
            dialog = new ProgressDialog(context);
        }

        @Override
        protected void onPreExecute(){
            this.dialog.setMessage("Processing");
            this.dialog.show();

        }

        @Override
        protected ArrayList<HashMap<String, String>> doInBackground(String... params) {

            String fullUrl = "http://utdallas.edu/~John.Cole/2017Spring/" + params[0] + ".txt";
            ArrayList<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();
            try {
                URL url = new URL(fullUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(4500);
                connection.setRequestProperty("User_Agent", "myapp"); //Change to actual name
                connection.connect();
                if (connection.getResponseCode() == 200) {
                    // Read the response...
                } else {
                    return null;
                }
                InputStream responseInputStream = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(responseInputStream, "UTF-8");
                BufferedReader reader = new BufferedReader(isr);

                for (String line = null; (line = reader.readLine()) != null; ) {
                    String[] cols = line.split(",");
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("Date", cols[0]);
                    map.put("Open", cols[1]);
                    map.put("High", cols[2]);
                    map.put("Low", cols[3]);
                    map.put("Close", cols[4]);
                    map.put("Volume", cols[5]);
                    map.put("Adj Close", cols[6]);
                    results.add(map);
                }
                return results;
                //close streams
            }
            catch(Exception e){
                Log.e("Error", e.getMessage());
                e.printStackTrace();

            }
            //to-do:return results
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<HashMap<String, String>> strings) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            if (strings == null) {
                Intent intent = new Intent(context, HomePage.class);
                intent.putExtra("404", "404");
                context.startActivity(intent);
            } else {

                StableArrayAdapter adapter = new StableArrayAdapter(context, android.R.layout.simple_list_item_1, strings);
                listView.setAdapter(adapter);
            }

        }
    }
}
