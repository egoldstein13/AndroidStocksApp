package esthergoldstein.assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
        String fullUrl = "http://google.com";
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

        }

    }
}
