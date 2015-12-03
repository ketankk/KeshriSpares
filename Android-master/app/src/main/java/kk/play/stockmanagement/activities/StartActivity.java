package kk.play.stockmanagement.activities;

import kk.play.stockmanagement.R;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

public class StartActivity extends Activity {
	Notification notification;
	NotificationManager notificationManager;
	Intent intent1,intent2;
	ActionBarDrawerToggle mDrawerToggle;
	String mTitle;
DrawerLayout mDrawerLayout;
//private String[] mPlanetTitles;
ListView mDrawerList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		/*mPlanetTitles=new String[]{"One","Two","Three"};
		mTitle="test";
		mDrawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
		 mDrawerList = (ListView) findViewById(R.id.left_drawer);
		mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_layout,mPlanetTitles));
		mDrawerToggle = new ActionBarDrawerToggle(
                this,                   host Activity 
                mDrawerLayout,          DrawerLayout object 
                R.drawable.ic_action_search,   nav drawer icon to replace 'Up' caret 
                R.string.drawer_open
        ) {

            *//** Called when a drawer has settled in a completely closed state. *//*
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
            }

            *//** Called when a drawer has settled in a completely open state. *//*
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mTitle);
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);


   
*/		
		ImageButton btn1 = (ImageButton) findViewById(R.id.btn1);
		intent1 = new Intent(this, MainActivity.class);

		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//GenerateNotification();

						startActivity(intent1);

			}
		});

		ImageButton btn2 = (ImageButton) findViewById(R.id.btn2);
		intent2 = new Intent(this, DailyReport.class);

		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//GenerateNotification();

						startActivity(intent2);

			}
		});

	
	}

	void GenerateNotification() {

		Context context = getApplicationContext();
		Intent myIntent = new Intent(context, StartActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(
				StartActivity.this, 0, myIntent, Intent.FLAG_ACTIVITY_NEW_TASK);
		notification = new Notification.Builder(context)
				.setContentTitle("StockManagement")
				.setContentText("You have new Notification!")
				.setTicker("Notification!").setWhen(System.currentTimeMillis())
				.setContentIntent(pendingIntent)
				.setDefaults(Notification.DEFAULT_SOUND).setAutoCancel(true)
				.setSmallIcon(R.drawable.ic_launcher)

				.build();
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(1, notification);
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

}
