package kk.play.keshrispares.controller;

import java.util.List;

import kk.play.keshrispares.R;
import kk.play.keshrispares.adapters.CompanyCustomListAdapter;

import android.app.Activity;
import android.view.View;
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
    

