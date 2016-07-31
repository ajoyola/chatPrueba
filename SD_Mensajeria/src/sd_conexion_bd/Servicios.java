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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import sd_mensajeria.usuario;

public class Servicios extends SQLQuery{ 
    //CONSULTAS A LA BASE
    
    public boolean validar_userName(String userName, String password, usuario u) throws IOException{
        try{
            this.conectar("localhost:3306", "mensajeria","root","");
            //System.out.println("\n" + userName+ " " + password + "\n");
            this.consulta=this.conexion.prepareStatement("call buscar_por_user(\""+userName+"\",\""+password+"\");");
            this.datos=this.consulta.executeQuery();
            while(this.datos.next()){
                u.setID(datos.getInt("id"));
                u.setNombre(datos.getString("nombre"));
                u.setApellido(datos.getString("apellido"));
                u.setUser(userName);
                Blob imagen = datos.getBlob("foto");
                Image im = javax.imageio.ImageIO.read(imagen.getBinaryStream());
                if (im != null){
                
                ImageIcon i = new ImageIcon(im.getScaledInstance(100,120, 0));
                u.setFoto(i);
                
                }
               return true;
            }return false;  
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
                modelo.addElement(datos.getString("nombre")+" "+datos.getString("apellido"));
            }
            lista.setModel(modelo);
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Servicios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo conectar correctamente a la base de datos");
        }        
    }
    
     public void registrar_usuario(String nombre, String apellido, String ciudad, String user, String pass,  String foto) throws FileNotFoundException{
         
         FileInputStream  fis = null;
         try{
           
            File file = new File(foto);
            fis = new  FileInputStream(file);
            this.conectar("localhost:3306", "mensajeria","root","");
            this.consulta=this.conexion.prepareStatement("call registrar_usuario(\""+nombre+"\",\""+apellido+"\",\""+ciudad+"\",\""+user+"\",\""+pass+"\",\""+foto+"\");");
            this.datos=this.consulta.executeQuery();
            
            
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Servicios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo conectar correctamente a la base de datos");
        }        
    }
    
}
