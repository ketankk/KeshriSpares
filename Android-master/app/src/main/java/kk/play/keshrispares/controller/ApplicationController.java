package kk.play.keshrispares.controller;



import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import android.app.Application;
import android.text.TextUtils;

public class ApplicationController extends Application{

	static final String TAG = ApplicationController.class.getSimpleName();
	RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;
	private static ApplicationController mInstance;
@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
	}

	public static synchronized ApplicationController getInstance() {
					return mInstance;

	}

	RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {

			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}
		return mRequestQueue;
	}

	public <T> void addToRequestQueue(Request<T> req, String tag) {
		req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
		getRequestQueue().add(req);

	}

	public <T> void addToRequestQueue(Request<T> req) {
		req.setTag(TAG);
		getRequestQueue().add(req);

	}

	public void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null)
			mRequestQueue.cancelAll(tag);
	}
}
