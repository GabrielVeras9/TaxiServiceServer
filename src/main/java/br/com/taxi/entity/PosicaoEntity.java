package br.com.taxi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "posicao", schema = "TAXI")
public class PosicaoEntity {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id_posicao;
		private Double latitude;
		private Double longitude;
		private String imagem;
		
		public PosicaoEntity() {
		}

		public PosicaoEntity(Long id_posicao, Double latitude, Double longitude, String imagem) {
			super();
			this.id_posicao = id_posicao;
			this.latitude = latitude;
			this.longitude = longitude;
			this.imagem = imagem;
		}

		public Long getId_posicao() {
			return id_posicao;
		}

		public void setId_posicao(Long id_posicao) {
			this.id_posicao = id_posicao;
		}

		public Double getLatitude() {
			return latitude;
		}

		public void setLatitude(Double latitude) {
			this.latitude = latitude;
		}

		public Double getLongitude() {
			return longitude;
		}

		public void setLongitude(Double longitude) {
			this.longitude = longitude;
		}

		public String getImagem() {
			return imagem;
		}

		public void setImagem(String imagem) {
			this.imagem = imagem;
		}
}
