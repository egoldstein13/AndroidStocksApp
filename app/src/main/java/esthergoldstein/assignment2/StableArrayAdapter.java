/** ***************************************************************************
 * Stock Information Program: StableArrayAdapter
 *
 * Sets the View for to display the data on screen. First it checks if the current
 * view is null. If it isn't, it initializes the TextViews. Then it sets the text
 * values for the TextViews as well as the alignment.
 *
 * Written by Esther Goldstein and Neel Jathanna for CS 4301.003,
 * Assignment 2, starting February 3, 2018.
 * NetIDs: emg140230 and nsj140030
 *
 * Esther Goldstein: HomePage.java, ResultsPage.onPostExecute,
 *                   ResultsPage.displayDialogAfterTimeout,
 * Neel Jathanna: StableArrayAdapter.java, ResultsPage.doInBackground
 ******************************************************************************/

package esthergoldstein.assignment2;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class StableArrayAdapter extends ArrayAdapter<HashMap<String, String>> {
    ArrayList<HashMap<String, String>> values;

    //Constructor for the StableArrayAdapter. Initializes the ArrayList of HashMaps
    //Written by Neel Jathanna
    public StableArrayAdapter(Context context, int textViewResourceId,
                              ArrayList<HashMap<String, String>> objects) {
        super(context, textViewResourceId, objects);
        values = objects;
    }

    //Class to hold the view for each ListView, with one TextView per column
    //Written by Neel Jathanna
    private class ViewHolder {
        TextView date;
        TextView open;
        TextView high;
        TextView low;
        TextView close;
        TextView volume;
        TextView adjClose;
    }

    /****************************************************************************
     * The function is called for each line in the text file. If the current view
     * is null, each TextView is initialized and the tag is set. Otherwise it gets
     * the tag of the current view. Then the text for each TextView is set based
     * on the values in the Hashmap. If the data contains the list headers (at
     * position 0), the gravity is set to be left-aligned. Otherwise it is set
     * to be right-aligned.
     * Written by Neel Jathanna
     ****************************************************************************/
    @Override
    public View getView(int position, View cvtView, ViewGroup parent) {
        Context cx = this.getContext();
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) cx.getSystemService(cx.LAYOUT_INFLATER_SERVICE);

        if (cvtView == null) {
            cvtView = inflater.inflate(R.layout.listview_format, null);
            holder = new ViewHolder();
            holder.date = (TextView) cvtView.findViewById(R.id.date);
            holder.open = (TextView) cvtView.findViewById(R.id.open);
            holder.high = (TextView) cvtView.findViewById(R.id.high);
            holder.low = (TextView) cvtView.findViewById(R.id.low);
            holder.close = (TextView) cvtView.findViewById(R.id.close);
            holder.volume = (TextView) cvtView.findViewById(R.id.volume);
            holder.adjClose = (TextView) cvtView.findViewById(R.id.adjClose);

            cvtView.setTag(holder);
        } else {
            holder = (ViewHolder) cvtView.getTag();
        }
        holder.date.setText(values.get(position).get("Date"));
        holder.open.setText(values.get(position).get("Open"));
        holder.high.setText(values.get(position).get("High"));
        holder.low.setText(values.get(position).get("Low"));
        holder.close.setText(values.get(position).get("Close"));
        holder.volume.setText(values.get(position).get("Volume"));
        holder.adjClose.setText(values.get(position).get("AdjClose"));

        if (position==0) {
            holder.date.setGravity(Gravity.LEFT);
            holder.open.setGravity(Gravity.LEFT);
            holder.high.setGravity(Gravity.LEFT);
            holder.low.setGravity(Gravity.LEFT);
            holder.close.setGravity(Gravity.LEFT);
            holder.volume.setGravity(Gravity.LEFT);
            holder.adjClose.setGravity(Gravity.LEFT);
        }
        else{
            holder.date.setGravity(Gravity.RIGHT);
            holder.open.setGravity(Gravity.RIGHT);
            holder.high.setGravity(Gravity.RIGHT);
            holder.low.setGravity(Gravity.RIGHT);
            holder.close.setGravity(Gravity.RIGHT);
            holder.volume.setGravity(Gravity.RIGHT);
            holder.adjClose.setGravity(Gravity.RIGHT);
        }
        return cvtView;
    }
}