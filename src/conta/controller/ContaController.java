package conta.controller;

import conta.exception.EntradaInvalidaException;
import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.service.ContaService;
import conta.util.Cores;
import conta.util.Leitura;

public class ContaController {

	private int agencia, numeroConta, tipo, aniversario;
	private String titular;
	private ContaService contaService = new ContaService();
	private Conta conta;

	public void criarConta() {

		agencia = Leitura.lerInteiro("Digite o numero da agencia: ");
		numeroConta = Leitura.lerInteiro("Digite o numero da conta:  ");
		titular = Leitura.lerString("Digite o nome do titular da conta:  ");
		tipo = Leitura.lerInteiro("Digite o tipo da sua conta:\n1 - Conta Corrente | 2 - Conta Poupança\n");

		switch (tipo) {
		case 1 -> conta = (ContaCorrente) new ContaCorrente(numeroConta, agencia, tipo, titular, 0f, 150f);
		case 2 -> {
			aniversario = Leitura.lerInteiro("Digite o dia do aniversário da conta: ");
			conta = (ContaPoupanca) new ContaPoupanca(numeroConta, agencia, tipo, titular, 0f, aniversario);
		}
		default -> throw new EntradaInvalidaException("Tipo de conta inválido!");
		}

		contaService.cadastrar(conta);

		System.out.println(Cores.TEXT_GREEN + "\nConta cadastrada com sucesso!" + Cores.TEXT_RESET);

		conta.visualizar();
	}

	public void listarContas() {
		contaService.listarTodas();
	}

	public void listarContaPorNumero() {
		buscarConta().visualizar();
	}

	public void atualizarDadosConta() {

		conta = buscarConta();

		System.out.println("\nAtualizando dados\n");

		agencia = Leitura.lerInteiro("Digite o numero da nova agencia: ");
		titular = Leitura.lerString("Alterando o nome do titular da conta:  ");

		contaService.atualizar(conta, agencia, titular);

		System.out.println(Cores.TEXT_GREEN + "Dados atualizados com sucesso!");
	}

	public void apagarConta() {

		int opcao;

		conta = buscarConta();

		conta.visualizar();

		System.out.println(Cores.TEXT_RED + "\nTem certeza que deseja excluir a conta ?\n" + Cores.TEXT_RESET);

		opcao = Leitura.lerInteiro("Digite '1 - SIM' ou '2 - NAO': ");

		switch (opcao) {
		case 1 -> {
			contaService.deletar(conta);
			System.out.println(Cores.TEXT_GREEN + "\nConta encerrada com sucesso!");
		}
		case 2 -> System.out.println("Operacao cancelada!");

		default -> System.out.println(Cores.TEXT_RED + "Opcao invalida! Operacao cancelada");
		}
	}

	public void sacar() {
		
		conta = buscarConta();

		conta.visualizar();

		float valorSaque = Leitura.lerFloat("\nDigite o valor que deseja sacar: ");

		contaService.sacar(conta, valorSaque);

		System.out.println(Cores.TEXT_GREEN + "\nSaque realizado com sucesso!\n" + Cores.TEXT_RESET);

		System.out.printf("Novo Saldo: %.2f\n", conta.getSaldo());

	}

	public void depositar() {		
		
		conta = buscarConta();

		conta.visualizar();

		float valorSaque = Leitura.lerFloat("\nDigite o valor que deseja depositar: ");

		contaService.depositar(conta, valorSaque);

		System.out.println(Cores.TEXT_GREEN + "\nDeposito realizado com sucesso!\n" + Cores.TEXT_RESET);

		System.out.printf("Novo Saldo: %.2f\n", conta.getSaldo());
	}
	
	public void transferir() {

		System.out.println("Digite a conta que irá enviar a transferencia: \n");

		Conta contaOrigem = buscarConta();

		contaOrigem.visualizar();

		float valorTransferencia = Leitura.lerFloat("\nDigite o valor que deseja trasnferir: ");

		System.out.println("\nDigite a conta que irá receber a transferencia: \n");

		Conta contaDestino = buscarConta();

		contaDestino.visualizar();

		contaService.transferir(contaOrigem, contaDestino, valorTransferencia);

		System.out.println(Cores.TEXT_GREEN + "Transferencia realizada com sucesso!\n");

	}

	private Conta buscarConta() {
		agencia = Leitura.lerInteiro("Digite o numero da agencia: ");
		numeroConta = Leitura.lerInteiro("Digite o numero da conta:  ");
		return contaService.procurarPorNumero(agencia, numeroConta);
	}

}
