package kk.play.keshrispares.adapters;

import java.io.File;
import java.util.List;

import kk.play.keshrispares.R;
import kk.play.keshrispares.database.CyclesItemDBHandler;
import kk.play.keshrispares.entity.Cycle;
import kk.play.keshrispares.activities.EditItem;
import kk.play.keshrispares.utils.CalendarUtil;
import kk.play.keshrispares.utils.LoadImage;
import kk.play.keshrispares.utils.PopupHandler;

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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ModelCustomListAdapter extends BaseAdapter {

    private Activity activity;
    private List<Cycle> cycles;
    private Cycle cycle;
    private TextView quantity;
    private TextView updateTime;
    private TextView lastUpdatedOn;
    private TextView name;
    private TextView description;


    private static LayoutInflater inflater = null;
    boolean viewGroupIsVisible = true;
    View vi1;
    private EditText newQuantity;

    public ModelCustomListAdapter(Activity activity, List<Cycle> cycles) {
        this.activity = activity;
        this.cycles = cycles;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        name = (TextView) vi.findViewById(R.id.modelname); // cycle name
        quantity = (TextView) vi.findViewById(R.id.quant); // quantity
        ImageView cycleImage = (ImageView) vi.findViewById(R.id.cycle_image); // thumb

        description = (TextView) vi.findViewById(R.id.description); // cycle
        // name
        cycle = cycles.get(position);// Get cycle from list of cycles using

        final long cycle_id = cycle.getId();// item Id from itemTable
        final String compName = cycle.getCompName();
        name.setText(cycle.getModelName());
        lastUpdatedOn.setText(cycle.getLastUpdatedDate());
        updateTime.setText(cycle.getLastUpdatedTime());
        quantity.setText(cycle.getQuantity() + "");


//Picasso Image Loading

        Picasso.with(activity).load(new File(cycle.getImage())).resize(100, 100).into(cycleImage);

        description.setText(cycle.getDescription());

        PopupHandler popupHandler=new PopupHandler(vi1,activity,inflater);
        popupHandler.popup(cycle);

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
