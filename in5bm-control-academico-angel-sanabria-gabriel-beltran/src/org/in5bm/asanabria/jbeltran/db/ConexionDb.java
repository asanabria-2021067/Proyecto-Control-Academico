package org.in5bm.asanabria.jbeltran.db;

/**
 *
 * @author Angel Sanabria
 * @date 3/05/2022
 * @time 09:12:25
 * @grade 5to Perito en Informatica B
 * @code IN5BM
 * @carnet 2021067
 */
import static com.mysql.cj.conf.PropertyKey.useSSL;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import static java.time.ZoneOffset.UTC;
import static java.util.Date.UTC;

public class ConexionDb {

    private Connection conexion;
    private static ConexionDb instancia;
    private final String URL;
    private final String IP_SERVER;
    private final String PORT;
    private final String DB;
    private final String USER;
    private final String PASSWORD;

    public static ConexionDb getInstance() {
        if (instancia == null) {
            instancia = new ConexionDb();
        }
        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }

    private ConexionDb() {
        IP_SERVER = "127.0.0.1";
        PORT = "3306";
        DB = "db_control_academico_in5bm";
        USER = "kinal";
        PASSWORD = "admin";

        URL = "jdbc:mysql://" + IP_SERVER + ":" + PORT + "/" + DB + "?allowPublicKeyRetrieval=true&serverTimezone=UTC&useSSL=false";
        System.out.println(URL);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("La conexion fue todo un exito");

            DatabaseMetaData dma = conexion.getMetaData();
            System.out.println("\nConected to: " + dma.getURL());
            System.out.println("Driver utilizad: " + dma.getDriverName());
            System.out.println("Version utilizada: " + dma.getDriverVersion() + "\n");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("No encuentra ninguna definicion para esta clase");

            /*}catch (InstantiationException e){
           e.printStackTrace();
           System.err.println("No se puede crear una instancia de objeto");
       } catch (IllegalAccessException e){
           e.printStackTrace()
           System.err.println("No se tienen permisos para acceder al paquete");;*/
        } catch (CommunicationsException e) {
            System.err.println("No esta levantando el servicio o el host es incorrecto");
            System.err.println("Verifique que el servicio este levantado "
                    + "Verifique que el IP_SERVER y el port se encuentre bien escrito");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Se produjo un error de tipo SQLException");
            System.out.println("SQL SATE: " + e.getSQLState());
            System.out.println("ERROR CODE: " + e.getErrorCode());
            System.out.println("MESSAGE: " + e.getMessage());
            System.out.println("\n");
        } catch (Exception e) {
            System.err.println("Se produjo un error al intetar establecer una conexion con la base de datos");
            e.printStackTrace();
        }

    }

}

/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConexionDb {
    private static Connection instanciaNueva = null;
    private static String bd="db_control_academico_in5bm";
    private static String url="jdbc:mysql://127.0.0.1:3306/";
    private static String user="kinal";
    private static String password="admin";
    private static String driver="com.mysql.cj.jdbc.Driver";
    private static Connection conexion = null;
    
    /*
    private final String URL;
    private final String IP_SERVER;
    private final String PORT;
    private final String DB;
    private final String USER;
    private final String PASSWORD;
    private final String DRIVER;
 */
 /*
    
    public ConexionDb(){
        
    }
    
    public static Connection conectar(){
        if (conexion==null){
 */
 /*
        Connection conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/control_academico_in5bm", "kinal", "admin");
        return conexion;
 */
 /*
            try{
                Class.forName(driver);
                conexion= DriverManager.getConnection(url+bd, user, password);
                System.out.println("SE INGRESO A LA BASE DE DATOS "+bd);
            }catch(ClassNotFoundException |SQLException e){
                System.out.println("NO SE CONECTO A LA BASE "+bd);
                e.printStackTrace();
            }
        }
        return conexion;
    }
 */
 /*
    public static void main(String [] args){
        ConexionDb conexion= new ConexionDb ();
        conexion.conectar();
    }
 */
    /*
    public static void cerrar(){
        try{
            if(conexion!=null){
                conexion.close();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public static Connection getInstance(){
        if (instanciaNueva == null){
            instanciaNueva = (Connection) new ConexionDb();
            
        }
        return instanciaNueva;
    }
    
}
*/
