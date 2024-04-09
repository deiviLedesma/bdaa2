/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NEGOCIO;

import DAO.ReporteDAO;
import JPA.Tramite;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase donde hacemos lo de reportes
 *
 * @author SDavidLedesma
 */
public class ReporteManager {

    private ReporteDAO reporteDAO;

    public ReporteManager() {
        this.reporteDAO = new ReporteDAO();
    }

    /**
     * Obtiene las placas y todo para el reporte
     *
     * @return el reporte
     */
    public List<Tramite> obtenerTramites() {
        return reporteDAO.obtenerTramites();
    }
    /*
        public static void generateTramitesReport(Date fechaInicio, Date fechaFin, String outputFilePath) {
        // Obtener una conexión JDBC
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PlacasItson", "root", "Inunanash")) {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("fechaInicio", fechaInicio);
            parameters.put("fechaFin", fechaFin);

            JasperReport report = JasperCompileManager.compileReport("ruta del pdf");
            JasperPrint print = JasperFillManager.fillReport(report, parameters, connection);
            JasperExportManager.exportReportToPdfFile(print, outputFilePath);
        } catch (SQLException | JRException e) {
            e.printStackTrace();
        }
    }
     */
    //el metodo de arriba era implementando el jasperReport
}
