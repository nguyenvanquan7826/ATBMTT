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

public class HoanViActivity extends BaseActivity implements OnClickListener,
		OnItemSelectedListener {
	public static final int n = 3;
	private TextView[] tv = new TextView[n];
	private EditText[] edit = new EditText[n];
	private Button btnOk;
	private EditText editResult;
	private TextView tvNghichDao;
	private Algorithm cal = new Algorithm();
	private WebView webInfo;
	private Spinner sp;
	private TableLayout table;
	DrawTable drawTable = new DrawTable(this);

	private int[] ttDung, ttHoanVi;
	private String input;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hoan_vi);

		connectView();
	}

	private void connectView() {

		for (int i = 0; i < n; i++) {
			tv[i] = (TextView) findViewById(idTv[i]);
			edit[i] = (EditText) findViewById(idEdit[i]);
		}

		table = (TableLayout) findViewById(R.id.table_hoanvi);
		table.setStretchAllColumns(true);

		editResult = (EditText) findViewById(R.id.edit_result_hoanvi);
		tvNghichDao = (TextView) findViewById(R.id.tv_nghich_dao_hoanvi);

		sp = (Spinner) findViewById(R.id.sp_hoanvi);
		btnOk = (Button) findViewById(R.id.btn_ok_hoanvi);
		btnOk.setOnClickListener(this);

		webInfo = (WebView) findViewById(R.id.web_info_hoanvi);
		WebSettings settings = webInfo.getSettings();
		settings.setDefaultTextEncodingName("utf-8");
		webInfo.loadUrl("file:///android_asset/mahoanvi.html");

		createSelectModel();
	}

	private void createSelectModel() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, new String[] { "Mã hóa",
						"Giải mã", });
		sp.setAdapter(adapter);
		sp.setOnItemSelectedListener(this);
	}

	private void resetData() {
		editResult.setText("");
		table.removeAllViews();
		changeModel(sp.getSelectedItemPosition());
	}

	private void mahoanvi(boolean type) {
		ArrayList<String[]> list = cal.hoanvi(ttDung, ttHoanVi, input, type);

		// in nghich dao
		if (!type) {
			String nghichDao = "Tính π^(-1) = (";
			for (int i = 0; i < ttHoanVi.length; i++) {
				nghichDao += (ttHoanVi[i] + 1) + " ";
			}
			nghichDao += ")";
			tvNghichDao.setText(nghichDao);
		}

		drawTable.createTable(table, null, list);

		editResult.setText(list.get(list.size() - 1)[0]);
	}

	private String checkInput() {
		String error = Algorithm.checkInput(edit,
				title[sp.getSelectedItemPosition()]);
		if (error != null) {
			return error;
		}

		ttDung = cal.converStringToArrNumber(edit[0].getText().toString()
				.trim());
		ttHoanVi = cal.converStringToArrNumber(edit[1].getText().toString()
				.trim());
		input = edit[2].getText().toString().trim();

		/* kiem tra thu tu dung va thu tu cua hoan vi co la cac cap so ko */

		// kiem tra do dai
		if (ttDung.length != ttHoanVi.length) {
			edit[1].requestFocus();
			return Const.errorHoanVi;
		}

		// kiem tra thu tu dung co la 1..M
		for (int i = 0; i < ttDung.length; i++) {
			if (ttDung[i] != i + 1) {
				edit[0].requestFocus();
				return Const.errorHoanViTTD;
			}
		}

		// kiem tra thu tu hoan vi
		int[] countHV = new int[ttHoanVi.length];

		for (int i = 0; i < ttHoanVi.length; i++) {
			if (ttHoanVi[i] > ttHoanVi.length) {
				edit[1].requestFocus();
				return Const.errorHoanVi;
			}
			countHV[ttHoanVi[i] - 1]++;
			if (countHV[ttHoanVi[i] - 1] >= 2) {
				edit[1].requestFocus();
				return Const.errorHoanVi;
			}
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
			mahoanvi(true);
			break;
		case 1:
			mahoanvi(false);
			break;
		default:
			break;
		}

	}

	private void changeModel(int position) {
		tvNghichDao.setText("");
		for (int i = 0; i < n; i++) {
			tv[i].setText(title[position][i] + ":");
		}
		if (edit[0].getText().toString().trim().equals("")) {
			edit[0].requestFocus();
		} else if (edit[1].getText().toString().trim().equals("")) {
			edit[1].requestFocus();
		} else {
			edit[2].requestFocus();
		}

	}

	private String[][] title = new String[][] {
			{ "Thứ tự đúng", "Hoán vị", "Bản rõ" },
			{ "Thứ tự đúng", "Hoán vị", "Bản mã" } };
	private int[] idEdit = { R.id.edit_0_hoanvi, R.id.edit_1_hoanvi,
			R.id.edit_2_hoanvi };
	private int[] idTv = { R.id.tv_0_hoanvi, R.id.tv_1_hoanvi, R.id.tv_2_hoanvi };

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		resetData();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}
}
