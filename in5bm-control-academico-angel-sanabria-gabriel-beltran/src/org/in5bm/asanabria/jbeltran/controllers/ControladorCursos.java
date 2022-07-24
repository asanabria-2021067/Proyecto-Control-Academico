package org.in5bm.asanabria.jbeltran.controllers;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.in5bm.asanabria.jbeltran.db.ConexionDb;
import org.in5bm.asanabria.jbeltran.models.CarreraTecnica;
import org.in5bm.asanabria.jbeltran.models.Curso;
import org.in5bm.asanabria.jbeltran.models.Horario;
import org.in5bm.asanabria.jbeltran.models.Instructor;
import org.in5bm.asanabria.jbeltran.models.Salon;
import org.in5bm.asanabria.jbeltran.reports.GenerarReporte;
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
public class ControladorCursos implements Initializable {

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

    private ObservableList<CarreraTecnica> listaObservableCarreras;
    private ObservableList<Horario> listaObservableHorarios;
    private ObservableList<Instructor> listaObservableInstructores;
    private ObservableList<Salon> listaObservableSalones;

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
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnReporte;

    @FXML
    private TableColumn<Curso, Integer> colId;

    @FXML
    private TableColumn<Curso, String> colNombre;

    @FXML
    private TableColumn<Curso, Integer> colCiclo;

    @FXML
    private TableColumn<Curso, Integer> colCupoMax;

    @FXML
    private TableColumn<Curso, Integer> colCupoMin;

    @FXML
    private ComboBox<CarreraTecnica> cmbCarrerasId;

    @FXML
    private ComboBox<Horario> cmbHorarioId;

    @FXML
    private ComboBox<Instructor> cmbInstructorId;

    @FXML
    private ComboBox<Salon> cmbSalonId;

    @FXML
    private TableColumn<Curso, String> colCarreraId;

    @FXML
    private TableColumn<Curso, Integer> colHorarioId;

    @FXML
    private TableColumn<Curso, Integer> colInstructorId;

    @FXML
    private TableColumn<Curso, String> colSalonId;

    @FXML
    private HBox hboxAñadir;

    @FXML
    private HBox hboxEliminar;

    @FXML
    private HBox hboxModificar;

    @FXML
    private HBox hboxReporte;

    @FXML
    private ImageView imgEliminar;

    @FXML
    private ImageView imgModificar;

    @FXML
    private ImageView imgNuevo;

    @FXML
    private ImageView imgReporte;

    @FXML
    private Spinner<Integer> spCiclo;

    @FXML
    private Spinner<Integer> spCupoMaximo;

    @FXML
    private Spinner<Integer> spCupoMinimo;

    @FXML
    private TableView<Curso> tblCursos;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombreCurso;

    private ObservableList listaCursos;

    /*
    public void cargarCombos(){
        ArrayList<Curso> combo1= new ArrayList<>(); 
        try{
            String SQL= "SELECT DISTINCT cursos.carreras_tecnicas_id AS ID , carreras_tecnicas.carrera AS CARRERAS FROM cursos, carreras_tecnicas WHERE cursos.carreras_tecnicas_id = carreras_tecnicas.codigo_tecnico ORDER BY carrera ASC";
            
            
            PreparedStatement pst = ConexionDb.getInstance().getConexion().prepareStatement(SQL);
            ResultSet rs = pst.executeQuery();
            //cmbCarreras.setId("Seleccione una carrera");
            while (rs.next()){
                Curso curso = new Curso();
                CarreraTecnica carrera = new CarreraTecnica();
                curso.setCarreraTecnicaId(rs.getString("ID"));
                carrera.setCarrera(rs.getString("CARRERAS"));
                
                combo1.add(curso);
                //combo1.add(carrera);
                //cmbCarreras.getItems().add(curso);
                //cmbCarreras.getItems().addAll(rs.getInt("ID"), rs.getString("CARRERAS"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    /*
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
     */
    public ObservableList getCursos() {
        ArrayList<Curso> lista = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = ConexionDb.getInstance().getConexion().prepareCall("{CALL sp_read_cursos()}");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setId(rs.getInt("id"));
                curso.setNombreCurso(rs.getString("nombre_curso"));
                curso.setCiclo(rs.getInt("ciclo"));
                curso.setCupoMaximo(rs.getInt("cupo_maximo"));
                curso.setCupoMinimo(rs.getInt("cupo_minimo"));
                curso.setCarreraTecnicaId(rs.getString("carreras_tecnicas_id"));
                curso.setHorarioId(rs.getInt("horario_id"));
                curso.setInstructorId(rs.getInt("instructor_id"));
                curso.setSalonId(rs.getString("salon_id"));

                System.out.println(curso);

                System.out.println("Se paso los sets");
                lista.add(curso);
                //System.out.println(alumno.toString());
            }
            listaCursos = FXCollections.observableArrayList(lista);
            numero = listaCursos.size();
        } catch (SQLException e) {
            System.err.println("\n Se produjo un error al intentar consultar la lista asignacion");
            e.printStackTrace();
            System.out.println("MESSAGE: " + e.getMessage());
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
        return listaCursos;
    }

    public void cargarDatos() {
        tblCursos.setItems(getCursos());
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCurso"));
        colCiclo.setCellValueFactory(new PropertyValueFactory<>("ciclo"));
        colCupoMax.setCellValueFactory(new PropertyValueFactory<>("cupoMaximo"));
        colCupoMin.setCellValueFactory(new PropertyValueFactory<>("cupoMinimo"));
        colCarreraId.setCellValueFactory(new PropertyValueFactory<>("carreraTecnicaId"));
        colHorarioId.setCellValueFactory(new PropertyValueFactory<>("horarioId"));
        colInstructorId.setCellValueFactory(new PropertyValueFactory<>("instructorId"));
        colSalonId.setCellValueFactory(new PropertyValueFactory<>("salonId"));
        cmbSalonId.setItems(getSalones());
        cmbCarrerasId.setItems(getCarreras());
        cmbHorarioId.setItems(getHorarios());
        cmbInstructorId.setItems(getInstructores());
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        SpinnerValueFactory<Integer> cicloValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(2020, 2060, 2021);
        SpinnerValueFactory<Integer> cupoMaximoValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(20, 200, 20);
        SpinnerValueFactory<Integer> cupoMinimoValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 60);
        spCupoMinimo.setValueFactory(cupoMinimoValue);
        spCiclo.setValueFactory(cicloValue);
        spCupoMaximo.setValueFactory(cupoMaximoValue);
        cambiarHabilitacion(false);
        cargarDatos();
        conteoLabel();
    }

    public void cambiarHabilitacion(boolean estado) {
        if (estado == true) {
            txtId.setEditable(false);
            txtNombreCurso.setEditable(true);
            spCiclo.setEditable(false);
            spCupoMaximo.setEditable(false);
            spCupoMinimo.setEditable(false);
            cmbCarrerasId.setEditable(false);
            cmbHorarioId.setEditable(false);
            cmbInstructorId.setEditable(false);
            cmbSalonId.setEditable(false);
            txtId.setDisable(true);
            txtNombreCurso.setDisable(false);
            spCiclo.setDisable(false);
            spCupoMaximo.setDisable(false);
            spCupoMinimo.setDisable(false);
            cmbCarrerasId.setDisable(false);
            cmbHorarioId.setDisable(false);
            cmbInstructorId.setDisable(false);
            cmbSalonId.setDisable(false);
        } else {
            txtId.setEditable(false);
            txtNombreCurso.setEditable(false);
            spCiclo.setEditable(false);
            spCupoMaximo.setEditable(false);
            spCupoMinimo.setEditable(false);
            cmbCarrerasId.setEditable(false);
            cmbHorarioId.setEditable(false);
            cmbInstructorId.setEditable(false);
            cmbSalonId.setEditable(false);
            txtId.setDisable(true);
            txtNombreCurso.setDisable(true);
            spCiclo.setDisable(true);
            spCupoMaximo.setDisable(true);
            spCupoMinimo.setDisable(true);
            cmbCarrerasId.setDisable(true);
            cmbHorarioId.setDisable(true);
            cmbInstructorId.setDisable(true);
            cmbSalonId.setDisable(true);
        }
    }

    public void limpiar() {
        txtId.clear();
        txtNombreCurso.clear();
        spCiclo.getValueFactory().setValue(2022);
        spCupoMaximo.getValueFactory().setValue(0);
        spCupoMinimo.getValueFactory().setValue(0);
        cmbCarrerasId.valueProperty().set(null);
        cmbHorarioId.valueProperty().set(null);
        cmbInstructorId.valueProperty().set(null);
        cmbSalonId.valueProperty().set(null);
    }

    private void conteoLabel() {
        String tamaño = " " + numero;
        System.out.println("Numero de datos: " + numero);
        lblConteo.setText(tamaño);
    }

    public ObservableList getSalones() {

        ArrayList<Salon> lista = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = ConexionDb.getInstance().getConexion()
                    .prepareCall("{CALL sp_read_salones()}");
            System.out.println(pstmt.toString());
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
            listaObservableSalones = FXCollections.observableArrayList(lista);

        } catch (SQLException e) {
            System.err.println("Se produjo un error al intentar consultar la lista salones");
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

        return listaObservableSalones;
    }

    public ObservableList getCarreras() {

        ArrayList<CarreraTecnica> lista = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = ConexionDb.getInstance().getConexion()
                    .prepareCall("{CALL sp_read_carreras()}");
            System.out.println(pstmt.toString());
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
            listaObservableCarreras = FXCollections.observableArrayList(lista);

        } catch (SQLException e) {
            System.err.println("Se produjo un error al intentar consultar la lista Carreras Tecnicas");
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

        return listaObservableCarreras;
    }

    public ObservableList getHorarios() {
        ArrayList<Horario> lista = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = ConexionDb.getInstance().getConexion().prepareCall("CALL sp_read_horarios()");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Horario horario = new Horario();
                horario.setId(rs.getInt(1));
                horario.setHorarioInicio(rs.getTime(2).toLocalTime());
                horario.setHorarioFinal(rs.getTime(3).toLocalTime());
                horario.setLunes(rs.getBoolean(4));
                horario.setMartes(rs.getBoolean(5));
                horario.setMiercoles(rs.getBoolean(6));
                horario.setJueves(rs.getBoolean(7));
                horario.setViernes(rs.getBoolean(8));
                lista.add(horario);
            }
            listaObservableHorarios = FXCollections.observableArrayList(lista);
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
        return listaObservableHorarios;
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
            listaObservableInstructores = FXCollections.observableArrayList(lista);
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
        return listaObservableInstructores;
    }

    private Horario buscarHorario(int id) {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Horario horario = null;

        try {
            pstmt = ConexionDb.getInstance().getConexion()
                    .prepareCall("{CALL sp_read_horarios_by_id(?)}");

            pstmt.setInt(1, id);

            System.out.println(pstmt.toString());

            rs = pstmt.executeQuery();

            while (rs.next()) {
                horario = new Horario(
                        rs.getInt("id"),
                        rs.getTime("horario_inicio").toLocalTime(),
                        rs.getTime("horario_final").toLocalTime(),
                        rs.getBoolean("lunes"),
                        rs.getBoolean("martes"),
                        rs.getBoolean("miercoles"),
                        rs.getBoolean("jueves"),
                        rs.getBoolean("viernes")
                );

                System.out.println(horario.toString());
            }

        } catch (SQLException e) {
            System.err.println("\nSe produjo un error al intentar listar la tabla de Horarios");
            System.err.println("Message: " + e.getMessage());
            System.err.println("Error code: " + e.getErrorCode());
            System.err.println("SQLState: " + e.getSQLState());
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

        return horario;
    }

    private CarreraTecnica buscarCarrerasTecnicas(String id) {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        CarreraTecnica carreraTecnica = null;

        try {
            pstmt = ConexionDb.getInstance().getConexion()
                    .prepareCall("{CALL sp_read_carreras_by_id(?)}");

            pstmt.setString(1, id);

            System.out.println(pstmt.toString());

            rs = pstmt.executeQuery();

            while (rs.next()) {

                carreraTecnica = new CarreraTecnica();

                carreraTecnica.setCodigo(rs.getString("codigo_tecnico"));
                carreraTecnica.setCarrera(rs.getString("carrera"));
                carreraTecnica.setGrado(rs.getString("grado"));
                carreraTecnica.setSeccion(rs.getString("seccion").charAt(0));
                carreraTecnica.setJornada(rs.getString("jornada"));

                System.out.println(carreraTecnica);
            }

        } catch (SQLException e) {
            System.err.println("\nSe produjo un error al intentar listar la tabla de Carreras Técnicas");
            System.err.println("Message: " + e.getMessage());
            System.err.println("Error code: " + e.getErrorCode());
            System.err.println("SQLState: " + e.getSQLState());
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

        return carreraTecnica;
    }

    private Salon buscarSalones(String id) {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Salon salon = null;

        try {
            pstmt = ConexionDb.getInstance().getConexion()
                    .prepareCall("{CALL sp_read_salones_by_id(?)}");

            pstmt.setString(1, id);

            System.out.println(pstmt.toString());

            rs = pstmt.executeQuery();

            while (rs.next()) {
                salon = new Salon();
                salon.setCodigo(rs.getString(1));
                salon.setDescripcion(rs.getString(2));
                salon.setCapacidadMax(rs.getInt(3));
                salon.setEdificio(rs.getString(4));
                salon.setNivel(rs.getInt(5));
                System.out.println(salon.toString());

            }

        } catch (SQLException e) {
            System.err.println("\nSe produjo un error al consultar la tabla de Salones");
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
        return salon;
    }

    private Instructor buscarInstructor(int id) {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Instructor instructor = null;

        try {
            pstmt = ConexionDb.getInstance().getConexion()
                    .prepareCall("{CALL sp_read_instructores_by_id(?)}");

            pstmt.setInt(1, id);

            System.out.println(pstmt.toString());

            rs = pstmt.executeQuery();

            while (rs.next()) {

                instructor = new Instructor();
                instructor.setId(rs.getInt(1));
                instructor.setNombre1(rs.getString(2));
                instructor.setNombre2(rs.getString(3));
                instructor.setNombre3(rs.getString(4));
                instructor.setApellido1(rs.getString(5));
                instructor.setApellido2(rs.getString(6));
                instructor.setDireccion(rs.getString(7));
                instructor.setEmail(rs.getString(8));
                instructor.setTelefono(rs.getString(9));
                instructor.setFechaNacimiento(rs.getDate(10));

                System.out.println(instructor);
            }

        } catch (SQLException e) {
            System.err.println("\nSe produjo un error al intentar listar la tabla de Instructores");
            System.err.println("Message: " + e.getMessage());
            System.err.println("Error code: " + e.getErrorCode());
            System.err.println("SQLState: " + e.getSQLState());
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

        return instructor;
    }

    @FXML
    private void seleccionarElemento() {
        if (existeElemento()) {
            txtId.setText(String.valueOf(((Curso) tblCursos.getSelectionModel().getSelectedItem()).getId()));
            txtNombreCurso.setText(((Curso) tblCursos.getSelectionModel().getSelectedItem()).getNombreCurso());
            spCiclo.getValueFactory().setValue(((Curso) tblCursos.getSelectionModel().getSelectedItem()).getCiclo());
            spCupoMaximo.getValueFactory().setValue(((Curso) tblCursos.getSelectionModel().getSelectedItem()).getCupoMaximo());
            spCupoMinimo.getValueFactory().setValue(((Curso) tblCursos.getSelectionModel().getSelectedItem()).getCupoMinimo());

            cmbCarrerasId.getSelectionModel().select(buscarCarrerasTecnicas(((Curso) tblCursos.getSelectionModel().getSelectedItem()).getCarreraTecnicaId()));

            cmbHorarioId.getSelectionModel().select(buscarHorario(((Curso) tblCursos.getSelectionModel().getSelectedItem()).getHorarioId()));

            cmbInstructorId.getSelectionModel().select(buscarInstructor(((Curso) tblCursos.getSelectionModel().getSelectedItem()).getInstructorId()));

            cmbSalonId.getSelectionModel().select(buscarSalones(((Curso) tblCursos.getSelectionModel().getSelectedItem()).getSalonId()));
        }
    }

    public boolean validacionesAgregar() {
        if (txtNombreCurso.getText().isEmpty() && cmbCarrerasId.getSelectionModel().getSelectedItem() == null
                && cmbHorarioId.getSelectionModel().getSelectedItem() == null && cmbInstructorId.getSelectionModel().getSelectedItem() == null
                && cmbSalonId.getSelectionModel().getSelectedItem() == null) {
            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
            alerta2.setTitle("Control Academico");
            alerta2.setHeaderText(null);
            alerta2.setContentText("SE NECESITA QUE SE LLENEN LOS CAMPOS NOMBRE CURSO, CARRERAS_ID, HORARIOS_ID, INSTRUCTOR_ID, SALON_ID");
            Stage stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta2.show();
            //limpiar();
            return false;
        } else if (txtNombreCurso.getText().isEmpty()) {
            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
            alerta2.setTitle("Control Academico");
            alerta2.setHeaderText(null);
            alerta2.setContentText("SE NECESITA QUE SE LLENE EL CAMPO NOMBRE CURSO");
            Stage stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta2.show();
            //limpiar();
            return false;
        } else if (txtNombreCurso.getText().length() > 255) {
            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
            alerta2.setTitle("Control Academico");
            alerta2.setHeaderText(null);
            alerta2.setContentText("SE NECESITA QUE EL VALOR NOMBRE CURSO SEA MENOR A 255");
            Stage stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta2.show();
            //limpiar();
            return false;
        } else if (cmbCarrerasId.getSelectionModel().getSelectedItem() == null) {
            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
            alerta2.setTitle("Control Academico");
            alerta2.setHeaderText(null);
            alerta2.setContentText("SE NECESITA QUE SE LLENE EL CAMPO CARRERAS_ID");
            Stage stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta2.show();
            //limpiar();
            return false;
        } else if (cmbHorarioId.getSelectionModel().getSelectedItem() == null) {
            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
            alerta2.setTitle("Control Academico");
            alerta2.setHeaderText(null);
            alerta2.setContentText("SE NECESITA QUE SE LLENE EL CAMPO HORARIOS_ID");
            Stage stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta2.show();
            //limpiar();
            return false;
        } else if (cmbInstructorId.getSelectionModel().getSelectedItem() == null) {
            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
            alerta2.setTitle("Control Academico");
            alerta2.setHeaderText(null);
            alerta2.setContentText("SE NECESITA QUE SE LLENE EL CAMPO INSTRUCTOR_ID");
            Stage stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta2.show();
            //limpiar();
            return false;
        } else if (cmbSalonId.getSelectionModel().getSelectedItem() == null) {
            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
            alerta2.setTitle("Control Academico");
            alerta2.setHeaderText(null);
            alerta2.setContentText("SE NECESITA QUE SE LLENE EL CAMPO SALON_ID");
            Stage stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta2.show();
            //limpiar();
            return false;
        }
        return true;
    }

    private boolean agregarCursos() {
        if (validacionesAgregar()) {
            Curso curso = new Curso();
            curso.setNombreCurso(txtNombreCurso.getText());
            curso.setCiclo(spCiclo.getValue());
            curso.setCupoMaximo(spCupoMaximo.getValue());
            curso.setCupoMinimo(spCupoMinimo.getValue());
            curso.setCarreraTecnicaId(((CarreraTecnica) cmbCarrerasId.getSelectionModel().getSelectedItem()).getCodigo());
            curso.setHorarioId(((Horario) cmbHorarioId.getSelectionModel().getSelectedItem()).getId());
            curso.setInstructorId(((Instructor) cmbInstructorId.getSelectionModel().getSelectedItem()).getId());
            curso.setSalonId(((Salon) cmbSalonId.getSelectionModel().getSelectedItem()).getCodigo());
            PreparedStatement pstmt = null;
            try {
                pstmt = ConexionDb.getInstance().getConexion().prepareCall("{CALL sp_create_cursos(?, ?, ?, ?, ?, ?, ?, ?)}");

                pstmt.setString(1, curso.getNombreCurso());
                pstmt.setInt(2, curso.getCiclo());
                pstmt.setInt(3, curso.getCupoMaximo());
                pstmt.setInt(4, curso.getCupoMinimo());
                pstmt.setString(5, curso.getCarreraTecnicaId());
                pstmt.setInt(6, curso.getHorarioId());
                pstmt.setInt(7, curso.getInstructorId());
                pstmt.setString(8, curso.getSalonId());

                System.out.println(pstmt.toString());
                pstmt.execute();
                listaCursos.add(curso);
                numero = listaCursos.size();
                return true;
            } catch (SQLException e) {
                System.err.println("\nSe produjo un error al intentar insertar "
                        + "el siguiente registro: " + curso.toString());
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @FXML
    private void clickNuevo() {
        switch (operacion) {
            case NINGUNO:
                cambiarHabilitacion(true);
                tblCursos.setDisable(true);
                tblCursos.getSelectionModel().clearSelection();
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
                if (agregarCursos()) {
                    cargarDatos();
                    conteoLabel();
                    limpiar();
                    cambiarHabilitacion(false);
                    tblCursos.setDisable(false);
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

    public boolean modificarCursos() {
        if (existeElemento()) {
            Curso cursos = new Curso();
            cursos.setNombreCurso(txtNombreCurso.getText());
            cursos.setCiclo(spCiclo.getValue());
            cursos.setCupoMaximo(spCupoMaximo.getValue());
            cursos.setCupoMinimo(spCupoMinimo.getValue());
            cursos.setCarreraTecnicaId(((CarreraTecnica) cmbCarrerasId.getSelectionModel().getSelectedItem()).getCodigo());
            cursos.setHorarioId(((Horario) cmbHorarioId.getSelectionModel().getSelectedItem()).getId());
            cursos.setInstructorId(((Instructor) cmbInstructorId.getSelectionModel().getSelectedItem()).getId());
            cursos.setSalonId(((Salon) cmbSalonId.getSelectionModel().getSelectedItem()).getCodigo());
            cursos.setId(Integer.parseInt(txtId.getText()));
            System.out.println(" ID " +cursos.getId());
            PreparedStatement pstmt = null;
            if (txtNombreCurso.getText().isEmpty()) {
                Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                alerta2.setTitle("Control Academico");
                alerta2.setHeaderText(null);
                alerta2.setContentText("SE NECESITA QUE SE LLENE EL CAMPO NOMBRE_CURSO");
                Stage stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
                alerta2.show();
            } else {
                try {
                    pstmt = ConexionDb.getInstance().getConexion().prepareCall("CALL sp_update_cursos(?,?,?,?,?,?,?,?,?)");
                    pstmt.setInt(9, cursos.getId());
                    pstmt.setString(1, cursos.getNombreCurso());
                    pstmt.setInt(2, cursos.getCiclo());
                    pstmt.setInt(3, cursos.getCupoMaximo());
                    pstmt.setInt(4, cursos.getCupoMinimo());
                    pstmt.setString(5, cursos.getCarreraTecnicaId());
                    pstmt.setInt(6, cursos.getHorarioId());
                    pstmt.setInt(7, cursos.getInstructorId());
                    pstmt.setString(8, cursos.getSalonId());

                    System.out.println(pstmt.toString());
                    pstmt.execute();
                    cargarDatos();
                    return true;

                } catch (SQLException e) {
                    System.err.println("\nSucedio un error al intentar actualizar el siguiente registro: " + cursos.toString());
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (pstmt != null) {
                            pstmt.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    public boolean existeElemento() {

        if ((tblCursos.getSelectionModel().getSelectedItem() != null)) {
            return true;
        } else if ((tblCursos.getSelectionModel().getSelectedItem() == null)) {
            return false;
        }

        // hacer if que si es igual a null poner todos los txt vacios
        //return (tblAlumnos.getSelectionModel().getSelectedItem() != null);
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

                    btnReporte.setDisable(false);
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
                tblCursos.setDisable(false);
                limpiar();
                operacion = Operacion.NINGUNO;
                break;
            case ACTUALIZAR:
                imgEliminar.setImage(new Image((PAQUETE_IMAGE + "cancelar.png")));
                btnEliminar.setText("Cancelar");
                cambiarHabilitacion(true);
                txtId.setEditable(false);
                if (modificarCursos()) {
                    conteoLabel();
                    btnNuevo.setDisable(false);
                    btnEliminar.setText("Eliminar");
                    imgEliminar.setImage(new Image((PAQUETE_IMAGE + "expediente.png")));
                    imgModificar.setImage(new Image((PAQUETE_IMAGE + "contrato.png")));
                    btnModificar.setText("Modificar");
                    btnReporte.setDisable(false);
                    limpiar();
                    cambiarHabilitacion(false);
                    tblCursos.getSelectionModel().clearSelection();
                }
                operacion = Operacion.NINGUNO;
                break;
        }
    }

    public boolean eliminarCursos() {
        Curso curso = ((Curso) tblCursos.getSelectionModel().getSelectedItem());
        //System.out.println(alumno.toString());
        PreparedStatement pst = null;
        try {
            //System.out.println(alumno.toString());
            String SQL = "{CALL sp_delete_cursos(?)}";
            System.out.println("Paso el if de confirmar");
            pst = ConexionDb.getInstance().getConexion().prepareCall(SQL);
            pst.setInt(1, curso.getId());
            System.out.println(pst.toString());
            pst.execute();
            numero = numero - 1;
            //cargarDatos();
            //registros.remove(tblAlumnos.getSelectionModel().getFocusedIndex());
            //limpiar();
            return true;
        } catch (SQLException e) {
            System.err.println("Se produjo un error al eliminar el registro: " + curso.toString());
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
    private void deseleccionarElemento() {
        //limpiar();
        tblCursos.getSelectionModel().clearSelection();
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
                        if (eliminarCursos()) {
                            limpiar();
                            conteoLabel();
                            listaCursos.remove(tblCursos.getSelectionModel().getFocusedIndex());
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
                        tblCursos.getSelectionModel().clearSelection();
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
                tblCursos.getSelectionModel().clearSelection();
                operacion = Operacion.NINGUNO;
                break;
        }
    }

    @FXML
    private void clickReporte() {
        Curso curso = new Curso();
        if (existeElemento()) {
            curso.setId(Integer.parseInt(txtId.getText()));
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("LOGO_CURSOS_ID", PAQUETE_IMAGE + "cursoReporte.png");
            parametros.put("idCurso", curso.getId());
            GenerarReporte.getInstance().mostrarReporte("ReporteCursosId.jasper", parametros, "Reporte de Cursos por Id");

        } else {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("LOGO_CURSOS", PAQUETE_IMAGE + "cursoReporte.png");
            GenerarReporte.getInstance().mostrarReporte("ReporteCursos.jasper", parametros, "Reporte de Cursos");
        }

    }
}
