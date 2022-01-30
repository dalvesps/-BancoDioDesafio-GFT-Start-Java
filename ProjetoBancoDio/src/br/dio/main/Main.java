package br.dio.main;

import static java.lang.Integer.parseInt;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import java.util.ArrayList;
import java.util.List;

import br.dio.conta.Conta;
import br.dio.dao.ContaDAO;
import br.dio.excecao.OpcaoInvalidaException;
import br.dio.excecao.ValorInvalidoException;

public class Main {
	public static void main(String[] args) {
		int opcao = 0;
		List<Conta> lista = new ArrayList<>();
		List<Conta> listaInativa = new ArrayList<>();
		Conta conta;

		do {
			try {
				opcao = parseInt(showInputDialog(getMenu1()));
				if (opcao < 1 || opcao > 4) {
					throw new OpcaoInvalidaException("Op��o Inv�lida");
				}
				switch (opcao) {
				case 1:
					ContaDAO.abrirConta(lista);
					break;
				case 2:
					ContaDAO.pesquisar(lista);
					break;
				case 3:
					conta = ContaDAO.acessarConta(lista);
					if (conta == null) {
						showMessageDialog(null, "Usu�rio ou senha inv�lidos");
					} else {
						exibirMenu2(conta, lista, listaInativa);
					}
					break;
				}
			} catch (NumberFormatException e) {
				showMessageDialog(null, "Voc� deve digitar um n�mero\n" + e);
			} catch (OpcaoInvalidaException e) {
				showMessageDialog(null, e);
			}
		} while (opcao != 4);

	}

	private static void exibirMenu2(Conta conta, List<Conta> lista, List<Conta> listaInativa) {
		int opcao = 0;

		do {
			try {
				opcao = parseInt(showInputDialog(getMenu2()));
				if (opcao < 1 || opcao > 6) {
					throw new OpcaoInvalidaException("Op��o Inv�lida");
				}
				switch (opcao) {
				case 1:
					ContaDAO.sacar(conta);
					break;
				case 2:
					ContaDAO.depositar(conta);
					break;
				case 3:
					ContaDAO.transferir(conta, lista);
					break;
				case 4:
					ContaDAO.consultarSaldo(conta);
					break;
				case 5:
					ContaDAO.fecharConta(conta, lista, listaInativa);
					opcao = 6;
					break;
					
				}
			} catch (OpcaoInvalidaException e) {
				showMessageDialog(null, e);
			} catch (ValorInvalidoException e) {
				showMessageDialog(null, e);
			}

		} while (opcao != 6);

	}

	private static String getMenu1() {
		String aux = "";
		aux += "1. Abrir Conta\n";
		aux += "2. Pesquisar Conta\n";
		aux += "3. Acessar Conta\n";
		aux += "4. Finalizar o Sistema";
		return aux;
	}

	private static String getMenu2() {
		String aux = "";
		aux += "1. Sacar\n";
		aux += "2. Depositar\n";
		aux += "3. Transferir\n";
		aux += "4. Consultar Saldo\n";
		aux += "5. Fechar Conta\n";
		aux += "6. Sair";
		return aux;
	}
}
