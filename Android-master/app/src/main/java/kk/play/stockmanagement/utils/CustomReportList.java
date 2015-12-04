package kk.play.stockmanagement.utils;

import java.util.List;

import kk.play.stockmanagement.R;
import kk.play.stockmanagement.database.DailyReportDBHandler;
import kk.play.stockmanagement.entity.Report;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomReportList extends BaseAdapter {
	LayoutInflater inflater;
	List<Report> reports;
	Activity activity;
	public CustomReportList(Activity activity) {
		this.activity=activity;

		DailyReportDBHandler handler=new DailyReportDBHandler(activity);
		this.reports=handler.getReport();

		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return reports.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (vi == null)
			vi = inflater.inflate(R.layout.report, null);
		
		TextView date=(TextView)vi.findViewById(R.id.reportdate);
		
		TextView quant=(TextView)vi.findViewById(R.id.reportquant);
		Report report=reports.get(position);
		date.setText(report.getDate());
		quant.setText(report.getQuant()+"");
		
		
		
		
		
		return vi;
	}

}
