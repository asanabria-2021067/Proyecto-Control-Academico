
package org.in5bm.asanabria.jbeltran.controllers;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.in5bm.asanabria.jbeltran.db.ConexionDb;
import org.in5bm.asanabria.jbeltran.models.Usuario;
import org.in5bm.asanabria.jbeltran.system.Principal;
/**
 *
 * @author Angel Sanabria
 * @date 3/05/2022
 * @time 09:10:17
 * @grade 5to Perito en Informatica B
 * @code IN5BM
 * @carnet 2021067
 */
public class ControladorLogin implements Initializable {
    private ObservableList<Usuario> listaUsuarios;
    private Principal escenarioPrincipal;
    Principal p = new Principal();

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField pfContraseña;
  
    @FXML
    private Button btnCrear;
    private final String PAQUETE_IMAGE = "org/in5bm/asanabria/jbeltran/resources/images/";

    String rol;
    
    public boolean validar(String user, String pass) {
        Usuario usuario = new Usuario();
        usuario.setUser(txtUsuario.getText());
        usuario.setPass(pfContraseña.getText().toString());
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String SQL = "CALL sp_validacion_existencia(?,?)";
        try {
            pstmt = ConexionDb.getInstance().getConexion().prepareStatement(SQL);
                pstmt.setString(1, usuario.getUser());
                pstmt.setString(2, usuario.getPass());
                rs =pstmt.executeQuery();
                System.out.println("Se paso del execute");
                if (rs.next()) {
                    System.out.println("Existe el usuario");
                return true;
                }else{
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Control Academico");
                    alerta.setHeaderText(null);
                    alerta.setContentText("EL USUARIO O CONTRASEÑA SON INCORRECTOS");
                    Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
                    alerta.show();
                }
                /*
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Control Academico");
                alerta.setHeaderText(null);
                alerta.setContentText("EL VALOR DE USUARIO Y CONTRASEÑA ES ERRONEO");
                Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
                alerta.show();
                return false;
                */
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean validarRol(String user) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String SQL = "CALL sp_validacion_rol(?)";
        try {
            pstmt = ConexionDb.getInstance().getConexion().prepareStatement(SQL);
            pstmt.setString(1, user);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                rol = rs.getString(1);
                System.out.println("ROL: "+rol);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @FXML
    private void agregarDatos(ActionEvent event) {
        ingreso();
                
    }

    public String obtenerRol(){
        return rol;
    }
    
    public boolean ingreso(){
        String usuario= txtUsuario.getText();
        String password = pfContraseña.getText();
        if(validar(usuario,password)){
            if(validarRol(usuario)){
                escenarioPrincipal.mostrarEscenaPrincipal();
                System.out.println("ROL COMPROBACION: "+rol);
                if(rol.equals("Estandar")){
                    System.out.println("Paso el if de rol");
                    return true;
                } else if (rol.equals("Administrador")){
                }
            }
                }
        return false;
}

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }
    
}

