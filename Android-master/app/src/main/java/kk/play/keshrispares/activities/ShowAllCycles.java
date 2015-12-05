package kk.play.keshrispares.activities;

import java.util.List;

import kk.play.keshrispares.R;
import kk.play.keshrispares.adapters.CycleCustomListAdapter;
import kk.play.keshrispares.database.CyclesItemDBHandler;
import kk.play.keshrispares.entity.Cycle;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class ShowAllCycles extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cycles);
		
		Bundle extras=getIntent().getExtras();
		String type=extras.getString("type");
		String companyName=extras.getString("compname");
		CyclesItemDBHandler dbHandler = new CyclesItemDBHandler(this);

List<Cycle> cycles = dbHandler.getCyclesByTypeComp(type,companyName);

		Log.d("showall", "jj");
		CycleCustomListAdapter adp = new CycleCustomListAdapter(this, cycles);

		ListView lview = (ListView)findViewById(R.id.list);

		lview.setAdapter(adp);
		

	}

	
}
