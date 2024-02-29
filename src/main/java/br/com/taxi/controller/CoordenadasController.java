package br.com.taxi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.taxi.DTO.CoordenadasDTO;
import br.com.taxi.service.CoordenadasService;

@RestController
@RequestMapping(value = "/servico/prestado")
public class CoordenadasController {
	
	@Autowired
	private CoordenadasService coordenadasService;
	
	@GetMapping
	public List<CoordenadasDTO> findAll(){
		List<CoordenadasDTO> result = coordenadasService.findAll();
		return result;
	}

	@PostMapping("/adicionar")
	public ResponseEntity<Void> adicionarCoordenadas(@RequestBody CoordenadasDTO coordenadasDTO) {
	    try {
	        coordenadasService.adicionarCoordenadas(coordenadasDTO);
	        return ResponseEntity.status(HttpStatus.CREATED).build();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
}