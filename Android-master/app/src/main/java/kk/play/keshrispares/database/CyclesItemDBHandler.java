package kk.play.keshrispares.database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import kk.play.keshrispares.entity.Cycle;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CyclesItemDBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "KeshriSpares";
    private static final int DATABASE_VERSION = 1;

    private static final String ITEM_TABLE_NAME = "cycles";
    private static final String SALES_TABLE_NAME = "sales";

    private static final String salesId = "sales_id";

    private static final String TYPE = "type";

    private static final String itemId = "item_id";
    private static final String COMP_NAME = "compName";
    private static final String MODEL_NAME = "modelName";

    private static final String IMAGE = "img";
    private static final String DESCRIPTION = "desc";
    private static final String COLOR = "color";
    private static final String SIZE = "size";
    private static final String PRICE = "price";

    private static final String QUANTITY = "quantity";

    private static final String DATE = "date";
    private static final String DATETIME = "date_time";

    private static final String TIME = "time";
    private static final String FLAG = "flag";
    private static final String UPDATED = "updated";
    private static final String COMP_TABLE_NAME="companies";
    private static final String compId="_compid";

    private static final String CREATE_COMPANY_TABLE="create table "
            + COMP_TABLE_NAME + "(" +compId
            + " integer primary key autoincrement," + COMP_NAME +" varchar(50))";

    private static final String CREATE_ITEM_TABLE = "create table "
            + ITEM_TABLE_NAME + "(" + itemId
            + " integer primary key autoincrement,"+ compId+" int(50), "+MODEL_NAME + " varchar(50),"
            + IMAGE + " varchar(20)," + DESCRIPTION + " varchar(100)," + COLOR
            + " varchar(10)," + SIZE + " int(5)," + TYPE + " varchar(20),"
            + PRICE + " varchar(20),"+UPDATED +" varchar(3), "
            +"foreign key (" + compId
            + ") references " + COMP_TABLE_NAME + "(" + compId + "))";

    private static final String CREATE_SALES_TABLE = "create table "
            + SALES_TABLE_NAME + "(" + salesId
            + " integer primary key autoincrement," + itemId + " int(50), "
            + QUANTITY + " int(5), " + FLAG + " int(2), " + DATETIME
            + " DATETIME DEFAULT CURRENT_TIMESTAMP," + DATE
            + " DATETIME DEFAULT CURRENT_DATE, " + TIME
            + " DATETIME DEFAULT CURRENT_TIME," + "foreign key (" + itemId
            + ") references " + ITEM_TABLE_NAME + "(" + itemId + ")" + ");";

    private SQLiteDatabase db;

    public CyclesItemDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL(CREATE_COMPANY_TABLE);
        db.execSQL(CREATE_ITEM_TABLE);
        db.execSQL(CREATE_SALES_TABLE);
        Log.d("Query", CREATE_ITEM_TABLE);
        // db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SALES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + COMP_TABLE_NAME);

        // Create tables again
        Log.d("Onupgrade", "DB");
        onCreate(db);
    }

    public String getCompByCompId(String id){
        String compName="";
        db=this.getReadableDatabase();
        Cursor cursor=db.query(COMP_TABLE_NAME,new String[]{COMP_NAME},compId+"=?",new String[]{id},null,null,null);
        while(cursor.moveToNext())
            compName=cursor.getString(0);
        db.close();
        return compName;
    }
    public List<Cycle> getCyclesByType(String type) {

        db = this.getReadableDatabase();
        Cursor cursor = db.query(ITEM_TABLE_NAME, new String[] { itemId, compId,
                        IMAGE, DESCRIPTION, SIZE, COLOR ,MODEL_NAME,PRICE}, TYPE + "=?",
                new String[] { type }, null, null, null);

        List<Cycle> cycleList = new ArrayList<Cycle>();
        while (cursor.moveToNext()) {
            Cycle cycle = new Cycle();

            cycle.setId(Integer.parseInt(cursor.getString(0)));
            cycle.setCompName(getCompByCompId(cursor.getString(1)));
            cycle.setImage(cursor.getString(2));
            cycle.setDescription(cursor.getString(3));
            cycle.setQuantity(getCountofCycle(cycle.getId()));
            cycle.setSize(Integer.parseInt(cursor.getString(4)));
            cycle.setColor(cursor.getString(5));
            cycle.setModelName(cursor.getString(6));
            cycle.setPrice(cursor.getString(7));
//Log.d("Model",cursor.getString(6));

            List<String> detailList = detailFromSalesTable(cycle.getId());
            cycle.setLastUpdatedDate(detailList.get(1));
            cycle.setLastUpdatedTime(detailList.get(2));

            cycleList.add(cycle);

        }
        db.close();
        return cycleList;

    }
    public List<String> getListOfCompaniesBytype(String type){
        List<String > companies=new ArrayList<String>();

        db = this.getReadableDatabase();
        String query="SELECT distinct "+ compId+" from "+ITEM_TABLE_NAME+" where "+TYPE+" =?";

        Cursor cursor = db.rawQuery(query, new String[]{type});

        while (cursor.moveToNext()) {
            companies.add(getCompByCompId(cursor.getString(0)));
//Log.d("Model",cursor.getString(0));



        }
        db.close();


        return companies;

    }
    public long addCompName(String name){
        long id=0;
        ContentValues values=new ContentValues();
        values.put(COMP_NAME,name);
        id=db.insert(COMP_TABLE_NAME,null,values);
        return id;
    }
    public long addNewCycle(Cycle cycle) {

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(compId, getCompIdByCompName(cycle.getCompName())+"");
        values.put(MODEL_NAME, cycle.getModelName());

        values.put(IMAGE, cycle.getImage());
        values.put(COLOR, cycle.getColor());
        values.put(DESCRIPTION, cycle.getDescription());
        values.put(SIZE, cycle.getSize());
        values.put(TYPE, cycle.getType());
        values.put(PRICE, cycle.getPrice());
        values.put(UPDATED, false);
        long id = db.insert(ITEM_TABLE_NAME, null, values);// primary key of
        // inserted item,-1
        // if not inserted
        db.close();
        return id;

    }

    public int getCountofCycle(long id) {
        db = this.getReadableDatabase();
        int count = 0;
        String selectQuery = "SELECT * FROM " + SALES_TABLE_NAME + " WHERE "
                + itemId + "=?";

        Cursor c = db.rawQuery(selectQuery, new String[] { id + "" });

        while (c.moveToNext()) {
            int quant = Integer
                    .parseInt(c.getString(c.getColumnIndex(QUANTITY)));
            int flag = Integer.parseInt(c.getString(c.getColumnIndex(FLAG)));
            if (flag == 1)
                count -= quant;
            if (flag == 0)
                count += quant;

        }

        c.close();
        db.close();
        return count;
    }

    /**
     * return last updated date, time of a item using item id
     */
    public List<String> detailFromSalesTable(long id) {
        int salesid = latestSalesIdByItemId(id);

        db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + SALES_TABLE_NAME + " WHERE "
                + salesId + "=?";
        Cursor c = db.rawQuery(selectQuery, new String[] { salesid + "" });
        String date = null;
        String time = null;

        if (c.moveToFirst()) {
            date = c.getString(c.getColumnIndex(DATE));
            time = c.getString(c.getColumnIndex(TIME));
        }

        List<String> detailList = new ArrayList<String>();
        detailList.add(salesid + "");
        detailList.add(date);
        detailList.add(time);
        c.close();
        db.close();
        return detailList;

    }

    public int latestSalesIdByItemId(long id) {
        db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + SALES_TABLE_NAME + " WHERE "
                + itemId + "=? ORDER BY " + DATETIME + " DESC LIMIT 1";
        int salesid = 0;
        Cursor c = db.rawQuery(selectQuery, new String[] { id + "" });
        if (c.moveToFirst()) {
            salesid = Integer.parseInt(c.getString(c.getColumnIndex(salesId)));
        }
        db.close();
        return salesid;
    }

    public boolean updateQuantity(long id, int quantity, String date,boolean flag) {
        // flag true(1) for sold..false(0) for Adding more cycles
        db = this.getWritableDatabase();
        String time=new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

        ContentValues values = new ContentValues();
        values.put(itemId, id);
        values.put(QUANTITY, quantity);
        values.put(TIME,time);
        values.put(DATE,date);
        values.put(FLAG, flag);


        if (db.insert(SALES_TABLE_NAME, null, values) > 0)// primary key of
        // inserted
        { // quantity,-1 if
            db.close(); // not inserted
            return true;
        }
        db.close();
        return false;
        // db.close();
    }

    public String getCompIdByCompName(String name){
        String id=null;
        Cursor cursor=db.query(COMP_TABLE_NAME,new String[]{compId},COMP_NAME+"=?",new String[]{name.toLowerCase()},null,null,null);
        while(cursor.moveToNext())
            id=cursor.getString(0);
        if(id==null)
            id=addCompName(name.toLowerCase())+"";
        return id;
    }
    public boolean updateCycle(Cycle cycle) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        long id = cycle.getId();
        values.put(compId, getCompIdByCompName(cycle.getCompName()));
        values.put(IMAGE, cycle.getImage());
        values.put(COLOR, cycle.getColor());
        values.put(DESCRIPTION, cycle.getDescription());
        values.put(SIZE, cycle.getSize());
        values.put(TYPE, cycle.getType());
        values.put(PRICE, cycle.getPrice());
        values.put(MODEL_NAME, cycle.getModelName());
        values.put(UPDATED, cycle.getSynced());

        int count = db.update(ITEM_TABLE_NAME, values, itemId + "=" + id + "",
                null);// primary key of
        // inserted item,-1
        // if not inserted
        db.close();
        if (count == 1)
            return true;
        return false;
    }

    public Cycle getCycleById(long cycleId) {
        db = this.getReadableDatabase();
        Cursor cursor = db.query(ITEM_TABLE_NAME, new String[] { itemId, compId,
                        IMAGE, DESCRIPTION, SIZE, COLOR,MODEL_NAME,PRICE,UPDATED,TYPE }, itemId + "=?",
                new String[] { cycleId + "" }, null, null, null);
        Cycle cycle = new Cycle();

        if (cursor.moveToFirst()) {

            cycle.setId(Integer.parseInt(cursor.getString(0)));
            cycle.setCompName(getCompByCompId(cursor.getString(1)));
            cycle.setImage(cursor.getString(2));
            cycle.setDescription(cursor.getString(3));
            cycle.setQuantity(getCountofCycle(cycle.getId()));
            cycle.setSize(Integer.parseInt(cursor.getString(4)));
            cycle.setColor(cursor.getString(5));
            cycle.setModelName(cursor.getString(6));
            cycle.setPrice(cursor.getString(7));
            cycle.setSynced(cursor.getString(8));
            cycle.setType(cursor.getString(9));
            //Log.d("sqlite", cycle.getType()+"");

        }
        db.close();
        return cycle;
    }

    public boolean deleteCycle(long cycleId) {
        db = this.getReadableDatabase();
        int count = db.delete(ITEM_TABLE_NAME, itemId + "=" + cycleId + "",
                null);
        db.close();
        if (count > 0)
            return true;
        return false;
    }


    long getTotalSales(String date){
        int count=0;
        db=this.getReadableDatabase();
        String query="SELECT  "+ QUANTITY+" from "+SALES_TABLE_NAME+" WHERE "+DATE+" =? and "+FLAG+" =?";
        Cursor c=db.rawQuery(query, new String[]{date+"",1+""});

        while(c.moveToNext()){
            long quant=Long.parseLong(c.getString(0));
            count+=quant;
        }
        db.close();
        return count;
    }

    public List<String> getAllDistinctDates(){
        List<String> dates=new ArrayList<String>();
        db=this.getReadableDatabase();
        String query="SELECT distinct "+ DATE+" from "+SALES_TABLE_NAME;
        Cursor c=db.rawQuery(query, null);

        while(c.moveToNext()){
            dates.add(c.getString(0));
        }
        db.close();
        return dates;
    }

    public List<Cycle> getAllCycles() {
        db = this.getReadableDatabase();
        Cursor cursor = db.query(ITEM_TABLE_NAME, new String[] { itemId, compId,
                        IMAGE, DESCRIPTION, SIZE, COLOR ,PRICE,MODEL_NAME,TYPE,UPDATED}, null,
                null, null, null, null);

        List<Cycle> cycleList = new ArrayList<Cycle>();
        while (cursor.moveToNext()) {
            Cycle cycle = new Cycle();

            cycle.setId(Integer.parseInt(cursor.getString(0)));
            cycle.setCompName(getCompByCompId(cursor.getString(1)));
            cycle.setImage(cursor.getString(2));
            cycle.setDescription(cursor.getString(3));
            cycle.setQuantity(getCountofCycle(cycle.getId()));
            cycle.setSize(Integer.parseInt(cursor.getString(4)));
            cycle.setColor(cursor.getString(5));
            cycle.setPrice(cursor.getString(6));
            cycle.setModelName(cursor.getString(7));
            cycle.setType(cursor.getString(8));
            List<String> detailList = detailFromSalesTable(cycle.getId());
            cycle.setLastUpdatedDate(detailList.get(1));
            cycle.setLastUpdatedTime(detailList.get(2));
            cycle.setSynced(cursor.getString(9));
            cycleList.add(cycle);

        }
        db.close();
        return cycleList;

    }

    public List<Cycle> getCyclesByTypeComp(String type, String companyName) {
        List<Cycle> cycles=new ArrayList<Cycle>();
        List<Cycle> cycles1=getCyclesByType(type);
        for(Cycle cycle:cycles1){
            if(cycle.getCompName().equals(companyName)){
                cycles.add(cycle);
            }
        }

        return cycles;
    }

    public List<Cycle> getAllFalseFlagCycles() {
        List<Cycle> cycles = new ArrayList();


        List<Cycle> allCycle = getAllCycles();

        for (Cycle cycle : allCycle) {
            if (cycle.getSynced().equals("0")){
                cycles.add(cycle);
                Log.d("FalseFlaggedCycle",cycle.toString());

            }

        }

        return cycles;

    }

}
