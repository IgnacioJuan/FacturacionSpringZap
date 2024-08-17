/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ista.edu.practica.Facturacion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author 59398
 */
@Getter
@Setter
@Entity
public class Detalle_Factura implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_detalle")
   private Integer id_detalle;
   @Column(name = "numero_factura")
   private Integer numero_factura;
   @Column(name = "cantidad")
   private Integer cantidad;
   
   @ManyToOne
   @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
   private Producto producto;

   @ManyToOne
   @JoinColumn(name = "id_factura", referencedColumnName = "id_factura")
   private Factura factura;
   
}
