/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sd_conexion_bd;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * * @author joyce-adri
 */
public class SQLQuery{
    protected Connection conexion;
    protected PreparedStatement consulta;
    protected ResultSet datos;
    
    public void conectar(String servidor, String db, String usuario, String clave) throws ClassNotFoundException,SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        try{
            this.conexion=DriverManager.getConnection("jdbc:mysql://"+ servidor+ "/"+ db,usuario,clave);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    
    public void desconectar() throws SQLException{
        this.conexion.close();
        this.consulta.close();
    }
    public void desconectar(ResultSet rs) throws SQLException{
        this.desconectar();
        rs.close();
    }
}
