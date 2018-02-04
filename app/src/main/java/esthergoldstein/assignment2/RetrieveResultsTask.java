package esthergoldstein.assignment2;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by esthergoldstein on 2/3/18.
 */

public class RetrieveResultsTask extends AsyncTask<String, Integer, ArrayList<String>>{
    private Context context;

    public RetrieveResultsTask(Context context){
        this.context = context.getApplicationContext();
    }
    @Override
    protected ArrayList<String> doInBackground(String... params) {
        String fullUrl = "http://utdallas.edu/~John.Cole/2017Spring/" + params[0] + ".txt";
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
                ArrayList<String> myList = new ArrayList<String>();
                myList.add("404");
                return myList;
            }
            InputStream responseInputStream = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(responseInputStream, "UTF-8");
            BufferedReader reader = new BufferedReader(isr);
            List<String> results = new ArrayList<String>();
            StringBuilder builder = new StringBuilder();
            for (String line = null; (line = reader.readLine()) != null; ) {
                results.add(line);
            }
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
    protected void onPostExecute(ArrayList<String> strings) {
        super.onPostExecute(strings);
        if(strings.get(0).equals("404")) {
            Intent intent = new Intent(context, HomePage.class);
            intent.putExtra("404", "404");
            context.startActivity(intent);
        }
        else{
            Intent intent = new Intent(context, ResultsPage.class);
            intent.putStringArrayListExtra("results", strings);
            context.startActivity(intent);
        }

    }
}
