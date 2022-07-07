package org.in5bm.asanabria.jbeltran.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.in5bm.asanabria.jbeltran.db.ConexionDb;
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
    private final String PAQUETE_IMAGE = "org/in5bm/asanabria/jbeltran/resources/images/";
    @FXML
    private MenuItem moduloAlumnos;

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    @FXML
    private MenuItem moduloAsignacionAlumnos;

    @FXML
    private MenuItem moduloCarrerasTecnicas;

    @FXML
    private MenuItem moduloCursos;

    @FXML
    private MenuItem moduloHorarios;

    @FXML
    private MenuItem moduloInstructores;

    @FXML
    private MenuItem moduloSalones;

    @FXML
    private void ventanaAlumno(ActionEvent event) throws IOException {
        escenarioPrincipal.mostrarEscenaAlumno();
    }

    @FXML
    void ventanaAsignacion(ActionEvent event) throws IOException {
        escenarioPrincipal.mostrarEscenaAsignacion();
    }
    
    @FXML
    void ventanaAcercaDe(ActionEvent event) throws IOException{
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
    }

    @FXML
    public void clickReporteAlumnos(ActionEvent event) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LOGO_ALUMNOS", PAQUETE_IMAGE + "graduado.png");
        GenerarReporte.getInstance().mostrarReporte("ReporteAlumnos.jasper", parametros, "Reporte de Alumnos");
    }

    @FXML
    public void clickReporteInstructores(ActionEvent event) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LOGO_INSTRUCTORES", PAQUETE_IMAGE + "instructor.png");
        GenerarReporte.getInstance().mostrarReporte("ReporteInstructores.jasper", parametros, "Reporte de Instructores");
    }

    @FXML
    public void clickReporteSalones(ActionEvent event) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LOGO_SALONES", PAQUETE_IMAGE + "salones.png");
        GenerarReporte.getInstance().mostrarReporte("ReporteSalones.jasper", parametros, "Reporte de Salones");
    }

    @FXML
    public void clickReporteCursos(ActionEvent event) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LOGO_CURSOS", PAQUETE_IMAGE + "cursos.png");
        GenerarReporte.getInstance().mostrarReporte("ReporteCursos.jasper", parametros, "Reporte de Cursos");
    }

    @FXML
    public void clickReporteCarreras(ActionEvent event) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LOGO_CARRERAS", PAQUETE_IMAGE + "carreras.png");
        GenerarReporte.getInstance().mostrarReporte("ReporteCarreras.jasper", parametros, "Reporte de Carreras");
    }

    @FXML
    public void clickReporteHorarios(ActionEvent event) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LOGO_HORARIOS", PAQUETE_IMAGE + "horario.png");
        GenerarReporte.getInstance().mostrarReporte("ReporteHorarios.jasper", parametros, "Reporte de Horarios");
    }

    @FXML
    public void clickReporteAsignaciones(ActionEvent event) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LOGO_ASIGNACIONES", PAQUETE_IMAGE + "asignacion.png");
        GenerarReporte.getInstance().mostrarReporte("ReporteAsignacion.jasper", parametros, "Reporte de Asignacion");
    }

    public boolean comprobacionLimite(int numero, int limite) {
        if (numero <= limite) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    public void clickReporteCursosId(ActionEvent event) {
        Statement pst = null;
        ResultSet rs = null;
        int limite = 0;
        try {
            String SQL = "SELECT COUNT(id) FROM cursos";
            System.out.println("Paso el if de confirmar");
            pst = ConexionDb.getInstance().getConexion().createStatement();
            System.out.println(pst.toString());
            rs = pst.executeQuery(SQL);
            if (rs.next()) {
                limite = rs.getInt(1);
            }
            System.out.println("EL VALOR MAXIMO ES: " + limite);
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
        int numero = 0;
        TextInputDialog td = new TextInputDialog("");
        td.setHeaderText("Ingrese el Id de el Curso");
        td.showAndWait();
        if (td.getEditor().getText().isEmpty()) {
            td.close();
        } else {
            int numero1 = Integer.parseInt(td.getEditor().getText());
            if (comprobacionLimite(numero1, limite)) {
                numero = numero1;
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("idCurso", numero);
                GenerarReporte.getInstance().mostrarReporte("ReporteCursosId.jasper", parametros, "Reporte de Cursos por Id");
            } else {
                td.setHeaderText("Seleccione un valor entre el 1 y " + limite);
                td.showAndWait();
                if (td.getEditor().getText().isEmpty()) {
                    td.close();
                } else {
                    int numero2 = Integer.parseInt(td.getEditor().getText());
                    if (comprobacionLimite(numero2, limite)) {
                        Map<String, Object> parametros = new HashMap<>();
                        parametros.put("idCurso", numero2);
                        GenerarReporte.getInstance().mostrarReporte("ReporteCursosId.jasper", parametros, "Reporte de Cursos por Id");
                    } else {
                        td.close();
                    }
                }
            }
        }
    }

    @FXML
    public void clickReporteAsignacionesId(ActionEvent event) {
        //hacer una sentencia de count para bd de los registros y ponerlo como limite
        int numero = 0;
        TextInputDialog td = new TextInputDialog("");
        td.setHeaderText("Ingrese el Id de Asignacion");
        td.showAndWait();
        Statement pst = null;
        ResultSet rs = null;
        int limite = 0;
        try {
            String SQL = "SELECT COUNT(id) FROM asignacion_alumnos";
            System.out.println("Paso el if de confirmar");
            pst = ConexionDb.getInstance().getConexion().createStatement();
            System.out.println(pst.toString());
            rs = pst.executeQuery(SQL);
            if (rs.next()) {
                limite = rs.getInt(1);
            }
            System.out.println("EL VALOR MAXIMO ES: " + limite);
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
        if (td.getEditor().getText().isEmpty()) {
            td.close();
        } else {
            int numero1 = Integer.parseInt(td.getEditor().getText());
            if (comprobacionLimite(numero1, limite)) {
                numero = numero1;
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("idAsignacion", numero);
                GenerarReporte.getInstance().mostrarReporte("ReporteAsignacionId.jasper", parametros, "Reporte de Asignacion por Id");
            } else {
                td.setHeaderText("Seleccione un valor entre el 1 y " + limite);
                td.showAndWait();
                if (td.getEditor().getText().isEmpty()) {
                    td.close();
                } else {
                    int numero2 = Integer.parseInt(td.getEditor().getText());
                    if (comprobacionLimite(numero1, limite)) {
                        //comprobacionLimite(numero2,limite);
                        Map<String, Object> parametros = new HashMap<>();
                        parametros.put("idAsignacion", numero2);
                        GenerarReporte.getInstance().mostrarReporte("ReporteAsignacionId.jasper", parametros, "Reporte de Asignacion por Id");
                    } else {
                        td.close();
                    }
                }
            }
        }
    }
}
