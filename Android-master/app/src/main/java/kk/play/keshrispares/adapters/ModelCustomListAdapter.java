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
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager.LayoutParams;
import android.support.v7.widget.RecyclerView;
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

public class ModelCustomListAdapter extends RecyclerView.Adapter<ModelCustomListAdapter.ViewHolder> {

    private Activity activity;
    private List<Cycle> cycles;
    private Cycle cycle;

    boolean viewGroupIsVisible = true;
    private EditText newQuantity;
private LayoutInflater inflater;
    public ModelCustomListAdapter(Activity activity, List<Cycle> cycles) {
        this.activity = activity;
        this.cycles = cycles;
        inflater=(LayoutInflater.from(activity));

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);
            ViewHolder vh=new ViewHolder(v);
            return  vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        cycle = cycles.get(position);// Get cycle from list of cycles using

        final long cycle_id = cycle.getId();// item Id from itemTable
        final String compName = cycle.getCompName();
        holder.name.setText(cycle.getModelName());
        holder.lastUpdatedOn.setText(cycle.getLastUpdatedDate());
        holder.updateTime.setText(cycle.getLastUpdatedTime());
        holder.quantity.setText(cycle.getQuantity() + "");


//Picasso Image Loading

        Picasso.with(activity).load(new File(cycle.getImage())).resize(100, 100).into(holder.cycleImage);

        holder. description.setText(cycle.getDescription());
View vi1=holder.vi;
        PopupHandler popupHandler=new PopupHandler(vi1,activity,inflater);
        popupHandler.popup(cycle);

       holder.vi.setOnLongClickListener(new OnLongClickListener() {

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
        setBackgroundColor(holder.vi,cycle.getColor());
    }

    public void setBackgroundColor(View vi,String color){
        int colorcode= Color.DKGRAY;
        switch (color){
            case "Red":
                colorcode=Color.parseColor("#FFF2424B");
                break;
            case "Black":
                colorcode=Color.BLACK;

                break;
            case "Green":
                colorcode=Color.parseColor("#FF087604");

                break;
            case "Violet":
                colorcode=Color.parseColor("#FF7F05DC");

                break;
            default:
                break;
        }
        vi.setBackgroundColor(colorcode);
    }


    @Override
    public int getItemCount() {
        return cycles.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

static class ViewHolder extends RecyclerView.ViewHolder{
    private TextView quantity;
    private TextView updateTime;
    private TextView lastUpdatedOn;
    private TextView name;
    private TextView description;
   private ImageView cycleImage;
private  View vi;
    public ViewHolder(View vi) {
        super(vi);
this.vi=vi;
        lastUpdatedOn = (TextView) vi.findViewById(R.id.lastupdated);
        updateTime = (TextView) vi.findViewById(R.id.updatedTime);
        name = (TextView) vi.findViewById(R.id.modelname);
        quantity = (TextView) vi.findViewById(R.id.quant);
        cycleImage = (ImageView) vi.findViewById(R.id.cycle_image);
        description = (TextView) vi.findViewById(R.id.description);


    }
}

}
