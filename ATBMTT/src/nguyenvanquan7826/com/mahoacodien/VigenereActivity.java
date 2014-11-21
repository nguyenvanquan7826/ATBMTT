package nguyenvanquan7826.com.mahoacodien;

import java.util.ArrayList;

import nguyenvanquan7826.com.R;
import nguyenvanquan7826.com.algorithm.Algorithm;
import nguyenvanquan7826.com.algorithm.DrawTable;
import nguyenvanquan7826.com.base.BaseActivity;
import nguyenvanquan7826.com.main.Const;
import android.graphics.Color;
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

public class VigenereActivity extends BaseActivity implements OnClickListener,
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

	private int[] ttKhoa;
	private String input;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vigenere);

		connectView();
	}

	private void connectView() {

		for (int i = 0; i < n; i++) {
			tv[i] = (TextView) findViewById(idTv[i]);
			edit[i] = (EditText) findViewById(idEdit[i]);
		}

		table = (TableLayout) findViewById(R.id.table_vigenere);
		table.setStretchAllColumns(true);

		editResult = (EditText) findViewById(R.id.edit_result_vigenere);

		sp = (Spinner) findViewById(R.id.sp_vigenere);
		btnOk = (Button) findViewById(R.id.btn_ok_vigenere);
		btnOk.setOnClickListener(this);

		webInfo = (WebView) findViewById(R.id.web_info_vigenere);
		WebSettings settings = webInfo.getSettings();
		settings.setDefaultTextEncodingName("utf-8");
		webInfo.loadUrl("file:///android_asset/mavigenere.html");

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

	private void mavigenere(boolean type) {

		ArrayList<String[]> list = cal.vigenere(input, ttKhoa, Const.z, type);

		drawTable.createTable(table, null, list);

		editResult.setText(list.get(list.size() - 1)[0]);
	}

	private String checkInput() {
		String error = Algorithm.checkInput(edit,
				title[sp.getSelectedItemPosition()]);
		if (error != null) {
			return error;
		}

		ttKhoa = cal.converStringToArrNumber(edit[0].getText().toString()
				.trim());
		input = edit[1].getText().toString().trim();

		for (int i = 0; i < ttKhoa.length; i++) {
			if (ttKhoa[i] >= Const.z) {
				edit[0].requestFocus();
				return Const.errorKhoaKhongGian;
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
			mavigenere(true);
			break;
		case 1:
			mavigenere(false);
			break;
		default:
			break;
		}
	}

	private void changeModel(int position) {
		for (int i = 0; i < n; i++) {
			tv[i].setText(title[position][i] + ":");
		}
		if (edit[0].getText().toString().trim().equals("")) {
			edit[0].requestFocus();
		} else {
			edit[1].requestFocus();
		}

	}

	private String[][] title = new String[][] { { "Thứ tự khóa", "Bản rõ" },
			{ "Thứ tự khóa", "Bản mã" } };
	private int[] idEdit = { R.id.edit_0_vigenere, R.id.edit_1_vigenere };
	private int[] idTv = { R.id.tv_0_vigenere, R.id.tv_1_vigenere };

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		resetData();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}
}
