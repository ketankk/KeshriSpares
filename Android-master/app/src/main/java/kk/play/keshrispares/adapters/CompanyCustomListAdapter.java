package kk.play.keshrispares.adapters;

import java.util.List;

import kk.play.keshrispares.R;
import kk.play.keshrispares.activities.ShowAllCycles;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CompanyCustomListAdapter extends BaseAdapter {

	Activity activity;
	List<String> companies;
	LayoutInflater inflater;
	String type;
	String name;
	public CompanyCustomListAdapter(Activity activity, List<String> companies,String type) {
		this.activity = activity;
		this.companies = companies;
		this.type=type;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return companies.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertview, ViewGroup root) {
View vi=convertview;
if(vi==null){
	vi=inflater.inflate(R.layout.companieslist, null);
}
TextView compname=(TextView)vi.findViewById(R.id.companyname);

String name=companies.get(position);
compname.setText(name);
final String name1=name;
vi.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		Log.d("ddd", name1+type);
		//Toast.makeText(activity, name1+type, Toast.LENGTH_LONG).show();
		Intent intent=new Intent(activity,ShowAllCycles.class);
		intent.putExtra("compname", name1);
		intent.putExtra("type", type);
		activity.startActivity(intent);
	}
});
		return vi;
	}

	

}
