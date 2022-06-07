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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.in5bm.asanabria.jbeltran.db.ConexionDb;
import org.in5bm.asanabria.jbeltran.models.*;
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
public class ControladorSalones implements Initializable {

    private Principal escenarioPrincipal;
    int numero;
    private final String PAQUETE_IMAGE = "org/in5bm/asanabria/jbeltran/resources/images/";
    private ObservableList<Salon> listaSalones = FXCollections.observableArrayList();

    private enum Operacion {
        NINGUNO, GUARDAR, ACTUALIZAR
    }
    private Operacion operacion = Operacion.NINGUNO;
    Salon salones = new Salon();
    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button clickEliminar;

    @FXML
    private Button btnReporte;

    @FXML
    private TableColumn<?, ?> colCapacidad;

    @FXML
    private TableColumn<?, ?> colCodigo;

    @FXML
    private TableColumn<?, ?> colDescripcion;

    @FXML
    private TableColumn<?, ?> colEdificio;

    @FXML
    private TableColumn<?, ?> colNivel;

    @FXML
    private ImageView imageEliminar;

    @FXML
    private ImageView imageModificar;

    @FXML
    private ImageView imageNuevo;

    @FXML
    private ImageView imageReporte;

    @FXML
    private ImageView imageDelete;

    @FXML
    private HBox hboxAñadir;

    @FXML
    private HBox hboxEliminar;

    @FXML
    private HBox hboxModificar;

    @FXML
    private HBox hboxReporte;

    @FXML
    private TableView<?> tblSalones;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtEdificio;

    @FXML
    private Spinner<Integer> spCapacidad;

    //@FXML
    //private ComboBox cboxEdificio;
    @FXML
    private Spinner<Integer> spNivel;

    @FXML
    private Label lblConteo;

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void cambiarHabilitacion(boolean estado) {
        if (estado == true) {
            spCapacidad.setDisable(false);
            txtCodigo.setDisable(true);
            txtDescripcion.setDisable(false);
            txtEdificio.setDisable(false);
            spNivel.setDisable(false);
            spCapacidad.setEditable(true);
            txtCodigo.setEditable(false);
            txtDescripcion.setEditable(true);
            txtEdificio.setEditable(true);
            spNivel.setEditable(true);

        } else {
            spCapacidad.setDisable(true);
            txtCodigo.setDisable(true);
            txtDescripcion.setDisable(true);
            txtEdificio.setDisable(true);
            spNivel.setDisable(true);
            spCapacidad.setEditable(false);
            txtCodigo.setEditable(false);
            txtDescripcion.setEditable(false);
            txtEdificio.setEditable(false);
            spNivel.setEditable(false);

        }
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

    @FXML
    private void clickRegresar(ActionEvent event) {
        escenarioPrincipal.mostrarEscenaPrincipal();

    }

    public void limpiar() {
        txtCodigo.setText("");
        txtDescripcion.setText("");
        spNivel.getValueFactory().setValue(1);
        spCapacidad.getValueFactory().setValue(1);
        txtEdificio.setText("");
    }

    /*
    public void cargarCombo(){
        try{
            String SQL= "SELECT salones.edificio FROM salones ORDER BY salones.edificio ASC";
            PreparedStatement pst = ConexionDb.getInstance().getConexion().prepareStatement(SQL);
            ResultSet rs = pst.executeQuery();
            cboxEdificio.setId("Seleccione un edificio");
            while (rs.next()){
                cboxEdificio.getItems().addAll(rs.getString("salones.edificio"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
     */
    public void cargarSpinners() {
        SpinnerValueFactory<Integer> capacidad = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 120, 1);
        SpinnerValueFactory<Integer> nivel = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 3, 1);
        spCapacidad.setValueFactory(capacidad);
        spNivel.setValueFactory(nivel);
    }

    public ObservableList getSalones() {
        ArrayList<Salon> lista = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = ConexionDb.getInstance().getConexion().prepareCall("CALL sp_read_salones");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Salon salones = new Salon();
                salones.setCodigo(rs.getString(1));
                salones.setDescripcion(rs.getString(2));
                salones.setCapacidadMax(rs.getInt(3));
                salones.setEdificio(rs.getString(4));
                salones.setNivel(rs.getInt(5));
                lista.add(salones);
                System.out.println(salones.toString());
            }
            listaSalones = FXCollections.observableArrayList(lista);
            numero = listaSalones.size();
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
        return listaSalones;
    }

    /*
     public void comboEdificios(){
        Salon s = new Salon();
        
        ObservableList<String> lista = FXCollections.observableArrayList("SI" , "NO"); //AÑADIR DESDE LA BASE DE DATOS
        cboxEdificio.getItems().addAll(listaSalones);
    }
     */
    public void cargarDatos() {
        tblSalones.setItems(getSalones());
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
        colEdificio.setCellValueFactory(new PropertyValueFactory<>("Edificio"));
        colNivel.setCellValueFactory(new PropertyValueFactory<>("Nivel"));
        colCapacidad.setCellValueFactory(new PropertyValueFactory<>("CapacidadMax"));
        //clickTabla();
    }

    @FXML
    private void conteoLabel() {
        String tamaño = " " + numero;
        System.out.println("Numero de datos: " + numero);
        lblConteo.setText(tamaño);
    }

    
    
    @FXML
    private void clickNuevo() {
        switch (operacion) {
            case NINGUNO:
                cambiarHabilitacion(true);
                txtCodigo.setEditable(true);
                txtCodigo.setDisable(false);
                tblSalones.setDisable(true);
                limpiar();
                btnNuevo.setText("Guardar");
                imageNuevo.setImage(new Image((PAQUETE_IMAGE + "agregar.png")));
                imageModificar.setImage(new Image((PAQUETE_IMAGE + "cancelar.png")));
                btnModificar.setText("Cancelar");
                btnModificar.setDisable(false);
                btnReporte.setDisable(true);
                btnEliminar.setDisable(true);
                operacion = ControladorSalones.Operacion.GUARDAR;
                break;
            case GUARDAR:
                if (txtCodigo.getText().isEmpty() && spCapacidad.getValueFactory().getValue().equals(1)) {
                    Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                    alerta2.setTitle("Control Academico");
                    alerta2.setHeaderText(null);
                    alerta2.setContentText("SE NECESITA QUE SE LLENEN LOS CAMPOS CODIGO Y CAPACIDAD");
                    Stage stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
                    alerta2.show();
                    limpiar();
                    //validacionesAgregar();
                } else {
                    validacionesAgregar();
                    if (agregarSalon()) {
                        cargarDatos();
                        conteoLabel();
                        limpiar();
                        cambiarHabilitacion(false);
                        tblSalones.setDisable(false);
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

        } else if (spCapacidad.getValueFactory().getValue() <= 1) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Control Academico");
            alerta.setHeaderText(null);
            alerta.setContentText("SE NECESITA AUMENTAR EL VALOR DE CAPACIDAD");
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta.show();
            limpiar();
        }
    }
    
    public boolean agregarSalon() {
        String codigo = txtCodigo.getText();
        //System.out.println(carne + nombre1+ nombre2+ nombre3+ apellido1+ apellido2);
        //validacionesAgregar();
        if (codigo.length() > 0) {
            System.out.println("Se paso el primer if");
            salones.setCodigo(txtCodigo.getText());
            salones.setDescripcion(txtDescripcion.getText());
            salones.setEdificio(txtEdificio.getText());
            salones.setNivel(spNivel.getValue());
            salones.setCapacidadMax(spCapacidad.getValue());
            System.out.println("Se setearon los datos");
            try {
                //validacionesAgregar();
                String SQL = "CALL sp_create_salones (?,?,?,?,?)";
                PreparedStatement pst = ConexionDb.getInstance().getConexion().prepareCall(SQL);
                System.out.println("Se paso el pst");
                pst.setString(1, salones.getCodigo());
                pst.setString(2, salones.getDescripcion());
                pst.setInt(3, salones.getCapacidadMax());
                pst.setString(4, salones.getEdificio());
                pst.setInt(5, salones.getNivel());
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
    
    public boolean modificarSalones() {
        if (existeElemento()) {

           try {
                String SQL = "CALL sp_update_salones(?,?,?,?,?)";
                PreparedStatement pst = ConexionDb.getInstance().getConexion().prepareCall(SQL);
                pst.setString(1, txtDescripcion.getText());
                pst.setInt(2, spCapacidad.getValueFactory().getValue());
                pst.setString(3, txtEdificio.getText());
                pst.setInt(4, spNivel.getValueFactory().getValue());
                pst.setString(5, txtCodigo.getText());
                pst.executeUpdate();
                //System.out.println("nombre" + salon.toString());
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
        //}
        return false;
    }

    @FXML
    private void clickModificar() {
        switch (operacion) {
            case NINGUNO:
                if (existeElemento()) {
                    cambiarHabilitacion(true);

                    btnNuevo.setDisable(true);

                    btnModificar.setText("Guardar");
                    imageModificar.setImage(new Image(PAQUETE_IMAGE + "Modifu.png"));

                    btnEliminar.setText("Cancelar");
                    imageDelete.setImage(new Image(PAQUETE_IMAGE + "cancelar.png"));

                    btnReporte.setDisable(true);
                    operacion = ControladorSalones.Operacion.ACTUALIZAR;
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
                tblSalones.setDisable(false);
                limpiar();
                operacion = Operacion.NINGUNO;
                break;
            case ACTUALIZAR:
                imageDelete.setImage(new Image((PAQUETE_IMAGE + "cancelar.png")));
                btnEliminar.setText("Cancelar");
                cambiarHabilitacion(true);
                if (spCapacidad.getValueFactory().getValue() <= 1) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Control Academico");
                    alerta.setHeaderText(null);
                    alerta.setContentText("SE NECESITA AUMENTAR EL VALOR DE CAPACIDAD");
                    Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
                    alerta.show();
                    limpiar();
                } else {
                    //System.out.println("Afuera del if");
                    if (modificarSalones()) {
                        //System.out.println("Se entro al if");
                        btnNuevo.setDisable(false);
                        btnEliminar.setText("Eliminar");
                        imageDelete.setImage(new Image((PAQUETE_IMAGE + "expediente.png")));
                        imageModificar.setImage(new Image((PAQUETE_IMAGE + "contrato.png")));
                        btnModificar.setText("Modificar");
                        btnReporte.setDisable(false);
                        limpiar();
                        cambiarHabilitacion(false);
                        operacion = Operacion.NINGUNO;
                    }

                }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //cargarCombo();
        cargarSpinners();
        cambiarHabilitacion(false);
        cargarDatos();
        conteoLabel();
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
        return (tblSalones.getSelectionModel().getSelectedItem() != null);
    }

    @FXML
    public void seleccionarElemento() {
        if (existeElemento()) {
            txtCodigo.setText(((Salon) tblSalones.getSelectionModel().getSelectedItem()).getCodigo());
            txtDescripcion.setText(((Salon) tblSalones.getSelectionModel().getSelectedItem()).getDescripcion());
            txtEdificio.setText(((Salon) tblSalones.getSelectionModel().getSelectedItem()).getEdificio());
            spNivel.getValueFactory().setValue(((Salon) tblSalones.getSelectionModel().getSelectedItem()).getNivel());
            spCapacidad.getValueFactory().setValue(((Salon) tblSalones.getSelectionModel().getSelectedItem()).getCapacidadMax());
            //}else{
            //limpiar();
            /*
            txtCarne.setText(null);
            txtNombre.setText(null);
            txtSegundoNombre.setText(null);
            txtTercerNombre.setText(null);
            txtApellido.setText(null);
            txtSegundoApellido.setText(null);
             */
        }
    }
    public boolean eliminarSalon() {
        Salon salon = ((Salon) tblSalones.getSelectionModel().getSelectedItem());
        //System.out.println(alumno.toString());
        PreparedStatement pst = null;
        try {
            //System.out.println(alumno.toString());
            String SQL = "CALL sp_delete_salones(?)";
            //System.out.println("Paso el if de confirmar");
            pst = ConexionDb.getInstance().getConexion().prepareCall(SQL);
            pst.setString(1, salon.getCodigo());
            System.out.println(pst);
            pst.execute();
            numero = numero - 1;
            //cargarDatos();
            //registros.remove(tblAlumnos.getSelectionModel().getFocusedIndex());
            limpiar();
            return true;
        } catch (SQLException e) {
            System.err.println("Se produjo un error al eliminar el registro: " + salon.toString());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("Llego al false");
        return false;
    }

    @FXML
    private void clickEliminar() {
        switch (operacion) {
            case ACTUALIZAR:
                btnNuevo.setDisable(false);
                btnEliminar.setText("Eliminar");
                imageDelete.setImage(new Image((PAQUETE_IMAGE + "expediente.png")));
                imageModificar.setImage(new Image((PAQUETE_IMAGE + "contrato.png")));
                btnModificar.setText("Modificar");
                btnReporte.setDisable(false);
                limpiar();
                cambiarHabilitacion(false);
                operacion = ControladorSalones.Operacion.NINGUNO;
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
                            listaSalones.remove(tblSalones.getSelectionModel().getFocusedIndex());
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
}
