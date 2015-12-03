package kk.play.stockmanagement.utils;

import java.util.ArrayList;
import java.util.List;

import kk.play.stockmanagement.R;
import kk.play.stockmanagement.R.id;
import kk.play.stockmanagement.R.layout;
import kk.play.stockmanagement.database.CyclesItemDBHandler;
import kk.play.stockmanagement.entity.Cycle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class AddItem extends Activity implements OnClickListener {

	EditText compname;
	EditText modelname;

	ImageView image;
	EditText desc;
	EditText size;
	EditText quantity;
	EditText price;

	Spinner type;
	Spinner color;
	ProgressDialog pdialog;
	Button addButton;
	String inputcompName;
	String inputmodelName;

	String inputImg;
	String inputDesc;
	String inputColor;
	String inputSize;
	String inputQuant;
	String inputType;
	String inputPrice;

	Cycle cycle;
    private static int RESULT_LOAD_IMAGE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item);

		addButton = (Button) findViewById(R.id.addButton);

		//
		compname = (EditText) findViewById(R.id.compname);
		modelname = (EditText) findViewById(R.id.modelname);

		image = (ImageView) findViewById(R.id.itemimage);
		desc = (EditText) findViewById(R.id.desc);
		size = (EditText) findViewById(R.id.size);
		quantity = (EditText) findViewById(R.id.quantity);
price=(EditText) findViewById(R.id.price);
		color = (Spinner) findViewById(R.id.color);

		List<String> colorList = new ArrayList<String>();
		colorList.add("Red");
		colorList.add("Green");
		colorList.add("Black");
		colorList.add("Violet");
		colorList.add("Others");
		// list type changed
		ArrayAdapter<String> adp1 = new ArrayAdapter<String>(
				getApplicationContext(), android.R.layout.simple_list_item_1,
				colorList);
		adp1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		color.setAdapter(adp1);

		type = (Spinner) findViewById(R.id.type);
		List<String> typeList = new ArrayList<String>();
		typeList.add("Gents");
		typeList.add("Ladies");
		typeList.add("Kids");
		typeList.add("Others");

		ArrayAdapter<String> adp2 = new ArrayAdapter<String>(
				getApplicationContext(), android.R.layout.simple_list_item_1,
				typeList);
		adp2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		type.setAdapter(adp2);

		addButton.setOnClickListener(this);
		image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                 
                startActivityForResult(i, RESULT_LOAD_IMAGE);
          				
			}
		});

	}
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
 
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
 
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            inputImg = cursor.getString(columnIndex);
            cursor.close();
             
            image.setImageBitmap(BitmapFactory.decodeFile(inputImg));
         
        }
        
	}
	

	@Override
	public void onClick(View arg0) {

		inputcompName = compname.getText().toString();
		inputmodelName = modelname.getText().toString();

		//inputImg = image;
		inputDesc = desc.getText().toString();
		inputColor = color.getSelectedItem().toString();
		inputSize = size.getText().toString();
		inputQuant = quantity.getText().toString();
		inputType = type.getSelectedItem().toString();
		inputPrice=price.getText().toString();
		addButton.setClickable(false);
		cycle = new Cycle();
		if (isValid(inputcompName))
			cycle.setCompName(inputcompName);
		else
			compname.setError("Enter Name");

		if (isValid(inputmodelName))
			cycle.setModelName(inputmodelName);
		else
			compname.setError("Enter Name");

		
		if (isValid(inputQuant))
			cycle.setQuantity(Integer.parseInt(inputQuant));
		else
			quantity.setError("Enter Initial Qunatity");

		if (isValid(inputDesc))
			cycle.setDescription(inputDesc);
		else
			desc.setError("Enter Description");

		cycle.setColor(inputColor);
		if (image!=null)

			cycle.setImage(inputImg);
		else
			
            image.setImageAlpha(R.drawable.minus);

		if (isValid(inputSize))

			cycle.setSize(Integer.parseInt(inputSize));
		else
			size.setError("Enter Size");

		if (isValid(inputPrice))

			cycle.setPrice(inputPrice);
		else
			size.setError("Enter Price");

		
		cycle.setType(inputType);
		pdialog = new ProgressDialog(AddItem.this);
		pdialog.setMessage("Adding Item...");
		pdialog.setCancelable(true);
		Log.d("preex", "preexec");
		if (isValid(inputImg) && isValid(inputcompName) && isValid(inputDesc)
				&& isValid(inputQuant) && isValid(inputSize)) {

		pdialog.show();

		CyclesItemDBHandler itemHandler = new CyclesItemDBHandler(
				getApplication());
		long itemId = itemHandler.addNewCycle(cycle);
		if (itemId != -1) {
			if (itemHandler.updateQuantity(itemId, cycle.getQuantity(), false))
				Toast.makeText(getApplicationContext(),
						"Added item named " + cycle.getCompName(),
						Toast.LENGTH_SHORT).show();
			resetEditTextBoxvalues();

		} else
			Toast.makeText(getApplicationContext(), "Error in Adding Item",
					Toast.LENGTH_SHORT).show();

		
		} else {
			Toast.makeText(getApplicationContext(), "Enter Valid Data",
					Toast.LENGTH_LONG).show();
			addButton.setClickable(true);

		}// end of adding item
	}
	boolean isValid(String text) {
		if (text == null || text.length() < 1)
			return false;
		return true;
	}
	void resetEditTextBoxvalues(){
		pdialog.dismiss();
		compname.setText("");
		modelname.setText("");

		image.setImageAlpha(R.drawable.abc_btn_default_mtrl_shape);;
		desc.setText("");
		size.setText("");
		quantity.setText("");
		price.setText("");
	}

}
