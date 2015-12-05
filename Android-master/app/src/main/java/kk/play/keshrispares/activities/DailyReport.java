package kk.play.keshrispares.activities;

import java.util.List;

import kk.play.keshrispares.R;
import kk.play.keshrispares.database.MysqlSynchronizer;
import kk.play.keshrispares.entity.Report;
import kk.play.keshrispares.utils.CustomReportList;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class DailyReport extends Activity{

	TextView date;
	TextView name;
	TextView quantity;
	List<Report> reports;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.reportlist);
		CustomReportList adp = new CustomReportList(this);

		ListView lview = (ListView)findViewById(R.id.reportlist);

		lview.setAdapter(adp);

		lview.setTextFilterEnabled(true);
		
		Button submit=(Button)findViewById(R.id.subbtn);
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
	syncSQLiteMysql();

			}
		});
		
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}
	
	public void syncSQLiteMysql(){
	
MysqlSynchronizer sync=new MysqlSynchronizer(DailyReport.this);
sync.synchronize();
Log.d("Synced", "snc");

	}

}
