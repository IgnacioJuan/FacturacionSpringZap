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
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author 59398
 */
@Getter
@Setter
@Entity
public class Factura implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_factura")
  private Integer id_factura;
  @Column(name = "fecha")
  private Date fecha;
  
  @ManyToOne
  @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
  private Cliente cliente;
  
  @OneToMany(mappedBy = "factura")
  private List<Detalle_Factura> detalles;
  
  
}
