package nguyenvanquan7826.com.mahoacodien;

import java.util.ArrayList;

import nguyenvanquan7826.com.R;
import nguyenvanquan7826.com.algorithm.Algorithm;
import nguyenvanquan7826.com.algorithm.DrawTable;
import nguyenvanquan7826.com.base.BaseActivity;
import nguyenvanquan7826.com.main.Const;
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
import android.widget.Toast;

public class HillActivity extends BaseActivity implements OnClickListener,
		OnItemSelectedListener {
	public static final int n = 5;
	private TextView tv;
	private EditText[] edit = new EditText[n];
	private Button btnOk;
	private TextView tvNghichDao;
	private EditText editResult;
	private Algorithm cal = new Algorithm();
	private WebView webInfo;
	private Spinner sp;
	private TableLayout tableNghichDao, tableResult;
	DrawTable drawTable = new DrawTable(this);

	private int[][] key;
	private String input;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hill);

		connectView();
	}

	private void connectView() {
		drawTable.setBorder(false);
		tv = (TextView) findViewById(R.id.tv_0_hill);
		editResult = (EditText) findViewById(R.id.edit_result_hill);
		tvNghichDao = (TextView) findViewById(R.id.tv_nghichdao_hill);

		for (int i = 0; i < n; i++) {
			edit[i] = (EditText) findViewById(idEdit[i]);
		}

		sp = (Spinner) findViewById(R.id.sp_hill);
		btnOk = (Button) findViewById(R.id.btn_ok_hill);
		btnOk.setOnClickListener(this);

		tableNghichDao = (TableLayout) findViewById(R.id.table_nghichdao_hill);
		tableNghichDao.setStretchAllColumns(true);

		tableResult = (TableLayout) findViewById(R.id.table_reslut_hill);
		tableResult.setStretchAllColumns(true);

		webInfo = (WebView) findViewById(R.id.web_info_hill);
		WebSettings settings = webInfo.getSettings();
		settings.setDefaultTextEncodingName("utf-8");
		webInfo.loadUrl("file:///android_asset/mahill.html");

		createSelectModel();
	}

	private void createSelectModel() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, new String[] {
						"Mã hóa: y = (ax + b) mod n ",
						"Giải mã: x = a^(-1).(y-b) mod n" });
		sp.setAdapter(adapter);
		sp.setOnItemSelectedListener(this);
	}

	private void resetData() {
		tvNghichDao.setText("");
		editResult.setText("");

		tableNghichDao.removeAllViews();
		tableResult.removeAllViews();

		changeModel(sp.getSelectedItemPosition());
	}

	private void mahill(boolean type) {
		ArrayList<String[]> list = cal.hill(input, key, Const.z, type);

		String[] titleRs = titltResult[0];
		int indexList = 0;
		if (!type) {
			tvNghichDao.setText(list.get(indexList++)[0]);
			ArrayList<String[]> listNghichDao = new ArrayList<String[]>();
			listNghichDao.add(list.get(indexList++));
			listNghichDao.add(new String[] { "" });
			String[] titleNghichDao = { "DetK^(-1)", "", "K\"", "", "K^(-1)",
					"", "mod n" };
			drawTable
					.createTable(tableNghichDao, titleNghichDao, listNghichDao);

			titleRs = titltResult[1];
		}

		ArrayList<String[]> listResult = new ArrayList<String[]>();
		listResult.add(list.get(indexList++));
		listResult.add(new String[] { "" });

		drawTable.createTable(tableResult, titleRs, listResult);

		editResult.setText(list.get(indexList)[0]);
	}

	private String checkInput() {
		String error = Algorithm.checkInput(edit,
				title[sp.getSelectedItemPosition()]);
		if (error != null) {
			return error;
		}
		key = new int[][] {
				{ Integer.parseInt(edit[1].getText().toString()),
						Integer.parseInt(edit[2].getText().toString()) },
				{ Integer.parseInt(edit[3].getText().toString()),
						Integer.parseInt(edit[4].getText().toString()) } };
		input = edit[0].getText().toString().trim();
		int detK = (key[0][0] * key[1][1] - key[0][1] * key[1][0]);

		int uc = cal.ucln(detK, Const.z);

		if (uc != 1) {
			edit[1].requestFocus();
			return "UCLN(Det(K),n) = UCLN(" + detK + "," + Const.z + ") = "
					+ uc + " != 1";
		}

		return null;
	}

	@Override
	public void onClick(View v) {
		String error = checkInput();
		if (error != null) {
			Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
			return;
		}
		switch (sp.getSelectedItemPosition()) {
		case 0:
			mahill(true);
			break;
		case 1:
			mahill(false);
			break;
		default:
			break;
		}
	}

	private void changeModel(int position) {
		tv.setText(title[position][0] + ":");
		edit[0].requestFocus();
	}

	private String[][] title = new String[][] {
			{ "Bản rõ", "k11", "k12", "k21", "k22" },
			{ "Bản mã", "k11", "k12", "k21", "k22" } };
	private String[][] titltResult = new String[][] {
			{ "X", "", "K", "", "X*K", "", "mod n" },
			{ "Y", "", "K^(-1)", "", "Y*K^(-1)", "", "mod n" } };

	private int[] idEdit = { R.id.edit_0_hill, R.id.edit_1_hill,
			R.id.edit_2_hill, R.id.edit_3_hill, R.id.edit_4_hill };

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		resetData();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}
}
