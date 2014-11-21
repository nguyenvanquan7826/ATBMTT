package nguyenvanquan7826.com.base;

import java.util.ArrayList;

import nguyenvanquan7826.com.R;
import nguyenvanquan7826.com.algorithm.Algorithm;
import nguyenvanquan7826.com.algorithm.DrawTable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TableLayout;

public class BangChuCaiActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bangchucai);

		TableLayout table = (TableLayout) findViewById(R.id.table_bangchucai);
		table.setStretchAllColumns(true);
		DrawTable drawTable = new DrawTable(this);

		ArrayList<String[]> list = new ArrayList<String[]>();
		String[] title = { "Chữ", "Số", " ", "Chữ", "Số", " ", "Chữ", "Số" };

		int row = 9;
		int col = 3;
		int count = 26;
		for (int i = 0; i < row; i++) {
			String[] str = new String[8];
			for (int j = 0; j < col; j++) {
				if (i + row * j < count) {
					str[j * 3] = Algorithm.toChar(i + row * j) + "";
					str[j * 3 + 1] = (i + row * j) + "";
				}
			}
			str[2] = str[5] = " ";
			list.add(str);
		}

		list.add(new String[] { "" });

		drawTable.createTable(table, title, list);
	}
}
