package kk.play.keshrispares.controller;

import java.util.List;

import kk.play.keshrispares.R;
import kk.play.keshrispares.activities.AddItem;
import kk.play.keshrispares.adapters.CompanyCustomListAdapter;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ListView;

public class CompanyListController {
	private FloatingActionButton fabView;

	public View createView(List<String> companies, View rootView, final Activity activity, String type) {

		
		CompanyCustomListAdapter adp = new CompanyCustomListAdapter(activity, companies,type);

		ListView lview = (ListView) rootView.findViewById(R.id.companylist);
	fabView= (FloatingActionButton) rootView.findViewById(R.id.fabtype);
 final String type_=type;
		fabView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(activity,AddItem.class);
				intent.putExtra("ComingFrom","type");
				intent.putExtra("type",type_);
				activity.startActivity(intent);

			}
		});


		lview.setAdapter(adp);

		lview.setTextFilterEnabled(true);
		return rootView;
	
}
	}
    

