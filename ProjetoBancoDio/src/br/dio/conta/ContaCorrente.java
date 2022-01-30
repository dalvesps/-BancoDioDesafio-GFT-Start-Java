package br.dio.conta;

import br.dio.atualizar.AtualizarLimite;
import br.dio.correntista.Correntista;

public class ContaCorrente extends Conta implements AtualizarLimite {

	//atributos
	private double limite;
	
	//construtor para inicializar os atributos
	public ContaCorrente(Correntista correntista, String senha, double limite) {
		super(correntista, senha);
		this.limite = limite;
	}
	
	//sobrescrevendo o m�todo da interface
	@Override
	public void atualizarLimite(double porcentagem) {
		this.limite *= (1+porcentagem/100);

	}
	
	//m�todos get para o atributo limite
	//n�oo tem sentido ter o m�todo set para o usu�rio alterar o limite
	public double getLimite() {
		return limite;
	}

}
