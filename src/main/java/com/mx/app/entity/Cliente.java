package com.mx.app.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CLIENTE")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Cliente {
	
	@Id
	@SequenceGenerator(name = "sql_sequence", sequenceName = "SQL_SEQUENCE_UNO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sql_sequence")
	@Column(name = "ID_CLIENTE")
	private Long idCliente;
	private String nombre;
	private String app;
	@Column(name = "FECHA_NACIMIENTO")
	private LocalDate fechaNacimiento;
	private Integer edad;
	private String correo;
	@Column(name = "MONTO_INVERSION")
	private BigDecimal montoInversion;
	
	
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public BigDecimal getMontoInversion() {
		return montoInversion;
	}
	public void setMontoInversion(BigDecimal montoInversion) {
		this.montoInversion = montoInversion;
	}
	
	
	
	
	

}
