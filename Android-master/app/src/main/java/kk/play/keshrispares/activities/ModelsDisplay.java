package kk.play.keshrispares.activities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import in.kuari.keshrispares.R;
import kk.play.keshrispares.adapters.ModelCustomListAdapter;
import kk.play.keshrispares.database.CyclesItemDBHandler;
import kk.play.keshrispares.entity.Cycle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ModelsDisplay extends Activity {
	private TextView compName;
	private FloatingActionButton fabView;
private RecyclerView.LayoutManager layoutManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cycles_models);





		Bundle extras=getIntent().getExtras();
		final String type=extras.getString("type");
		final String companyName=extras.getString("compname");
		CyclesItemDBHandler dbHandler = new CyclesItemDBHandler(this);





		compName=(TextView)findViewById(R.id.compname1);
		compName.setText(companyName);
		fabView=(FloatingActionButton)findViewById(R.id.fab);
		List<Cycle> cycles = dbHandler.getCyclesByTypeComp(type,companyName);
		Log.d("date",System.currentTimeMillis()+" --"+new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime()));





		ModelCustomListAdapter adp = new ModelCustomListAdapter(this, cycles);

		RecyclerView recyclerView = (RecyclerView)findViewById(R.id.models_list);

		layoutManager=new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setAdapter(adp);
//final CoordinatorLayout coordinatorLayout=(CoordinatorLayout) findViewById(R.id.coordinatorlayout);

		fabView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(ModelsDisplay.this,AddItem.class);
                intent.putExtra("ComingFrom","typecomp");
                intent.putExtra("compname",companyName);
                intent.putExtra("type",type);
				startActivity(intent);

			}
		});
		

	}

	
}
