package kk.play.keshrispares.database;

import java.util.ArrayList;
import java.util.List;

import kk.play.keshrispares.entity.Report;
import android.app.Activity;
import android.util.Log;

public class DailyReportDBHandler {
	
	Activity context;
	Report report;

	public DailyReportDBHandler(Activity context){
		this.context=context;
	}
	List<Report> reports=new ArrayList<Report>();

	CyclesItemDBHandler handler;
	
	public List<Report> getReport(){
		handler=new CyclesItemDBHandler(context);
		List<String> dates=handler.getAllDistinctDates();
		for(String date:dates){
			long quant=handler.getTotalSales(date);

			report=new Report();

			report.setDate(date);
			report.setQuant(quant);
			reports.add(report);


		}
		return reports;
	}
	

}

	
