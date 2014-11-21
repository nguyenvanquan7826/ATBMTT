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

public class AffineActivity extends BaseActivity implements OnClickListener,
		OnItemSelectedListener {
	public static final int n = 3;
	private TextView[] tv = new TextView[1];
	private EditText[] edit = new EditText[n];
	private Button btnOk;
	private TextView tvNghichDao;
	private EditText editResult;
	private Algorithm cal = new Algorithm();
	private WebView webInfo;
	private Spinner sp;
	private TableLayout table;
	DrawTable drawTable = new DrawTable(this);

	private int ka, kb;
	private String input;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_affine);

		connectView();
	}

	private void connectView() {
		tv[0] = (TextView) findViewById(idTv[0]);
		tvNghichDao = (TextView) findViewById(idTv[2]);
		for (int i = 0; i < n; i++) {
			edit[i] = (EditText) findViewById(idEdit[i]);
		}

		table = (TableLayout) findViewById(R.id.table_affine);
		table.setStretchAllColumns(true);

		editResult = (EditText) findViewById(R.id.edit_result_affine);

		sp = (Spinner) findViewById(R.id.sp_affine);
		btnOk = (Button) findViewById(R.id.btn_ok_affine);
		btnOk.setOnClickListener(this);

		webInfo = (WebView) findViewById(R.id.web_info_affine);
		WebSettings settings = webInfo.getSettings();
		settings.setDefaultTextEncodingName("utf-8");
		webInfo.loadUrl("file:///android_asset/maaffine.html");

		createSelectModel();
	}

	private void createSelectModel() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, new String[] {
						"Mã hóa: y = (ax + b) mod n ",
						"Giải mã: x = a^(-1).(y-b) mod n",
						"Tính số cách chọn khóa" });
		sp.setAdapter(adapter);
		sp.setOnItemSelectedListener(this);
	}

	private void resetData() {
		tvNghichDao.setText("");
		editResult.setText("");
		table.removeAllViews();
		changeModel(sp.getSelectedItemPosition());
	}

	private void maAffine(boolean type) {

		ArrayList<String[]> list = cal.maAffine(input, ka, kb, Const.z, type);

		if (!type) {
			int[] nd = new int[1];
			cal.phanTuNghichDao(ka, Const.z, nd);
			tvNghichDao.setText("Tính a^(-1) = " + nd[0]);
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

		ka = Integer.parseInt(edit[1].getText().toString().trim());
		kb = Integer.parseInt(edit[2].getText().toString().trim());
		input = edit[0].getText().toString().trim();

		if (ka >= Const.z) {
			edit[1].requestFocus();
			return Const.errorKhoaKhongGian;
		}
		if (kb >= Const.z) {
			edit[2].requestFocus();
			return Const.errorKhoaKhongGian;
		}
		int uc = cal.ucln(ka, Const.z);
		if (uc != 1) {
			edit[1].requestFocus();
			return "UCLN của a và n là " + uc + " != 1";
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
			maAffine(true);
			break;
		case 1:
			maAffine(false);
			break;
		default:
			break;
		}
	}

	private void changeModel(int position) {
		tv[0].setText(title[position][0] + ":");
		edit[0].requestFocus();
	}

	private String[][] title = new String[][] { { "Bản rõ", "a", "b" },
			{ "Bản mã", "a", "b" }, { "", "a", "b" } };
	private int[] idEdit = { R.id.edit_0_affine, R.id.edit_1_affine,
			R.id.edit_2_affine };
	private int[] idTv = { R.id.tv_0_affine, R.id.tv_1_affine, R.id.tv_2_affine };

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		if (position < 2) {
			resetData();
		} else {
			sp.setSelection(0);
			new AffineDialogCountKey(this).show();
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}
}
