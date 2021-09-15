package com.microservicios.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_customers")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @NotEmpty(message = "Numero de documento no puede ser vacio.")
  @Size(min = 8, max = 8, message = "Tamaño del numero de documento es 8.")
  @Column(name = "number_id", unique = true, length = 8, nullable = false)
  private String numberId;
  
  @NotEmpty(message = "Nombre no puede ser vacio.")
  @Column(name = "first_name", nullable = false)
  private String firstName;
  
  @NotEmpty(message = "Apellido no puede ser vacio.")
  @Column(name = "last_name", nullable = false)
  private String lastName;
  
  @NotEmpty(message = "Correo no puede ser vacio.")
  @Email(message = "No es una direccion de correo valida.")
  @Column(unique = true, nullable = false)
  private String email;
 
  @Column(name = "photo_url")
  private String photoUrl;
  
  @NotNull(message = "Region no puede ser vacia.")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "region_id")
  @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
  private Region region;
  
  private String state;
  
}
