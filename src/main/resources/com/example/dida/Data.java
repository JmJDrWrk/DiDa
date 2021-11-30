

public class SQL_HISTORIAL{
    private String CREATE_TABLE_USERS = "CREATE TABLE USERS"
		+"(id INTEGER auto_increment,"
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
    
    
    
    
//  try {oldTable = FXMLLoader.load(getClass().getResource("users_table.fxml"));}
//  catch (IOException e1) {System.out.println("table not found");} 
    
    
    
//  String a = "SELECT * FROM asd WHERE id = 'asd' AND WHERE id = 'asd';";
//  if(a.endsWith("AND;")) {a = new String(a.replaceAll("AND;",""));}
    
    
	
//	//CREAR TABLA
//
//    try(Connection conexionDataBase = DriverManager.getConnection(DATABASE, "root","")){
//        Statement statement = conexionDataBase.createStatement();
//    	statement.executeUpdate(crear);
//        System.out.println("Tabla creada");
//    }catch (Exception e) {
//    	e.printStackTrace();
//    	System.out.println("Creacion de la tabla fallida");
//    }
//	
//	//INSERTAR 1
//    try(Connection conexionDataBase = DriverManager.getConnection(DATABASE, "root","")){
//        Statement statement = conexionDataBase.createStatement();
//    	statement.executeUpdate(insertar1);
//        System.out.println("Datos 1 insertados");
//    }catch (Exception e) {
//    	e.printStackTrace();
//    	System.out.println("Insecion 1 fallida");
//    }
//	
//    
//	//INSERTAR 2
//    try(Connection conexionDataBase = DriverManager.getConnection(DATABASE, "root","")){
//        Statement statement = conexionDataBase.createStatement();
//    	statement.executeUpdate(insertar2);
//        System.out.println("Datos 2 insertados");
//    }catch (Exception e) {
//    	e.printStackTrace();
//    	System.out.println("Insecion 2 fallida");
//    }
//    
    
//  @FXML TableColumn<Users, String> id_col;
//  @FXML TableColumn<Users, String> name_col;
//  @FXML TableColumn<Users, String> surname_col;
//  @FXML TableColumn<Users, String> pass_col;
//  @FXML TableColumn<Users, String> mail_col;
//  @FXML TableColumn<Users, String> hist_col;
//  @FXML TableColumn<Users, String> admin_col;   
}



