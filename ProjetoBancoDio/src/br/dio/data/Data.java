package br.dio.data;

public class Data {
	
	// atributos
	private int dia;
	private int mes;
	private int ano;

	// construtor para inicializar todos os atributos
	public Data(int dia, int mes, int ano) {
		super();
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	}

	// sobrescrevendo toString() de Object
	public String toString() {
		return dia+"/"+mes+"/"+ano;
	}
	
	// métodos get e set para todos os atributos private
	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}
	
}
