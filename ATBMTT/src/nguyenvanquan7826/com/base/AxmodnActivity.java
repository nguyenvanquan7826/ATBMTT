package nguyenvanquan7826.com.base;

import java.util.ArrayList;

import nguyenvanquan7826.com.R;
import nguyenvanquan7826.com.algorithm.Algorithm;
import nguyenvanquan7826.com.algorithm.DrawTable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

public class AxmodnActivity extends BaseActivity implements OnClickListener {

	// private EditText editA, editX, editN;
	public static final int n = 3;
	private EditText[] edit = new EditText[n];
	private Button btnOk;
	private TableLayout table;
	private EditText editResult;
	private Algorithm cal = new Algorithm();
	private DrawTable drawTable = new DrawTable(this);
	private WebView webInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_axmodn);
		connectView();
	}

	private void connectView() {
		// editA = (EditText) findViewById(R.id.edit_a_axmodn);
		// editX = (EditText) findViewById(R.id.edit_x_axmodn);
		// editN = (EditText) findViewById(R.id.edit_n_axmodn);

		for (int i = 0; i < n; i++) {
			edit[i] = (EditText) findViewById(idEdit[i]);
		}

		editResult = (EditText) findViewById(R.id.edit_result_axmodn);

		btnOk = (Button) findViewById(R.id.btn_ok_axmodn);
		btnOk.setOnClickListener(this);

		table = (TableLayout) findViewById(R.id.table_axmodn);
		table.setColumnStretchable(0, true);
		table.setColumnStretchable(1, true);
		table.setColumnStretchable(2, true);

		webInfo = (WebView) findViewById(R.id.web_info_axmodn);
		WebSettings settings = webInfo.getSettings();
		settings.setDefaultTextEncodingName("utf-8");
		webInfo.loadUrl("file:///android_asset/axmodn.html");
	}

	private String checkInput() {
		return Algorithm.checkInput(edit, title);
	}

	private void axmodn() {
		String error = checkInput();
		if (error != null) {
			Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
			return;
		}

		int a = Integer.parseInt(edit[0].getText().toString());
		int x = Integer.parseInt(edit[1].getText().toString());
		int n = Integer.parseInt(edit[2].getText().toString());

		ArrayList<String[]> list = cal.axmodn(a, x, n, new int[1]);
		drawTable.createTable(table, new String[] { "x", "a", "d = 1" }, list);
		editResult.setText(list.get(list.size() - 1)[0]);
	}

	@Override
	public void onClick(View v) {
		if (v == btnOk) {
			axmodn();
		}
	}

	private int[] idEdit = { R.id.edit_a_axmodn, R.id.edit_x_axmodn,
			R.id.edit_n_axmodn };
	private String[] title = { "a", "x", "n" };
}
