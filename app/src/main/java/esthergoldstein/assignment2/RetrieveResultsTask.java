package esthergoldstein.assignment2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by esthergoldstein on 2/3/18.
 */

public class RetrieveResultsTask extends AsyncTask<String, Integer, Bitmap>{
    @Override
    protected Bitmap doInBackground(String... params) {
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
                // Handle the error
            }
            InputStream responseInputStream = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(responseInputStream, "UTF-8");
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder builder = new StringBuilder();
            for (String line = null; (line = reader.readLine()) != null; ) {
                builder.append(line).append("\n");
            }
            String output = builder.toString();
            //close streams
        }
        catch(Exception e){
            Log.e("Error", e.getMessage());
            e.printStackTrace();

        }
        //to-do:return results
        return null;
    }
}
