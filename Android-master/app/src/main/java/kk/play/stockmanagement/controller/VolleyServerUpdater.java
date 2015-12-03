package kk.play.stockmanagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kk.play.stockmanagement.entity.Cycle;
import kk.play.stockmanagement.interfaces.VolleyCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;

public class VolleyServerUpdater {
	Context context;
	String tag = "string_req";
List<Cycle> cycles;
	public VolleyServerUpdater(Context context) {
		super();
		this.context = context;
	}

	public void updateServerDB(String url,Cycle cycle) {
		
		Map<String, String> params = new HashMap<String, String>();
			params.put("id", cycle.getId()+"");	
			params.put("compname", cycle.getCompName());
			params.put("modelname", cycle.getModelName());
			params.put("img", cycle.getImage());
			params.put("desc", cycle.getDescription());
			params.put("color",cycle.getColor());
			params.put("size", cycle.getSize()+"");
			params.put("types", cycle.getType());
			params.put("price", cycle.getPrice());
			
		
		CustomRequest jsObjRequest = new CustomRequest(Method.POST, url, params, new Response.Listener<JSONArray>() {

		            @Override
		            public void onResponse(JSONArray response) {
		            
		                Log.d("Response: ", response.toString());

		            }
		        }, new Response.ErrorListener() {

		            @Override
		            public void onErrorResponse(VolleyError response) {
		                Log.d("Response: ", response.toString());
		            }
		        });

		ApplicationController.getInstance().addToRequestQueue(jsObjRequest, tag);
	}

	public JSONObject getIdFromServerDB(final VolleyCallBack callback,String url) {
		JSONObject response1=null;
		CustomRequest jsObjRequest = new CustomRequest(Method.POST, url,null,new Response.Listener<JSONArray>() {

		            @Override
		            public void  onResponse(JSONArray response) {
		                try {
							Log.d("Response: ", response.getJSONObject(0).getString("item_id"));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		                //response1=response;
		                callback.onSuccess(response);

		            }
		        }, new Response.ErrorListener() {

		            @Override
		            public void onErrorResponse(VolleyError response) {
		                Log.d("Response: ", response.toString());
		            }
		        });

		ApplicationController.getInstance().addToRequestQueue(jsObjRequest, tag);
		return response1;
	}


}
