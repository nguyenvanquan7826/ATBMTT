package nguyenvanquan7826.com.mahoacodien;

import nguyenvanquan7826.com.R;
import nguyenvanquan7826.com.algorithm.Algorithm;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AffineDialogCountKey extends Dialog implements OnClickListener {
	private final String title = "Tính số cách chọn khóa";
	private Context context;

	private EditText editN;
	private TextView tvResult;
	private Button btnOk;

	public AffineDialogCountKey(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(title);
		setContentView(R.layout.dialog_affine_key);
		connectView();

	}

	private void connectView() {
		editN = (EditText) findViewById(R.id.edit_n_dialog_affine);
		tvResult = (TextView) findViewById(R.id.tv_result_dialog_affine);
		btnOk = (Button) findViewById(R.id.btn_ok_dialog_affine);
		btnOk.setOnClickListener(this);
	}

	private void countKey() {
		String text = editN.getText().toString().trim();
		if (text.equals("")) {
			Toast.makeText(context, "bạn chưa nhập n", Toast.LENGTH_SHORT);
			editN.requestFocus();
			return;
		}
		int n = Integer.parseInt(text);
		Algorithm cal = new Algorithm();
		long[] result = new long[1];

		String textResult = "Số cách chọn khóa = số cách chọn a * số cách chọn b\n"
				+ "+/ Số cách chọn b = n = "
				+ n
				+ " cách\n"
				+ "+/ Số cách chọn a = Φ(n) = Φ(" + n + ")\n";

		textResult += cal.countKeyAffine(n, result);
		textResult += "\n=> Số cách chọn khóa = " + result[0] + "*" + n + " = "
				+ (n * result[0]);
		tvResult.setText(textResult);
		tvResult.setTextColor(Color.BLUE);
	}

	@Override
	public void onClick(View arg0) {
		countKey();
	}
}