package nguyenvanquan7826.com.main;

import nguyenvanquan7826.com.R;
import nguyenvanquan7826.com.algorithm.Algorithm;
import nguyenvanquan7826.com.base.AxmodnActivity;
import nguyenvanquan7826.com.base.BaseActivity;
import nguyenvanquan7826.com.base.PhanTuNghichDaoActivity;
import nguyenvanquan7826.com.mahoacodien.AffineActivity;
import nguyenvanquan7826.com.mahoacodien.HillActivity;
import nguyenvanquan7826.com.mahoacodien.HoanViActivity;
import nguyenvanquan7826.com.mahoacodien.MaTuSinhActyvity;
import nguyenvanquan7826.com.mahoacodien.MaVongActivity;
import nguyenvanquan7826.com.mahoacodien.VigenereActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends BaseActivity implements OnClickListener {

	private Button btnAxmodn, btnNghichDao, btnCreateKeyRSA, btnVong,
			btnHoanVi, btnAffine, btnVigenere, btnHill, btnTuSinh;
	Algorithm cal = new Algorithm();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		connectView();

	}

	private void connectView() {
		btnAxmodn = (Button) findViewById(R.id.btn_axmodn);
		btnAxmodn.setOnClickListener(this);

		btnNghichDao = (Button) findViewById(R.id.btn_nghichdao);
		btnNghichDao.setOnClickListener(this);

		// btnCreateKeyRSA = (Button) findViewById(R.id.btn_create_key_rsa);
		// btnCreateKeyRSA.setOnClickListener(this);

		btnVong = (Button) findViewById(R.id.btn_vong);
		btnVong.setOnClickListener(this);

		btnHoanVi = (Button) findViewById(R.id.btn_hoanvi);
		btnHoanVi.setOnClickListener(this);

		btnAffine = (Button) findViewById(R.id.btn_affine);
		btnAffine.setOnClickListener(this);

		btnVigenere = (Button) findViewById(R.id.btn_vigenere);
		btnVigenere.setOnClickListener(this);

		btnHill = (Button) findViewById(R.id.btn_hill);
		btnHill.setOnClickListener(this);

		btnTuSinh = (Button) findViewById(R.id.btn_tusinh);
		btnTuSinh.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.btn_axmodn:
			intent = new Intent(this, AxmodnActivity.class);
			break;
		case R.id.btn_nghichdao:
			intent = new Intent(this, PhanTuNghichDaoActivity.class);
			break;
		// case R.id.btn_create_key_rsa:
		// intent = new Intent(this, RSAActivity.class);
		// break;
		case R.id.btn_vong:
			intent = new Intent(this, MaVongActivity.class);
			break;
		case R.id.btn_hoanvi:
			intent = new Intent(this, HoanViActivity.class);
			break;
		case R.id.btn_affine:
			intent = new Intent(this, AffineActivity.class);
			break;
		case R.id.btn_vigenere:
			intent = new Intent(this, VigenereActivity.class);
			break;
		case R.id.btn_hill:
			intent = new Intent(this, HillActivity.class);
			break;
		case R.id.btn_tusinh:
			intent = new Intent(this, MaTuSinhActyvity.class);
			break;
		default:
			break;
		}
		startActivity(intent);
	}

	/* lưu ý: import android.support.v7.widget.ShareActionProvider; */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.actionbar_menu, menu);

		// Set up ShareActionProvider's default share intent
		MenuItem shareItem = menu.findItem(R.id.action_share);
		ShareActionProvider myShareActionProvider = (ShareActionProvider) MenuItemCompat
				.getActionProvider(shareItem);

		Intent myIntent = new Intent();
		myIntent.setAction(Intent.ACTION_SEND);
		myIntent.putExtra(Intent.EXTRA_TEXT,
				"Đây là đoạn text tôi muốn chia sẻ");
		myIntent.setType("text/plain");

		myShareActionProvider.setShareIntent(myIntent);

		return super.onCreateOptionsMenu(menu);
	}
}
