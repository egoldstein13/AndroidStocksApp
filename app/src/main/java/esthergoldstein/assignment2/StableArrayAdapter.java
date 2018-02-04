/******************************************************************************
 * Class that is used to display high score lists.  This is the adapter, or
 * model, that contains the strings.  The scores list is tab-separated values
 * with the score, name, and date, in that order.
 *
 * Written by John Cole.
 ******************************************************************************/

package esthergoldstein.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StableArrayAdapter extends ArrayAdapter<HashMap<String, String>>
  {
  ArrayList<HashMap<String, String>> values;

  public StableArrayAdapter(Context context, int textViewResourceId,
                            ArrayList<HashMap<String, String>> objects)
    {
    super(context, textViewResourceId, objects);
    values = objects;
    }

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
   * This overridden function is called for each line in the list.  Split the
   * data string on tabs and put each component into the TextView in the View
   * we return, which is then displayed.
   *
   * Since it does not seem possible to assign each of the components of the
   * ListView line a percentage of the screen width, that is done in the code
   * below.  These look reasonably good on both my Asus tablet and my Galaxy
   * S5 phone.
   * @param position
   * @param cvtView
   * @param parent
   * @return
   ****************************************************************************/
  @Override
  public View getView(int position, View cvtView, ViewGroup parent)
    {
    int width = parent.getWidth();
    Context cx = this.getContext();
    ViewHolder holder;
    LayoutInflater inflater = (LayoutInflater) cx.getSystemService(cx.LAYOUT_INFLATER_SERVICE);

    if(cvtView == null) {
        cvtView = inflater.inflate(R.layout.listview_format, parent, false);
        holder = new ViewHolder();
        holder.date = (TextView) cvtView.findViewById(R.id.date);
        holder.open = (TextView) cvtView.findViewById(R.id.open);
        holder.high = (TextView) cvtView.findViewById(R.id.high);
        holder.low = (TextView) cvtView.findViewById(R.id.low);
        holder.close = (TextView) cvtView.findViewById(R.id.close);
        holder.volume = (TextView) cvtView.findViewById(R.id.volume);
        holder.adjClose = (TextView) cvtView.findViewById(R.id.adjClose);
        cvtView.setTag(holder);
        holder.date.setText(values.get(position).get("date"));
        holder.open.setText(values.get(position).get("open"));
        holder.high.setText(values.get(position).get("high"));
        holder.low.setText(values.get(position).get("low"));
        holder.close.setText(values.get(position).get("close"));
        holder.volume.setText(values.get(position).get("volume"));
        holder.adjClose.setText(values.get(position).get("adjClose"));
    }
    else{
      holder = (ViewHolder) cvtView.getTag();
  }

    return cvtView;
    }
  }