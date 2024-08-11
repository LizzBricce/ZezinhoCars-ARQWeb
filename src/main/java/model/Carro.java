package model;

import java.io.Serializable;

public class Carro implements Serializable{
	private static long serialVersionUID = 1L;
	private static int contador = 0;
		
	private int id;	
	private int avaliacao;
	private double preco;
	private long km;
	private CategoriaEnum categoria;
	private CombustivelEnum tipoCombustivel;
	private String marca;
	private String modelo;
	private String anoFabricacao;
	private String cor;
	private Boolean destaque;
	private Boolean lancamento;
	private Boolean oferta;
	// fotos
	
	private Carro() {
		this.id = ++this.contador;
	}

	private Carro(int avaliacao, double preco, long km, CategoriaEnum categoria, String marca, String modelo,
			String anoFabricacao, String cor, CombustivelEnum tipoCombustivel, Boolean destaque, Boolean lancamento,
			Boolean oferta) {
		this();
		this.avaliacao = avaliacao;
		this.preco = preco;
		this.km = km;
		this.categoria = categoria;
		this.marca = marca;
		this.modelo = modelo;
		this.anoFabricacao = anoFabricacao;
		this.cor = cor;
		this.tipoCombustivel = tipoCombustivel;
		this.destaque = destaque;
		this.lancamento = lancamento;
		this.oferta = oferta;
	}
	
	private Carro(int id, int avaliacao, double preco, long km, CategoriaEnum categoria, String marca, String modelo,
			String anoFabricacao, String cor, CombustivelEnum tipoCombustivel, Boolean destaque, Boolean lancamento,
			Boolean oferta) {
		this.id = id;
		this.avaliacao = avaliacao;
		this.preco = preco;
		this.km = km;
		this.categoria = categoria;
		this.marca = marca;
		this.modelo = modelo;
		this.anoFabricacao = anoFabricacao;
		this.cor = cor;
		this.tipoCombustivel = tipoCombustivel;
		this.destaque = destaque;
		this.lancamento = lancamento;
		this.oferta = oferta;
	}
	
	public String toCsv() {
        return id + ";" + avaliacao + ";" + preco + ";" + km + ";" + categoria.ordinal() + ";" + marca + ";" + modelo + ";" + 
               anoFabricacao + ";" + cor + ";" + tipoCombustivel.ordinal() + ";" + (destaque ? 1 : 0) + ";" + 
               (lancamento ? 1 : 0) + ";" + (oferta ? 1 : 0);
    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static int getContador() {
		return contador;
	}

	public int getId() {
		return id;
	}

	public int getAvaliacao() {
		return avaliacao;
	}

	public double getPreco() {
		return preco;
	}

	public long getKm() {
		return km;
	}

	public CategoriaEnum getCategoria() {
		return categoria;
	}

	public CombustivelEnum getTipoCombustivel() {
		return tipoCombustivel;
	}
	
	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public String getAnoFabricacao() {
		return anoFabricacao;
	}

	public String getCor() {
		return cor;
	}


	public Boolean getDestaque() {
		return destaque;
	}

	public Boolean getLancamento() {
		return lancamento;
	}

	public Boolean getOferta() {
		return oferta;
	}
	

	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public void setKm(long km) {
		this.km = km;
	}

	public void setCategoria(CategoriaEnum categoria) {
		this.categoria = categoria;
	}

	public void setTipoCombustivel(CombustivelEnum tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setAnoFabricacao(String anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public void setDestaque(Boolean destaque) {
		this.destaque = destaque;
	}

	public void setLancamento(Boolean lancamento) {
		this.lancamento = lancamento;
	}

	public void setOferta(Boolean oferta) {
		this.oferta = oferta;
	}



	public static class Builder {
		private int id;
		private int avaliacao;
		private double preco;
		private long km;
		private CategoriaEnum categoria;
		private CombustivelEnum tipoCombustivel;
		private String marca;
		private String modelo;
		private String anoFabricacao;
		private String cor;
		private Boolean destaque;
		private Boolean lancamento;
		private Boolean oferta;
		
		public Builder() {
			this.id = 0;
			this.avaliacao = 0;
			this.preco = 0.0;
			this.km = 0;
			this.categoria = null;
			this.marca = "";
			this.modelo = "";
			this.anoFabricacao = "";
			this.tipoCombustivel = null;
			this.destaque = false;
			this.lancamento = false;
			this.oferta = false;
		}
		
		public Builder id(int id) {
			this.id = id;
			return this;
		}
		
		public Builder avaliacao(int avaliacao) {
			this.avaliacao = avaliacao;
			return this;
		}
		
		public Builder preco(double preco) {
			this.preco = preco;
			return this;
		}
		
		public Builder km(long km){
			this.km = km;
			return this;
		}
		
		public Builder categoria(CategoriaEnum categoria) {
			this.categoria = categoria;
			return this;
		}
		
		public Builder marca(String marca) {
			this.marca = marca;
			return this;
		}
		
		public Builder modelo(String modelo) {
			this.modelo = modelo;
			return this;
		}
		
		public Builder anoFabricacao(String anoFabricacao) {
			this.anoFabricacao = anoFabricacao;
			return this;
		}
		
		public Builder cor(String cor) {
			this.cor = cor;
			return this;
		}
		
		public Builder tipoCombustivel(CombustivelEnum tipoCombustivel) {
			this.tipoCombustivel = tipoCombustivel;
			return this;
		}
		
		public Builder destaque(boolean destaque) {
			this.destaque = destaque;
			return this;
		}
		
		public Builder lancamento(boolean lancamento) {
			this.lancamento = lancamento;
			return this;
		}
		
		public Builder oferta(boolean oferta) {
			 this.oferta = oferta;
			 return this;
		}
		
		public Carro build() {
			if(this.id != 0) {
				return new Carro(this.id, this.avaliacao, this.preco, this.km, this.categoria, this.marca, this.modelo, 
						this.anoFabricacao, this.cor, this.tipoCombustivel, this.destaque, this.lancamento, this.oferta);
			}
			
			return new Carro(this.avaliacao, this.preco, this.km, this.categoria, this.marca, this.modelo, 
					this.anoFabricacao, this.cor, this.tipoCombustivel, this.destaque, this.lancamento, this.oferta);
			
		}
	}
	
}
