package conta.controller;

import java.util.Scanner;
import conta.exception.RegraDeNegocioException;
import conta.model.Conta;
import conta.service.ContaService;
import conta.util.Cores;

public class ContaController {

	private Scanner scan;
	private int agencia, numeroConta, tipo;
	private ContaService contaService = new ContaService();
	private Conta conta;

	public ContaController(Scanner scanner) {
		this.scan = scanner;
	}

	public void criarConta() {

		try {
			lerAgenciaEConta();
			tipo = Integer.parseInt(leitura("Digite o tipo da sua conta:\n1 - Conta Corrente | 2 - Conta Poupança\n"));

		} catch (NumberFormatException exception) {
			throw new RegraDeNegocioException("Entrada de dados invalida! Digite apenas numeros.");
		}
		String titular = leitura("Digite o nome do titular da conta:  ");
		
		conta = new Conta(numeroConta, agencia, tipo, titular, 1000f);
	
		contaService.criarConta(conta);

		System.out.println(Cores.TEXT_GREEN + "\nConta cadastrada com sucesso!" + Cores.TEXT_RESET);
		
		conta.visualizar();
	}

	public void listarContas() {
		contaService.listarContas();
	}

	public void listarContaPorNumero() {
		buscarConta().visualizar();
	}

	public void atualizarDadosConta() {

		Conta conta = buscarConta();

		try {
			agencia = Integer.parseInt(leitura("Digite o numero da agencia: "));
			tipo = Integer.parseInt(leitura("Digite o tipo da conta: "));
		} catch (NumberFormatException e) {
			throw new RegraDeNegocioException("Entrada de dados invalida! Digite apenas numeros.");
		}
		String titular = leitura("Digite o nome do titular: ");

		contaService.atualizarDadosConta(conta, agencia, tipo, titular);

		System.out.println(Cores.TEXT_GREEN + "Dados atualizados com sucesso!");
	}

	public void apagarConta() {

		int opcao;

		Conta conta = buscarConta();

		System.out.printf("\nConta: %d | Agencia: %d | Saldo: %.2f | Titular: %s\n", conta.getNumero(),
				conta.getAgencia(), conta.getSaldo(), conta.getTitular());

		System.out.printf(
				Cores.TEXT_RED + "\nTem certeza que deseja excluir a conta ?" + Cores.TEXT_RESET
						+ "\nConta: %d | Agencia: %d | Titular: %s\n\n",
				conta.getNumero(), conta.getAgencia(), conta.getTitular());

		try {
			opcao = Integer.parseInt(leitura("Digite '1 - ' ou '2 - NAO':\n"));
		} catch (NumberFormatException e) {
			throw new RegraDeNegocioException("Entrada de dados invalida! Digite apenas numeros.");
		}

		switch (opcao) {
		case 1 -> {
			contaService.apagarConta(conta);
			System.out.println(Cores.TEXT_GREEN + "\nConta encerrada com sucesso!");
		}
		case 2 -> System.out.println("Operacao cancelada!");

		default -> System.out.println(Cores.TEXT_RED + "Opcao invalida! Operacao cancelada");
		}
	}

	public void sacar() {
		alterarSaldo("sacar", "\nDigite o valor que deseja sacar: ", "\nSaque realizado com sucesso!\n");
	}

	public void depositar() {
		alterarSaldo("depositar", "\nDigite o valor que deseja depositar: ", "\nDeposito realizado com sucesso!\n");
	}

	public void alterarSaldo(String operacao, String perguntaUsuario, String statusOperacao) {
		
		Conta conta = buscarConta();

		System.out.printf("\nConta: %d | Agencia: %d | Saldo: %.2f | Titular: %s\n", conta.getNumero(), conta.getAgencia(), conta.getSaldo(), conta.getTitular());
		
		float valorSaque = lerValorDesejado(perguntaUsuario);
		
		if(operacao.equals("depositar")) contaService.depositar(conta,valorSaque);
		else contaService.sacar(conta, valorSaque);
		
		System.out.println(Cores.TEXT_GREEN + statusOperacao + Cores.TEXT_RESET);
		
		System.out.printf("Novo Saldo: %.2f\n", conta.getSaldo());
		
	}
	
	public void transferir() {
		
		System.out.println("Digite a conta que irá enviar a transferencia: \n");
		
		Conta contaSaida = buscarConta();

		System.out.printf("\nConta: %d | Agencia: %d | Saldo: %.2f | Titular: %s\n", contaSaida.getNumero(),
				contaSaida.getAgencia(), contaSaida.getSaldo(), contaSaida.getTitular());
		
		float valorTransferencia = lerValorDesejado("\nDigite o valor que deseja trasnferir: ");
		
		
		System.out.println("\nDigite a conta que irá receber a transferencia: \n");
		
		Conta contaRecebimento = buscarConta();

		System.out.printf("\nConta: %d | Agencia: %d | Saldo: %.2f | Titular: %s\n", contaRecebimento.getNumero(),
				contaRecebimento.getAgencia(), contaRecebimento.getSaldo(), contaRecebimento.getTitular());
		
		contaService.transferir(contaSaida, contaRecebimento, valorTransferencia);
		
		System.out.println(Cores.TEXT_GREEN + "Transferencia realizada com sucesso!\n");

	}

	private String leitura(String mensagem) {
		System.out.printf(Cores.ANSI_BLACK_BACKGROUND + Cores.TEXT_YELLOW + mensagem + Cores.TEXT_RESET);
		return scan.nextLine();
	}

	private void lerAgenciaEConta() {

		try {
			agencia = Integer.parseInt(leitura("Digite o numero da agencia:  "));
			numeroConta = Integer.parseInt(leitura("Digite o numero da conta:  "));
		} catch (NumberFormatException exception) {
			throw new RegraDeNegocioException("Entrada de dados invalida! Digite apenas numeros.");
		}
	}

	private Conta buscarConta() {
		lerAgenciaEConta();
		return contaService.listarContaPorNumero(agencia, numeroConta);
	}

	private float lerValorDesejado(String mensagem) {

		float valorDeposito = Float.parseFloat(leitura(mensagem));

		if (valorDeposito < 0)
			throw new RegraDeNegocioException("O valor não pode ser negativo!");

		return valorDeposito;
	}
	
}
