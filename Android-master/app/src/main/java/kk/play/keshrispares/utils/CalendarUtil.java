package kk.play.keshrispares.utils;

import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

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
}
