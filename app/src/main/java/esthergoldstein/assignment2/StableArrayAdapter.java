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

import java.util.List;

public class StableArrayAdapter extends ArrayAdapter<String>
  {
  List<String> values;

  public StableArrayAdapter(Context context, int textViewResourceId,
                            List<String> objects)
    {
    super(context, textViewResourceId, objects);
    values = objects;
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
    LayoutInflater inflater = (LayoutInflater) cx.getSystemService(cx.LAYOUT_INFLATER_SERVICE);
    View rowView = null;
    //View rowView = inflater.inflate(R.layout.scorelayout, parent, false);
    String str = values.get(position);
    String[] vals = str.split("\t");
    //TextView tvName = (TextView) rowView.findViewById(R.id.sl_name);
    TextView tvName = null;
    tvName.setWidth((int) (width * .30));
    tvName.setText(vals[2]);

    TextView tvScore = null;
    //TextView tvScore = (TextView) rowView.findViewById(R.id.sl_score);
    int stemp = Integer.parseInt(vals[0]);
    tvScore.setWidth((int) (width * .15));
    tvScore.setText(Integer.toString(stemp));

    TextView tvDate = null;
    //TextView tvDate = (TextView) rowView.findViewById(R.id.sl_date);
    tvDate.setWidth((int) (width * .5));
    tvDate.setText(vals[1].replace("CDT ", ""));
    return rowView;
    }
  }