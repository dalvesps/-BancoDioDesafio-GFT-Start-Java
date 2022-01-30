package br.dio.conta;

import java.util.Random;

import br.dio.correntista.Correntista;

public abstract class Conta {

	// atributos
	private int numeroDaConta;
	private Correntista correntista;
	private String senha;
	private double saldo;

	// construtor para inicializar todos os atributos
	public Conta(Correntista correntista, String senha) {
		this(correntista, senha, 0);
	}

	public Conta(Correntista correntista, String senha, double saldo) {
		Random gerador = new Random();
		this.numeroDaConta = gerador.nextInt(9000)+1000;
		this.correntista = correntista;
		this.senha = senha;
		this.saldo = saldo;
	}

	// sobrescrevendo toString() da classe Object
	public String toString() {
		String aux = "";
		aux += "N�mero da conta -> " + numeroDaConta + "\n";
		aux += correntista.toString();
		aux += "Saldo -> " + String.format("%.2f", saldo);
		return aux;
	}

	// m�todo para sacar um valor da conta
	public void sacar(double valor) {
		this.saldo -= valor;
	}

	// m�todo para depositar um valor da conta
	public void depositar(double valor) {
		this.saldo += valor;
	}

	// m�todo para transferir um valor de uma conta para outra
	public void transferir(Conta conta, double valor) {
		sacar(valor);
		conta.depositar(valor);
	}

	// m�todos get e set para todos os atributos private
	public int getNumeroDaConta() {
		return numeroDaConta;
	}

	public String getSenha() {
		return senha;
	}

	public Correntista getCorrentista() {
		return correntista;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
}
