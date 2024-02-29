package br.com.taxi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.taxi.DTO.PosicaoDTO;
import br.com.taxi.entity.PosicaoEntity;
import br.com.taxi.repository.PosicaoRepository;

@Service
public class PosicaoService {
		
		@Autowired
	    private PosicaoRepository posicaoRepository;

	    @Autowired
	    private GoogleDriveService googleDriveService;
	    
	    public String uploadFileToDrive(MultipartFile file) {
	        try {

	            String linkGoogleDrive = googleDriveService.uploadFileToDrive(file);
	            return linkGoogleDrive;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	    
	    @Transactional(readOnly = true)
		public List<PosicaoDTO> findAll(){
			List<PosicaoEntity> result = posicaoRepository.findAll();
			return result.stream().map(x -> new PosicaoDTO(x)).toList();

		}
	    
	    public void LocalizarTaxi(PosicaoDTO dto, MultipartFile file) {
	    	PosicaoEntity posicao = new PosicaoEntity();
	    	posicao.setLatitude(dto.getLatitude());
	    	posicao.setLongitude(dto.getLongitude());
	    	posicao.setImagem(dto.getImagem());
	        
	        try {
	            String linkGoogleDrive = googleDriveService.uploadFileToDrive(file);
	            posicao.setImagem(linkGoogleDrive);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        posicaoRepository.save(posicao);
	    }
	}
