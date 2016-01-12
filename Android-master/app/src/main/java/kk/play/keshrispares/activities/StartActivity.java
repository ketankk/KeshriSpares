package kk.play.keshrispares.activities;

import kk.play.keshrispares.R;
import kk.play.keshrispares.adapters.NavDrawerAdapter;
import kk.play.keshrispares.entity.NavigationDrawerItem;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.transition.Explode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends Activity {
	Notification notification;
	NotificationManager notificationManager;
	Intent intent1,intent3,intent2;
	ActionBarDrawerToggle mDrawerToggle;

	private DrawerLayout mDrawerLayout;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private Activity activity;
//private String[] mPlanetTitles;
ListView mDrawerList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
		setContentView(R.layout.activity_start);
activity=this;

		ImageButton btn1 = (ImageButton) findViewById(R.id.btn1);
		intent1 = new Intent(this, CompanyDisplay.class);

		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//GenerateNotification();
				getWindow().setExitTransition(new Explode());

						startActivity(intent1,ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());

			}
		});

		ImageButton btn2 = (ImageButton) findViewById(R.id.btn2);
		intent2 = new Intent(this, DailyReport.class);

		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//GenerateNotification();
getWindow().setExitTransition(new Explode());
						startActivity(intent2, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());

			}
		});

		ImageButton btn3 = (ImageButton) findViewById(R.id.btn3);
		intent3 = new Intent(this, DisplayCyclesSearch.class);

		btn3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//GenerateNotification();
				getWindow().setExitTransition(new Explode());

				startActivity(intent3,ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());

			}
		});


	}

	void GenerateNotification() {

		/*Context context = getApplicationContext();
		Intent myIntent = new Intent(context, StartActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(
				StartActivity.this, 0, myIntent, Intent.FLAG_ACTIVITY_CLEAR_TASK);
		notification = new Notification.Builder(context)
				.setContentTitle("StockManagement")
				.setContentText("You have new Notification!")
				.setTicker("Notification!").setWhen(System.currentTimeMillis())
				.setContentIntent(pendingIntent)
				.setDefaults(Notification.DEFAULT_SOUND).setAutoCancel(true)
				.setSmallIcon(R.drawable.ic_launcher)

				.build();
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(1, notification);*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	/*
	 * @Override protected void onActivityResult(int requestCode, int
	 * resultCode, Intent data) { if (requestCode == 1) { if (resultCode ==
	 * RESULT_OK) { String result = data.getStringExtra("result"); Log.d("TAG",
	 * result); }
	 * 
	 * if (resultCode == RESULT_CANCELED) { String result =
	 * data.getStringExtra("result"); Log.d("TAGCancel", result); } } }
	 */
void populateDrawer(){
TypedArray navMenuIcons;
	List<NavigationDrawerItem> navigationDrawerItems;
	ListView mDrawerList= (ListView) findViewById(R.id.left_drawer);

	mDrawerTitle=mTitle=getTitle();

navigationDrawerItems =new ArrayList<NavigationDrawerItem>();

	NavigationDrawerItem navigationDrawerItem=new NavigationDrawerItem();
	navigationDrawerItem.setIcon("");
	navigationDrawerItem.setTitle("JJ");

	navigationDrawerItems.add(navigationDrawerItem);
	navigationDrawerItems.add(navigationDrawerItem);

	mDrawerLayout=(DrawerLayout) findViewById(R.id.drawerlayout);

	NavDrawerAdapter adapter=new NavDrawerAdapter(getApplicationContext(),navigationDrawerItems);


mDrawerList.setAdapter(adapter);
mDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.app_name,R.string.app_name);
mDrawerLayout.setDrawerListener(mDrawerToggle);

}
}
