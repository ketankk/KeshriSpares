package kk.play.keshrispares.controller;

import java.util.List;

import in.kuari.keshrispares.R;
import kk.play.keshrispares.activities.AddItem;
import kk.play.keshrispares.adapters.CompanyCustomListAdapter;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class CompanyListController {
	private FloatingActionButton fabView;
	private RecyclerView.LayoutManager layoutManager;

	public View createView(List<String> companies, View rootView, final Activity activity, String type) {

		
		CompanyCustomListAdapter adp = new CompanyCustomListAdapter(activity, companies,type);

		RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.companylist);
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

layoutManager=new LinearLayoutManager(activity);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setAdapter(adp);

		//lview.setTextFilterEnabled(true);
		return rootView;
	
}
	}
    

