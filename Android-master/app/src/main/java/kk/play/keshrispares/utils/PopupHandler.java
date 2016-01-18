package kk.play.keshrispares.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import in.kuari.keshrispares.R;
import kk.play.keshrispares.database.CyclesItemDBHandler;
import kk.play.keshrispares.entity.Cycle;

/**
 * Created by sultan_mirza on 12/6/15.
 */
public class PopupHandler {

    private  View view;
    private  Context context;
    private LayoutInflater inflater;
    private TextView popupHeader;
    private TextView datePicker;
    private Activity activity;
    private EditText newQuantity;
    private TextView quantView;
    private CoordinatorLayout coordinatorLayout;
    private String date;
    private String selYear;
    private String selMonth;
    private String selDate;
    public PopupHandler() {
    }
    public PopupHandler(View view, Context context, LayoutInflater inflater,TextView quantView) {
        this.view=view;
        this.context=context;
        this.inflater=inflater;
        activity=(Activity) context;
        this.quantView=quantView;

    }

    public void popup(final Cycle cycle){
        final String compName=cycle.getCompName();
        final long cycle_id=cycle.getId();

     date=CalendarUtil.getCurrentDate();
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                View popupQuantView = inflater.inflate(R.layout.popup, null);
                final PopupWindow popupWindow = new PopupWindow(popupQuantView,
                        ViewPager.LayoutParams.WRAP_CONTENT, ViewPager.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true);
                popupHeader = (TextView) popupQuantView
                        .findViewById(R.id.popupheader);
               // popupWindow.showAsDropDown(view, 150, 300);
                popupWindow.showAtLocation(popupQuantView, Gravity.CENTER,0,0);
                newQuantity = (EditText) popupQuantView
                        .findViewById(R.id.quantityUpdate);
                TextView btnDismiss = (TextView) popupQuantView
                        .findViewById(R.id.popupDismiss);
                popupHeader.setText(compName+" "+cycle.getModelName());

                datePicker = (TextView) popupQuantView.findViewById(R.id.datepicker);
                CalendarUtil.setCurrentDate(datePicker);


                final DatePickerDialog.OnDateSetListener onDateSetListener =new DatePickerDialog.OnDateSetListener(){

                    @Override
                    public void onDateSet(DatePicker view, int year1, int monthOfYear, int dayOfMonth) {

                        selYear=year1+"";
                        selMonth=monthOfYear+1+"";
                        if(monthOfYear<10)
                            selMonth="0"+selMonth;
                        selDate=""+dayOfMonth;

                        if(dayOfMonth<10)
                        selDate="0"+selDate;
                        date=selDate+"-"+selMonth+"-"+selYear;
                        datePicker.setText(date);

                    }
                };
String CURRENT_DATE[]=CalendarUtil.getCurrentDate().split("-");
               final int currDate=Integer.parseInt(CURRENT_DATE[0]);
                final int currMon=Integer.parseInt(CURRENT_DATE[1])-1;
                final int currYear=Integer.parseInt(CURRENT_DATE[2]);
                Log.d(currDate+currMon+currYear+"","efe");
                datePicker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog datePickerDialog=new DatePickerDialog(context,onDateSetListener,currYear,currMon,currDate);
                        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                        datePickerDialog.show();

                    }
                });




                btnDismiss.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dismissPopup(popupWindow);
                    }
                });

                TextView btnAddnew = (TextView) popupQuantView
                        .findViewById(R.id.newStock);
                btnAddnew.setOnClickListener(new TextView.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (newQuantity.getText().toString().length() > 0) {
                            dismissPopup(popupWindow);
                            final int updatedQuantity = Integer
                                    .parseInt(newQuantity.getText().toString());


                            CyclesItemDBHandler itemHandler = new CyclesItemDBHandler(
                                    activity);
                            boolean flag =itemHandler.updateQuantity(cycle_id,
                                    updatedQuantity,date, false);
                            if(flag) {
                                showSnackbar(updatedQuantity + " " + cycle.getModelName() + " Added");
                            quantView.setText(cycle.getQuantity()+updatedQuantity+"");
                                cycle.setQuantity(cycle.getQuantity()+updatedQuantity);

                            }

                        }
                    }
                });
                TextView btnSold = (TextView) popupQuantView
                        .findViewById(R.id.sold);
                btnSold.setOnClickListener(new TextView.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (newQuantity.getText().toString().length() > 0) {
                            dismissPopup(popupWindow);
                            final int updatedQuantity = Integer
                                    .parseInt(newQuantity.getText().toString());

                            CyclesItemDBHandler itemHandler = new CyclesItemDBHandler(
                                    activity);
                            boolean flag=itemHandler.updateQuantity(cycle_id,
                                    updatedQuantity,date, true);
                            if(flag) {
                                showSnackbar(updatedQuantity + " " + cycle.getModelName() + " Sold");
                                quantView.setText(cycle.getQuantity()-updatedQuantity+"");
                                cycle.setQuantity(cycle.getQuantity()-updatedQuantity);


                            }
                        }
                    }
                });

            }

        });


    }
    public  void showSnackbar(String msg){
        coordinatorLayout =(CoordinatorLayout) activity.findViewById(R.id.coordinatorlayout);
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, msg, Snackbar.LENGTH_LONG);

        snackbar.show();
    }
    void dismissPopup(PopupWindow popupWindow){

        popupWindow.dismiss();

    }
}
