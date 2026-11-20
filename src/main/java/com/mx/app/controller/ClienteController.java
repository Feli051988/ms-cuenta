package com.mx.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mx.app.dto.ClienteRequestDTO;
import com.mx.app.dto.ClienteResponseDTO;
import com.mx.app.entity.Cliente;
import com.mx.app.service.ClienteService;
import com.mx.app.service.ClienteServiceImp;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Controller
@RequestMapping("api/cliente")
@CrossOrigin
@Tag(name = "MS-CUENTAS", description = "Operaciones relacionados conla cuenta de clientes")
@Validated
public class ClienteController {

	private final ClienteService service;
	private final ClienteServiceImp imp;

	public ClienteController(ClienteService service, ClienteServiceImp imp) {
		this.service = service;
		this.imp = imp;
	}

	// http://localhost:9000/api/cliente
	@GetMapping
	@Operation(summary = "Lista de Clientes", description = "Permite obtener todos los clientes de la base de datos")
	public ResponseEntity<List<ClienteResponseDTO>> lista() {
		return ResponseEntity.ok(service.listar());
	}

	// http://localhost:9000/api/cliente
	@GetMapping("/{id}")
	@Operation(summary = "Busca un cliente por ID", description = "Permite obtener un cliente de la base de datos")
	public ResponseEntity<ClienteResponseDTO> buscar(@PathVariable() Long id) {
		return ResponseEntity.ok(service.buscar(id));
	}

	// http://localhost:9000/api/cliente
	@PostMapping
	@Operation(summary = "Guarda un nuevo cliente", description = "Permite almacenar un cliente de la base de datos")
	public ResponseEntity<ClienteResponseDTO> guardar(@Valid @RequestBody ClienteRequestDTO request) {
		ClienteResponseDTO clienteGuardar = service.guardar(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteGuardar);
	}

	// http://localhost:9000/api/cliente
	@PutMapping("/{id}")
	@Operation(summary = "Actualiza un cliente completo", description = "Permite actualizar un cliente de la base de datos")
	public ResponseEntity<ClienteResponseDTO> actualizar(@PathVariable() Long id,
			@Valid @RequestBody ClienteRequestDTO request) {
		ClienteResponseDTO clienteactualizar = service.actualziar(id, request);
		return ResponseEntity.status(HttpStatus.OK).body(clienteactualizar);
	}

	// http://localhost:9000/api/cliente/
	@DeleteMapping("/{id}")
	@Operation(summary = "Elimina un cliente completo", description = "Permite eliminar un cliente de la base de datos")
	public ResponseEntity<ClienteResponseDTO> eliminar(@PathVariable() Long id) {
		service.eliminar(id);
		return ResponseEntity.noContent().build();
	}

	// buscar por nombre http://localhost:9000/api/cliente/buscarNombre/
	@GetMapping("buscarNombre/{nombre}")
	public ResponseEntity<ClienteResponseDTO> buscarNombre(@PathVariable() String nombre) {
		return ResponseEntity.ok(imp.buscarNombre(nombre));
	}
}
