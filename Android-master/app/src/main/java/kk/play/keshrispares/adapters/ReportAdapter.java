package kk.play.keshrispares.adapters;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import kk.play.keshrispares.database.CyclesItemDBHandler;
import kk.play.keshrispares.database.DailyReportDBHandler;
import kk.play.keshrispares.entity.Report;

/**
 * Created by sultan_mirza on 12/26/15.
 */
public class ReportAdapter {
    private Context context;

    public ReportAdapter(Context context) {
        this.context=context;
    }

   public List<String> getWeeklySale(){
        DailyReportDBHandler handler=new DailyReportDBHandler((Activity)context);

        List<String> list=new ArrayList<>();
        handler.getReport();
        return list;

    }
   public List<Report> getDailyReport(){
        DailyReportDBHandler handler=new DailyReportDBHandler((Activity)context);
        List<Report> reports=handler.getReport();
       return reports;
    }
}
