package nguyenvanquan7826.com.mahoacongkhai;

import java.util.ArrayList;

import nguyenvanquan7826.com.R;
import nguyenvanquan7826.com.algorithm.Algorithm;
import nguyenvanquan7826.com.algorithm.DrawTable;
import nguyenvanquan7826.com.base.BaseActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

public class RSAActivity extends BaseActivity implements OnClickListener,
		OnItemSelectedListener {

	public static final int n = 3;
	private TextView[] tv = new TextView[n];
	private EditText[] edit = new EditText[n];
	private Button btnOk;
	private TextView tvResult;
	private Algorithm cal = new Algorithm();
	private WebView webInfo;
	private Spinner sp;
	private TableLayout table;
	DrawTable drawTable = new DrawTable(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creat_key_rsa);

		connectView();
	}

	private void connectView() {

		for (int i = 0; i < n; i++) {
			tv[i] = (TextView) findViewById(idTv[i]);
			edit[i] = (EditText) findViewById(idEdit[i]);
		}

		table = (TableLayout) findViewById(R.id.table_rsa);
		table.setColumnStretchable(0, true);
		table.setColumnStretchable(1, true);
		table.setColumnStretchable(2, true);
		tvResult = (TextView) findViewById(R.id.tv_result_key_rsa);

		sp = (Spinner) findViewById(R.id.sp_create_key_rsa);
		btnOk = (Button) findViewById(R.id.btn_ok_key_rsa);
		btnOk.setOnClickListener(this);

		webInfo = (WebView) findViewById(R.id.web_info_rsa);
		WebSettings settings = webInfo.getSettings();
		settings.setDefaultTextEncodingName("utf-8");
		webInfo.loadUrl("file:///android_asset/rsa.html");

		createSelectModel();
	}

	private void createSelectModel() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, new String[] { "Tạo khóa",
						"Mã hóa: y = x^e mod n", "Giải mã: x = y^d mod n" });
		sp.setAdapter(adapter);
		sp.setOnItemSelectedListener(this);
	}

	private boolean checkInput() {

		// if (!Algorithm.checkEditText(this, edit[0],
		// title[sp.getSelectedItemPosition()][0])) {
		// return false;
		// }
		// if (!Algorithm.checkEditText(this, edit[1],
		// title[sp.getSelectedItemPosition()][1])) {
		// return false;
		// }
		// if (!Algorithm.checkEditText(this, edit[2],
		// title[sp.getSelectedItemPosition()][2])) {
		// return false;
		// }
		return true;
	}

	private void resetData() {
		tvResult.setText("");
		table.removeAllViews();
		changeModel(sp.getSelectedItemPosition());
	}

	private void createKeyRSA() {
		if (checkInput()) {

			int[] num = new int[n];
			for (int i = 0; i < n; i++) {
				num[i] = Integer.parseInt(edit[i].getText().toString().trim());
			}
			ArrayList<String[]> list;
			int result[] = new int[1];
			switch (sp.getSelectedItemPosition()) {
			case 0:
				tvResult.setText(cal.createKeyRSA(num[0], num[1], num[2]));
				break;
			case 1:
				list = cal.axmodn(num[0], num[1], num[2], result);
				drawTable.createTable(table,
						new String[] { "e", "x", "d = 1" }, list);
				tvResult.setText(getString(R.string.banma) + result[0]);
				break;
			case 2:
				list = cal.axmodn(num[0], num[1], num[2], result);
				drawTable.createTable(table,
						new String[] { "d", "y", "D = 1" }, list);
				tvResult.setText(getString(R.string.banro) + result[0]);
			default:
				break;
			}

		}
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_ok_key_rsa:
			createKeyRSA();
			break;
		default:
			break;
		}
	}

	private void changeModel(int position) {
		for (int i = 0; i < n; i++) {
			tv[i].setText(title[position][i] + " = ");
			edit[i].setText("");
			edit[i].setHint("nhập " + title[position][i]);
		}
		edit[0].requestFocus();

	}

	private String[][] title = new String[][] { { "p", "q", "e" },
			{ "x", "e", "n" }, { "y", "d", "n" } };
	private int[] idEdit = { R.id.edit_0_key_rsa, R.id.edit_1_key_rsa,
			R.id.edit_2_key_rsa };
	private int[] idTv = { R.id.tv_0_key_rsa, R.id.tv_1_key_rsa,
			R.id.tv_2_key_rsa };

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		resetData();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}
}
