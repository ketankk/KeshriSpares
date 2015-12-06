package kk.play.keshrispares.activities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import kk.play.keshrispares.R;
import kk.play.keshrispares.adapters.CycleCustomListAdapter;
import kk.play.keshrispares.database.CyclesItemDBHandler;
import kk.play.keshrispares.entity.Cycle;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

public class ShowAllCycles extends Activity {
	private TextView compName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cycles_models);
		
		Bundle extras=getIntent().getExtras();
		String type=extras.getString("type");
		String companyName=extras.getString("compname");
		CyclesItemDBHandler dbHandler = new CyclesItemDBHandler(this);

		compName=(TextView)findViewById(R.id.compname1);
		compName.setText(companyName);
List<Cycle> cycles = dbHandler.getCyclesByTypeComp(type,companyName);
		Log.d("date",System.currentTimeMillis()+" --"+new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime()));
		Log.d("showall", "jj");

		CycleCustomListAdapter adp = new CycleCustomListAdapter(this, cycles);

		ListView lview = (ListView)findViewById(R.id.models_list);

		lview.setAdapter(adp);
		

	}

	
}
