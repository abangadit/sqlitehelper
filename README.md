# sqlitehelper
sqlite helper for android


example usage
===================================

nama table user
===================================
public final String TABLE_USER = "user_info";
field table user
===================================
public final String[] table_user_field = { "user_id", "member_id",
		"member_username", "member_email", "member_mobile",
		"member_rekening", "member_photo", "region_name", "level_name",
		"member_score", "member_token" };
field type table user
===================================
public final String[] table_type = { "text", "text", "text", "text",
		"text", "text", "text", "text", "text", "text", "text"};


declare database name
===================================
SqliteHelper database;
create a new database
===================================
database = new SqliteHelper(getApplicationContext(),config.DATABASE_NAME, config.DATABASE_VERSION);
create user_info table
===================================
database.createTable(config.TABLE_USER, config.table_user_field,config.table_type);




get total row from table
===================================
int total_row = database.getRowTable(config.TABLE_USER);


insert data to table
===================================
declare field value 
===================================
-- MUST BE SAME LENGTH WITH FIELD COLUMN ! --

String field_value[] = { "", member_id, member_username, member_email,
				member_mobile, member_rekening, member_photo, region_name, level_name,
				member_score, member_token };
insert process		
===================================		
database.insertDataToTable(config.TABLE_USER, config.table_user_field,field_value);


get result from last query
===================================	
String result = database.getResult();