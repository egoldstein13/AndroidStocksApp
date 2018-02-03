package esthergoldstein.assignment2;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by esthergoldstein on 2/3/18.
 */

public class RetrieveResultsTask<URL, Integer, Bitmap> extends AsyncTask{
    @Override
    protected Object doInBackground(Object[] objects) {
        String fullUrl = "http://google.com";
        try {
            java.net.URL url = new java.net.URL(fullUrl);
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
