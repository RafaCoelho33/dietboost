package model;

public class Alimento {
	private int id;
	private String nome;
	private float gorduras;
	private float carboidratos;
	private float proteinas;
	private float calorias;

	public Alimento() {
		id = -1;
		nome = "";
		gorduras = 0.01F;
		carboidratos = 0.01F;
		proteinas = 0.01F;
		calorias = 0.01F;
	}

	public Alimento(int id, String nome, float gorduras, float carboidratos, float proteinas, float calorias) {
		setId(id);
		setNome(nome);
		setGorduras(gorduras);
		setCarboidratos(carboidratos);
		setProteinas(proteinas);
		setCalorias(calorias);
	}		
	
	public int getID() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	public String getNome() {
		return nome;
	}
	public void setNome (String nome) {
		this.nome = nome;
	}


	public float getGorduras() {
		return gorduras;
	}
	public void setGorduras(float gorduras) {
		this.gorduras = gorduras;
	}

	public float getCarboidratos() {
		return carboidratos;
	}
	public void setCarboidratos(float carboidratos) { this.carboidratos = carboidratos; }
	
	public float getProteinas() {
		return proteinas;
	}
	public void setProteinas (float proteinas) { this.proteinas = proteinas; }

	public float getCalorias() {
		return calorias;
	}
	public void setCalorias (float calorias) { this.calorias = calorias; }

	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */

	@Override
	public String toString() {
		return "Alimento: " + nome + "   ID: " + id + "   Gorduras: " + gorduras + "   Carboidratos: "
				+ carboidratos  + "   Proteinas: " + proteinas + "   Calorias: " + calorias;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getID() == ((Alimento) obj).getID());
	}	
}