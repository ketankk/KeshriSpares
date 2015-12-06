package kk.play.keshrispares.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import kk.play.keshrispares.R;
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
    public PopupHandler() {
    }
    public PopupHandler(View view, Context context, LayoutInflater inflater) {
        this.view=view;
        this.context=context;
        this.inflater=inflater;
        activity=(Activity) context;

    }

    public void popup(Cycle cycle){
        final String compName=cycle.getCompName();
        final long cycle_id=cycle.getId();
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                View popupQuantView = inflater.inflate(R.layout.popup, null);
                final PopupWindow popupWindow = new PopupWindow(popupQuantView,
                        ViewPager.LayoutParams.WRAP_CONTENT, ViewPager.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true);
                popupHeader = (TextView) popupQuantView
                        .findViewById(R.id.popupheader);
                popupWindow.showAsDropDown(view, 150, 300);
                newQuantity = (EditText) popupQuantView
                        .findViewById(R.id.quantityUpdate);
                TextView btnDismiss = (TextView) popupQuantView
                        .findViewById(R.id.popupDismiss);
                popupHeader.setText(compName);

                datePicker = (TextView) popupQuantView.findViewById(R.id.datepicker);
                CalendarUtil.setCurrentDate(datePicker);

                btnDismiss.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                TextView btnAddnew = (TextView) popupQuantView
                        .findViewById(R.id.newStock);
                btnAddnew.setOnClickListener(new TextView.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (newQuantity.getText().toString().length() > 0) {
                            popupWindow.dismiss();
                            final int updatedQuantity = Integer
                                    .parseInt(newQuantity.getText().toString());

                            Toast.makeText(context, updatedQuantity + "",
                                    Toast.LENGTH_SHORT).show();
                            CyclesItemDBHandler itemHandler = new CyclesItemDBHandler(
                                    activity);
                            itemHandler.updateQuantity(cycle_id,
                                    updatedQuantity, false);

                        }
                    }
                });
                TextView btnSold = (TextView) popupQuantView
                        .findViewById(R.id.sold);
                btnSold.setOnClickListener(new TextView.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (newQuantity.getText().toString().length() > 0) {

                            popupWindow.dismiss();
                            final int updatedQuantity = Integer
                                    .parseInt(newQuantity.getText().toString());

                            Toast.makeText(activity, updatedQuantity + "",
                                    Toast.LENGTH_SHORT).show();
                            CyclesItemDBHandler itemHandler = new CyclesItemDBHandler(
                                    activity);
                            itemHandler.updateQuantity(cycle_id,
                                    updatedQuantity, true);
                        }
                    }
                });

            }

        });

    }
}
