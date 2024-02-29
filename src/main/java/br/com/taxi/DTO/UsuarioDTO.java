package br.com.taxi.DTO;


import br.com.taxi.entity.PosicaoEntity;
import br.com.taxi.entity.UsuarioEntity;

public class UsuarioDTO {
	
	private Long id_usuario;
	private String email;
	private String senha;
	private String foto;
		
    public UsuarioDTO() {
    }
		
    public UsuarioDTO(UsuarioEntity entity) {
    	id_usuario = entity.getId_usuario();
    	email = entity.getEmail();
    	senha = entity.getSenha();
    	foto = entity.getFoto();
    }
    
    public UsuarioDTO(PosicaoEntity posicaoEntity) {
        this.foto = posicaoEntity.getImagem();
    }

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
}
