

public class SQL_HISTORIAL{
    private String CREATE_TABLE_USERS = "CREATE TABLE USERS"
		+"(id INTEGER,"
		+"name VARCHAR,"
		+"surname VARCHAR,"
		+"mail VARCHAR,"
		+"isadmin VARCHAR,"
		+"pass VARCHAR,"
		+"id_h VARCHAR)";
    
    
    private String CREATE_TABLE_GUITARS = "CREATE TABLE GUITARS (id INTEGER, name VARCHAR, brand VARCHAR, type VARCHAR, color VARCHAR, nstrings VARCHAR);";
    
    private String CREATE_TABLE_AMPS = "CREATE TABLE GUITARS (id INTEGER, name VARCHAR, brand VARCHAR, nspeakers VARCHAR, nwatts VARCHAR, ninputs VARCHAR, noutputs VARCHAR);";

    String sql = "CREATE TABLE IF NOT EXISTS jugadores" + 
        "(id INTEGER auto_increment, " + 
        " posicion VARCHAR(255), " + 
        " pierna buena VARCHAR(255), " +
        " filigranas INTEGER(10), " +
        " club VARCHAR(255))";
}



