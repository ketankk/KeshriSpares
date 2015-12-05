package kk.play.keshrispares.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class ImageDownload extends AsyncTask<String, Void, Bitmap> {
	ImageView imageView;

	public ImageDownload(ImageView imageView) {
		super();
this.imageView=imageView;	}

	@Override
	protected Bitmap doInBackground(String... urls) {
		String urldisplay = urls[0];
        Bitmap bitmapImage = null;
        bitmapImage=(BitmapFactory.decodeFile(urldisplay));

        return bitmapImage;
	}
	
	protected void onPostExecute(Bitmap result){
		imageView.setImageBitmap(result);
	}
	

}
