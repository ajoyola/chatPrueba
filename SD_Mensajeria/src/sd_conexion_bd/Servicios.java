/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd_conexion_bd;

/**
 *
 * @author joyce-adri
 */

import java.awt.Rectangle;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Servicios extends SQLQuery{ 
    //CONSULTAS A LA BASE
    
    public String validar_userName(String userName, String password){
        String nombre=null, apellido=null;
        try{
            this.conectar("localhost:3306", "mensajeria","root","1234");
            this.consulta=this.conexion.prepareStatement("call buscar_por_user(\""+userName+"\",\""+password+"\");");
            this.datos=this.consulta.executeQuery();
            while(this.datos.next()){
                nombre = datos.getString("nombre");
                apellido = datos.getString("apellido");
            }
        }  
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Servicios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo conectar correctamente a la base de datos");
        }               
        return nombre+" "+apellido;
    }
          
     /**
      * Cargar la lista de los contactos del usuario de la base de datos y mostararla como lista en el tab contactos
      * lista es la lista donde se va a presentar los contactos del usuario
    */
    public void cargar_contactos(JList lista, int userID){
        try{
            this.conectar("localhost:3306", "mensajeria","root","1234");
            this.consulta=this.conexion.prepareStatement("call consultar_contactos(\""+userID+"\");");
            this.datos=this.consulta.executeQuery();
            DefaultListModel modelo = new DefaultListModel();
            while(this.datos.next()){
                modelo.addElement(datos.getString("user"));
            }
            lista.setModel(modelo);
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Servicios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo conectar correctamente a la base de datos");
        }        
    }
    
}
