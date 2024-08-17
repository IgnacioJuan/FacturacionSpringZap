/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ista.edu.practica.Facturacion.repository;

import ista.edu.practica.Facturacion.model.Detalle_Factura;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author 59398
 */
public interface DetalleRepository extends JpaRepository<Detalle_Factura, Integer>{
    
}
