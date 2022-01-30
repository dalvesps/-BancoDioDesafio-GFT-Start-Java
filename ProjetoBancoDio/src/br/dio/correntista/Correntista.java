package br.dio.correntista;

public class Correntista {
	
	//atributos
	private String nome;
	private String cpf;
	
	//construtor para inicializar todos os atributos
	public Correntista(String nome, String cpf) {
		super();
		this.nome = nome;
		this.cpf = cpf;
	}
	
	//sobrescrevendo toString() de Object
	public String toString() {
		String aux = "";
		aux += "Nome -> "+nome+"\n";
		aux += "CPF -> "+cpf+"\n";
		return aux;
	}

	//m�todos get e set para todos os atributos private
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}	

}
