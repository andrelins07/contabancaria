package conta.controller;

import conta.model.Conta;
import conta.service.ContaService;
import conta.util.Cores;
import conta.util.Leitura;

public class ContaController {

	private int agencia, numeroConta, tipo;
	private ContaService contaService = new ContaService();
	private Conta conta;

	public void criarConta() {

		agencia = Leitura.lerInteiro("Digite o numero da agencia: ");
		numeroConta = Leitura.lerInteiro("Digite o numero da conta:  ");
		
		String titular = Leitura.lerString("Digite o nome do titular da conta:  ");
		tipo = Leitura.lerInteiro("Digite o tipo da sua conta:\n1 - Conta Corrente | 2 - Conta Poupança\n");

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
		
		System.out.println("\nAtualizando dados\n");
		
		agencia = Leitura.lerInteiro("Digite o numero da nova agencia: ");
		String titular = Leitura.lerString("Alterando o nome do titular da conta:  ");
		tipo = Leitura.lerInteiro("Alterando o tipo da sua conta:\n1 - Conta Corrente | 2 - Conta Poupança\n");
		
		contaService.atualizarDadosConta(conta, agencia, tipo, titular);

		System.out.println(Cores.TEXT_GREEN + "Dados atualizados com sucesso!");
	}

	public void apagarConta() {

		int opcao;

		Conta conta = buscarConta();

		System.out.printf("\nConta: %d | Agencia: %d | Saldo: %.2f | Titular: %s\n", conta.getNumero(),
				conta.getAgencia(), conta.getSaldo(), conta.getTitular());

		System.out.println(Cores.TEXT_RED + "\nTem certeza que deseja excluir a conta ?\n" + Cores.TEXT_RESET);
			
	
		opcao = Leitura.lerInteiro("Digite '1 - SIM' ou '2 - NAO': ");
	
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

	private void alterarSaldo(String operacao, String perguntaUsuario, String statusOperacao) {

		Conta conta = buscarConta();

		System.out.printf("\nConta: %d | Agencia: %d | Saldo: %.2f | Titular: %s\n", conta.getNumero(),
				conta.getAgencia(), conta.getSaldo(), conta.getTitular());

		float valorSaque = Leitura.lerFloat(perguntaUsuario);

		if (operacao.equals("depositar"))
			contaService.depositar(conta, valorSaque);
		else
			contaService.sacar(conta, valorSaque);

		System.out.println(Cores.TEXT_GREEN + statusOperacao + Cores.TEXT_RESET);

		System.out.printf("Novo Saldo: %.2f\n", conta.getSaldo());

	}

	public void transferir() {

		System.out.println("Digite a conta que irá enviar a transferencia: \n");

		Conta contaSaida = buscarConta();

		System.out.printf("\nConta: %d | Agencia: %d | Saldo: %.2f | Titular: %s\n", contaSaida.getNumero(),
				contaSaida.getAgencia(), contaSaida.getSaldo(), contaSaida.getTitular());

		float valorTransferencia = Leitura.lerFloat("\nDigite o valor que deseja trasnferir: ");

		System.out.println("\nDigite a conta que irá receber a transferencia: \n");

		Conta contaRecebimento = buscarConta();

		System.out.printf("\nConta: %d | Agencia: %d | Saldo: %.2f | Titular: %s\n", contaRecebimento.getNumero(),
				contaRecebimento.getAgencia(), contaRecebimento.getSaldo(), contaRecebimento.getTitular());

		contaService.transferir(contaSaida, contaRecebimento, valorTransferencia);

		System.out.println(Cores.TEXT_GREEN + "Transferencia realizada com sucesso!\n");

	}
	
	private Conta buscarConta() {
		agencia = Leitura.lerInteiro("Digite o numero da agencia: ");
		numeroConta = Leitura.lerInteiro("Digite o numero da conta:  ");
		return contaService.listarContaPorNumero(agencia, numeroConta);
	}

}
