package kk.play.stockmanagement.controller;

import java.util.List;

import kk.play.stockmanagement.R;
import kk.play.stockmanagement.adapters.CycleCustomListAdapter;
import kk.play.stockmanagement.database.CyclesItemDBHandler;
import kk.play.stockmanagement.entity.Cycle;
import android.app.Activity;
import android.view.View;
import android.widget.ListView;

public class CycleListController{
	
	
	public View createView(View rootView, Activity activity,String type) {
		CyclesItemDBHandler gentsHandler = new CyclesItemDBHandler(activity);

		List<Cycle> cycles = gentsHandler.getCyclesByType(type);

		
		CycleCustomListAdapter adp = new CycleCustomListAdapter(activity, cycles);

		ListView lview = (ListView) rootView.findViewById(R.id.list);

		lview.setAdapter(adp);

		lview.setTextFilterEnabled(true);
		return rootView;
	
}
	
}