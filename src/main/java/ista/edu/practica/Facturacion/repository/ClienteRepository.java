/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ista.edu.practica.Facturacion.repository;

import ista.edu.practica.Facturacion.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author 59398
 */
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query(value = "Select * from cliente c where c.cedula = :cedula", nativeQuery = true)
    public Cliente buscarCliente(String cedula);
}
