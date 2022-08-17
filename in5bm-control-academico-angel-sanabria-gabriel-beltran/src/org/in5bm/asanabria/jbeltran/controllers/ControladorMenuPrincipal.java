package org.in5bm.asanabria.jbeltran.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.in5bm.asanabria.jbeltran.db.ConexionDb;
import org.in5bm.asanabria.jbeltran.models.Usuario;
import org.in5bm.asanabria.jbeltran.reports.GenerarReporte;
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
public class ControladorMenuPrincipal implements Initializable {

    ControladorAlumnos alumno = new ControladorAlumnos();
    private Principal escenarioPrincipal;
    private Stage stage;
    private Scene scene;
    private Parent root;
    int limite = 0;
    private final String PAQUETE_IMAGE = "org/in5bm/asanabria/jbeltran/resources/images/";
    @FXML
    public MenuItem moduloAlumnos;

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    @FXML
    public MenuItem moduloAsignacionAlumnos;

    @FXML
    public MenuItem moduloCarrerasTecnicas;

    @FXML
    public MenuItem moduloCursos;

    @FXML
    public MenuItem moduloHorarios;

    @FXML
    public MenuItem moduloInstructores;

    @FXML
    public MenuItem moduloSalones;

    @FXML
    private void ventanaAlumno(ActionEvent event) throws IOException {
        escenarioPrincipal.mostrarEscenaAlumno();
    }

    @FXML
    void ventanaAsignacion(ActionEvent event) throws IOException {
        escenarioPrincipal.mostrarEscenaAsignacion();
    }

    @FXML
    void ventanaAcercaDe(ActionEvent event) throws IOException {
        escenarioPrincipal.mostrarAcercaDe();
    }

    @FXML
    void ventanaCarreras(ActionEvent event) throws IOException {
        escenarioPrincipal.mostrarEscenaCarreras();
    }

    @FXML
    void ventanaCurso(ActionEvent event) throws IOException {
        escenarioPrincipal.mostrarEscenaCursos();
    }

    @FXML
    void ventanaHorario(ActionEvent event) throws IOException {
        escenarioPrincipal.mostrarEscenaHorarios();
    }

    @FXML
    void ventanaInstructor(ActionEvent event) throws IOException {
        escenarioPrincipal.mostrarEscenaInstructor();
    }

    @FXML
    void ventanaSalon(ActionEvent event) throws IOException {
        escenarioPrincipal.mostrarEscenaSalones();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ControladorLogin login = new ControladorLogin();
        if (login.retornarRol().equals("Estandar")) {
            deshabilitarItems();
        }
        if (login.retornarRol().equals("Administrador")) {
            activarItems();

        }
    }

    @FXML
    public void clickReporteAlumnos(ActionEvent event) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LOGO_ALUMNOS", PAQUETE_IMAGE + "alumnoReporte.png");
        GenerarReporte.getInstance().mostrarReporte("ReporteAlumnos.jasper", parametros, "Reporte de Alumnos");
    }

    @FXML
    public void clickReporteInstructores(ActionEvent event) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LOGO_INSTRUCTORES", PAQUETE_IMAGE + "instructorReporte.png");
        GenerarReporte.getInstance().mostrarReporte("ReporteInstructores.jasper", parametros, "Reporte de Instructores");
    }

    @FXML
    public void clickReporteSalones(ActionEvent event) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LOGO_SALONES", PAQUETE_IMAGE + "salonReporte.png");
        GenerarReporte.getInstance().mostrarReporte("ReporteSalones.jasper", parametros, "Reporte de Salones");
    }

    @FXML
    public void clickReporteCursos(ActionEvent event) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LOGO_CURSOS", PAQUETE_IMAGE + "cursoReporte.png");
        GenerarReporte.getInstance().mostrarReporte("ReporteCursos.jasper", parametros, "Reporte de Cursos");
    }

    @FXML
    public void clickReporteCarreras(ActionEvent event) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LOGO_CARRERAS", PAQUETE_IMAGE + "carreraReporte.png");
        GenerarReporte.getInstance().mostrarReporte("ReporteCarreras.jasper", parametros, "Reporte de Carreras");
    }

    @FXML
    public void clickReporteHorarios(ActionEvent event) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LOGO_HORARIOS", PAQUETE_IMAGE + "horarioReporte.png");
        GenerarReporte.getInstance().mostrarReporte("ReporteHorarios.jasper", parametros, "Reporte de Horarios");
    }

    @FXML
    public void clickReporteAsignaciones(ActionEvent event) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LOGO_ASIGNACIONES", PAQUETE_IMAGE + "asignacionReporte.png");
        GenerarReporte.getInstance().mostrarReporte("ReporteAsignacion.jasper", parametros, "Reporte de Asignacion");
    }

    public boolean comprobacionCursos(int numero) {
        Statement pst = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT id FROM cursos WHERE id = " + numero;
            System.out.println("Paso el if de confirmar");
            pst = ConexionDb.getInstance().getConexion().createStatement();
            System.out.println(pst.toString());
            rs = pst.executeQuery(SQL);
            if (rs.next()) {
                return true;
            } else {

                return false;
            }

            //cargarDatos();
            //registros.remove(tblAlumnos.getSelectionModel().getFocusedIndex());
            //limpiar();
        } catch (SQLException e) {
            System.err.println("Se produjo un error al intentar ejecutar la sentencia");
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
        return false;
    }

    public boolean comprobacionAsignacion(int numero) {
        Statement pst = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT id FROM asignacion_alumnos WHERE id = " + numero;
            System.out.println("Paso el if de confirmar");
            pst = ConexionDb.getInstance().getConexion().createStatement();
            System.out.println(pst.toString());
            rs = pst.executeQuery(SQL);
            if (rs.next()) {

                return true;
            } else {
                return false;
            }

            //cargarDatos();
            //registros.remove(tblAlumnos.getSelectionModel().getFocusedIndex());
            //limpiar();
        } catch (SQLException e) {
            System.err.println("Se produjo un error al intentar ejecutar la sentencia");
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
        return false;
    }

    @FXML
    public void cerrar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Esta seguro de salir de la aplicacion?");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
        Optional<ButtonType> confirmar = alert.showAndWait();
        if (confirmar.get().equals(ButtonType.OK)) {
            System.exit(0);
        }
    }

    @FXML
    public void clickReporteCursosId(ActionEvent event) {
        int numero = 0;
        TextInputDialog td = new TextInputDialog("");
        td.setHeaderText("Ingrese el Id de el Curso");
        td.showAndWait();
        if (td.getEditor().getText().isEmpty()) {
            td.close();
        } else {
            try {
                int numero1 = Integer.parseInt(td.getEditor().getText());
                if (comprobacionCursos(numero1)) {
                    numero = numero1;
                    Map<String, Object> parametros = new HashMap<>();
                    parametros.put("idCurso", numero);
                    parametros.put("LOGO_CURSOS_ID", PAQUETE_IMAGE + "cursoReporte.png");
                    GenerarReporte.getInstance().mostrarReporte("ReporteCursosId.jasper", parametros, "Reporte de Cursos por Id");
                } else {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Control Academico");
                    alerta.setHeaderText(null);
                    alerta.setContentText("EL VALOR DEL ID NO EXISTE");
                    Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
                    alerta.showAndWait();
                    td.setHeaderText("SELECCIONE OTRO ID");
                    td.showAndWait();
                    if (td.getEditor().getText().isEmpty()) {
                        td.close();
                    } else {
                        int numero2 = Integer.parseInt(td.getEditor().getText());
                        if (comprobacionCursos(numero2)) {
                            //comprobacionLimite(numero2,limite);
                            System.out.println(" " + numero2);
                            Map<String, Object> parametros2 = new HashMap<>();
                            parametros2.put("idCurso", numero2);
                            parametros2.put("LOGO_CURSOS_ID", PAQUETE_IMAGE + "cursoReporte.png");
                            GenerarReporte.getInstance().mostrarReporte("ReporteCursosId.jasper", parametros2, "Reporte de Cursos por Id");
                        } else {
                            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                            alerta2.setTitle("Control Academico");
                            alerta2.setHeaderText(null);
                            alerta2.setContentText("NO EXISTE EL VALOR");
                            stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
                            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
                            alerta.showAndWait();
                            td.close();
                        }
                    }
                }
            } catch (NumberFormatException e) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Control Academico");
                alerta.setHeaderText(null);
                alerta.setContentText("EL VALOR INGRESADO ES INCORRECTO, SE NECESITA VALOR NUMERICO");
                Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
                alerta.showAndWait();
                td.setHeaderText("SELECCIONE UN ID");
                td.showAndWait();
                if (td.getEditor().getText().isEmpty()) {
                    td.close();
                } else {
                    int numero2 = Integer.parseInt(td.getEditor().getText());
                    if (comprobacionCursos(numero2)) {
                        //comprobacionLimite(numero2,limite);
                        System.out.println(" " + numero2);
                        Map<String, Object> parametros2 = new HashMap<>();
                        parametros2.put("idCurso", numero2);
                        parametros2.put("LOGO_CURSOS_ID", PAQUETE_IMAGE + "cursoReporte.png");
                        GenerarReporte.getInstance().mostrarReporte("ReporteCursosId.jasper", parametros2, "Reporte de Cursos por Id");
                    } else {
                        Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                        alerta2.setTitle("Control Academico");
                        alerta2.setHeaderText(null);
                        alerta2.setContentText("NO EXISTE EL VALOR");
                        stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
                        alerta.showAndWait();
                        td.close();
                    }
                }
            }
        }
    }

    @FXML
    public void clickReporteAsignacionesId(ActionEvent event) {
        int numero = 0;
        TextInputDialog td = new TextInputDialog("");
        td.setHeaderText("Ingrese el Id de Asignacion");
        td.showAndWait();
        if (td.getEditor().getText().isEmpty()) {
            td.close();
        } else {
            try {
                int numero1 = Integer.parseInt(td.getEditor().getText());
                if (comprobacionAsignacion(numero1)) {
                    numero = numero1;
                    Map<String, Object> parametros = new HashMap<>();
                    parametros.put("idAsignacion", numero);
                    parametros.put("LOGO_ASIGNACIONES_ID", PAQUETE_IMAGE + "asignacionReporte.png");
                    GenerarReporte.getInstance().mostrarReporte("ReporteAsignacionId.jasper", parametros, "Reporte de Asignacion por Id");
                } else {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Control Academico");
                    alerta.setHeaderText(null);
                    alerta.setContentText("EL VALOR DEL ID NO EXISTE");
                    Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
                    alerta.showAndWait();
                    td.setHeaderText("SELECCIONE OTRO ID");
                    td.showAndWait();
                    if (td.getEditor().getText().isEmpty()) {
                        td.close();
                    } else {
                        int numero2 = Integer.parseInt(td.getEditor().getText());
                        if (comprobacionAsignacion(numero2)) {
                            //comprobacionLimite(numero2,limite);
                            System.out.println(" " + numero2);
                            Map<String, Object> parametros2 = new HashMap<>();
                            parametros2.put("idAsignacion", numero2);
                            parametros2.put("LOGO_ASIGNACIONES_ID", PAQUETE_IMAGE + "asignacionReporte.png");
                            GenerarReporte.getInstance().mostrarReporte("ReporteAsignacionId.jasper", parametros2, "Reporte de Asignacion por Id");
                        } else {
                            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                            alerta2.setTitle("Control Academico");
                            alerta2.setHeaderText(null);
                            alerta2.setContentText("NO EXISTE EL VALOR");
                            stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
                            stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
                            alerta.showAndWait();
                            td.close();
                        }
                    }
                }
            } catch (NumberFormatException e) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Control Academico");
                alerta.setHeaderText(null);
                alerta.setContentText("EL VALOR INGRESADO ES INCORRECTO, SE NECESITA VALOR NUMERICO");
                Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
                alerta.showAndWait();
                td.setHeaderText("SELECCIONE UN ID");
                td.showAndWait();
                if (td.getEditor().getText().isEmpty()) {
                    td.close();
                } else {
                    int numero2 = Integer.parseInt(td.getEditor().getText());
                    if (comprobacionAsignacion(numero2)) {
                        //comprobacionLimite(numero2,limite);
                        System.out.println(" " + numero2);
                        Map<String, Object> parametros2 = new HashMap<>();
                        parametros2.put("idAsignacion", numero2);
                        parametros2.put("LOGO_ASIGNACIONES_ID", PAQUETE_IMAGE + "asignacionReporte.png");
                        GenerarReporte.getInstance().mostrarReporte("ReporteAsignacionId.jasper", parametros2, "Reporte de Asignacion por Id");
                    } else {
                        Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                        alerta2.setTitle("Control Academico");
                        alerta2.setHeaderText(null);
                        alerta2.setContentText("NO EXISTE EL VALOR");
                        stage = (Stage) alerta2.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(new Image((PAQUETE_IMAGE + "logo.png")));
                        alerta.showAndWait();
                        td.close();
                    }
                }
            }
        }
    }

    public void activarItems() {
        moduloAlumnos.setDisable(false);
        moduloCarrerasTecnicas.setDisable(false);
        moduloHorarios.setDisable(false);
        moduloInstructores.setDisable(false);
        moduloSalones.setDisable(false);
        moduloAsignacionAlumnos.setDisable(false);
        moduloCursos.setDisable(false);
    }

    public void deshabilitarItems() {
        moduloAlumnos.setDisable(true);
        moduloCarrerasTecnicas.setDisable(true);
        moduloHorarios.setDisable(true);
        moduloInstructores.setDisable(true);
        moduloSalones.setDisable(true);
        moduloAsignacionAlumnos.setDisable(false);
        moduloCursos.setDisable(false);
    }

    public void cerrarSesion() {
        escenarioPrincipal.mostrarEscenaLogin();
    }
}
