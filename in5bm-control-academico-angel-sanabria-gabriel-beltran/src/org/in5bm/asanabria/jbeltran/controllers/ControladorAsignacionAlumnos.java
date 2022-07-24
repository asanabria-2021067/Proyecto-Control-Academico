package org.in5bm.asanabria.jbeltran.controllers;

import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.sql.Connection;
import java.sql.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.control.ComboBox;
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
import org.in5bm.asanabria.jbeltran.models.Alumno;
import org.in5bm.asanabria.jbeltran.system.Principal;
import org.in5bm.asanabria.jbeltran.models.AsignacionAlumno;
import org.in5bm.asanabria.jbeltran.models.Curso;
import org.in5bm.asanabria.jbeltran.reports.GenerarReporte;

/**
 *
 * @author Angel Sanabria
 * @date 3/05/2022
 * @time 09:26:28
 * @grade 5to Perito en Informatica B
 * @code IN5BM
 * @carnet 2021067
 */
public class ControladorAsignacionAlumnos implements Initializable {

    private Principal escenarioPrincipal;
    @FXML
    private Label lblConteo;

    private enum Operacion {
        NINGUNO, GUARDAR, ACTUALIZAR
    }
    int numero;
    ConexionDb con;
    private Operacion operacion = Operacion.NINGUNO;
    private final String PAQUETE_IMAGE = "org/in5bm/asanabria/jbeltran/resources/images/";

    private ObservableList<Alumno> listaObservableAlumnos;
    private ObservableList<Curso> listaObservableCursos;
    private ObservableList<AsignacionAlumno> listaObservableAsignacion;

    @FXML
    private Button btnNuevo;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnReporte;
    @FXML
    private ImageView imgNuevo;
    @FXML
    private ImageView imgModificar;
    @FXML
    private ImageView imgEliminar;
    @FXML
    private ImageView imgReporte;
    @FXML
    private TextField txtId;
    @FXML
    private JFXDatePicker dpFechaAsignacion;
    @FXML
    private HBox hboxAñadir;
    @FXML
    private HBox hboxModificar;
    @FXML
    private HBox hboxEliminar;
    @FXML
    private HBox hboxReporte;
    @FXML
    private ComboBox<Alumno> cmbAlumno;
    @FXML
    private ComboBox<Curso> cmbCursoId;
    @FXML
    private TableView<AsignacionAlumno> tblAsignacionAlumno;
    @FXML
    private TableColumn<AsignacionAlumno, Integer> colId;
    @FXML
    private TableColumn<AsignacionAlumno, String> colAlumnoId;
    @FXML
    private TableColumn<AsignacionAlumno, Integer> colCursoId;
    @FXML
    private TableColumn<AsignacionAlumno, LocalDate> colFechaAsignacion;

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
    public void getAsignacion(){
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
            listaObservableAlumnos = FXCollections.observableArrayList(lista);
            //numero = listaAlumnos.size();
        } catch (SQLException e) {
            System.err.println("\nSe produjo un error al intentar consultar la lista alumnos:");
            System.out.println("Message: " + e.getMessage());
            System.out.println("Error code: " + e.getErrorCode());
            System.out.println("SQLSate" + e.getSQLState());
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
        return listaObservableAlumnos;
    }

    public ObservableList getAsignacion() {
        ArrayList<AsignacionAlumno> lista = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = ConexionDb.getInstance().getConexion().prepareCall("CALL sp_read_asignacion");
            rs = pstmt.executeQuery();
            System.out.println(pstmt);
            while (rs.next()) {
                AsignacionAlumno asignacion = new AsignacionAlumno();
                asignacion.setId(rs.getInt(1));
                asignacion.setAlumnoId(rs.getString(2));
                asignacion.setCursoId(rs.getInt(3));
                asignacion.setFechaAsignacion(rs.getTimestamp(4).toLocalDateTime());
                System.out.println(asignacion);
                lista.add(asignacion);
                //System.out.println(alumno.toString());
            }
            listaObservableAsignacion = FXCollections.observableArrayList(lista);
            numero = listaObservableAsignacion.size();
        } catch (SQLException e) {
            System.err.println("\nSe produjo un error al intentar consultar la lista asignacion:");
            System.out.println("Message: " + e.getMessage());
            System.out.println("Error code: " + e.getErrorCode());
            System.out.println("SQLSate" + e.getSQLState());
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
        return listaObservableAsignacion;
    }

    public ObservableList getCursos() {
        ArrayList<Curso> lista = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = ConexionDb.getInstance().getConexion().prepareCall("CALL sp_read_cursos");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setId(rs.getInt(1));
                curso.setNombreCurso(rs.getString(2));
                curso.setCiclo(rs.getInt(3));
                curso.setCupoMaximo(rs.getInt(4));
                curso.setCupoMinimo(rs.getInt(5));
                curso.setCarreraTecnicaId(rs.getString(6));
                curso.setHorarioId(rs.getInt(7));
                curso.setInstructorId(rs.getInt(8));
                //curso.setSalonId(rs.getString(9));
                lista.add(curso);
                //System.out.println(alumno.toString());
            }
            listaObservableCursos = FXCollections.observableArrayList(lista);
            //numero = listaCursos.size();
        } catch (SQLException e) {
            System.err.println("\nSe produjo un error al intentar consultar la lista cursos:");
            System.out.println("Message: " + e.getMessage());
            System.out.println("Error code: " + e.getErrorCode());
            System.out.println("SQLSate" + e.getSQLState());
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
        return listaObservableCursos;
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
        txtId.setText("");
        cmbAlumno.valueProperty().set(null);
        cmbCursoId.valueProperty().set(null);
        dpFechaAsignacion.getEditor().clear();
    }

    public void cargarDatos() {
        tblAsignacionAlumno.setItems(getAsignacion());
        colId.setCellValueFactory(new PropertyValueFactory<AsignacionAlumno, Integer>("id"));
        colAlumnoId.setCellValueFactory(new PropertyValueFactory<AsignacionAlumno, String>("alumnoId"));
        colCursoId.setCellValueFactory(new PropertyValueFactory<AsignacionAlumno, Integer>("cursoId"));
        colFechaAsignacion.setCellValueFactory(new PropertyValueFactory<AsignacionAlumno, LocalDate>("fechaAsignacion"));
        cmbAlumno.setItems(getAlumnos());
        cmbCursoId.setItems(getCursos());
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
    private void conteoLabel() {
        String tamaño = " " + numero;
        System.out.println("Numero de datos: " + numero);
        lblConteo.setText(tamaño);
    }

    public boolean validacionesAgregar() {
        if ((cmbAlumno.getSelectionModel().getSelectedItem() == null) && (cmbCursoId.getSelectionModel()
                .getSelectedItem() == null)) {
            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
            alerta2.setTitle("Control Academico");
            alerta2.setHeaderText(null);
            alerta2.setContentText("SE NECESITA QUE SE LLENEN LOS CAMPOS ALUMNO_ID, CURSO_ID");
            Stage stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta2.show();
            //limpiar();
            return false;

        } else if (cmbAlumno.getSelectionModel().getSelectedItem() == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Control Academico");
            alerta.setHeaderText(null);
            alerta.setContentText("SE NECESITA LLENAR EL VALOR DE ALUMNO_ID");
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta.show();
            //limpiar();
            return false;

        } else if (cmbCursoId.getSelectionModel().getSelectedItem() == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Control Academico");
            alerta.setHeaderText(null);
            alerta.setContentText("SE NECESITA LLENAR EL VALOR DE CURSO_ID");
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta.show();
            //limpiar();
            return false;

        } else if (dpFechaAsignacion.getValue() == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Control Academico");
            alerta.setHeaderText(null);
            alerta.setContentText("SE NECESITA LLENAR EL VALOR DE FECHA");
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta.show();
            //limpiar();
            return false;
        }
        return true;
    }

    @FXML
    private void clickNuevo() {
        switch (operacion) {
            case NINGUNO:
                cambiarHabilitacion(true);
                tblAsignacionAlumno.setDisable(true);
                tblAsignacionAlumno.getSelectionModel().clearSelection();
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
                if (agregarAsignacion()) {
                    cargarDatos();
                    conteoLabel();
                    limpiar();
                    cambiarHabilitacion(false);
                    tblAsignacionAlumno.setDisable(false);
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

    public void cambiarHabilitacion(boolean estado) {
        if (estado == true) {
            txtId.setDisable(true);
            cmbCursoId.setDisable(false);
            cmbAlumno.setDisable(false);
            dpFechaAsignacion.setDisable(false);
            txtId.setEditable(false);
            cmbCursoId.setEditable(false);
            cmbAlumno.setEditable(false);
            //dpFechaAsignacion.setEditable(true);

        } else {
            txtId.setDisable(true);
            cmbCursoId.setDisable(true);
            cmbAlumno.setDisable(true);
            dpFechaAsignacion.setDisable(true);
            txtId.setEditable(false);
            cmbCursoId.setEditable(false);
            cmbAlumno.setEditable(false);
            //dpFechaAsignacion.setEditable(false);
        }
    }

    public boolean agregarAsignacion() {
        PreparedStatement pst = null;
        AsignacionAlumno asignacion = new AsignacionAlumno();
        //System.out.println(carne + nombre1+ nombre2+ nombre3+ apellido1+ apellido2);
        validacionesAgregar();
        System.out.println("Se paso el primer if");
        //asignacion.setId(Integer.parseInt(txtId.getText().toString()));
        //System.out.println(cmbAlumno.getValue().toString());
        System.out.println("----------------------------------------------------");
        //System.out.println("------- " +cmbAlumno.getValue().toString());
        if (validacionesAgregar()) {
            asignacion.setAlumnoId((((Alumno) cmbAlumno.getSelectionModel().getSelectedItem()).getCarne()));
            System.out.println(asignacion.getAlumnoId());
            asignacion.setCursoId((((Curso) cmbCursoId.getSelectionModel().getSelectedItem()).getId()));
            System.out.println(asignacion.getCursoId());
            System.out.println("----------------------------------------------------");
            System.out.println(asignacion.getCursoId());
            asignacion.setFechaAsignacion(dpFechaAsignacion.getValue().atStartOfDay());
            asignacion.setFechaAsignacion(dpFechaAsignacion.getValue().atTime(LocalTime.now()));
            System.out.println("Se setearon los datos");
            try {
                //validacionesAgregar();
                String SQL = "{CALL sp_create_asignacion (?,?,?)}";
                pst = ConexionDb.getInstance().getConexion().prepareCall(SQL);
                System.out.println("Se paso el pst");
                //pst.setString(1, String.valueOf(asignacion.getId()));
                pst.setString(1, asignacion.getAlumnoId());
                pst.setString(2, String.valueOf(asignacion.getCursoId()));
                pst.setTimestamp(3, Timestamp.valueOf(asignacion.getFechaAsignacion()));
                System.out.println(pst.toString());
                pst.executeUpdate();
                listaObservableAsignacion.add(asignacion);
                numero = listaObservableAsignacion.size();
                /*
                            cargarDatos();
                            conteoLabel();
                            limpiar();
                 */
                return true;
            } catch (SQLException e) {
                System.err.println("\nSe produjo un error al intentar consultar la lista alumnos:");
                System.out.println("Message: " + e.getMessage());
                System.out.println("Error code: " + e.getErrorCode());
                System.out.println("SQLSate" + e.getSQLState());
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
        }
        return false;
    }

    public boolean modificarAsignacion() {
        if (existeElemento()) {
            AsignacionAlumno asignacion = new AsignacionAlumno();
            PreparedStatement pst = null;
            System.out.println("Segundo if");
            asignacion.setAlumnoId((((Alumno) cmbAlumno.getSelectionModel().getSelectedItem()).getCarne()));
            System.out.println(asignacion.getAlumnoId());
            asignacion.setCursoId((((Curso) cmbCursoId.getSelectionModel().getSelectedItem()).getId()));
            System.out.println(asignacion.getCursoId());
            asignacion.setFechaAsignacion(dpFechaAsignacion.getValue().atStartOfDay());
            asignacion.setFechaAsignacion(dpFechaAsignacion.getValue().atTime(LocalTime.now()));
            try {
                String SQL = "{CALL sp_update_asignacion(?,?,?,?)}";
                pst = ConexionDb.getInstance().getConexion().prepareCall(SQL);
                pst.setString(4, txtId.getText());
                pst.setString(1, asignacion.getAlumnoId());
                pst.setInt(2, asignacion.getCursoId());
                pst.setTimestamp(3, Timestamp.valueOf(asignacion.getFechaAsignacion()));
                pst.executeUpdate();
                System.out.println(pst.toString());
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
                tblAsignacionAlumno.setDisable(false);
                limpiar();
                operacion = Operacion.NINGUNO;
                break;
            case ACTUALIZAR:
                imgEliminar.setImage(new Image((PAQUETE_IMAGE + "cancelar.png")));
                btnEliminar.setText("Cancelar");
                cambiarHabilitacion(true);
                txtId.setEditable(false);
                if (modificarAsignacion()) {
                    btnNuevo.setDisable(false);
                    btnEliminar.setText("Eliminar");
                    imgEliminar.setImage(new Image((PAQUETE_IMAGE + "expediente.png")));
                    imgModificar.setImage(new Image((PAQUETE_IMAGE + "contrato.png")));
                    btnModificar.setText("Modificar");
                    btnReporte.setDisable(false);
                    limpiar();
                    cambiarHabilitacion(false);
                    tblAsignacionAlumno.getSelectionModel().clearSelection();
                }
                operacion = Operacion.NINGUNO;
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

        if ((tblAsignacionAlumno.getSelectionModel().getSelectedItem() != null)) {
            return true;
        } else if ((tblAsignacionAlumno.getSelectionModel().getSelectedItem() == null)) {
            return false;
        }

        // hacer if que si es igual a null poner todos los txt vacios
        //return (tblAlumnos.getSelectionModel().getSelectedItem() != null);
        return false;
    }

    private Curso buscarCurso(int id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Curso curso = null;
        try {
            pstmt = ConexionDb.getInstance().getConexion().prepareCall("{CALL sp_read_cursos_by_id(?)}");
            pstmt.setInt(1, id);
            System.out.println(pstmt.toString());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                curso = new Curso(rs.getInt(1), rs.getString(2),
                        rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9));

                System.out.println(curso.toString());
            }
        } catch (SQLException e) {
            System.err.println("\nSe produjo un error al intentar consultar el buscar alumnos:");
            System.out.println("Message: " + e.getMessage());
            System.out.println("Error code: " + e.getErrorCode());
            System.out.println("SQLSate" + e.getSQLState());
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
        return curso;
    }

    private Alumno buscarAlumno(String id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Alumno alumno = null;
        try {
            pstmt = ConexionDb.getInstance().getConexion().prepareCall("{CALL sp_read_alumnos_by_id(?)}");
            pstmt.setString(1, id);
            System.out.println(pstmt.toString());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                alumno = new Alumno(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));

                System.out.println(alumno.toString());
            }
        } catch (SQLException e) {
            System.err.println("\nSe produjo un error al intentar consultar el buscar alumnos:");
            System.out.println("Message: " + e.getMessage());
            System.out.println("Error code: " + e.getErrorCode());
            System.out.println("SQLSate" + e.getSQLState());
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
        return alumno;
    }

    @FXML
    private void deseleccionarElemento() {
        //limpiar();
        tblAsignacionAlumno.getSelectionModel().clearSelection();
    }

    @FXML
    public void seleccionarElemento() {
        if (existeElemento()) {
            txtId.setText(String.valueOf(((AsignacionAlumno) tblAsignacionAlumno.getSelectionModel().getSelectedItem()).getId()));
            cmbAlumno.getSelectionModel().select(buscarAlumno(((AsignacionAlumno) tblAsignacionAlumno.getSelectionModel().getSelectedItem()).getAlumnoId()));
            cmbCursoId.getSelectionModel().select(buscarCurso(((AsignacionAlumno) tblAsignacionAlumno.getSelectionModel().getSelectedItem()).getCursoId()));
            dpFechaAsignacion.setValue(((AsignacionAlumno) tblAsignacionAlumno.getSelectionModel().getSelectedItem()).getFechaAsignacion().toLocalDate());
        } else {
            tblAsignacionAlumno.getSelectionModel().clearSelection();
            limpiar();
        }
    }

    public boolean eliminarAsignacion() {
        AsignacionAlumno asignacion = ((AsignacionAlumno) tblAsignacionAlumno.getSelectionModel().getSelectedItem());
        //System.out.println(alumno.toString());
        PreparedStatement pst = null;
        try {
            //System.out.println(alumno.toString());
            String SQL = "{CALL sp_delete_asignacion(?)}";
            System.out.println("Paso el if de confirmar");
            pst = ConexionDb.getInstance().getConexion().prepareCall(SQL);
            pst.setInt(1, asignacion.getId());
            System.out.println(pst.toString());
            pst.execute();
            numero = numero - 1;
            //cargarDatos();
            //registros.remove(tblAlumnos.getSelectionModel().getFocusedIndex());
            //limpiar();
            return true;
        } catch (SQLException e) {
            System.err.println("Se produjo un error al eliminar el registro: " + asignacion.toString());
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

    @FXML
    private void clickEliminar() {
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
                    Alert alert = new Alert(AlertType.CONFIRMATION, "¿Esta seguro de eliminar el registro?");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
                    Optional<ButtonType> confirmar = alert.showAndWait();
                    if (confirmar.get().equals(ButtonType.OK)) {
                        if (eliminarAsignacion()) {
                            limpiar();
                            conteoLabel();
                            listaObservableAsignacion.remove(tblAsignacionAlumno.getSelectionModel().getFocusedIndex());
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
                        tblAsignacionAlumno.getSelectionModel().clearSelection();
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
                tblAsignacionAlumno.getSelectionModel().clearSelection();
                operacion = Operacion.NINGUNO;
                break;
        }
    }

    @FXML
    public void reporte() {
        AsignacionAlumno asignacion = new AsignacionAlumno();
        if (existeElemento()) {
            asignacion.setId(Integer.parseInt(txtId.getText()));
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("LOGO_ASIGNACIONES_ID", PAQUETE_IMAGE + "asignacionReporte.png");
            parametros.put("idAsignacion", asignacion.getId());
            GenerarReporte.getInstance().mostrarReporte("ReporteAsignacionId.jasper", parametros, "Reporte de Asignacion por Id");

        } else {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("LOGO_ASIGNACIONES", PAQUETE_IMAGE + "asignacionReporte.png");
            GenerarReporte.getInstance().mostrarReporte("ReporteAsignacion.jasper", parametros, "Reporte de Asignacion");
        }

    }

}
