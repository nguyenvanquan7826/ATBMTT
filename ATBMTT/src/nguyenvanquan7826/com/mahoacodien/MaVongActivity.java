package nguyenvanquan7826.com.mahoacodien;

import java.util.ArrayList;

import nguyenvanquan7826.com.R;
import nguyenvanquan7826.com.algorithm.Algorithm;
import nguyenvanquan7826.com.algorithm.DrawTable;
import nguyenvanquan7826.com.base.BaseActivity;
import nguyenvanquan7826.com.main.Const;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
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

public class MaVongActivity extends BaseActivity implements OnClickListener,
		OnItemSelectedListener {
	public static final int n = 2;
	private TextView[] tv = new TextView[n];
	private EditText[] edit = new EditText[n];
	private Button btnOk;
	private EditText editResult;
	private Algorithm cal = new Algorithm();
	private WebView webInfo;
	private Spinner sp;
	private TableLayout table;
	DrawTable drawTable = new DrawTable(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ma_vong);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		connectView();
	}

	private void connectView() {

		for (int i = 0; i < n; i++) {
			tv[i] = (TextView) findViewById(idTv[i]);
			edit[i] = (EditText) findViewById(idEdit[i]);
		}

		table = (TableLayout) findViewById(R.id.table_vong);
		table.setStretchAllColumns(true);

		editResult = (EditText) findViewById(R.id.edit_result_vong);

		sp = (Spinner) findViewById(R.id.sp_vong);
		btnOk = (Button) findViewById(R.id.btn_ok_vong);
		btnOk.setOnClickListener(this);

		webInfo = (WebView) findViewById(R.id.web_info_vong);
		WebSettings settings = webInfo.getSettings();
		settings.setDefaultTextEncodingName("utf-8");
		// webInfo.loadUrl("file:///android_asset/madichvong.html");

		createSelectModel();
	}

	private void createSelectModel() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				new String[] { "Mã hóa: y = (x+k) mod n",
						"Giải mã: x = (y-k) mod n", });
		sp.setAdapter(adapter);
		sp.setOnItemSelectedListener(this);
	}

	private void resetData() {
		editResult.setText("");
		table.removeAllViews();
		changeModel(sp.getSelectedItemPosition());
	}

	private void mavong(boolean type) {
		ArrayList<String[]> list = cal.maVong(edit[0].getText().toString()
				.trim(), Integer.parseInt(edit[1].getText().toString().trim()),
				Const.z, type);
		drawTable.createTable(table, null, list);
		editResult.setText(list.get(list.size() - 1)[0]);
	}

	private String checkInput() {
		String error = Algorithm.checkInput(edit,
				title[sp.getSelectedItemPosition()]);
		if (error != null) {
			return error;
		}

		int k = Integer.parseInt(edit[1].getText().toString().trim());
		if (k < 0 || k >= Const.z) {
			error = Const.errorKhoaKhongGian;
		}
		return error;
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
			mavong(true);
			break;
		case 1:
			mavong(false);
			break;
		default:
			break;
		}

	}

	private void changeModel(int position) {
		for (int i = 0; i < n; i++) {
			tv[i].setText(title[position][i] + ":");
			edit[i].setText("");
			edit[i].setHint("nhập " + title[position][i].toLowerCase());
		}
		edit[0].requestFocus();

	}

	private String[][] title = new String[][] { { "Bản rõ", "Khóa" },
			{ "Bản mã", "Khóa" } };
	private int[] idEdit = { R.id.edit_0_vong, R.id.edit_1_vong };
	private int[] idTv = { R.id.tv_0_vong, R.id.tv_1_vong };

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		resetData();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}
}
