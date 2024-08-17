/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ista.edu.practica.Facturacion.service;

import ista.edu.practica.Facturacion.model.Producto;
import ista.edu.practica.Facturacion.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author 59398
 */
@Service
public class ProductoServiceImpl extends GenericServiceImpl<Producto, Integer> implements ProductoService{

    @Autowired
    ProductoRepository productoRepository;
    
    @Override
    public CrudRepository<Producto, Integer> getDao() {
        return productoRepository;
    }
    
}
