/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.in5bm.asanabria.jbeltran.reports;

/**
 *
 * @author informatica
 */
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


public class GenerarReporte {
    private static GenerarReporte instancia;
    private JasperViewer jasperViewer;
    private GenerarReporte() {
        Locale locale = new Locale("es", "GT");
        Locale.setDefault(locale);
    }

    public static GenerarReporte getInstance(){
        if(instancia == null){
            instancia = new GenerarReporte();
        }
        return instancia;
    }
    
    public void mostrarReporte(String nombreReporte, Map<String, Object> parametros, String titulo){
        try{
            URL urlFile = new URL(getClass().getResource(nombreReporte).toString());
            
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(urlFile);
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, ConexionDb.getInstance().getConexion());
            
            jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setTitle(titulo);
            jasperViewer.setVisible(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
