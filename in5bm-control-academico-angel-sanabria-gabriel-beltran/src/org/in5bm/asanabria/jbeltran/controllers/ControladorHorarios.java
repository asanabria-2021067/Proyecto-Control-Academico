package org.in5bm.asanabria.jbeltran.controllers;

import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.sql.*;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.in5bm.asanabria.jbeltran.db.ConexionDb;
import org.in5bm.asanabria.jbeltran.models.Horario;
import org.in5bm.asanabria.jbeltran.reports.GenerarReporte;
import org.in5bm.asanabria.jbeltran.system.Principal;

/**
 *
 * @author Angel Sanabria
 * @date 3/05/2022
 * @time 09:25:04
 * @grade 5to Perito en Informatica B
 * @code IN5BM
 * @carnet 2021067
 */
public class ControladorHorarios implements Initializable {

    private Principal escenarioPrincipal;
    private ObservableList<Horario> listaHorarios = FXCollections.observableArrayList();
    int numero;
    ConexionDb con;
    Horario horario = new Horario();
    @FXML
    private Label lblConteo;

    private enum Operacion {
        NINGUNO, GUARDAR, ACTUALIZAR
    }
    private Operacion operacion = Operacion.NINGUNO;
    private final String PAQUETE_IMAGE = "org/in5bm/asanabria/jbeltran/resources/images/";

    @FXML
    private Button btnNuevo;
    @FXML
    private ImageView imgNuevo;
    @FXML
    private Button btnModificar;
    @FXML
    private ImageView imgModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private ImageView imgEliminar;
    @FXML
    private Button btnReporte;
    @FXML
    private ImageView imgReporte;

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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        cambiarHabilitacion(false);
        cargarDatos();
        conteoLabel();
    }

    @FXML
    private HBox hboxAñadir;

    @FXML
    private TableView<?> tblHorarios;

    @FXML
    private HBox hboxModificar;

    @FXML
    private HBox hboxEliminar;

    @FXML
    private HBox hboxReporte;

    @FXML
    private TextField txtId;

    @FXML
    private CheckBox cbLunes;

    @FXML
    private CheckBox cbMartes;

    @FXML
    private CheckBox cbMiercoles;

    @FXML
    private CheckBox cbJueves;

    @FXML
    private CheckBox cbViernes;

    @FXML
    private JFXTimePicker dpInicio;

    @FXML
    private JFXTimePicker dpFinal;

    @FXML
    private TableColumn<Horario, Integer> colId;

    @FXML
    private TableColumn<Horario, LocalTime> colHorarioInicio;

    @FXML
    private TableColumn<Horario, LocalTime> colHorarioFinal;

    @FXML
    private TableColumn<Horario, Boolean> colLunes;

    @FXML
    private TableColumn<Horario, Boolean> colMartes;

    @FXML
    private TableColumn<Horario, Boolean> colMiercoles;

    @FXML
    private TableColumn<Horario, Boolean> colJueves;

    @FXML
    private TableColumn<Horario, Boolean> colViernes;

    public ControladorHorarios() {
    }

    @FXML
    private void deseleccionarElemento() {
        //limpiar();
        tblHorarios.getSelectionModel().clearSelection();
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
            listaHorarios = FXCollections.observableArrayList(lista);
            numero = listaHorarios.size();
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
        return listaHorarios;
    }

    public void cargarDatos() {
        tblHorarios.setItems(getHorarios());
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colHorarioInicio.setCellValueFactory(new PropertyValueFactory<>("horarioInicio"));
        colHorarioFinal.setCellValueFactory(new PropertyValueFactory<>("horarioFinal"));
        colLunes.setCellValueFactory(new PropertyValueFactory<>("lunes"));
        colMartes.setCellValueFactory(new PropertyValueFactory<>("martes"));
        colMiercoles.setCellValueFactory(new PropertyValueFactory<>("miercoles"));
        colJueves.setCellValueFactory(new PropertyValueFactory<>("jueves"));
        colViernes.setCellValueFactory(new PropertyValueFactory<>("viernes"));
    }

    public boolean existeElemento() {
        if ((tblHorarios.getSelectionModel().getSelectedItem() != null)) {
            return true;
        } else if ((tblHorarios.getSelectionModel().getSelectedItem() == null)) {
            return false;
        }
        return false;
    }

    @FXML
    public void seleccionarElemento() {
        if (existeElemento()) {
            txtId.setText(String.valueOf(((Horario) tblHorarios.getSelectionModel().getSelectedItem()).getId()));
            dpInicio.setValue(((Horario) tblHorarios.getSelectionModel().getSelectedItem()).getHorarioInicio());
            dpFinal.setValue(((Horario) tblHorarios.getSelectionModel().getSelectedItem()).getHorarioFinal());
            cbLunes.setSelected((((Horario) tblHorarios.getSelectionModel().getSelectedItem()).getLunes()));
            cbMartes.setSelected((((Horario) tblHorarios.getSelectionModel().getSelectedItem()).getMartes()));
            cbMiercoles.setSelected((((Horario) tblHorarios.getSelectionModel().getSelectedItem()).getMiercoles()));
            cbJueves.setSelected((((Horario) tblHorarios.getSelectionModel().getSelectedItem()).getJueves()));
            cbViernes.setSelected((((Horario) tblHorarios.getSelectionModel().getSelectedItem()).getViernes()));
        }
    }

    public boolean validacionesAgregar() {
        if (dpInicio.getValue() == null && dpFinal.getValue() == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Control Academico");
            alerta.setHeaderText(null);
            alerta.setContentText("SE NECESITA LLENAR EL VALOR DE HORARIO INICIO Y HORARIO FINAL");
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta.show();
            return false;
        } else if (dpInicio.getValue() == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Control Academico");
            alerta.setHeaderText(null);
            alerta.setContentText("SE NECESITA LLENAR EL VALOR DE HORARIO INICIO");
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta.show();
            return false;
        } else if (dpFinal.getValue() == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Control Academico");
            alerta.setHeaderText(null);
            alerta.setContentText("SE NECESITA LLENAR EL VALOR DE HORARIO FINAL");
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
            alerta.show();
            return false;

        }
        return true;
    }

    public boolean agregarHorarios() {
        PreparedStatement pst = null;
        Horario horarios = new Horario();
        horario.setHorarioInicio(dpInicio.getValue());
        horario.setHorarioFinal(dpFinal.getValue());
        horario.setLunes(Boolean.valueOf(cbLunes.selectedProperty().get()));
        horario.setMartes(Boolean.valueOf(cbMartes.selectedProperty().get()));
        horario.setMiercoles(Boolean.valueOf(cbMiercoles.selectedProperty().get()));
        horario.setJueves(Boolean.valueOf(cbJueves.selectedProperty().get()));
        horario.setViernes(Boolean.valueOf(cbViernes.selectedProperty().get()));
        if (validacionesAgregar()) {
            try {
                String SQL = "{CALL sp_create_horarios (?,?,?,?,?,?,?)}";
                pst = ConexionDb.getInstance().getConexion().prepareCall(SQL);
                System.out.println("Se paso el pst");
                pst.setString(1, horario.getHorarioInicio().toString());
                pst.setString(2, horario.getHorarioFinal().toString());
                pst.setBoolean(3, horario.getLunes());
                pst.setBoolean(4, horario.getMartes());
                pst.setBoolean(5, horario.getMiercoles());
                pst.setBoolean(6, horario.getJueves());
                pst.setBoolean(7, horario.getViernes());
                System.out.println(pst.toString());
                pst.executeUpdate();
                listaHorarios.add(horario);
                System.out.println("++++++++++++++++++++++++++++++++++++");
                System.out.println(listaHorarios.size() + "-------------");
                numero = listaHorarios.size();
                /*
                            cargarDatos();
                            conteoLabel();
                            limpiar();
                 */
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("SE PRODUJO UN ERROR AL INGRESAR LOS DATOS " + horario.toString());
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

    private void conteoLabel() {
        String tamaño = " " + numero;
        System.out.println("Numero de datos: " + numero);
        lblConteo.setText(tamaño);
    }

    public void limpiar() {
        txtId.setText(null);
        dpInicio.setValue(null);
        dpFinal.setValue(null);
        cbLunes.setSelected(false);
        cbMartes.setSelected(false);
        cbMiercoles.setSelected(false);
        cbJueves.setSelected(false);
        cbViernes.setSelected(false);
    }

    public void cambiarHabilitacion(boolean estado) {
        if (estado == true) {
            txtId.setDisable(true);
            dpInicio.setDisable(false);
            dpFinal.setDisable(false);
            cbLunes.setDisable(false);
            cbMartes.setDisable(false);
            cbMiercoles.setDisable(false);
            cbJueves.setDisable(false);
            cbViernes.setDisable(false);
            txtId.setEditable(false);
        } else {
            txtId.setDisable(true);
            dpInicio.setDisable(true);
            dpFinal.setDisable(true);
            cbLunes.setDisable(true);
            cbMartes.setDisable(true);
            cbMiercoles.setDisable(true);
            cbJueves.setDisable(true);
            cbViernes.setDisable(true);
            txtId.setEditable(true);
        }
    }

    @FXML
    private void clicNuevo(ActionEvent event) {
        switch (operacion) {
            case NINGUNO:
                cambiarHabilitacion(true);
                tblHorarios.setDisable(true);
                tblHorarios.getSelectionModel().clearSelection();
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
                if (agregarHorarios()) {
                    //validacionesAgregar();
                    //agregarAlumno();
                    cargarDatos();
                    conteoLabel();
                    limpiar();
                    tblHorarios.setDisable(false);
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

    public boolean modificarHorarios() {
        if (existeElemento()) {
            PreparedStatement pst = null;
            Horario horarios = new Horario();
            horario.setHorarioInicio(dpInicio.getValue());
            horario.setHorarioFinal(dpFinal.getValue());
            horario.setLunes(Boolean.valueOf(cbLunes.selectedProperty().get()));
            horario.setMartes(Boolean.valueOf(cbMartes.selectedProperty().get()));
            horario.setMiercoles(Boolean.valueOf(cbMiercoles.selectedProperty().get()));
            horario.setJueves(Boolean.valueOf(cbJueves.selectedProperty().get()));
            horario.setViernes(Boolean.valueOf(cbViernes.selectedProperty().get()));
            try {
                //validacionesAgregar();
                String SQL = "{CALL sp_update_horarios (?,?,?,?,?,?,?,?)}";
                pst = ConexionDb.getInstance().getConexion().prepareCall(SQL);
                System.out.println("Se paso el pst");
                pst.setString(1, horario.getHorarioInicio().toString());
                pst.setString(2, horario.getHorarioFinal().toString());
                pst.setBoolean(3, horario.getLunes());
                pst.setBoolean(4, horario.getMartes());
                pst.setBoolean(5, horario.getMiercoles());
                pst.setBoolean(6, horario.getJueves());
                pst.setBoolean(7, horario.getViernes());
                pst.setInt(8, Integer.parseInt(txtId.getText()));
                System.out.println(pst.toString());
                pst.executeUpdate();
                /*
                            cargarDatos();
                            conteoLabel();
                            limpiar();
                 */
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("SE PRODUJO UN ERROR AL INGRESAR LOS DATOS " + horario.toString());
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
    private void clicModificar(ActionEvent event) {
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
                tblHorarios.setDisable(false);
                limpiar();
                operacion = Operacion.NINGUNO;
                break;
            case ACTUALIZAR:
                imgEliminar.setImage(new Image((PAQUETE_IMAGE + "cancelar.png")));
                btnEliminar.setText("Cancelar");
                cambiarHabilitacion(true);
                //System.out.println("Afuera del if");
                if (modificarHorarios()) {
                    cargarDatos();
                    //System.out.println("Se entro al if");
                    btnNuevo.setDisable(false);
                    btnEliminar.setText("Eliminar");
                    imgEliminar.setImage(new Image((PAQUETE_IMAGE + "expediente.png")));
                    imgModificar.setImage(new Image((PAQUETE_IMAGE + "contrato.png")));
                    btnModificar.setText("Modificar");
                    btnReporte.setDisable(false);
                    limpiar();
                    cambiarHabilitacion(false);
                    operacion = Operacion.NINGUNO;
                }

        }
    }

    public boolean eliminarHorario() {
        Horario horario = ((Horario) tblHorarios.getSelectionModel().getSelectedItem());
        //System.out.println(alumno.toString());
        PreparedStatement pst = null;
        try {
            //System.out.println(alumno.toString());
            String SQL = "CALL sp_delete_horarios(?)";
            //System.out.println("Paso el if de confirmar");
            pst = ConexionDb.getInstance().getConexion().prepareCall(SQL);
            pst.setInt(1, horario.getId());
            System.out.println(pst);
            pst.execute();
            numero = numero - 1;
            //cargarDatos();
            //registros.remove(tblAlumnos.getSelectionModel().getFocusedIndex());
            limpiar();
            return true;
        } catch (SQLException e) {
            System.err.println("Se produjo un error al eliminar el registro: " + horario.toString());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("Llego al false");
        return false;
    }

    @FXML
    private void clicEliminar(ActionEvent event) {
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
                        eliminarHorario();
                        conteoLabel();
                        if (eliminarHorario()) {
                            listaHorarios.remove(tblHorarios.getSelectionModel().getFocusedIndex());
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
    private void clicReporte(ActionEvent event) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LOGO_HORARIOS", PAQUETE_IMAGE + "horarioReporte.png");
        GenerarReporte.getInstance().mostrarReporte("ReporteHorarios.jasper", parametros, "Reporte de Horarios");

    }
}
