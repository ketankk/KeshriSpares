package kk.play.keshrispares.activities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import kk.play.keshrispares.R;
import kk.play.keshrispares.adapters.ReportAdapter;
import kk.play.keshrispares.database.MysqlSynchronizer;
import kk.play.keshrispares.entity.Report;
import kk.play.keshrispares.utils.CalendarUtil;
import kk.play.keshrispares.utils.CustomReportList;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Days;
import org.joda.time.LocalDate;

public class DailyReport extends Activity{

	private TextView date;
	private TextView name;
	private TextView quantity;
	private List<Report> reports;
    private ReportAdapter adp;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reportlist);
		CustomReportList adp = new CustomReportList(this);
      ListView lview = (ListView)findViewById(R.id.reportlist);

		lview.setAdapter(adp);

		lview.setTextFilterEnabled(true);
		
		Button submit=(Button)findViewById(R.id.subbtn);
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
	syncSQLiteMysql();

			}
		});


        //displayChartDateWise(date);//just for printing only dailyWiseGraph


	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}
	
	public void syncSQLiteMysql(){
	
MysqlSynchronizer sync=new MysqlSynchronizer(DailyReport.this);
sync.synchronize();
Log.d("Synced", "snc");

	}


    public void displayChartDateWise(View view){
        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();
        adp=new ReportAdapter(this);
        reports=adp.getDailyReport();
        int i=0;
        for(Report report:reports) {

            entries.add(new BarEntry(report.getQuant(), i++));
            labels.add(report.getDate());
        }
createChart(labels,entries);

    }

    public void  displayChartCurrentWeek(View view){
        List<BarEntry> entries=new ArrayList<>();
        List<String> labels=new ArrayList<>();

        adp=new ReportAdapter(this);
        reports=adp.getDailyReport();
        LocalDate monday= CalendarUtil.getCurrentWeekMondayDate();

        for(Report report:reports){
          LocalDate date= CalendarUtil.getDateFromString(report.getDate());
//            Log.d(""+date.getWeekyear(),date.getWeekOfWeekyear()+"");
            Log.d(date+"dd",monday+"mon");
            if(date.isAfter(monday)||date.isEqual(monday)){
                Log.d(date+"dy",monday+"mn"+Days.daysBetween(monday,date).getDays());

                entries.add(new BarEntry(report.getQuant(), Days.daysBetween(monday,date).getDays()));
            }
        }
        labels.add("Mon");
        labels.add("Tue");
        labels.add("Wed");
        labels.add("Thu");
        labels.add("Fri");
        labels.add("Sat");
        labels.add("Sun");
        createChart(labels,entries);

    }

/*

Year factor not added....
 */
    public void displayMonthlyWise(View view){
        List<BarEntry> entries=new ArrayList<>();
        List<String> labels=new ArrayList<>();


        labels.add("Jan");
        labels.add("Feb");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        labels.add("Aug");
        labels.add("Sep");
        labels.add("Oct");
        labels.add("Nov");
        labels.add("Dec");
        adp=new ReportAdapter(this);
        reports=adp.getDailyReport();
LocalDate date=null;
        int quant[]={0,0,0,0,0,0,0,0,0,0,0,0};
        for (Report report:reports){
          date=  CalendarUtil.getDateFromString(report.getDate());
            quant[date.getMonthOfYear()-1]+=report.getQuant();
        }int count=0;

        for(int i:quant) {
            entries.add(new BarEntry(i, count++));

        }
        createChart(labels,entries);
    }
    private void createChart(List<String> labels,List<BarEntry> entries){
        BarChart chart=new BarChart(this);
        setContentView(chart);
        YAxis yAxis=chart.getAxisRight();
        yAxis.setAxisLineWidth(1);
        BarDataSet dataSet=new BarDataSet(entries,"No. of cycles sold");
        BarData data=new BarData(labels,dataSet);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.setData(data);

    }
}
