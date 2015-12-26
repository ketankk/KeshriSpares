package kk.play.keshrispares.utils;

import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;

/**
 * Created by sultan_mirza on 12/6/15.
 */
public class CalendarUtil {
    private static int year;
    private static int month;
    private static int date;
    public static void setCurrentDate(View view)
    {

        TextView d=(TextView)view;
        d.setText( getCurrentDate() );
    }
    public static String getCurrentDate(){
        Calendar calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        date=calendar.get(Calendar.DATE);
        StringBuilder str=new StringBuilder();
        return date+"-"+(month+1)+"-"+year;
    }
    public static LocalDate getCurrentWeekMondayDate(){
        LocalDate today=LocalDate.now();
        LocalDate monday=today.withDayOfWeek(DateTimeConstants.MONDAY);
        return monday;
    }
    public static String getDayFromDate(String date){
        String[] cc=date.split("-");

        int yyyy=Integer.parseInt(cc[2]);
        int mm=Integer.parseInt(cc[1]);
        int dd=Integer.parseInt(cc[0]);
        DateTime dateTime=new DateTime(yyyy,mm,dd,0,0,0);

        Log.d(dateTime.dayOfWeek().getAsString()+"dAY",dateTime.dayOfWeek().getAsText());
        String day=dateTime.dayOfWeek().getAsText();
        return day;
    }
    public static LocalDate getDateFromString(String date){
        DateTimeFormatter formatter= DateTimeFormat.forPattern("dd-MM-yyyy");
        LocalDate localDate=formatter.parseLocalDate(date);
        return localDate;
    }
}
