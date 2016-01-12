package kk.play.keshrispares.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kk.play.keshrispares.R;
import kk.play.keshrispares.controller.SearchCycleController;
import kk.play.keshrispares.database.CyclesItemDBHandler;
import kk.play.keshrispares.entity.Cycle;

public class DisplayCyclesSearch extends Activity {

    private Spinner types;
    private Spinner compNames;
    private Spinner modelNames;
    private Spinner sizes;
    private CyclesItemDBHandler handler;
    private Button getbtn;

    private String selectedType;
    private String selectedCompName;
    private String selectedModelName;
    private String selectedSize;
    private  List<String> companies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle_display);

        getbtn=(Button)findViewById(R.id.getbtn);

        getbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               getCycles();
            }
        });
        populateSpinners();
    }


    public void populateSpinners(){
        handler=new CyclesItemDBHandler(this);
        final List<Cycle> cycles=handler.getAllCycles();
        types=(Spinner) findViewById(R.id.type_);
        sizes=(Spinner) findViewById(R.id.size_);
        compNames=(Spinner) findViewById(R.id.comp_);
        modelNames=(Spinner) findViewById(R.id.model_);

        populateTypes(types);
        types.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                populateSizes(parent.getSelectedItem().toString(),cycles);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void populatecompNames(String selsize,List<String> companies){

       ArrayAdapter<String> adp=new ArrayAdapter(this,android.R.layout.simple_list_item_1,companies);

        compNames.setAdapter(adp);
        adp.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);



    }

    public void populatemodelNames(View view){
        List<String> modelList=new ArrayList();

    }

    public void populateTypes(View view){
        List<String> typeList=new ArrayList();
        typeList.add("All");
        typeList.add("Gents");
        typeList.add("Ladies");
        typeList.add("Kids");
        typeList.add("Others");
        ArrayAdapter<String> adp=new ArrayAdapter(this,android.R.layout.simple_list_item_1,typeList);

        types.setAdapter(adp);
        adp.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);
        selectedType=types.getSelectedItem().toString();

    }

    public void populateSizes(String type,List<Cycle> cycles){
        Set<String> sizeList=new HashSet<>();
        String sel=type;
        if(sel.equals("All"))
            sel="GentsLadiesKidsOthers";

        for(Cycle cycle:cycles){
            if(sel.contains(cycle.getType())) {
                sizeList.add(cycle.getSize() + "\"");
            }
        }
        if(sizeList.size()==0)
            sizeList.add("No Item Found");
        List list=new ArrayList(sizeList);
        ArrayAdapter<String> adp=new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);

        sizes.setAdapter(adp);
        adp.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);
        selectedSize=sizes.getSelectedItem().toString();

        if(type.equals("All"))
           companies= handler.getAllCompanies();
       else
            companies =handler.getListOfCompaniesBytype(type);

        populatecompNames(selectedSize,companies);
    }


     void getCycles(){
         Log.d("Cycles","cycle.toString()"+selectedType+selectedCompName+selectedModelName+selectedSize);

         List<Cycle> cycles;//=new ArrayList<>();
         SearchCycleController cycleController=new SearchCycleController(this);
        cycles=cycleController.getCyclesBy(selectedType,selectedCompName,selectedModelName,selectedSize);
         for(Cycle cycle:cycles)
             Log.d("Cycles",cycle.toString());
}

}

