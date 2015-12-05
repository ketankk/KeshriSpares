package kk.play.keshrispares.activities;

import java.util.ArrayList;
import java.util.List;

import kk.play.keshrispares.R;
import kk.play.keshrispares.database.CyclesItemDBHandler;
import kk.play.keshrispares.entity.Cycle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditItem extends Activity implements OnClickListener{
	EditText compNameEdit;
	EditText modelNameEdit;

	ImageView imageEdit;
	EditText descEdit;
	EditText sizeEdit;
	EditText priceEdit;
	TextView quantityEdit;
	Spinner typeEdit;
	Spinner colorEdit;
	
	Button editButton;
Button deleteButton;

	String inputCompName;
	String inputModelName;
	String inputImgPath;
	String inputDesc;
	String inputColor;
	String inputSize;
	String inputQuant;
	String inputType;
	String inputPrice;
	Context context;
	boolean isUpdated;
	boolean isDeleted;

	long cycleId;
	private static int RESULT_LOAD_IMAGE = 1;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_item);
		context = getApplicationContext();
		Bundle extras = getIntent().getExtras();
		if (extras != null)
			cycleId = extras.getLong("cycleId");
		CyclesItemDBHandler handler = new CyclesItemDBHandler(
				getApplicationContext());
		Cycle cycle = handler.getCycleById(cycleId);
		editButton = (Button) findViewById(R.id.editButton);

		//
		compNameEdit = (EditText) findViewById(R.id.editcompanyname);
		modelNameEdit = (EditText) findViewById(R.id.editmodelname);

		imageEdit = (ImageView) findViewById(R.id.editimage);
		descEdit = (EditText) findViewById(R.id.editdesc);
		sizeEdit = (EditText) findViewById(R.id.editsize);
		quantityEdit = (TextView) findViewById(R.id.editquantity);
		priceEdit = (EditText) findViewById(R.id.editprice);

		colorEdit = (Spinner) findViewById(R.id.editcolor);
		deleteButton=(Button)findViewById(R.id.deleteItem);
		compNameEdit.setText(cycle.getCompName());
		modelNameEdit.setText(cycle.getModelName());

		imageEdit.setImageBitmap(BitmapFactory.decodeFile(cycle.getImage()));
		descEdit.setText(cycle.getDescription());
		sizeEdit.setText(cycle.getSize() + "");
		final String inputSynced=cycle.getSynced();

		quantityEdit.setText(cycle.getQuantity() + "");
priceEdit.setText(cycle.getPrice());
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
		colorEdit.setAdapter(adp1);

		typeEdit = (Spinner) findViewById(R.id.edittype);
		List<String> typeList = new ArrayList<String>();
		typeList.add("Gents");
		typeList.add("Ladies");
		typeList.add("Kids");
		typeList.add("Others");

		imageEdit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

				startActivityForResult(i, RESULT_LOAD_IMAGE);


			}
		});

		ArrayAdapter<String> adp2 = new ArrayAdapter<String>(
				getApplicationContext(), android.R.layout.simple_list_item_1,
				typeList);
		adp2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		typeEdit.setAdapter(adp2);
		Button dismissPopup = (Button) findViewById(R.id.dismissEditPopup);
		dismissPopup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				backToMain();
			}
		});

		editButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				inputCompName = compNameEdit.getText().toString();
				inputModelName = modelNameEdit.getText().toString();

				inputDesc = descEdit.getText().toString();
				inputColor = colorEdit.getSelectedItem().toString();
				inputSize = sizeEdit.getText().toString();
				inputQuant = quantityEdit.getText().toString();
				inputType = typeEdit.getSelectedItem().toString();
                inputPrice=priceEdit.getText().toString();
				editButton.setClickable(false);
				final Cycle cycle = new Cycle();
				cycle.setId(cycleId);
				if (isValid(inputCompName))
					cycle.setCompName(inputCompName);
				else
					compNameEdit.setError("Enter Name");

				if (isValid(inputQuant))
					cycle.setQuantity(Integer.parseInt(inputQuant));
				else
					quantityEdit.setError("Enter Initial Qunatity");

				if (isValid(inputDesc))
					cycle.setDescription(inputDesc);
				else
					descEdit.setError("Enter Description");

				cycle.setColor(inputColor);
				if (isValid(inputImgPath))

					cycle.setImage(inputImgPath);

				if (isValid(inputSize))

					cycle.setSize(Integer.parseInt(inputSize));
				else
					sizeEdit.setError("Enter Size");

				if (isValid(inputModelName))

					cycle.setModelName(inputModelName);
				else
					modelNameEdit.setError("Enter Model Name");
				if (isValid(inputPrice))

					cycle.setPrice(inputPrice);
				else
					priceEdit.setError("Enter Price");

				cycle.setType(inputType);
				cycle.setSynced(inputSynced);

				if (isValid(inputImgPath) && isValid(inputCompName)
						&& isValid(inputDesc) && isValid(inputQuant)
						&& isValid(inputSize)) {

					final CyclesItemDBHandler itemHandler = new CyclesItemDBHandler(
							getApplicationContext());
					new AlertDialog.Builder(EditItem.this)
							.setTitle("Update Item")
							.setMessage(
									"Are you sure you want to update this item?")
							.setPositiveButton(android.R.string.yes,
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int which) {
											isUpdated = itemHandler
													.updateCycle(cycle);
											if (isUpdated) {
												new AlertDialog.Builder(EditItem.this).setTitle("Item Updated Successfully..!!")
												.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
													
													@Override
													public void onClick(DialogInterface arg0, int arg1) {
														backToMain();
														
													}
												}).show();

											} else {
												Toast.makeText(getApplicationContext(),
														"Error in Adding Item", Toast.LENGTH_SHORT)
														.show();
												editButton.setClickable(true);
											}
										}
									})
							.setNegativeButton(android.R.string.no,
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int which) {
											// do nothing
										}
									})
							.setIcon(android.R.drawable.ic_dialog_alert).show();
					

				}

			}
		});
		
		deleteButton.setOnClickListener(this);
	}

	void backToMain() {
		Intent intent = new Intent(getApplicationContext(), MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		startActivity(intent);
	}

	boolean isValid(String text) {
		if (text == null || text.length() < 1)
			return false;
		return true;
	}



	@Override
	public void onClick(View arg0) {
		deleteButton.setClickable(false);
		final CyclesItemDBHandler itemHandler=new CyclesItemDBHandler(context);
		new AlertDialog.Builder(EditItem.this)
		.setTitle("Delete Item")
		.setMessage(
				"Are you sure you want to delete this item?")
		.setPositiveButton(android.R.string.yes,
				new DialogInterface.OnClickListener() {
					public void onClick(
							DialogInterface dialog,
							int which) {
						isDeleted=itemHandler
								.deleteCycle(cycleId);
						if (isDeleted) {
							new AlertDialog.Builder(EditItem.this).setTitle("Item Deleted Successfully..!!")
							.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface arg0, int arg1) {
									backToMain();
									
								}
							}).show();

						} else {
							Toast.makeText(getApplicationContext(),
									"Error in Deleting Item", Toast.LENGTH_SHORT)
									.show();
							deleteButton.setClickable(true);
						}
					}
				})
		.setNegativeButton(android.R.string.no,
				new DialogInterface.OnClickListener() {
					public void onClick(
							DialogInterface dialog,
							int which) {
						// do nothing
					}
				})
		.setIcon(android.R.drawable.ic_dialog_alert).show();
		
	}@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			inputImgPath = cursor.getString(columnIndex);
			cursor.close();

			imageEdit.setImageBitmap(BitmapFactory.decodeFile(inputImgPath));

		}

	}

}
