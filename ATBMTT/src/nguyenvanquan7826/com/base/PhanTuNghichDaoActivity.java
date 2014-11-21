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

public class PhanTuNghichDaoActivity extends BaseActivity implements
		OnClickListener {

	// private EditText editA, editN;
	public static final int n = 2;
	private EditText[] edit = new EditText[n];
	private Button btnOk;
	private EditText editResult;
	private Algorithm cal = new Algorithm();
	private TableLayout table;
	private DrawTable drawTable = new DrawTable(this);
	private WebView webInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phan_tu_nghich_dao);

		connectView();

	}

	private void connectView() {
		// editA = (EditText) findViewById(R.id.edit_a_nghichdao);
		// editN = (EditText) findViewById(R.id.edit_n_nghichdao);

		for (int i = 0; i < n; i++) {
			edit[i] = (EditText) findViewById(idEdit[i]);
		}

		editResult = (EditText) findViewById(R.id.edit_result_nghich_dao);
		btnOk = (Button) findViewById(R.id.btn_ok_nghichdao);
		btnOk.setOnClickListener(this);
		table = (TableLayout) findViewById(R.id.table_nghich_dao);
		table.setColumnStretchable(0, true);
		table.setColumnStretchable(1, true);
		table.setColumnStretchable(2, true);

		webInfo = (WebView) findViewById(R.id.web_info_nghichdao);
		WebSettings settings = webInfo.getSettings();
		settings.setDefaultTextEncodingName("utf-8");
		webInfo.loadUrl("file:///android_asset/nghichdao.html");
	}

	private String checkInput() {
		return Algorithm.checkInput(edit, title);
	}

	private void nghichDao() {
		String error = checkInput();
		if (error != null) {
			Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
			return;
		}

		int a = Integer.parseInt(edit[0].getText().toString());
		int n = Integer.parseInt(edit[1].getText().toString());

		ArrayList<String[]> list = cal.phanTuNghichDao(a, n, new int[1]);

		drawTable.createTable(table, new String[] { "a", "v", "y" }, list);
		editResult.setText(list.get(list.size() - 1)[0]);
	}

	@Override
	public void onClick(View v) {
		if (v == btnOk) {
			nghichDao();
		}
	}

	private int[] idEdit = { R.id.edit_a_nghichdao, R.id.edit_n_nghichdao };
	private String[] title = { "a", "n" };
}
