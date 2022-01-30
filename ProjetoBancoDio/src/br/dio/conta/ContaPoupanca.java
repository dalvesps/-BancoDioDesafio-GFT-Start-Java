package br.dio.conta;

import br.dio.atualizar.AtualizarSaldo;
import br.dio.correntista.Correntista;
import br.dio.data.Data;

public class ContaPoupanca extends Conta implements AtualizarSaldo {

	// atributos
	private Data dataAniversario;

	// construtor para inicializar os atributos
	public ContaPoupanca(Correntista correntista, String senha, Data dataAniversario) {
		super(correntista, senha);
		this.dataAniversario = dataAniversario;
	}

	// sobrescrevendo o método da interface
	@Override
	public void atualizarSaldo(double porcentagem) {
		if(dataAniversario.getDia() == 10) {
			setSaldo(getSaldo()*(1+porcentagem/100));
		}
	}

	public Data getDataAniversario() {
		return dataAniversario;
	}

	public void setDataAniversario(Data dataAniversario) {
		this.dataAniversario = dataAniversario;
	}
	
}
