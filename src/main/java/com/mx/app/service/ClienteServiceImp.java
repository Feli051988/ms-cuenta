package com.mx.app.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mx.app.dto.ClienteRequestDTO;
import com.mx.app.dto.ClienteResponseDTO;
import com.mx.app.entity.Cliente;
import com.mx.app.exception.ClienteNoEncontradoException;
import com.mx.app.mapper.ClienteMapper;
import com.mx.app.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClienteServiceImp implements ClienteService {

	private final ClienteRepository repo;
	private final ClienteMapper mapper;

	public ClienteServiceImp(ClienteRepository repo, ClienteMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}

	@Override
	public ClienteResponseDTO guardar(ClienteRequestDTO request) {
		Cliente cliente = mapper.toEntity(request);

		int edadActual = Period.between(cliente.getFechaNacimiento(), LocalDate.now()).getYears();

		cliente.setEdad(edadActual);
		Cliente clienteGuardar = repo.save(cliente);
		return mapper.toResponse(clienteGuardar);
	}

	@Override
	public ClienteResponseDTO actualziar(Long id, ClienteRequestDTO request) {
		Cliente cliente = repo.findById(id).orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encotrado"));

		cliente.setNombre(request.getNombre());
		cliente.setApp(request.getApp());
		cliente.setFechaNacimiento(request.getFechaNacimiento());
		// calcular edad
		int edadActual = Period.between(cliente.getFechaNacimiento(), LocalDate.now()).getYears();

		cliente.setEdad(edadActual);
		
		cliente.setCorreo(request.getCorreo());
		cliente.setMontoInversion(request.getMontoInversion());

		Cliente clienteActualizar = repo.save(cliente);
		return mapper.toResponse(clienteActualizar);
	}

	@Override
	public void eliminar(Long id) {
		if (!repo.existsById(id)) {
			throw new ClienteNoEncontradoException("Cliente no encoyrado con id:  " + id);
		}
		repo.deleteById(id);

	}

	@Override
	public ClienteResponseDTO buscar(Long id) {
		Cliente cliente = repo.findById(id).orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encotrado"));
		return mapper.toResponse(cliente);
	}

	@Override
	public List<ClienteResponseDTO> listar() {
		// TODO Auto-generated method stub
		return repo.findAll().stream().map(mapper::toResponse).toList();
	}

	// ============== metodos propios ========================

	public ClienteResponseDTO buscarNombre(String nombre) {
		Cliente cliente = repo.findByNombre(nombre);
		return mapper.toResponse(cliente);
	}

}
