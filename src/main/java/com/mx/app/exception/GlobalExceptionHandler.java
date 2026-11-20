package com.mx.app.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// Cliente no encontrado: HTTP 404
	@ExceptionHandler(ClienteNoEncontradoException.class)
	public ResponseEntity<Map<String, Object>> clienteNoEncontrado(ClienteNoEncontradoException ex,
			HttpServletRequest request) {

		Map<String, Object> error = new LinkedHashMap<>();

		error.put("fecha", LocalDateTime.now());
		error.put("status", HttpStatus.NOT_FOUND.value());
		error.put("error", "Not Found");
		error.put("mensaje", ex.getMessage());
		error.put("path", request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	// Errores de validación: HTTP 400
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> validacion(MethodArgumentNotValidException ex,
			HttpServletRequest request) {

		Map<String, Object> error = new LinkedHashMap<>();

		error.put("fecha", LocalDateTime.now());
		error.put("status", HttpStatus.BAD_REQUEST.value());
		error.put("error", "Bad Request");
		error.put("mensaje", ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
		error.put("path", request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	// Cualquier otra excepción no controlada: HTTP 500
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> errorGeneral(Exception ex, HttpServletRequest request) {

		Map<String, Object> error = new LinkedHashMap<>();

		error.put("fecha", LocalDateTime.now());
		error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.put("error", "Internal Server Error");
		error.put("mensaje", "Ocurrió un error interno en el servidor");
		error.put("path", request.getRequestURI());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
}
