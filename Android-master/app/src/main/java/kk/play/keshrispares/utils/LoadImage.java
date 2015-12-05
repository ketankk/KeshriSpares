package kk.play.keshrispares.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by sultan_mirza on 12/4/15.
 */
public class LoadImage extends AsyncTask<ImageView,Void,Bitmap>{
    String path;

ImageView imageView;
    Bitmap myBitmap;
public LoadImage(String path,ImageView imageView){
    this.imageView=imageView;
    this.path=path;
}
    @Override
    protected Bitmap doInBackground(ImageView... params) {


        File imgfile=new File(path);
        // image
        Log.d("Path",path);
        if(imgfile.exists()){
            myBitmap = BitmapFactory.decodeFile(imgfile.getAbsolutePath());

        }



        return myBitmap;

    }
    @Override
    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
    }
}
