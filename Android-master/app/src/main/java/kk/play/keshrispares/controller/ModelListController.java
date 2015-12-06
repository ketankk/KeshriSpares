package kk.play.keshrispares.controller;

import java.util.List;

import kk.play.keshrispares.R;
import kk.play.keshrispares.adapters.ModelCustomListAdapter;
import kk.play.keshrispares.database.CyclesItemDBHandler;
import kk.play.keshrispares.entity.Cycle;
import android.app.Activity;
import android.view.View;
import android.widget.ListView;

public class ModelListController {
	
	
	public View createView(View rootView, Activity activity,String type) {
		CyclesItemDBHandler gentsHandler = new CyclesItemDBHandler(activity);

		List<Cycle> cycles = gentsHandler.getCyclesByType(type);

		
		ModelCustomListAdapter adp = new ModelCustomListAdapter(activity, cycles);

		ListView lview = (ListView) rootView.findViewById(R.id.companylist);

		lview.setAdapter(adp);

		lview.setTextFilterEnabled(true);
		return rootView;
	
}
	
}