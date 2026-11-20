package com.mx.app.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClienteRequestDTO {
	
	
    @NotBlank(message = "El nombre no puede ser vacio")
    @Size(max = 50, message = "No puede superar los 50 caracteres")
	private String nombre;
    
    @NotBlank(message = "El nombre no puede ser vacio")
    @Size(max = 50, message = "No puede superar los 50 caracteres")
	private String app;
    
    @NotNull(message = "Fecha de nacimiento no pude ser vacio o nulo")
    @PastOrPresent(message = "la fecha de nacimiento no pude ser futura")
	private LocalDate fechaNacimiento;
    
   // @NotNull(message = "La edad no pude ser nulo o vacio")
    @Min(18)
    @Max(120)
	private Integer edad;
    
    @NotBlank(message = "el correo no puede ser vacio o nulo")
    @Email(message = "el correo debe de tener un formato valido @hotmail, @gmail, @yahoo")
	private String correo;
    
    @NotNull(message = "EL monto de inversion no debe de ser nulo o vacio")
    @Positive(message = "El monto de invesion debe ser mayor a 0")
	private BigDecimal montoInversion;

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
