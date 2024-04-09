/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import JPA.Licencia;

/**
 *
 * @author SDavidLedesma
 */
public interface ILicenciaDAO {
    
    public void insertarLicencia(Licencia licencia);
    public void actualizarLicencia(Licencia licencia);
    public void eliminarLicencia(Long idlicencia);
    public Licencia obtenerLicenciaPorId (Long id);
}
