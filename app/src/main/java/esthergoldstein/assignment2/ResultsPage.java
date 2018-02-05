package esthergoldstein.assignment2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by esthergoldstein on 2/3/18.
 */
public class ResultsPage extends AppCompatActivity {

    static ListView listView;
    static StableArrayAdapter adapter;
    static ArrayList<HashMap<String, String>> results;
    static Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page);
        results = new ArrayList<HashMap<String, String>>();
        Intent iSent = getIntent();
        String strText = "";
        if(iSent != null){
            strText = iSent.getStringExtra("text");
        }
        listView = (ListView) findViewById(R.id.listview);
        new RetrieveResultsTask(ResultsPage.this).execute(strText);
    }

    public static class RetrieveResultsTask extends AsyncTask<String, Integer, ArrayList<HashMap<String, String>>>{
        private Context context;
        private ProgressDialog dialog;

        public RetrieveResultsTask(Context context){
            this.context = context.getApplicationContext();
            dialog = new ProgressDialog(context);
        }

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Processing");
            this.dialog.show();
            handler = new Handler();
            handler.postDelayed(new Runnable(){
                public void run() {
                    if(handler!=null)
                        handler.removeCallbacksAndMessages(null);
                        displayDialogAfterTimeout(true);
                }
            }, 45000);
            super.onPreExecute();
        }

        @Override
        protected ArrayList<HashMap<String, String>> doInBackground(String... params) {

            String fullUrl = "http://utdallas.edu/~John.Cole/2017Spring/" + params[0] + ".txt";
            try {
                URL url = new URL(fullUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(1000);
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
                    map.put("AdjClose", cols[6]);
                    results.add(map);
                }
                return results;
            }
            catch(Exception e){
                Log.e("Error", e.getMessage());
                e.printStackTrace();

            }
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
                adapter = new StableArrayAdapter(context, R.layout.listview_format, results);
                listView.setAdapter(adapter);
            }

        }
        public void displayDialogAfterTimeout(Boolean result){
            cancel(result);
            Intent intent = new Intent(context, HomePage.class);
            intent.putExtra("Timeout", "Timeout");
            context.startActivity(intent);
        }
    }
}
