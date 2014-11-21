package nguyenvanquan7826.com.base;

import nguyenvanquan7826.com.Balan;
import nguyenvanquan7826.com.R;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("NewApi")
public class CalActivity extends ActionBarActivity implements OnClickListener {

	private final String TAG = "Calculator";

	private int[] idBtn = new int[] { R.id.btn_del, R.id.btn_result,
			R.id.btn_ac, R.id.btn_binh, R.id.btn_mod, R.id.btn_7, R.id.btn_8,
			R.id.btn_9, R.id.btn_div, R.id.btn_4, R.id.btn_5, R.id.btn_6,
			R.id.btn_mul, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_sub,
			R.id.btn_0, R.id.btn_dot, R.id.btn_plus };

	private String[] text = { "Del", "=", "AC", "²", "Mod", "7", "8", "9", "/",
			"4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "+" };

	private Button[] btn = new Button[idBtn.length];

	private TextView tvMath;
	private EditText editMath;
	private BaseInputConnection textFieldInputConnection;
	private Balan balan = new Balan();

	private boolean touch = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cal);
		connectView();
	}

	private void connectView() {
		tvMath = (TextView) findViewById(R.id.tv_math);
		editMath = (EditText) findViewById(R.id.edit_math);

		editMath.setFocusableInTouchMode(true);

		editMath.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.d(TAG, "ontouch");
				touch = true;
				v.onTouchEvent(event);
				// InputMethodManager imm = (InputMethodManager) v.getContext()
				// .getSystemService(Context.INPUT_METHOD_SERVICE);
				// if (imm != null) {
				// imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
				// }
				return true;
			}
		});

		editMath.setInputType(InputType.TYPE_NULL);
		if (android.os.Build.VERSION.SDK_INT >= 11) {
			editMath.setRawInputType(InputType.TYPE_CLASS_TEXT);
			editMath.setTextIsSelectable(true);
		}
		textFieldInputConnection = new BaseInputConnection(editMath, true);

		for (int i = 0; i < idBtn.length; i++) {
			btn[i] = (Button) findViewById(idBtn[i]);
			btn[i].setOnClickListener(this);
		}
	}

	private void clickButton(int i) {
		if (!touch) {
			editMath.setText("");
			if (!balan.isNumber(text[i]) && !balan.isError()) {
				editMath.getText().insert(editMath.getSelectionStart(),
						tvMath.getText());
			}
			touch = true;
		}
		editMath.getText().insert(editMath.getSelectionStart(), text[i]);
		editMath.requestFocus();
	}

	private void result() {
		String math = editMath.getText().toString().trim();
		if (math.length() > 0) {
			touch = true;
			balan.setError(false);
			String ans = balan.valueMath(math);
			tvMath.setText(balan.getStandardizeMath());
			if (!balan.isError()) {
				tvMath.setText(ans);
			} else {
				tvMath.setText("Error");
			}
			touch = false;
			btn[0].requestFocus();
		}
	}

	private void ac() {
		editMath.setText("");
	}

	private void clear() {
		ac();
		tvMath.setText("");
	}

	@Override
	public void onClick(View v) {
		for (int i = 3; i < idBtn.length; i++) {
			if (v.getId() == idBtn[i]) {
				clickButton(i);
				break;
			}
		}
		if (v.getId() == R.id.btn_ac) {
			clear();
		}
		if (v.getId() == R.id.btn_del) {
			textFieldInputConnection.sendKeyEvent(new KeyEvent(
					KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
		}
		if (v.getId() == R.id.btn_result) {
			result();
			editMath.setSelection(editMath.getText().toString().length());
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.cal, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.open:
			editMath.getText().insert(editMath.getSelectionStart(), "(");
			editMath.requestFocus();
			return super.onOptionsItemSelected(item);
		case R.id.close:
			editMath.getText().insert(editMath.getSelectionStart(), ")");
			editMath.requestFocus();
			return super.onOptionsItemSelected(item);
		default:
			return super.onOptionsItemSelected(item);
		}

	}

}
