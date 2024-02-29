package br.com.taxi.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

import br.com.taxi.DTO.FilesDTO;
import br.com.taxi.entity.PosicaoEntity;
import br.com.taxi.repository.PosicaoRepository;

@Service
public class GoogleDriveService {

    private static final String FOLDER_ID = "1Xv3o3l0W2EW2ECv85fkkxQj7whiPCW52";

    @Autowired
    private Drive googleDriveService;
    
    @Autowired
    private PosicaoRepository posicaoRepository;
    
    public List<FilesDTO> findAll(){
		List<PosicaoEntity> result = posicaoRepository.findAll();
		return result.stream().map(x -> new FilesDTO(x)).toList();
	}

    public String uploadFileToDrive(MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                com.google.api.services.drive.model.File driveFile = new File();
                driveFile.setName(file.getOriginalFilename());
                driveFile.setParents(Collections.singletonList(FOLDER_ID));

                InputStream inputStream = file.getInputStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                byte[] fileContent = outputStream.toByteArray();

                driveFile = googleDriveService.files().create(driveFile, new ByteArrayContent(file.getContentType(), fileContent))
                        .setFields("id")
                        .execute();

                String fileId = driveFile.getId();
                
                PosicaoEntity posicao = new PosicaoEntity();
                String linkDaImagem = "https://drive.google.com/uc?id=" + fileId;
                posicao.setImagem(linkDaImagem);
                posicaoRepository.save(posicao);

                return "https://drive.google.com/uc?id="+fileId;
            } else {
                return "Por favor, selecione um arquivo para upload.";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Erro durante o upload da imagem para o Google Drive: " + e.getMessage();
        }
    }
}