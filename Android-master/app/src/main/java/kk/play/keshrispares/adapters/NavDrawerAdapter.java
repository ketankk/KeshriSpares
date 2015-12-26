package kk.play.keshrispares.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import kk.play.keshrispares.R;
import kk.play.keshrispares.entity.NavigationDrawerItem;

/**
 * Created by sultan_mirza on 12/19/15.
 */
public class NavDrawerAdapter extends BaseAdapter {
    private Context context;
    private List<NavigationDrawerItem> navigationDrawerList;
    private LayoutInflater inflater;
    public NavDrawerAdapter(Context context, List<NavigationDrawerItem> navigationDrawerList) {
        this.context=context;
        this.navigationDrawerList=navigationDrawerList;
        this.inflater=(LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return navigationDrawerList.size();
    }

    @Override
    public Object getItem(int position) {
        return navigationDrawerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
if(convertView==null)
    convertView=inflater.inflate(R.layout.navigation_drawer,null);

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.drawer_img);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.drawer_txt);

        imgIcon.setImageResource(R.drawable.additem);
txtTitle.setText("KKK");
        return convertView;
    }

}
