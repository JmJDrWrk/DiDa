package com.example.dida;

public class SQL_SENTENCE {
    static  String CREATE_TABLE_USERS = "CREATE TABLE USERS"
		+"(id INTEGER auto_increment,"
		+"name VARCHAR,"
		+"surname VARCHAR,"
		+"mail VARCHAR,"
		+"isadmin VARCHAR,"
		+"pass VARCHAR,"
		+"id_h VARCHAR)";
    
    static String DROP_TABLE_USERS = "DROP TABLE USERS";
    static String SELECT_FROM_USERS = "SELECT * FROM USERS";
    static String CLEAR_TABLE_USERS = "DELETE FROM TABLE USERS WHERE id > -1";
}
