package kk.play.stockmanagement.controller;

import java.util.List;

import kk.play.stockmanagement.R;
import kk.play.stockmanagement.adapters.CompanyCustomListAdapter;
import kk.play.stockmanagement.adapters.CycleCustomListAdapter;
import kk.play.stockmanagement.database.CyclesItemDBHandler;
import kk.play.stockmanagement.entity.Cycle;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

public class CompanyListController {
public View createView(List<String> companies, View rootView, Activity activity,String type) {

		
		CompanyCustomListAdapter adp = new CompanyCustomListAdapter(activity, companies,type);

		ListView lview = (ListView) rootView.findViewById(R.id.list);

		lview.setAdapter(adp);

		lview.setTextFilterEnabled(true);
		return rootView;
	
}
	}
    

