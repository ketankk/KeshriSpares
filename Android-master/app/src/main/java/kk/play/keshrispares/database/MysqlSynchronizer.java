package kk.play.keshrispares.database;

import java.util.ArrayList;
import java.util.List;

import kk.play.keshrispares.controller.VolleyServerUpdater;
import kk.play.keshrispares.entity.Cycle;
import kk.play.keshrispares.interfaces.VolleyCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class MysqlSynchronizer {
	Context context;

	public MysqlSynchronizer(Context context) {
		super();
		this.context = context;
	}



	public void getAllCyclesIdFromMysql() {
		 String url = "http://keshri.comxa.com/getfromdb.php";
		//String url = "http://kuari.in/keshrispares/getfromdb.php";

		VolleyServerUpdater updater = new VolleyServerUpdater(context);
		updater.getIdFromServerDB(new VolleyCallBack() {

			@Override
			public void onSuccess(JSONArray jsonArray) {
				List<String> cyclesId = new ArrayList();

				for (int i = 0; i < jsonArray.length(); i++) {
					try {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
Log.d("mysql", jsonObject.getString("item_id"));
						
						cyclesId.add(jsonObject.getString("item_id"));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				updateSQLiteFlag(cyclesId);
			}
		}, url);

	}

	public void updateSQLiteFlag(List<String> idsList) {
		CyclesItemDBHandler handler = new CyclesItemDBHandler(context);
		Cycle cycle;

		for (int i=0;i<idsList.size();i++) {
			String id = idsList.get(i);
			cycle = handler.getCycleById(Long.parseLong(id));
			Log.d("cycle name",cycle.getCompName()+"");
			if(cycle.getCompName()!=null){
				if (cycle.getSynced().equals("0")) {
					cycle.setSynced("1");
					handler.updateCycle(cycle);
				}
			}
		}
		
	}

	

	public void updateMySQL(List<Cycle> cycles) {
		
		//String url = "http://kuari.in/keshrispares/connection.php";
		String url = "http://keshri.comxa.com/connection.php";

		VolleyServerUpdater updater = new VolleyServerUpdater(context);
		for(Cycle cycle:cycles){
			Log.d("updateSQL", cycle.getId()+"");

		updater.updateServerDB(url,cycle);
		}
		

	}

	public void synchronize() {

		this.getAllCyclesIdFromMysql();//DB to local sync
		CyclesItemDBHandler handler=new CyclesItemDBHandler(context);
		List<Cycle>cycles=handler.getAllFalseFlagCycles();//local to db
		this.updateMySQL(cycles);
	}
}
