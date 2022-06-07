package org.in5bm.asanabria.jbeltran.controllers;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.in5bm.asanabria.jbeltran.db.ConexionDb;
import org.in5bm.asanabria.jbeltran.models.CarreraTecnica;
import org.in5bm.asanabria.jbeltran.system.Principal;

/**
 *
 * @author Angel Sanabria y Gabriel Beltran
 * @date 29/04/2022
 * @time 16:05:06
 * @grade 5to Perito en Informatica B
 * @code IN5BM
 * @carnet 2021067, 2021022
 */
public class ControladorCarrerasTecnicas implements Initializable {

    private Principal escenarioPrincipal;
    private final String PAQUETE_IMAGE = "org/in5bm/asanabria/jbeltran/resources/images/";
    int numero;
    private ObservableList<CarreraTecnica> listaCarreras;

    private enum Operacion {
        NINGUNO, GUARDAR, ACTUALIZAR
    }
    private Operacion operacion = Operacion.NINGUNO;
    CarreraTecnica carrera = new CarreraTecnica();
    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnReporte;

    @FXML
    private TableColumn<CarreraTecnica, String> colCarrera;

    @FXML
    private TableColumn<CarreraTecnica, String> colCodigo;

    @FXML
    private TableColumn<CarreraTecnica, String> colGrado;

    @FXML
    private TableColumn<CarreraTecnica, String> colJornada;

    @FXML
    private TableColumn<CarreraTecnica, String> colSeccion;

    @FXML
    private HBox hboxAñadir;

    @FXML
    private HBox hboxEliminar;

    @FXML
    private HBox hboxModificar;

    @FXML
    private HBox hboxReporte;

    @FXML
    private ImageView imageEliminar;

    @FXML
    private ImageView imageModificar;

    @FXML
    private ImageView imageNuevo;

    @FXML
    private ImageView imageReporte;

    @FXML
    private TableView<?> tblCarreras;

    @FXML
    private TextField txtCarrera;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtJornada;

    @FXML
    private TextField txtSeccion;

    @FXML
    private TextField txtGrado;

    @FXML
    private Label lblConteo;

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    @FXML
    public void seleccionarElemento() {
        if (existeElemento()) {
            txtCodigo.setText(((CarreraTecnica) tblCarreras.getSelectionModel().getSelectedItem()).getCodigo());
            txtGrado.setText(((CarreraTecnica) tblCarreras.getSelectionModel().getSelectedItem()).getGrado());
            txtJornada.setText(((CarreraTecnica) tblCarreras.getSelectionModel().getSelectedItem()).getJornada());
            txtSeccion.setText(String.valueOf(((CarreraTecnica) tblCarreras.getSelectionModel().getSelectedItem()).getSeccion()));
            txtCarrera.setText(((CarreraTecnica) tblCarreras.getSelectionModel().getSelectedItem()).getCarrera());
        }
    }

    public void limpiar() {
        txtCodigo.setText("");
        txtCarrera.setText("");
        txtJornada.setText("");
        txtGrado.setText("");
        txtSeccion.setText("");

    }

    /*
    public void cargarCombos(){
        try{
            String SQL= "SELECT DISTINCT jornada FROM carreras_tecnicas ORDER BY jornada ASC";
            PreparedStatement pst = ConexionDb.getInstance().getConexion().prepareStatement(SQL);
            ResultSet rs = pst.executeQuery();
            cboxJornada.setId("Seleccione una jornada");
            while (rs.next()){
                cboxJornada.getItems().addAll(rs.getString("jornada"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        try{
            String SQL= "SELECT DISTINCT seccion FROM carreras_tecnicas ORDER BY seccion ASC";
            PreparedStatement pst = ConexionDb.getInstance().getConexion().prepareStatement(SQL);
            ResultSet rs = pst.executeQuery();
            cboxSeccion.setId("Seleccione una seccion");
            while (rs.next()){
                cboxSeccion.getItems().addAll(rs.getString("seccion"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        try{
            String SQL= "SELECT DISTINCT grado FROM carreras_tecnicas ORDER BY grado ASC";
            PreparedStatement pst = ConexionDb.getInstance().getConexion().prepareStatement(SQL);
            ResultSet rs = pst.executeQuery();
            cboxGrado.setId("Seleccione un grado");
            while (rs.next()){
                cboxGrado.getItems().addAll(rs.getString("grado"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
     */
    
    public void validacionesAgregar() {
        if (txtCodigo.getText().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Control Academico");
            alerta.setHeaderText(null);
            alerta.setContentText("SE NECESITA LLENAR EL VALOR DE CODIGO");
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta.show();
            limpiar();

        } else if (txtCodigo.getText().length() >= 15) {
            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
            alerta2.setTitle("Control Academico");
            alerta2.setHeaderText(null);
            alerta2.setContentText("SE NECESITA QUE EL VALOR DEL CODIGO SEA MENOR A 15 LETRAS O VALORES");
            Stage stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta2.show();
            limpiar();

    }
    }
    public boolean agregarCarrera() {
        String codigo = txtCodigo.getText();
        //System.out.println(carne + nombre1+ nombre2+ nombre3+ apellido1+ apellido2);
        validacionesAgregar();
        if (codigo.length() > 0) {
            System.out.println("Se paso el primer if");
            carrera.setCodigo(txtCodigo.getText());
            carrera.setCarrera(txtCarrera.getText());
            carrera.setGrado(txtGrado.getText());
            carrera.setJornada(txtJornada.getText());
            carrera.setSeccion(txtSeccion.getText().charAt(0));
            System.out.println("Se setearon los datos");
            try {
                String SQL = "CALL sp_create_carreras (?,?,?,?,?)";
                PreparedStatement pst = ConexionDb.getInstance().getConexion().prepareCall(SQL);
                System.out.println("Se paso el pst");
                pst.setString(1, carrera.getCodigo());
                pst.setString(2, carrera.getCarrera());
                pst.setString(3, carrera.getGrado());
                pst.setString(5, carrera.getJornada());
                pst.setString(4, carrera.getSeccion().toString());
                System.out.println("Se asigno los valores al call");
                pst.executeUpdate();
                /*
                        cargarDatos();
                        conteoLabel();
                        limpiar();
                 */
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("No se genero el registro");
            }
        }
        return false;
    }

    @FXML
    private void clickNuevo() {
        switch (operacion) {
            case NINGUNO:
                cambiarHabilitacion(true);
                txtCodigo.setEditable(true);
                txtCodigo.setDisable(false);
                tblCarreras.setDisable(true);
                limpiar();
                btnNuevo.setText("Guardar");
                imageNuevo.setImage(new Image((PAQUETE_IMAGE + "agregar.png")));
                imageModificar.setImage(new Image((PAQUETE_IMAGE + "cancelar.png")));
                btnModificar.setText("Cancelar");
                btnModificar.setDisable(false);
                btnReporte.setDisable(true);
                btnEliminar.setDisable(true);
                operacion = Operacion.GUARDAR;
                break;
            case GUARDAR:
                if(txtCodigo.getText().isEmpty() && txtCarrera.getText().isEmpty()
                    && txtGrado.getText().isEmpty() && txtJornada.getText().isEmpty()){
                    Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                    alerta2.setTitle("Control Academico");
                    alerta2.setHeaderText(null);
                    alerta2.setContentText("SE NECESITA QUE SE LLENEN LOS CAMPOS");
                    Stage stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
                    alerta2.show();
                    limpiar();
                }else{
                if (agregarCarrera()) {
                    cargarDatos();
                    conteoLabel();
                    limpiar();
                    cambiarHabilitacion(false);
                    tblCarreras.setDisable(false);
                    btnNuevo.setText("Nuevo");
                    imageNuevo.setImage(new Image(PAQUETE_IMAGE + "anadir.png"));
                    imageModificar.setImage(new Image(PAQUETE_IMAGE + "contrato.png"));
                    btnModificar.setText("Modificar");
                    btnNuevo.setDisable(false);
                    btnReporte.setDisable(false);
                    btnEliminar.setDisable(false);

                    operacion = Operacion.NINGUNO;
                }
                break;

        }
    }
    }

    public void cambiarHabilitacion(boolean estado) {
        if (estado == true) {
            txtCodigo.setDisable(true);
            txtCarrera.setDisable(false);
            txtGrado.setDisable(false);
            txtJornada.setDisable(false);
            txtSeccion.setDisable(false);
            txtCodigo.setEditable(false);
            txtCarrera.setEditable(true);
            txtGrado.setEditable(true);
            txtJornada.setEditable(true);
            txtSeccion.setEditable(true);

        } else {
            txtCodigo.setDisable(true);
            txtCarrera.setDisable(true);
            txtGrado.setDisable(true);
            txtJornada.setDisable(true);
            txtSeccion.setDisable(true);
            txtCodigo.setEditable(false);
            txtCarrera.setEditable(false);
            txtGrado.setEditable(false);
            txtJornada.setEditable(false);
            txtSeccion.setEditable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //cargarCombos();
        cambiarHabilitacion(false);
        cargarDatos();
        conteoLabel();
    }

    @FXML
    public void reporte() {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("INFO");
        alerta.setContentText("Opcion restringida, solo para modo pago");
        alerta.show();
        Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
    }
    
    public boolean existeElemento() {
        /*
        if((tblAlumnos.getSelectionModel().getSelectedItem() != null)){
            return true;
        } else {
            return false;
        } 
         */
        // hacer if que si es igual a null poner todos los txt vacios
        return (tblCarreras.getSelectionModel().getSelectedItem() != null);
    }
    
    public void validacionesModificar(){
        
    }
    
    public boolean modificarCarreras() {
        if (existeElemento()) {
            try {
                String SQL = "CALL sp_update_carreras(?,?,?,?,?)";
                PreparedStatement pst = ConexionDb.getInstance().getConexion().prepareCall(SQL);
                pst.setString(1, txtCarrera.getText());
                pst.setString(2, txtGrado.getText());
                pst.setString(3, txtSeccion.getText());
                pst.setString(4, txtJornada.getText());
                pst.setString(5, txtCodigo.getText());
                pst.executeUpdate();
                //System.out.println("nombre" + carreras.toString());
                //System.out.println("Pasamos el execute");
                cargarDatos();
                limpiar();
                conteoLabel();
                btnNuevo.setDisable(true);
                btnReporte.setDisable(true);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return false;
    }
    @FXML
    public void clickModificar() {
        switch (operacion) {
            case NINGUNO:
                if (existeElemento()) {
                    cambiarHabilitacion(true);

                    btnNuevo.setDisable(true);

                    btnModificar.setText("Guardar");
                    imageModificar.setImage(new Image(PAQUETE_IMAGE + "Modifu.png"));

                    btnEliminar.setText("Cancelar");
                    imageEliminar.setImage(new Image(PAQUETE_IMAGE + "cancelar.png"));

                    btnReporte.setDisable(true);
                    operacion = Operacion.ACTUALIZAR;
                } else {
                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setTitle("Control Academico");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Antes de continuar, selecciona un registro");
                    Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
                    alerta.show();
                }
                break;
            case GUARDAR:
                btnNuevo.setText("Nuevo");
                imageNuevo.setImage(new Image(PAQUETE_IMAGE + "anadir.png"));
                imageModificar.setImage(new Image(PAQUETE_IMAGE + "contrato.png"));
                btnModificar.setText("Modificar");
                btnNuevo.setDisable(false);
                btnReporte.setDisable(false);
                btnEliminar.setDisable(false);
                cambiarHabilitacion(false);
                tblCarreras.setDisable(false);
                limpiar();
                operacion = Operacion.NINGUNO;
                break;
            case ACTUALIZAR:
                imageEliminar.setImage(new Image((PAQUETE_IMAGE + "cancelar.png")));
                btnEliminar.setText("Cancelar");
                cambiarHabilitacion(true);
                    //System.out.println("Afuera del if");
                    if (modificarCarreras()) {
                        //System.out.println("Se entro al if");
                        btnNuevo.setDisable(false);
                        btnEliminar.setText("Eliminar");
                        imageEliminar.setImage(new Image((PAQUETE_IMAGE + "expediente.png")));
                        imageModificar.setImage(new Image((PAQUETE_IMAGE + "contrato.png")));
                        btnModificar.setText("Modificar");
                        btnReporte.setDisable(false);
                        limpiar();
                        cambiarHabilitacion(false);
                        operacion = Operacion.NINGUNO;
                    }

                }
        }
    
     public boolean eliminarSalon() {
        CarreraTecnica carreras = ((CarreraTecnica) tblCarreras.getSelectionModel().getSelectedItem());
        //System.out.println(alumno.toString());
        PreparedStatement pst = null;
        try {
            //System.out.println(alumno.toString());
            String SQL = "CALL sp_delete_carreras(?)";
            //System.out.println("Paso el if de confirmar");
            pst = ConexionDb.getInstance().getConexion().prepareCall(SQL);
            pst.setString(1, carreras.getCodigo());
            System.out.println(pst);
            pst.execute();
            numero = numero - 1;
            //cargarDatos();
            //registros.remove(tblAlumnos.getSelectionModel().getFocusedIndex());
            limpiar();
            return true;
        } catch (SQLException e) {
            System.err.println("Se produjo un error al eliminar el registro: " + carreras.toString());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("Llego al false");
        return false;
    }

    
    @FXML
    public void clickEliminar() {
        switch (operacion) {
            case ACTUALIZAR:
                btnNuevo.setDisable(false);
                btnEliminar.setText("Eliminar");
                imageEliminar.setImage(new Image((PAQUETE_IMAGE + "expediente.png")));
                imageModificar.setImage(new Image((PAQUETE_IMAGE + "contrato.png")));
                btnModificar.setText("Modificar");
                btnReporte.setDisable(false);
                limpiar();
                cambiarHabilitacion(false);
                operacion = Operacion.NINGUNO;
                break;
            case NINGUNO:
                if (existeElemento()) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Esta seguro de eliminar el registro?");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
                    Optional<ButtonType> confirmar = alert.showAndWait();
                    if (confirmar.get() == ButtonType.OK) {
                        eliminarSalon();
                        conteoLabel();
                        if (eliminarSalon()) {
                            listaCarreras.remove(tblCarreras.getSelectionModel().getFocusedIndex());
                            cargarDatos();
                            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                            stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
                            alerta.setTitle("Control Academico");
                            alerta.setHeaderText(null);
                            alerta.setContentText("Se elimino el registro");
                            alerta.show();
                        } else {
                            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                            alerta.setTitle("Control Academico");
                            stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
                            alerta.setHeaderText(null);
                            alerta.setContentText("No se elimino el registro");
                            alerta.show();
                        }
                    }
                } else {
                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setTitle("Control Academico");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Antes de continuar, selecciona un registro");
                    Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
                    alerta.show();
                }
                break;
        }
    }
    

    @FXML
    private void clickRegresar(ActionEvent event) {
        escenarioPrincipal.mostrarEscenaPrincipal();

    }

    public ObservableList getCarreras() {
        ArrayList<CarreraTecnica> lista = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = ConexionDb.getInstance().getConexion().prepareCall("{CALL sp_read_carreras()}");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CarreraTecnica carrera = new CarreraTecnica();
                carrera.setCodigo(rs.getString(1));
                carrera.setCarrera(rs.getString(2));
                carrera.setGrado(rs.getString(3));
                carrera.setSeccion(rs.getString(4).charAt(0));
                carrera.setJornada(rs.getString(5));
                lista.add(carrera);

                System.out.println(carrera.toString());
            }
            listaCarreras = FXCollections.observableArrayList(lista);
            System.out.println("Tamaño de lista: " + listaCarreras.size());
            numero = listaCarreras.size();
        } catch (SQLException e) {
            System.err.println("\n Se produjo un error al intentar consultar la lista alumnos");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("---Tamaño de lista: " + listaCarreras.size());
        //tblCarreras.setItems(getCarreras());
        System.out.println("-------------------------------");
        System.out.println(carrera.toString());
        System.out.println("Tamaño de lista: " + listaCarreras.size());
        return listaCarreras;
    }

    @FXML
    private void cargarDatos() {
        tblCarreras.setItems(getCarreras());
        colCodigo.setCellValueFactory(new PropertyValueFactory<CarreraTecnica, String>("codigo"));
        colCarrera.setCellValueFactory(new PropertyValueFactory<CarreraTecnica, String>("carrera"));
        colGrado.setCellValueFactory(new PropertyValueFactory<CarreraTecnica, String>("grado"));
        colJornada.setCellValueFactory(new PropertyValueFactory<CarreraTecnica, String>("jornada"));
        colSeccion.setCellValueFactory(new PropertyValueFactory<CarreraTecnica, String>("seccion"));
        //clickTabla();
    }

    private void conteoLabel() {
        String tamaño = " " + numero;
        System.out.println("Numero de datos: " + numero);
        lblConteo.setText(tamaño);
    }

}
