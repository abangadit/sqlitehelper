/**
* Sqlitehelper class implements
* creating a database
* creating,inserting,deleting a table using only 1 method
* you dont have to create method for every action you took
* in your application
*
* @author  @abang_adit (Aditya Eka Putra)
* @version 0.0.1
* @since   2014-07-18 
* 
* if you have any question or you want to help me improve it
* you can contact me on
* 
* twitter  : @abang_adit
* email    : swara.ogawa@gmail.com
* facebook : facebook.com/abang_adit
* 
*/
package com.abangadit.helper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.abangadit.config.appSettings;
import com.abangadit.model.userInfo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;


public class SqliteHelper extends SQLiteOpenHelper{
	SQLiteDatabase db;
	private String resultValue;
	private int row;
	private appSettings config;
	
	/**
	 * @param context    : getAplicationContext()
	 * @param db_name    : your databse name
	 * @param db_version : your database version
	 */
	public SqliteHelper(Context context,String db_name,int db_version) {
		super(context, db_name, null, db_version);	
		db = this.getWritableDatabase();
		config = new appSettings();
	}
	
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	

	
	/**
	 * This method will return the exception if there is a
	 * problem during creating a table
	 * or/and
	 * will return "success" if the proccess was succesfully
	 * excecuted
	 * ====================================================
	 * @param table_name : your table name
	 * @param field_name : array contain your field name ,ex : {"name","email","phone"}
	 * @param field_type : array contain your field type ,ex : {"TEXT","TEXT","TEXT"}
	 */
	public void createTable(String table_name,String field_name[],String field_type[]){
		String CREATE_TABLE;
		
		CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+table_name+" ( " +
                "id_"+table_name+" INTEGER PRIMARY KEY AUTOINCREMENT, ";
		if(field_name.length == field_type.length){
			for (int i = 0; i < field_name.length; i++) {
				CREATE_TABLE += ""+field_name[i]+" "+field_type[i]+",";
			}
			CREATE_TABLE = CREATE_TABLE.substring(0, CREATE_TABLE.length() - 1);
			
			CREATE_TABLE += ")";
			
		}else{
			resultValue = "field name and field type length must be same !";
		}
		
		try {
			db.execSQL(CREATE_TABLE);
			resultValue = "success creating table";
		} catch (SQLiteException e) {
			resultValue = e.toString();
		}
	}
	
	
	/**
	 * NOTE : this method is still on development it only can
	 * proccess TEXT type data, it cannot process another
	 * TYPE of data now. 
	 * 
	 * This method will return the exception if there is a
	 * problem during inserting data into a table
	 * or/and
	 * will return "success" if the proccess was succesfully
	 * excecuted
	 * 
	 * ====================================================
	 * @param table_name : your table name
	 * @param field_name : array contain your field name ,ex : {"name","email","phone"}
	 * @param field_value : array contain your field type ,ex : {"ADIT","a@a.com","09982"}
	 */
	public void insertDataToTable(String table_name,String field_name[],String field_value[]){
		String INSERT_DATA = "";

		INSERT_DATA = "INSERT INTO "+table_name+" (";
		if(field_name.length == field_value.length){
			for (int i = 0; i < field_name.length; i++) {
				INSERT_DATA += ""+field_name[i]+",";
			}
			INSERT_DATA = INSERT_DATA.substring(0, INSERT_DATA.length() - 1);
			
			INSERT_DATA += ") VALUES (";
			
			for (int i = 0; i < field_value.length; i++) {
				INSERT_DATA += " '"+field_value[i]+"' ,";
			}
			INSERT_DATA = INSERT_DATA.substring(0, INSERT_DATA.length() - 1);
			
			INSERT_DATA += ")";
			
		}else{
			resultValue = "field name and field type length must be same !";
		}
		
		try {
			db.execSQL(INSERT_DATA);
			resultValue = "success inserting table";
		} catch (SQLiteException e) {
			resultValue = e.toString();
			resultValue += "query : "+ INSERT_DATA;
		}
	}
	
	/**
	 * This method will return your total row from a table
	 * 
	 * @param table_name : your table name
	 * @return will return your total row from a table
	 */
	public int getRowTable(String table_name){
		String countQuery = "SELECT  * FROM " + table_name;
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        row = cursor.getCount();
        
		return row;
	}
	
	/**
	 * Delete All data from a table
	 * @param table_name : your table name
	 */
	public void deleteDataFromTable(String table_name){
		String DELETE_DATA = "DELETE FROM "+table_name;
		try {
			db.execSQL(DELETE_DATA);
			resultValue = "success deleting data on table";
		} catch (SQLiteException e) {
			resultValue = e.toString();
		}
	}
	
	
	
	// Get All Data
    public List<userInfo> getToken() {
        List<userInfo> user = new ArrayList<userInfo>();

        // 1. build the query
        String query = "SELECT  * FROM "+config.TABLE_USER;
 
    	// 2. get reference to writable DB
        Cursor cursor = db.rawQuery(query, null);
 
        // 3. go over each row, build book and add it to list
        userInfo users = null;
        
        if (cursor.moveToFirst()) {
            do {
            	users = new userInfo();
            	users.setToken(cursor.getString(4));
            	users.setUsername(cursor.getString(2));
                // Add book to books
                user.add(users);
            } while (cursor.moveToNext());
        }
        
        return user;
    }
		
	
	/** Getting result from process excecuted before
	 * 
	 * @return : Return value from a process excecuted before
	 */
	public String getResult(){
		return resultValue;
	}

	public List<userInfo> getUserInfo() {
		List<userInfo> user = new ArrayList<userInfo>();

        // 1. build the query
        String query = "SELECT  * FROM "+config.TABLE_USER;
 
    	// 2. get reference to writable DB
        Cursor cursor = db.rawQuery(query, null);
 
        // 3. go over each row, build book and add it to list
        
        /*{ "user_id", "member_id",
			"member_username", "member_email", "member_mobile",
			"member_rekening", "member_photo", "region_name", "level_name",
			"member_score", "member_token" };*/
			
        userInfo users = null;
        
        if (cursor.moveToFirst()) {
            do {
            	users = new userInfo();
            	users.setID(cursor.getString(2));
            	users.setUsername(cursor.getString(3));
            	users.setEmail(cursor.getString(4));
            	users.setMobile(cursor.getString(5));
            	users.setRekening(cursor.getString(6));
            	users.setPhoto(cursor.getString(7));
            	users.setRegion(cursor.getString(8));
            	users.setLevel(cursor.getString(9));
            	users.setScore(cursor.getString(10));
            	users.setToken(cursor.getString(11));
                // Add book to books
                user.add(users);
            } while (cursor.moveToNext());
        }
        
        return user;
	}
	
}
