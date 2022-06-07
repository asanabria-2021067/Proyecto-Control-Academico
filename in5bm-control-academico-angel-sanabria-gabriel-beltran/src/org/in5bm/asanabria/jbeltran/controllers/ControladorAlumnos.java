package org.in5bm.asanabria.jbeltran.controllers;

import org.in5bm.asanabria.jbeltran.models.Alumno;
import java.sql.Connection;
import java.sql.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.in5bm.asanabria.jbeltran.db.ConexionDb;
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
public class ControladorAlumnos implements Initializable {

    private Principal escenarioPrincipal;

    private enum Operacion {
        NINGUNO, GUARDAR, ACTUALIZAR
    }
    int numero;
    ConexionDb con;
    Alumno alumno = new Alumno();
    private Operacion operacion = Operacion.NINGUNO;
    private final String PAQUETE_IMAGE = "org/in5bm/asanabria/jbeltran/resources/images/";
    //ArrayList<Alumno> conteo = new ArrayList<>();

    @FXML
    private HBox hboxAñadir;

    @FXML
    private HBox hboxEliminar;

    @FXML
    private HBox hboxModificar;

    @FXML
    private HBox hboxReporte;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnReporte;

    @FXML
    private ImageView imageNuevo;

    @FXML
    private ImageView imageModificar;

    @FXML
    private ImageView imageDelete;

    @FXML
    private ImageView imageReporte;

    @FXML
    private TableView<Alumno> tblAlumnos;

    @FXML
    private TableColumn<Alumno, String> colApellido1;

    @FXML
    private TableColumn<Alumno, String> colApellido2;

    @FXML
    private TableColumn<Alumno, String> colCarne;

    @FXML
    private TableColumn<Alumno, String> colNombre1;

    @FXML
    private TableColumn<Alumno, String> colNombre2;

    @FXML
    private TableColumn<Alumno, String> colNombre3;

    private ObservableList<Alumno> listaAlumnos = FXCollections.observableArrayList();

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtCarne;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtSegundoApellido;

    @FXML
    private TextField txtSegundoNombre;

    @FXML
    private TextField txtTercerNombre;

    @FXML
    private Label lblConteo;

    public ControladorAlumnos() {

    }

    /*
    public void getAlumnos(){
        PreparedStatement pst = null;
        ResultSet rs=null;
        try{
            pst = ConexionDb.getInstance().getConexion().prepareStatement("SELECT * FROM alumnos");
        }catch(SQLException e){
            System.out.println("Se produjo un error al mostrar la lista de alumnos");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
     */
    public ObservableList getAlumnos() {
        ArrayList<Alumno> lista = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = ConexionDb.getInstance().getConexion().prepareCall("CALL sp_read_alumnos");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setCarne(rs.getString(1));
                alumno.setNombre1(rs.getString(2));
                alumno.setNombre2(rs.getString(3));
                alumno.setNombre3(rs.getString(4));
                alumno.setApellido1(rs.getString(5));
                alumno.setApellido2(rs.getString(6));
                lista.add(alumno);
                //System.out.println(alumno.toString());
            }
            listaAlumnos = FXCollections.observableArrayList(lista);
            numero = listaAlumnos.size();
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
        return listaAlumnos;
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    @FXML
    private void clickRegresar(ActionEvent event) {
        escenarioPrincipal.mostrarEscenaPrincipal();

    }

    /*
    @FXML
    public void clicNuevo(){
        btnNuevo.setText("Agregar");
        habilitarCampos();
        //insertarDatos();
        //deshabilitarCampos();
        regresoNuevo();
        
    }
     */
    public void limpiar() {
        txtCarne.setText("");
        txtNombre.setText("");
        txtSegundoNombre.setText("");
        txtTercerNombre.setText("");
        txtApellido.setText("");
        txtSegundoApellido.setText("");
    }

    public void cargarDatos() {
        tblAlumnos.setItems(getAlumnos());
        colCarne.setCellValueFactory(new PropertyValueFactory<Alumno, String>("Carne"));
        colNombre1.setCellValueFactory(new PropertyValueFactory<Alumno, String>("Nombre1"));
        colNombre2.setCellValueFactory(new PropertyValueFactory<Alumno, String>("Nombre2"));
        colNombre3.setCellValueFactory(new PropertyValueFactory<Alumno, String>("Nombre3"));
        colApellido1.setCellValueFactory(new PropertyValueFactory<Alumno, String>("Apellido1"));
        colApellido2.setCellValueFactory(new PropertyValueFactory<Alumno, String>("Apellido2"));
        //clickTabla();
    }

    /*
    public ObservableList<Alumno> listaAlumnos(){
            ObservableList<Alumno> lista= FXCollections.observableArrayList();
            ResultSet rs = null;
            PreparedStatement st = null;
            String SQL ="SELECT * FROM alumnos";
            try{
                st=ConexionDb.getInstance().getConexion().prepareStatement(SQL);
                rs= st.executeQuery();
                Alumno alumno;
                while(rs.next()){
                    alumno = new Alumno(rs.getString("carne"), rs.getString("nombre1"),
                            rs.getString("Nombre2"),rs.getString("Nombre3"),
                            rs.getString("Apellido1"),rs.getString("Apellido2"));
                    lista.add(alumno);
                }
                
            }catch(SQLException e){
                e.printStackTrace();
            } finally {
                try {
                    if(st != null){
                        st.close();
                    }
                    if(rs != null){
                        rs.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        return lista;
    }
     */
    @FXML
    private void conteoLabel() {
        String tamaño = " " + numero;
        System.out.println("Numero de datos: " + numero);
        lblConteo.setText(tamaño);
    }

    public void validacionesAgregar() {
        if (txtCarne.getText().isEmpty() && txtNombre.getText().isEmpty() && txtApellido.getText().isEmpty()) {
                    Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                    alerta2.setTitle("Control Academico");
                    alerta2.setHeaderText(null);
                    alerta2.setContentText("SE NECESITA QUE SE LLENEN LOS CAMPOS CARNE, NOMBRE, APELLIDO");
                    Stage stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
                    alerta2.show();
                    limpiar();
    }else if (txtCarne.getText().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Control Academico");
            alerta.setHeaderText(null);
            alerta.setContentText("SE NECESITA LLENAR EL VALOR DE CARNE");
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta.show();
            limpiar();

        } else if (txtCarne.getText().length() >= 15) {
            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
            alerta2.setTitle("Control Academico");
            alerta2.setHeaderText(null);
            alerta2.setContentText("SE NECESITA QUE EL VALOR DE CARNE SEA MENOR A 15 LETRAS O VALORES");
            Stage stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta2.show();
            limpiar();

        } else if (txtNombre.getText().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Control Academico");
            alerta.setHeaderText(null);
            alerta.setContentText("SE NECESITA LLENAR EL VALOR DE NOMBRE");
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta.show();
            limpiar();

        } else if (txtNombre.getText().length() >= 15) {
            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
            alerta2.setTitle("Control Academico");
            alerta2.setHeaderText(null);
            alerta2.setContentText("SE NECESITA QUE EL VALOR DE NOMBRE SEA MENOR A 15 LETRAS");
            Stage stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta2.show();
            limpiar();

        } else if (txtApellido.getText().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Control Academico");
            alerta.setHeaderText(null);
            alerta.setContentText("SE NECESITA LLENAR EL VALOR DE APELLIDO");
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta.show();
            limpiar();

        } else if (txtApellido.getText().length() > 15) {
            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
            alerta2.setTitle("Control Academico");
            alerta2.setHeaderText(null);
            alerta2.setContentText("SE NECESITA QUE EL VALOR DE APELLIDO SEA MENOR A 15 LETRAS");
            Stage stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta2.show();
            limpiar();
        }
    }

    @FXML
    private void clickNuevo() {
        switch (operacion) {
            case NINGUNO:
                cambiarHabilitacion(true);
                txtCarne.setEditable(true);
                txtCarne.setDisable(false);
                tblAlumnos.setDisable(true);
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
                    if (agregarAlumno()) {
                        validacionesAgregar();
                        //agregarAlumno();
                        //cargarDatos();
                        conteoLabel();
                        limpiar();
                        tblAlumnos.setDisable(false);
                        cambiarHabilitacion(false);
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

    public void cambiarHabilitacion(boolean estado) {
        if (estado == true) {
            txtCarne.setDisable(true);
            txtNombre.setDisable(false);
            txtSegundoNombre.setDisable(false);
            txtTercerNombre.setDisable(false);
            txtApellido.setDisable(false);
            txtSegundoApellido.setDisable(false);
            txtCarne.setEditable(false);
            txtNombre.setEditable(true);
            txtSegundoNombre.setEditable(true);
            txtTercerNombre.setEditable(true);
            txtApellido.setEditable(true);
            txtSegundoApellido.setEditable(true);

        } else {
            txtCarne.setDisable(true);
            txtNombre.setDisable(true);
            txtSegundoNombre.setDisable(true);
            txtTercerNombre.setDisable(true);
            txtApellido.setDisable(true);
            txtSegundoApellido.setDisable(true);
            txtCarne.setEditable(false);
            txtNombre.setEditable(false);
            txtSegundoNombre.setEditable(false);
            txtTercerNombre.setEditable(false);
            txtApellido.setEditable(false);
            txtSegundoApellido.setEditable(false);
        }
    }

    public boolean agregarAlumno() {
        String carne = txtCarne.getText();
        String nombre1 = txtNombre.getText();
        String nombre2 = txtSegundoNombre.getText();
        String nombre3 = txtTercerNombre.getText();
        String apellido1 = txtApellido.getText();
        String apellido2 = txtSegundoApellido.getText();
        PreparedStatement pst = null;
        //System.out.println(carne + nombre1+ nombre2+ nombre3+ apellido1+ apellido2);
        validacionesAgregar();
        if (carne.length() > 0 && nombre1.length() > 0 && apellido1.length() > 0) {
            System.out.println("Se paso el primer if");
            alumno.setCarne(txtCarne.getText());
            alumno.setNombre1(txtNombre.getText());
            alumno.setNombre2(txtSegundoNombre.getText());
            alumno.setNombre3(txtTercerNombre.getText());
            alumno.setApellido1(txtApellido.getText());
            alumno.setApellido2(txtSegundoApellido.getText());
            System.out.println("Se setearon los datos");
            try {
                //validacionesAgregar();
                String SQL = "{CALL sp_create_alumnos (?,?,?,?,?,?)}";
                pst = ConexionDb.getInstance().getConexion().prepareCall(SQL);
                System.out.println("Se paso el pst");
                pst.setString(1, alumno.getCarne());
                pst.setString(2, alumno.getNombre1());
                pst.setString(3, alumno.getNombre2());
                pst.setString(4, alumno.getNombre3());
                pst.setString(5, alumno.getApellido1());
                pst.setString(6, alumno.getApellido2());
                System.out.println(pst.toString());
                pst.executeUpdate();
                listaAlumnos.add(alumno);
                /*
                        cargarDatos();
                        conteoLabel();
                        limpiar();
                 */
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("SE PRODUJO UN ERROR AL INGRESAR LOS DATOS " + alumno.toString());
            } finally {
                try {
                    if (pst != null) {
                        pst.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public void validacionesModificar() {
        if (txtNombre.getText().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Control Academico");
            alerta.setHeaderText(null);
            alerta.setContentText("SE NECESITA LLENAR EL VALOR DE NOMBRE");
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta.show();
            limpiar();

        } else if (txtNombre.getText().length() >= 15) {
            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
            alerta2.setTitle("Control Academico");
            alerta2.setHeaderText(null);
            alerta2.setContentText("SE NECESITA QUE EL VALOR DE NOMBRE SEA MENOR A 15 LETRAS");
            Stage stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta2.show();
            limpiar();

        } else if (txtApellido.getText().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Control Academico");
            alerta.setHeaderText(null);
            alerta.setContentText("SE NECESITA LLENAR EL VALOR DE APELLIDO");
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta.show();
            limpiar();

            if (txtApellido.getText().length() > 15) {
                Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                alerta2.setTitle("Control Academico");
                alerta2.setHeaderText(null);
                alerta2.setContentText("SE NECESITA QUE EL VALOR DE APELLIDO SEA MENOR A 15 LETRAS");
                stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
                alerta.show();
                limpiar();

            }
        }
    }

    public boolean modificarAlumno() {
        if (existeElemento()) {
            PreparedStatement pst = null;
            System.out.println("Segundo if");
            try {
                validacionesModificar();
                String SQL = "{CALL sp_update_alumnos(?,?,?,?,?,?)}";
                pst = ConexionDb.getInstance().getConexion().prepareCall(SQL);
                pst.setString(1, txtNombre.getText());
                pst.setString(2, txtSegundoNombre.getText());
                pst.setString(3, txtTercerNombre.getText());
                pst.setString(4, txtApellido.getText());
                pst.setString(5, txtSegundoApellido.getText());
                pst.setString(6, txtCarne.getText());
                pst.executeUpdate();
                System.out.println(pst.toString());
                //System.out.println("nombre" + txtNombre.getText() + txtSegundoNombre.getText() + txtTercerNombre.getText() + txtApellido.getText() + txtSegundoApellido.getText() + txtCarne.getText());
                //System.out.println("Pasamos el execute");
                cargarDatos();
                limpiar();
                conteoLabel();
                btnNuevo.setDisable(true);
                btnReporte.setDisable(true);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (pst != null) {
                        pst.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
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
                tblAlumnos.setDisable(false);
                limpiar();
                operacion = Operacion.NINGUNO;
                break;
            case ACTUALIZAR:
                imageDelete.setImage(new Image((PAQUETE_IMAGE + "cancelar.png")));
                btnEliminar.setText("Cancelar");
                cambiarHabilitacion(true);
                txtCarne.setEditable(false);
                //validacionesModificar();
                if (modificarAlumno()) {
                    btnNuevo.setDisable(false);
                    btnEliminar.setText("Eliminar");
                    imageDelete.setImage(new Image((PAQUETE_IMAGE + "expediente.png")));
                    imageModificar.setImage(new Image((PAQUETE_IMAGE + "contrato.png")));
                    btnModificar.setText("Modificar");
                    btnReporte.setDisable(false);
                    limpiar();
                    cambiarHabilitacion(false);
                    tblAlumnos.getSelectionModel().clearSelection();
                }
                operacion = Operacion.NINGUNO;
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {;
        cambiarHabilitacion(false);
        cargarDatos();
        conteoLabel();
    }

    /*
    public void clickTabla(){
        tblAlumnos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener <Alumno> (){
            @Override
            public void changed(ObservableValue<? extends Alumno> arg0, 
                Alumno valorAnterior, Alumno valorSeleccionado){
                if(valorSeleccionado != null){
                    txtCarne.setText(valorSeleccionado.getCarne());                
                    txtNombre.setText(valorSeleccionado.getNombre1());
                    txtSegundoNombre.setText(valorSeleccionado.getNombre2());
                    txtTercerNombre.setText(valorSeleccionado.getNombre3());
                    txtApellido.setText(valorSeleccionado.getApellido1());
                    txtSegundoApellido.setText(valorSeleccionado.getApellido2());
                }   
            }
                    
                }
        );
    }
     */
    public boolean existeElemento() {

        if ((tblAlumnos.getSelectionModel().getSelectedItem() != null)) {
            return true;
        } else if ((tblAlumnos.getSelectionModel().getSelectedItem() == null)) {
            return false;
        }

        // hacer if que si es igual a null poner todos los txt vacios
        //return (tblAlumnos.getSelectionModel().getSelectedItem() != null);
        return false;
    }

    @FXML
    public void seleccionarElemento() {
        if (existeElemento()) {
            txtCarne.setText(((Alumno) tblAlumnos.getSelectionModel().getSelectedItem()).getCarne());
            txtNombre.setText(((Alumno) tblAlumnos.getSelectionModel().getSelectedItem()).getNombre1());
            txtSegundoNombre.setText(((Alumno) tblAlumnos.getSelectionModel().getSelectedItem()).getNombre2());
            txtTercerNombre.setText(((Alumno) tblAlumnos.getSelectionModel().getSelectedItem()).getNombre3());
            txtApellido.setText(((Alumno) tblAlumnos.getSelectionModel().getSelectedItem()).getApellido1());
            txtSegundoApellido.setText(((Alumno) tblAlumnos.getSelectionModel().getSelectedItem()).getApellido2());
        } else {
            tblAlumnos.getSelectionModel().clearSelection();
            limpiar();
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

    /*
    @FXML
    private void eliminarDatos(){
        btnNuevo.setDisable(false);
        btnReporte.setDisable(false);
        String nombreE = btnEliminar.getText();
         if (nombreE.equals("Eliminar")){
            String carne= txtCarne.getText();
            String nombre1=txtNombre.getText();
            String nombre2=txtSegundoNombre.getText();
            String nombre3=txtTercerNombre.getText();
            String apellido1=txtApellido.getText();
            String apellido2=txtSegundoApellido.getText();
                if(carne.length()>0 && nombre1.length()>0&& nombre2.length()>0&& apellido1.length()>0){
                     
                    try{
                        String SQL = "CALL sp_eliminar_alumnos(?)";
                        Alert alert = new Alert(AlertType.CONFIRMATION, "¿Esta seguro de eliminar el registro?");
                        Optional <ButtonType> confirmar = alert.showAndWait();
                        
                        if (confirmar.get() == ButtonType.OK) {
                            PreparedStatement pst = ConexionDb.getInstance().getConexion().prepareCall(SQL);
                            pst.setString(1, txtCarne.getText());
                        /*
                            pst.setString(2, txtSegundoNombre.getText());
                            pst.setString(3, txtTercerNombre.getText());
                            pst.setString(4, txtApellido.getText());
                            pst.setString(5, txtSegundoApellido.getText());
                            pst.setString(6, txtCarne.getText());
     */
 /*
                        pst.execute();
                        numero = numero - 1;
                        cargarDatos();
                        limpiar();
                        }else{
                            limpiar();
                    }

                    }catch(SQLException e){
                        System.out.println("No se elimino");
                        e.printStackTrace();
                    }
                }

            }else if(nombreE=="Cancelar"){
                regresoEliminar();
            }
}
     */
    public boolean eliminarAlumno() {
        Alumno alumno = ((Alumno) tblAlumnos.getSelectionModel().getSelectedItem());
        //System.out.println(alumno.toString());
        PreparedStatement pst = null;
        try {
            //System.out.println(alumno.toString());
            String SQL = "{CALL sp_delete_alumnos(?)}";
            System.out.println("Paso el if de confirmar");
            pst = ConexionDb.getInstance().getConexion().prepareCall(SQL);
            pst.setString(1, alumno.getCarne());
            System.out.println(pst.toString());
            pst.execute();
            numero = numero - 1;
            //cargarDatos();
            //registros.remove(tblAlumnos.getSelectionModel().getFocusedIndex());
            //limpiar();
            return true;
        } catch (SQLException e) {
            System.err.println("Se produjo un error al eliminar el registro: " + alumno.toString());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Llego al false");
        return false;
    }

    /*
        String carne= txtCarne.getText();
        String nombre1=txtNombre.getText();
        String nombre2=txtSegundoNombre.getText();
        String nombre3=txtTercerNombre.getText();
        String apellido1=txtApellido.getText();
        String apellido2=txtSegundoApellido.getText();
            //if(carne.length()>0 && nombre1.length()>0&& nombre2.length()>0&& apellido1.length()>0){
                     
                    try{
                        String SQL = "CALL sp_delete_alumnos(?)";
                        Alert alert = new Alert(AlertType.CONFIRMATION, "¿Esta seguro de eliminar el registro?");
                        Optional <ButtonType> confirmar = alert.showAndWait();
                        
                        if (confirmar.get() == ButtonType.OK) {
                            PreparedStatement pst = ConexionDb.getInstance().getConexion().prepareCall(SQL);
                            pst.setString(1, txtCarne.getText());
                        /*
                            pst.setString(2, txtSegundoNombre.getText());
                            pst.setString(3, txtTercerNombre.getText());
                            pst.setString(4, txtApellido.getText());
                            pst.setString(5, txtSegundoApellido.getText());
                            pst.setString(6, txtCarne.getText());
     */
 /*
                        pst.execute();
                        numero = numero - 1;
                        cargarDatos();
                        limpiar();
                        }else{
                            limpiar();
                    }

                    }catch(SQLException e){
                        System.out.println("No se elimino");
                        e.printStackTrace();
                    }
                    //}
     */
    @FXML
    private void eliminarDatos() {
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
                operacion = Operacion.NINGUNO;
                break;
            case NINGUNO:
                if (existeElemento()) {
                    Alert alert = new Alert(AlertType.CONFIRMATION, "¿Esta seguro de eliminar el registro?");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
                    Optional<ButtonType> confirmar = alert.showAndWait();
                    if (confirmar.get().equals(ButtonType.OK)) {
                        if (eliminarAlumno()) {
                            limpiar();
                            conteoLabel();
                            //listaAlumnos.remove(tblAlumnos.getSelectionModel().getFocusedIndex());
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
                    } else {
                        tblAlumnos.getSelectionModel().clearSelection();
                        limpiar();
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
                tblAlumnos.getSelectionModel().clearSelection();
                operacion = Operacion.NINGUNO;
                break;
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

}
