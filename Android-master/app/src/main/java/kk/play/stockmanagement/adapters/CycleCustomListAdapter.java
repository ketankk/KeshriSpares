package kk.play.stockmanagement.adapters;

import java.util.List;

import kk.play.stockmanagement.R;
import kk.play.stockmanagement.controller.ApplicationController;
import kk.play.stockmanagement.database.CyclesItemDBHandler;
import kk.play.stockmanagement.entity.Cycle;
import kk.play.stockmanagement.utils.EditItem;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;

public class CycleCustomListAdapter extends BaseAdapter {

	private Activity activity;
	private List<Cycle> cycles;
	Cycle cycle;
	TextView quantity;
	TextView updateTime;
	TextView lastUpdatedOn;
	TextView name;
	TextView description;
	TextView popupHeader;

	ImageLoader imageloader = ApplicationController.getInstance()
			.getImageLoader();
	private String serverUrl = "";
	private static LayoutInflater inflater = null;
	boolean viewGroupIsVisible = true;
	View vi1;
	EditText newQuantity;

	public CycleCustomListAdapter(Activity activity, List<Cycle> cycles) {
		this.activity = activity;
		this.cycles = cycles;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// imageloader =new ImageLoader(activity.getApplicationContext());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		if (vi == null) {
			vi = inflater.inflate(R.layout.list_row, null);
		}
		vi1 = vi;

		lastUpdatedOn = (TextView) vi.findViewById(R.id.lastupdated); // last
		// updated
		// on
		updateTime = (TextView) vi.findViewById(R.id.updatedTime); // last

		name = (TextView) vi.findViewById(R.id.name); // cycle name
		quantity = (TextView) vi.findViewById(R.id.quant); // quantity
		ImageView cycleImage = (ImageView) vi.findViewById(R.id.cycle_image); // thumb
		// image
		description = (TextView) vi.findViewById(R.id.description); // cycle
		// name
		cycle = cycles.get(position);// Get cycle from list of cycles using

		final long cycle_id = cycle.getId();// item Id from itemTable
		final String compName = cycle.getCompName();
		name.setText(cycle.getModelName());
		lastUpdatedOn.setText(cycle.getLastUpdatedDate());
		updateTime.setText(cycle.getLastUpdatedTime());
		quantity.setText(cycle.getQuantity() + "");
		serverUrl += cycle.getImage();
		
		String imgurl="http://www.hypercityindia.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/m/a/maxit_enduro_50_mx_cycle.jpg";
		cycle.setImage(imgurl);
		//new ImageDownload(cycleImage).execute(cycle.getImage());// Load
		// image
		// Asynchronously

		if (imageloader == null) {
			imageloader = ApplicationController.getInstance().getImageLoader();
		}
		// NetworkImageView
		// cycleImage=(NetworkImageView)vi.findViewById(R.id.cycle_image);
		/**
		 * CACHE used for uploading image...not working as on 20-10-2015...check
		 * LruBitmapCache class
		 */
		// cycleImage.setImageUrl("http://bit.ly/1jXCKkH",imageloader);//change
		// the url of image****
		//cycleImage.setImageBitmap(BitmapFactory.decodeFile("http://www.hypercityindia.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/m/a/maxit_enduro_50_mx_cycle.jpg"));

		description.setText(cycle.getDescription());
		vi1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				View popupQuantView = inflater.inflate(R.layout.popup, null);
				final PopupWindow popupWindow = new PopupWindow(popupQuantView,
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				popupWindow.setFocusable(true);
				popupHeader = (TextView) popupQuantView
						.findViewById(R.id.popupheader);
				popupWindow.showAsDropDown(vi1, 80, 230);

				newQuantity = (EditText) popupQuantView
						.findViewById(R.id.quantityUpdate);
				Button btnDismiss = (Button) popupQuantView
						.findViewById(R.id.popupDismiss);
				popupHeader.setText(compName);
				btnDismiss.setOnClickListener(new Button.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
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

							Toast.makeText(activity, updatedQuantity + "",
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

		vi1.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View arg0) {

				
				 Toast.makeText(activity, cycle_id + "", Toast.LENGTH_LONG)
				 .show();
				 

				Intent intent = new Intent(activity, EditItem.class);
intent.putExtra("cycleId", cycle_id);
activity.startActivity(intent);
				return true;
			}
		});
		return vi;
	}

	@Override
	public int getCount() {
		return cycles.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}
