package kk.play.stockmanagement.utils;

import kk.play.stockmanagement.R;
import kk.play.stockmanagement.R.drawable;
import kk.play.stockmanagement.activities.StartActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.widget.Toast;

public class BroadCastReceiver extends WakefulBroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		if(isInternetConnected(context)){
		    Intent i=new Intent(context,StockManagementService.class);

		    context.startService(i);
		}
		
	}
	private boolean isInternetConnected(Context context){
		ConnectivityManager cm=	(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		return (cm.getActiveNetworkInfo()!=null);
	}
	
	void GenerateNotification(Context context) {

		Intent myIntent = new Intent(context, StartActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(
				context, 0, myIntent, Intent.FLAG_ACTIVITY_NEW_TASK);
		Notification notification = new Notification.Builder(context)
				.setContentTitle("StockManagement")
				.setContentText("You have new Notification!")
				.setTicker("Notification!").setWhen(System.currentTimeMillis())
				.setContentIntent(pendingIntent)
				.setDefaults(Notification.DEFAULT_SOUND).setAutoCancel(true)
				.setSmallIcon(R.drawable.ic_launcher)

				.build();
		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(1, notification);
	}

}
