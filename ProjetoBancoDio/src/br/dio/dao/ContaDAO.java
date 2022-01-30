package br.dio.dao;

import java.util.List;
import br.dio.conta.Conta;
import br.dio.conta.ContaCorrente;
import br.dio.conta.ContaPoupanca;
import br.dio.correntista.Correntista;
import br.dio.data.Data;
import br.dio.excecao.OpcaoInvalidaException;
import br.dio.excecao.ValorInvalidoException;

import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;

public class ContaDAO {

	public static void abrirConta(List<Conta> lista) throws OpcaoInvalidaException {
		String nome;
		String cpf;
		String senha;
		int tipo;
		double limite;
		int dia;
		int mes;
		int ano;
		Data dataAniversario;
		Conta conta = null; //vari�vel auxiliar utilizada para poder acessar o n�mero da conta para imprimir no v�deo
		
		tipo = parseInt(showInputDialog("1. Conta Corrente\n2. Conta Poupan�a"));
		if(tipo < 1 || tipo > 2) {
			throw new OpcaoInvalidaException("Tipo de conta inv�lido");
		}
		nome = showInputDialog("Informe o nome do correntista");
		cpf = showInputDialog("Informe o CPF do correntista");
		senha = showInputDialog("Informe a senha da conta");
		Correntista correntista = new Correntista(nome, cpf);
		if(tipo == 1) {
			limite = parseDouble(showInputDialog("Informe o limite"));
			conta = new ContaCorrente(correntista, senha, limite);
			
		}
		else {
			dia = parseInt(showInputDialog("Informe o dia de abertura da conta"));
			mes = parseInt(showInputDialog("Informe o m�s de abertura da conta"));
			ano = parseInt(showInputDialog("Informe o ano de abertura da conta"));
			dataAniversario = new Data(dia, mes, ano);
			conta = new ContaPoupanca(correntista, senha, dataAniversario);
		}
		
		lista.add(conta);
		showMessageDialog(null, "N�mero da sua conta -> "+conta.getNumeroDaConta());
		
	}

	/*como o m�todo pesquisar vai ser utilizado em outro momento no programa, alterei o m�todo
	 * para retornar a posi��o do objeto dentro do ArrayList. Dessa forma, os m�todos que precisarem
	 * do m�todo pesquisar v�o receber a posi��o do objeto encontrado e cada um far� sua l�gica espec�fica
	 */
	public static void pesquisar(List<Conta> lista) {
		Conta conta = pesquisarAux(lista);
		String aux = "";
		if(conta == null) {
			showMessageDialog(null,"Correntista n�o encontrado");
		}
		else {
			aux += "N�mero da conta -> "+conta.getNumeroDaConta()+"\n";
			aux += "Correntista -> "+conta.getCorrentista().getNome()+"\n";
			if(conta instanceof ContaCorrente) {
				aux += "Conta do tipo CORRENTE";
			}
			else {
				aux += "Conta do tipo POUPAN�A";
			}
			showMessageDialog(null, aux);
		}
		
		
	}
	
	//m�todo auxiliar que retorna a posi��o do objeto no ArrayList
	private static Conta pesquisarAux(List<Conta> lista) {
		Conta contaAux = null;
		String cpf = showInputDialog("Informe o CPF a ser pesquisado");
		
		for(Conta conta : lista) {
			if(conta.getCorrentista().getCpf().equals(cpf)) {
				contaAux = conta;
				break;
			}
		}
		return contaAux;
	}

	public static Conta acessarConta(List<Conta> lista) {
		Conta contaAux = null;
		int numeroDaConta = parseInt(showInputDialog("Informe o n�mero da conta"));
		String senha = showInputDialog("Informe a senha");
		for(Conta conta : lista) {
			if(conta.getNumeroDaConta() == numeroDaConta && conta.getSenha().equalsIgnoreCase(senha)) {
				contaAux = conta;
				break;
			}
		}
		return contaAux;
	}

	public static void sacar(Conta conta) throws ValorInvalidoException {
		double valor = parseDouble(showInputDialog("Informe o valor a ser sacado"));
		if(valor <= 0) {
			throw new ValorInvalidoException("Valor deve ser positivo");
		}
		conta.sacar(valor);		
	}

	public static void depositar(Conta conta) throws ValorInvalidoException {
		double valor = parseDouble(showInputDialog("Informe o valor a ser depositado"));
		if(valor <= 0) {
			throw new ValorInvalidoException("Valor deve ser positivo");
		}
		conta.depositar(valor);		
		
	}

	public static void transferir(Conta conta, List<Conta> lista) throws ValorInvalidoException {
		//pesquisar os dados da conta de destino. Pesquisa por CPF do correntista
		Conta destino = pesquisarAux(lista);
		if(destino == null) {
			showMessageDialog(null, "Conta de destino n�o encontrada");
		}
		else {
			double valor = parseDouble(showInputDialog("Informe o valor a ser transferido"));
			if(valor <= 0) {
				throw new ValorInvalidoException("Valor deve ser positivo");
			}
			conta.transferir(destino, valor);
		}
		
	}

	public static void consultarSaldo(Conta conta) {
		showMessageDialog(null, "Saldo em conta -> R$ "+String.format("%.2f", conta.getSaldo()));
		
	}

	public static void fecharConta(Conta conta, List<Conta> lista, List<Conta> listaInativa) {
		String aux = "";
		aux += "N�mero da conta -> "+conta.getNumeroDaConta()+"\n";
		aux += "Correntista -> "+conta.getCorrentista().getNome()+"\n";
		int resp = showConfirmDialog(null, "Tem certeza que deseja fechar a conta?\n\n"+aux);
		if(resp == 0) {
			listaInativa.add(conta);
			lista.remove(conta);
			showMessageDialog(null,"Conta fechada! Foi um prazer atend�-lo!");
		}
		
	}
}
