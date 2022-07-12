package org.in5bm.asanabria.jbeltran.controllers;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.util.Optional;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
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
import org.in5bm.asanabria.jbeltran.models.Instructor;
import org.in5bm.asanabria.jbeltran.reports.GenerarReporte;
import org.in5bm.asanabria.jbeltran.system.Principal;

/**
 *
 * @author Angel Sanabria
 * @date 3/05/2022
 * @time 09:12:25
 * @grade 5to Perito en Informatica B
 * @code IN5BM
 * @carnet 2021067
 */
public class ControladorInstructores implements Initializable {

    private Principal escenarioPrincipal;
    int numero;
    @FXML
    private Label lblConteo;

    private enum Operacion {
        NINGUNO, GUARDAR, ACTUALIZAR
    }
    private Operacion operacion = Operacion.NINGUNO;
    private final String PAQUETE_IMAGE = "org/in5bm/asanabria/jbeltran/resources/images/";
    private ObservableList<Instructor> listaInstructor = FXCollections.observableArrayList();

    @FXML
    private HBox hboxAñadir;
    @FXML
    private Button btnNuevo;
    @FXML
    private ImageView imgNuevo;
    @FXML
    private HBox hboxModificar;
    @FXML
    private Button btnModificar;
    @FXML
    private ImageView imgModificar;
    @FXML
    private HBox hboxEliminar;
    @FXML
    private Button btnEliminar;
    @FXML
    private ImageView imgEliminar;
    @FXML
    private HBox hboxReporte;
    @FXML
    private Button btnReporte;
    @FXML
    private ImageView imgReporte;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombre1;
    @FXML
    private TextField txtNombre2;
    @FXML
    private TextField txtNombre3;
    @FXML
    private TextField txtApellido1;
    @FXML
    private TextField txtApellido2;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefono;
    @FXML
    private DatePicker dpFechaNacimiento;
    @FXML
    private TableView<Instructor> tblInstructores;
    @FXML
    private TableColumn<Instructor, Integer> colId;
    @FXML
    private TableColumn<Instructor, String> colNombre1;
    @FXML
    private TableColumn<Instructor, String> colNombre2;
    @FXML
    private TableColumn<Instructor, String> colNombre3;
    @FXML
    private TableColumn<Instructor, String> colApellido1;
    @FXML
    private TableColumn<Instructor, String> colApellido2;
    @FXML
    private TableColumn<Instructor, String> colDireccion;
    @FXML
    private TableColumn<Instructor, String> colEmail;
    @FXML
    private TableColumn<Instructor, String> colTelefono;
    @FXML
    private TableColumn<Instructor, String> colFecha;

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

    @FXML
    private void deseleccionarElemento() {
        //limpiar();
        tblInstructores.getSelectionModel().clearSelection();
    }

    public ObservableList getInstructores() {
        ArrayList<Instructor> lista = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = ConexionDb.getInstance().getConexion().prepareCall("CALL sp_read_instructores");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Instructor instructores = new Instructor();
                instructores.setId(rs.getInt(1));
                instructores.setNombre1(rs.getString(2));
                instructores.setNombre2(rs.getString(3));
                instructores.setNombre3(rs.getString(4));
                instructores.setApellido1(rs.getString(5));
                instructores.setApellido2(rs.getString(6));
                instructores.setDireccion(rs.getString(7));
                instructores.setEmail(rs.getString(8));
                instructores.setTelefono(rs.getString(9));
                instructores.setFechaNacimiento(rs.getDate(10));
                lista.add(instructores);
                //System.out.println(alumno.toString());
            }
            listaInstructor = FXCollections.observableArrayList(lista);
            System.out.println("ARREGLO: " + listaInstructor);
            numero = listaInstructor.size();
        } catch (SQLException e) {
            System.err.println("\n Se produjo un error al intentar consultar la lista instructores");
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
        return listaInstructor;
    }

    public void cargarDatos() {
        tblInstructores.setItems(getInstructores());
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre1.setCellValueFactory(new PropertyValueFactory<>("nombre1"));
        colNombre2.setCellValueFactory(new PropertyValueFactory<>("nombre2"));
        colNombre3.setCellValueFactory(new PropertyValueFactory<>("nombre3"));
        colApellido1.setCellValueFactory(new PropertyValueFactory<>("apellido1"));
        colApellido2.setCellValueFactory(new PropertyValueFactory<>("apellido2"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));

        //clickTabla();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        cambiarHabilitacion(false);
        cargarDatos();
        conteoLabel();
    }

    public void limpiar() {
        txtId.setText(null);
        txtNombre1.setText(null);
        txtNombre2.setText(null);
        txtNombre3.setText(null);
        txtApellido1.setText(null);
        txtApellido2.setText(null);
        txtEmail.setText(null);
        txtDireccion.setText(null);
        txtTelefono.setText(null);
        dpFechaNacimiento.setValue(null);

    }

    private void conteoLabel() {
        String tamaño = " " + numero;
        System.out.println("Numero de datos: " + numero);
        lblConteo.setText(tamaño);
    }

    public void cambiarHabilitacion(boolean estado) {
        if (estado == true) {
            txtId.setDisable(true);
            txtNombre1.setDisable(false);
            txtNombre2.setDisable(false);
            txtNombre3.setDisable(false);
            txtApellido1.setDisable(false);
            txtApellido2.setDisable(false);
            txtEmail.setDisable(false);
            txtDireccion.setDisable(false);
            txtTelefono.setDisable(false);
            dpFechaNacimiento.setDisable(false);
            txtId.setEditable(false);
            txtNombre1.setEditable(true);
            txtNombre2.setEditable(true);
            txtNombre3.setEditable(true);
            txtApellido1.setEditable(true);
            txtApellido2.setEditable(true);
            txtEmail.setEditable(true);
            txtDireccion.setEditable(true);
            txtTelefono.setEditable(true);
        } else {
            txtId.setDisable(true);
            txtNombre1.setDisable(true);
            txtNombre2.setDisable(true);
            txtNombre3.setDisable(true);
            txtApellido1.setDisable(true);
            txtApellido2.setDisable(true);
            txtEmail.setDisable(true);
            txtDireccion.setDisable(true);
            txtTelefono.setDisable(true);
            dpFechaNacimiento.setDisable(true);
            txtId.setEditable(false);
            txtNombre1.setEditable(false);
            txtNombre2.setEditable(false);
            txtNombre3.setEditable(false);
            txtApellido1.setEditable(false);
            txtApellido2.setEditable(false);
            txtEmail.setEditable(false);
            txtDireccion.setEditable(false);
            txtTelefono.setEditable(false);
        }
    }

    public boolean validaciones() {
        if (txtNombre1.getText() == null && txtApellido1.getText() == null && txtEmail.getText() == null
                && txtTelefono.getText() == null && dpFechaNacimiento.getValue() == null) {
            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
            alerta2.setTitle("Control Academico");
            alerta2.setHeaderText(null);
            alerta2.setContentText("SE NECESITA LLENAR EL VALOR DE NOMBRE, APELLIDO, EMAIL, TELEFONO");
            Stage stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta2.show();
            return false;
        } else if (txtNombre1.getText() == null) {
            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
            alerta2.setTitle("Control Academico");
            alerta2.setHeaderText(null);
            alerta2.setContentText("SE NECESITA LLENAR EL VALOR DE NOMBRE");
            Stage stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta2.show();
            return false;
            //limpiar();

        } else if (txtNombre1.getText().length() >= 15) {
            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
            alerta2.setTitle("Control Academico");
            alerta2.setHeaderText(null);
            alerta2.setContentText("SE NECESITA QUE EL VALOR DE NOMBRE SEA MENOR A 15 LETRAS");
            Stage stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta2.show();
            return false;
            //limpiar();

        } else if (txtApellido1.getText() == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Control Academico");
            alerta.setHeaderText(null);
            alerta.setContentText("SE NECESITA LLENAR EL VALOR DE PRIMER APELLIDO");
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta.show();
            return false;
            //limpiar();

        } else if (txtApellido1.getText().length() > 15) {
            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
            alerta2.setTitle("Control Academico");
            alerta2.setHeaderText(null);
            alerta2.setContentText("SE NECESITA QUE EL VALOR DE PRIMER APELLIDO SEA MENOR A 15 LETRAS");
            Stage stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta2.show();
            return false;
            //limpiar();

        } else if (txtEmail.getText() == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Control Academico");
            alerta.setHeaderText(null);
            alerta.setContentText("SE NECESITA LLENAR EL VALOR DE EMAIL");
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta.show();
            return false;
            //limpiar();

        } else if (txtEmail.getText().length() > 45) {
            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
            alerta2.setTitle("Control Academico");
            alerta2.setHeaderText(null);
            alerta2.setContentText("SE NECESITA QUE EL VALOR DE EMAIL SEA MENOR A 45 VALORES");
            Stage stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta2.show();
            return false;
            //limpiar();

        } else if (txtTelefono.getText() == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Control Academico");
            alerta.setHeaderText(null);
            alerta.setContentText("SE NECESITA LLENAR EL VALOR DE TELEFONO");
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta.show();
            return false;
            //limpiar();

        } else if (txtTelefono.getText().length() > 8) {
            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
            alerta2.setTitle("Control Academico");
            alerta2.setHeaderText(null);
            alerta2.setContentText("SE NECESITA QUE EL VALOR DE TELEFONO SEA MENOR A 8 VALORES");
            Stage stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta2.show();
            return false;
            //limpiar();
        } else if (dpFechaNacimiento.getValue() == null) {
            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
            alerta2.setTitle("Control Academico");
            alerta2.setHeaderText(null);
            alerta2.setContentText("SE NECESITA QUE SE LLENE EL VALOR DE FECHA");
            Stage stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta2.show();
            return false;
        }
        return true;
    }

    public boolean agregarInstructores() {
        /*
        String nombre1 = txtNombre1.getText();
        String apellido1 = txtApellido1.getText();
        String email = txtEmail.getText();
        String telefono = txtTelefono.getText();
         */
        PreparedStatement pst = null;
        //System.out.println(carne + nombre1+ nombre2+ nombre3+ apellido1+ apellido2);
        //if (nombre1.length() > 0 && apellido1.length() > 0 && email.length() > 0 && telefono.length() > 0) {
        System.out.println("Se paso el primer if");
        Instructor instructor = new Instructor();
        System.out.println("Nombre1: " + txtNombre1.getText());
        System.out.println("Apellido1: " + txtApellido1.getText());
        System.out.println("Email: " + txtEmail.getText());
        System.out.println("Telefono: " + txtTelefono.getText());

        if (validaciones()) {
            //if(validarEmail()){
            //instructor.setId(Integer.parseInt(txtId.getText()));
            instructor.setNombre1(txtNombre1.getText());
            instructor.setNombre2(txtNombre2.getText());
            instructor.setNombre3(txtNombre3.getText());
            instructor.setApellido1(txtApellido1.getText());
            instructor.setApellido2(txtApellido2.getText());
            instructor.setDireccion(txtDireccion.getText());
            instructor.setEmail(txtEmail.getText());
            instructor.setTelefono(txtTelefono.getText());
            instructor.setFechaNacimiento(Date.valueOf(dpFechaNacimiento.getValue()));
            System.out.println("Se setearon los datos");
            try {
                //validacionesAgregar();
                String SQL = "{CALL sp_create_instructores (?,?,?,?,?,?,?,?,?)}";
                pst = ConexionDb.getInstance().getConexion().prepareCall(SQL);
                System.out.println("Se paso el pst");
                pst.setString(1, instructor.getNombre1());
                pst.setString(2, instructor.getNombre2());
                pst.setString(3, instructor.getNombre3());
                pst.setString(4, instructor.getApellido1());
                pst.setString(5, instructor.getApellido2());
                pst.setString(6, instructor.getDireccion());
                pst.setString(7, instructor.getEmail());
                pst.setString(8, instructor.getTelefono());
                pst.setDate(9, instructor.getFechaNacimiento());
                System.out.println(pst.toString());
                pst.executeUpdate();
                listaInstructor.add(instructor);
                numero = listaInstructor.size();
                /*
                            cargarDatos();
                            conteoLabel();
                            limpiar();
                 */
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("SE PRODUJO UN ERROR AL INGRESAR LOS DATOS " + instructor.toString());
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
        //}
        return false;
    }

    public boolean validarEmail(String email) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = ConexionDb.getInstance().getConexion().prepareStatement("SELECT email from instructores WHERE email LIKE %" + email + "%");
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Control Academico");
                alerta.setHeaderText(null);
                alerta.setContentText("EL EMAIL YA ESTA EN USO, INGRESE OTRO");
                Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
                alerta.show();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @FXML
    private void clickNuevo() {
        switch (operacion) {
            case NINGUNO:
                cambiarHabilitacion(true);
                tblInstructores.setDisable(true);
                tblInstructores.getSelectionModel().clearSelection();
                limpiar();
                btnNuevo.setText("Guardar");
                imgNuevo.setImage(new Image((PAQUETE_IMAGE + "agregar.png")));
                imgModificar.setImage(new Image((PAQUETE_IMAGE + "cancelar.png")));
                btnModificar.setText("Cancelar");
                btnModificar.setDisable(false);
                btnReporte.setDisable(true);
                btnEliminar.setDisable(true);
                operacion = Operacion.GUARDAR;
                break;
            case GUARDAR:
                if (agregarInstructores()) {
                    //validacionesAgregar();
                    //agregarAlumno();
                    cargarDatos();
                    conteoLabel();
                    limpiar();
                    tblInstructores.setDisable(false);
                    cambiarHabilitacion(false);
                    btnNuevo.setText("Nuevo");
                    imgNuevo.setImage(new Image(PAQUETE_IMAGE + "anadir.png"));
                    imgModificar.setImage(new Image(PAQUETE_IMAGE + "contrato.png"));
                    btnModificar.setText("Modificar");
                    btnNuevo.setDisable(false);
                    btnReporte.setDisable(false);
                    btnEliminar.setDisable(false);

                    operacion = Operacion.NINGUNO;
                }

                break;

        }
    }

    @FXML
    public void seleccionarElemento() {
        txtId.setText(String.valueOf((((Instructor) tblInstructores.getSelectionModel().getSelectedItem()).getId())));
        txtNombre1.setText(((Instructor) tblInstructores.getSelectionModel().getSelectedItem()).getNombre1());
        txtNombre2.setText(((Instructor) tblInstructores.getSelectionModel().getSelectedItem()).getNombre2());
        txtNombre3.setText(((Instructor) tblInstructores.getSelectionModel().getSelectedItem()).getNombre3());
        txtApellido1.setText(((Instructor) tblInstructores.getSelectionModel().getSelectedItem()).getApellido1());
        txtApellido2.setText(((Instructor) tblInstructores.getSelectionModel().getSelectedItem()).getApellido2());
        txtDireccion.setText(((Instructor) tblInstructores.getSelectionModel().getSelectedItem()).getDireccion());
        txtEmail.setText(((Instructor) tblInstructores.getSelectionModel().getSelectedItem()).getEmail());
        txtTelefono.setText(((Instructor) tblInstructores.getSelectionModel().getSelectedItem()).getTelefono());
        dpFechaNacimiento.setValue((((Instructor) tblInstructores.getSelectionModel().getSelectedItem()).getFechaNacimiento().toLocalDate()));

    }

    public boolean existeElemento() {

        if ((tblInstructores.getSelectionModel().getSelectedItem() != null)) {
            return true;
        } else if ((tblInstructores.getSelectionModel().getSelectedItem() == null)) {
            return false;
        }

        // hacer if que si es igual a null poner todos los txt vacios
        //return (tblAlumnos.getSelectionModel().getSelectedItem() != null);
        return false;
    }

    public boolean modificarInstructor() {
        if (existeElemento()) {
            PreparedStatement pst = null;
            System.out.println("Segundo if");
            if (validaciones()) {
                try {
                    Instructor instructor = new Instructor();
                    instructor.setNombre1(txtNombre1.getText());
                    instructor.setNombre2(txtNombre2.getText());
                    instructor.setNombre3(txtNombre3.getText());
                    instructor.setApellido1(txtApellido1.getText());
                    instructor.setApellido2(txtApellido2.getText());
                    instructor.setDireccion(txtDireccion.getText());
                    instructor.setEmail(txtEmail.getText());
                    instructor.setTelefono(txtTelefono.getText());
                    instructor.setFechaNacimiento(Date.valueOf(dpFechaNacimiento.getValue()));
                    String SQL = "{CALL sp_update_instructores(?,?,?,?,?,?,?,?,?,?)}";
                    pst = ConexionDb.getInstance().getConexion().prepareCall(SQL);
                    pst.setString(1, instructor.getNombre1());
                    pst.setString(2, instructor.getNombre2());
                    pst.setString(3, instructor.getNombre3());
                    pst.setString(4, instructor.getApellido1());
                    pst.setString(5, instructor.getApellido2());
                    pst.setString(6, instructor.getDireccion());
                    pst.setString(7, instructor.getEmail());
                    pst.setString(8, instructor.getTelefono());
                    pst.setDate(9, instructor.getFechaNacimiento());
                    pst.setInt(10, Integer.parseInt(txtId.getText()));
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
        }
        return false;
    }

    @FXML
    private void clickModificar(ActionEvent event) {
        switch (operacion) {
            case NINGUNO:
                if (existeElemento()) {
                    cambiarHabilitacion(true);

                    btnNuevo.setDisable(true);

                    btnModificar.setText("Guardar");
                    imgModificar.setImage(new Image(PAQUETE_IMAGE + "Modifu.png"));

                    btnEliminar.setText("Cancelar");
                    imgEliminar.setImage(new Image(PAQUETE_IMAGE + "cancelar.png"));

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
                imgNuevo.setImage(new Image(PAQUETE_IMAGE + "anadir.png"));
                imgModificar.setImage(new Image(PAQUETE_IMAGE + "contrato.png"));
                btnModificar.setText("Modificar");
                btnNuevo.setDisable(false);
                btnReporte.setDisable(false);
                btnEliminar.setDisable(false);
                cambiarHabilitacion(false);
                tblInstructores.setDisable(false);
                limpiar();
                operacion = Operacion.NINGUNO;
                break;
            case ACTUALIZAR:
                imgEliminar.setImage(new Image((PAQUETE_IMAGE + "cancelar.png")));
                btnEliminar.setText("Cancelar");
                cambiarHabilitacion(true);
                txtId.setEditable(false);
                //validacionesModificar();
                if (modificarInstructor()) {
                    btnNuevo.setDisable(false);
                    btnEliminar.setText("Eliminar");
                    imgEliminar.setImage(new Image((PAQUETE_IMAGE + "expediente.png")));
                    imgModificar.setImage(new Image((PAQUETE_IMAGE + "contrato.png")));
                    btnModificar.setText("Modificar");
                    btnReporte.setDisable(false);
                    limpiar();
                    cambiarHabilitacion(false);
                    tblInstructores.getSelectionModel().clearSelection();
                }
                operacion = Operacion.NINGUNO;
                break;
        }
    }

    public boolean eliminarInstructores() {
        Instructor instructor = ((Instructor) tblInstructores.getSelectionModel().getSelectedItem());
        //System.out.println(alumno.toString());
        PreparedStatement pst = null;
        try {
            //System.out.println(alumno.toString());
            String SQL = "CALL sp_delete_instructores(?)";
            //System.out.println("Paso el if de confirmar");
            pst = ConexionDb.getInstance().getConexion().prepareCall(SQL);
            pst.setInt(1, instructor.getId());
            System.out.println(pst);
            pst.execute();
            numero = numero - 1;
            //cargarDatos();
            //registros.remove(tblAlumnos.getSelectionModel().getFocusedIndex());
            limpiar();
            return true;
        } catch (SQLException e) {
            System.err.println("Se produjo un error al eliminar el registro: " + instructor.toString());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("Llego al false");
        return false;
    }

    @FXML
    private void clickEliminar(ActionEvent event) {
        switch (operacion) {
            case ACTUALIZAR:
                btnNuevo.setDisable(false);
                btnEliminar.setText("Eliminar");
                imgEliminar.setImage(new Image((PAQUETE_IMAGE + "expediente.png")));
                imgModificar.setImage(new Image((PAQUETE_IMAGE + "contrato.png")));
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
                        //eliminarInstructores();
                        conteoLabel();
                        if (eliminarInstructores()) {
                            listaInstructor.remove(tblInstructores.getSelectionModel().getFocusedIndex());
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
    public void reporte() {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LOGO_INSTRUCTORES", PAQUETE_IMAGE + "instructorReporte.png");
        GenerarReporte.getInstance().mostrarReporte("ReporteInstructores.jasper", parametros, "Reporte de Instructores");
    }
}
