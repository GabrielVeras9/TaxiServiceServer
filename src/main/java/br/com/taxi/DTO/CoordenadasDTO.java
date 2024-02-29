package br.com.taxi.DTO;

import br.com.taxi.entity.PosicaoEntity;

public class CoordenadasDTO {
			
	    private Long id_posicao;
	    private Double latitude;
	    private Double longitude;
			
	    public CoordenadasDTO() {
	    }
			
	    public CoordenadasDTO(PosicaoEntity entity) {
	        id_posicao = entity.getId_posicao();
	        latitude = entity.getLatitude();
	        longitude = entity.getLongitude();
	    }

	 // Construtor com argumentos para criar uma instância de PosicaoDTO
	    public CoordenadasDTO(Long id_posicao, Double latitude, Double longitude) {
	        this.id_posicao = id_posicao;
	        this.latitude = latitude;
	        this.longitude = longitude;
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
}
