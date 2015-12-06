package kk.play.keshrispares.activities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import kk.play.keshrispares.R;
import kk.play.keshrispares.adapters.ModelCustomListAdapter;
import kk.play.keshrispares.database.CyclesItemDBHandler;
import kk.play.keshrispares.entity.Cycle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class ShowAllCycles extends Activity {
	private TextView compName;
	private FloatingActionButton fabView;

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
		fabView=(FloatingActionButton)findViewById(R.id.fab);
		List<Cycle> cycles = dbHandler.getCyclesByTypeComp(type,companyName);
		Log.d("date",System.currentTimeMillis()+" --"+new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime()));
		Log.d("showall", "jj");

		ModelCustomListAdapter adp = new ModelCustomListAdapter(this, cycles);

		ListView lview = (ListView)findViewById(R.id.models_list);

		lview.setAdapter(adp);
final CoordinatorLayout coordinatorLayout=(CoordinatorLayout) findViewById(R.id.coordinatorlayout);

		fabView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(ShowAllCycles.this,AddItem.class);

				startActivity(intent);
				/*Snackbar snackbar = Snackbar
						.make(coordinatorLayout, "Message is deleted", Snackbar.LENGTH_LONG)
						.setAction("UNDO", new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Message is restored!", Snackbar.LENGTH_SHORT);
								snackbar1.show();
							}
						});

				snackbar.show();*/
			}
		});
		

	}

	
}
