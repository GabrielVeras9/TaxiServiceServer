package br.com.taxi.DTO;

import br.com.taxi.entity.PosicaoEntity;

public class FilesDTO {
    
    private String imagem;
    
    public FilesDTO() {
    }
    
    public FilesDTO(PosicaoEntity entity) {
       
        if (entity.getImagem() != null) {
            imagem = entity.getImagem();
        } else {
            imagem = "null"; // ou qualquer valor padrão que você deseje
        }
    }
    
    public FilesDTO(String imagem) {
        
        if (imagem != null) {
            this.imagem = imagem;
        } else {
            this.imagem = ""; // ou qualquer valor padrão que você deseje
        }
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        if (imagem != null) {
            this.imagem = imagem;
        } else {
            this.imagem = ""; // ou qualquer valor padrão que você deseje
        }
    }
}
