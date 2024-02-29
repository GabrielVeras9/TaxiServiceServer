package br.com.taxi.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.taxi.DTO.PosicaoDTO;
import br.com.taxi.service.PosicaoService;

@RestController
@RequestMapping("/taxitas")
public class PosicaoController {

    @Autowired
    private PosicaoService posicaoService;
    
    @GetMapping("/tempo/real")
    public List<PosicaoDTO> listarPosicoes() {
        return posicaoService.findAll();
    }
    
    @PostMapping("/buscar/local")
    public ResponseEntity<Void> cadastrarFrota(
            @RequestPart("dto") PosicaoDTO dto,
            @RequestPart("file") MultipartFile file) {
        
        posicaoService.LocalizarTaxi(dto, file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
