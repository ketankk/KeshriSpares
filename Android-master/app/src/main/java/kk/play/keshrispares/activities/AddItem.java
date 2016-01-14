package kk.play.keshrispares.activities;

import java.util.ArrayList;
import java.util.List;


import in.kuari.keshrispares.R;
import kk.play.keshrispares.database.CyclesItemDBHandler;
import kk.play.keshrispares.entity.Cycle;
import kk.play.keshrispares.utils.CalendarUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
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

	private EditText compname;
    private EditText modelname;

    private ImageView image;
    private EditText desc;
    private EditText size;
    private EditText quantity;
    private EditText price;

    private Spinner type;
    private Spinner colors;
    private ProgressDialog pdialog;
    private Button addButton;
    private String inputcompName;
    private String inputmodelName;

    private String inputImg;
    private String inputDesc;
    private String inputColor;
    private String inputSize;
    private String inputQuant;
    private String inputType;
    private String inputPrice;

    private Cycle cycle;

    private String comingfrom;
    private String companyName_;
    private String type_;
    private static int RESULT_LOAD_IMAGE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item);
		Bundle extras=getIntent().getExtras();
        comingfrom=extras.getString("ComingFrom");

		addButton = (Button) findViewById(R.id.addButton);

		//
		compname = (EditText) findViewById(R.id.compname);
		modelname = (EditText) findViewById(R.id.modelname);

		image = (ImageView) findViewById(R.id.itemimage);
		desc = (EditText) findViewById(R.id.desc);
		size = (EditText) findViewById(R.id.size);
		quantity = (EditText) findViewById(R.id.quantity);
		price=(EditText) findViewById(R.id.price);
		colors = (Spinner) findViewById(R.id.color);

		List<String> colorList = new ArrayList();
		colorList.add("Red");
		colorList.add("Green");
		colorList.add("Black");
		colorList.add("Violet");
		colorList.add("Others");
		// list type changed
		ArrayAdapter<String> adp1 = new ArrayAdapter(
				this, android.R.layout.simple_list_item_1,
				colorList);
		adp1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		colors.setAdapter(adp1);

		type = (Spinner) findViewById(R.id.type);

		List<String> typeList = new ArrayList();
		typeList.add("Gents");
		typeList.add("Ladies");
		typeList.add("Kids");
		typeList.add("Others");

		ArrayAdapter<String> adp2 = new ArrayAdapter(
				getApplicationContext(), android.R.layout.simple_list_item_1,
				typeList);
		adp2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		type.setAdapter(adp2);

        if(comingfrom.equals("typecomp")) {
             companyName_ = extras.getString("compname");
            type_ = extras.getString("type");
            type.setVisibility(View.GONE);
            compname.setVisibility(View.GONE);
        }
        if(comingfrom.equals("type")) {
            type_ = extras.getString("type");
            type.setVisibility(View.GONE);
        }
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
            if(cursor!=null)
            cursor.moveToFirst();
 
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            inputImg = cursor.getString(columnIndex);
            cursor.close();
             
            image.setImageBitmap(BitmapFactory.decodeFile(inputImg));
         
        }
        
	}
	

	@Override
	public void onClick(View arg0) {

        if(comingfrom.equals("type")||comingfrom.equals("typecomp")) {
            inputType = type_;
            if (comingfrom.equals("typecomp"))
                inputcompName = companyName_;
            else
                inputcompName = compname.getText().toString();
        }
        else {
            inputcompName = compname.getText().toString();
            inputType = type.getSelectedItem().toString();
        }

        inputmodelName = modelname.getText().toString();

		//inputImg = image;
		inputDesc = desc.getText().toString();
		inputColor = colors.getSelectedItem().toString();
		inputSize = size.getText().toString();
		inputQuant = quantity.getText().toString();
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
		if (inputImg!=null)

			cycle.setImage(inputImg);
		else
			
            image.setImageAlpha(R.drawable.ic_launcher);

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
            final String date= CalendarUtil.getCurrentDate();

		CyclesItemDBHandler itemHandler = new CyclesItemDBHandler(
				getApplication());
		long itemId = itemHandler.addNewCycle(cycle);
		if (itemId != -1) {
			if (itemHandler.updateQuantity(itemId, cycle.getQuantity(),date, false))
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

		image.setImageAlpha(R.drawable.abc_btn_default_mtrl_shape);
		desc.setText("");
		size.setText("");
		quantity.setText("");
		price.setText("");
	}

}
