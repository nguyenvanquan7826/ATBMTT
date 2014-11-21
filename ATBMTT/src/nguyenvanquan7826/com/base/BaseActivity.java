package nguyenvanquan7826.com.base;

import nguyenvanquan7826.com.R;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class BaseActivity extends ActionBarActivity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.actionbar_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// switch (item.getItemId()) {
		// case R.id.action_cal:
		// startActivity(new Intent(this, CalActivity.class));
		// return super.onOptionsItemSelected(item);
		// case R.id.action_bangchucai:
		// startActivity(new Intent(this, BangChuCaiActivity.class));
		// return super.onOptionsItemSelected(item);
		// default:
		// return super.onOptionsItemSelected(item);
		// }
		return super.onOptionsItemSelected(item);
	}
}