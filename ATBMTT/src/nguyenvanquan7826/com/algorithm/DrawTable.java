package nguyenvanquan7826.com.algorithm;

import java.util.ArrayList;

import nguyenvanquan7826.com.R;
import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DrawTable {

	private Activity activity;
	private boolean border;

	public void setBorder(boolean border) {
		this.border = border;
	}

	public DrawTable(Activity activity) {
		this.activity = activity;
		border = true;
	}

	public void createTable(TableLayout table, String[] title,
			ArrayList<String[]> list) {

		resetTable(table, title);
		if (list != null) {
			for (int i = 0; i < list.size() - 1; i++) {
				table.addView(createRow(list.get(i)));
				Log.d("axmodn", "add row");
			}
		}
	}

	private void resetTable(TableLayout table, String[] title) {
		table.removeAllViews();
		createTitle(table, title);
	}

	private void createTitle(TableLayout table, String[] item) {
		if (item != null) {
			TableRow tr = createRow(item);
			for (int i = 0; i < item.length; i++) {
				TextView tv = (TextView) tr.getChildAt(i);
				if (border) {
					tv.setBackgroundResource(R.drawable.border_title);
					tv.setTextColor(activity.getResources().getColor(
							R.color.white));
				} else {
					tv.setTextColor(activity.getResources().getColor(
							R.color.blue));
				}

			}
			table.addView(tr);
		}

	}

	private TableRow createRow(String[] item) {
		TableRow tr = new TableRow(activity);
		for (int i = 0; i < item.length; i++) {
			tr.addView(createTextView(item[i]));
		}
		return tr;
	}

	private TextView createTextView(String text) {
		TextView tv = new TextView(activity);
		tv.setGravity(Gravity.CENTER);
		tv.setText(text);
		tv.setPadding(10, 0, 10, 0);
		if (border) {
			tv.setBackgroundResource(R.drawable.border_cell);
		}
		return tv;
	}

}
