package org.in5bm.asanabria.jbeltran.reports;

import java.net.URL;
import java.util.Locale;
import java.util.Map;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.in5bm.asanabria.jbeltran.db.ConexionDb;

/**
 *
 * @author Angel Sanabria
 * @date 3/05/2022
 * @time 09:12:25
 * @grade 5to Perito en Informatica B
 * @code IN5BM
 * @carnet 2021067
 */
public class GenerarReporte {

    private static GenerarReporte instancia;
    private JasperViewer jasperViewer;
    private final String PAQUETE_IMAGE = "org/in5bm/asanabria/jbeltran/resources/images/";

    private GenerarReporte() {

        Locale locale = new Locale("es", "GT");
        Locale.setDefault(locale);
    }

    public static GenerarReporte getInstance() {
        if (instancia == null) {
            instancia = new GenerarReporte();
        }
        return instancia;
    }

    public void mostrarReporte(String nombreReporte, Map<String, Object> parametros, String titulo) {
        try {
            parametros.put("IMAGE_LOGO", PAQUETE_IMAGE + "Logosin.png");
            URL urlFile = new URL(getClass().getResource(nombreReporte).toString());
            System.out.println("Despues del URL");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(urlFile);
            System.out.println("Despues del loader");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, ConexionDb.getInstance().getConexion());
            System.out.println("Despues del print");
            jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setTitle(titulo);
            System.out.println("Despues del titulo");
            jasperViewer.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
