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

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import sd_mensajeria.usuario;

public class Servicios extends SQLQuery{ 
    //CONSULTAS A LA BASE
    
    public boolean validar_userName(String userName, String password, usuario u) throws IOException{
        try{
            this.conectar("localhost:3306", "mensajeria","root","");
            System.out.println("\n" + userName+ " " + password + "\n");
            this.consulta=this.conexion.prepareStatement("call buscar_por_user(\""+userName+"\",\""+password+"\");");
            this.datos=this.consulta.executeQuery();
            while(this.datos.next()){
                u.setID(datos.getInt("id"));
                u.setNombre(datos.getString("nombre"));
                u.setApellido(datos.getString("apellido"));
                u.setUser(userName);
                Blob imagen = datos.getBlob("foto");
                ImageIcon i = new ImageIcon(javax.imageio.ImageIO.read(imagen.getBinaryStream()).getScaledInstance(100,120, 0));
                u.setFoto(i); 
                //System.out.println(i);
            }
            return true;
        }  
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Servicios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "--No se pudo conectar correctamente a la base de datos");
            System.exit(0);
        }return false;               
    }
          
     /**
      * Cargar la lista de los contactos del usuario de la base de datos y mostararla como lista en el tab contactos
      * lista es la lista donde se va a presentar los contactos del usuario
    */
    public void cargar_contactos(JList lista, String userName, int u_id){
        try{
            this.conectar("localhost:3306", "mensajeria","root","");
            this.consulta=this.conexion.prepareStatement("call consultar_contactos(\""+userName+"\",\""+u_id+"\");");
            this.datos=this.consulta.executeQuery();
            DefaultListModel modelo = new DefaultListModel();
            while(this.datos.next()){
                modelo.addElement(datos.getString("user")+": "+datos.getString("nombre")+" "+datos.getString("apellido"));
            }
            lista.setModel(modelo);
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Servicios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo conectar correctamente a la base de datos");
        }        
    }
    
}
