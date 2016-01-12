package kk.play.keshrispares.adapters;

import java.util.List;

import kk.play.keshrispares.R;
import kk.play.keshrispares.activities.ModelsDisplay;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class CompanyCustomListAdapter extends RecyclerView.Adapter<CompanyCustomListAdapter.ViewHolder> {

	private Activity activity;
	private List<String> companies;
	private String type;
	private String name;
	public CompanyCustomListAdapter(Activity activity, List<String> companies,String type) {
		this.activity = activity;
		this.companies = companies;
		this.type=type;

			}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return companies.size();
	}


	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
@Override
	public CompanyCustomListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
		View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.companieslist,parent,false);
	ViewHolder vh=new ViewHolder(v);
	return  vh;
	}
	@Override
	public void onBindViewHolder(ViewHolder holder, int position)  {


		String name=companies.get(position);
        holder.compname.setText(name);
        final String name1=name;
        holder.vi.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
            Log.d("ddd", name1+type);
		Intent intent=new Intent(activity,ModelsDisplay.class);
		intent.putExtra("compname", name1);
		intent.putExtra("type", type);
		activity.startActivity(intent);
	}
});

	}

	 static class ViewHolder extends RecyclerView.ViewHolder{
		TextView compname;
    View vi;
		public ViewHolder(View vi) {
			super(vi);
            this.vi=vi;
			compname=(TextView)vi.findViewById(R.id.companyname);
		}
	}

}
