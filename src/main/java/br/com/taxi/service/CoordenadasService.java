package br.com.taxi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.taxi.DTO.CoordenadasDTO;
import br.com.taxi.entity.PosicaoEntity;
import br.com.taxi.repository.PosicaoRepository;

@Service
public class CoordenadasService {
	
	@Autowired
    private PosicaoRepository posicaoRepository;
    
    @Transactional(readOnly = true)
	public List<CoordenadasDTO> findAll(){
		List<PosicaoEntity> result = posicaoRepository.findAll();
		return result.stream().map(x -> new CoordenadasDTO(x)).toList();
		
	}
    
    public void adicionarCoordenadas(CoordenadasDTO dto) {
    	PosicaoEntity posicao = new PosicaoEntity();
    	posicao.setLatitude(dto.getLatitude());
    	posicao.setLongitude(dto.getLongitude());
        posicaoRepository.save(posicao);
    }
}
