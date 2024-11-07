package conta;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import conta.util.Cores;
import conta.exception.RegraDeNegocioException;
import conta.model.Conta;

public class Menu {

	public static Scanner scan = new Scanner(System.in);
	public static List<Conta> contas = new ArrayList<Conta>();
	public static int agencia, numeroConta;

	public static void main(String[] args) {

		int opcao;

		while (true) {
			System.out.print(Cores.TEXT_YELLOW + """
					***************************************************
							BANCO DO BRASIL COM Z
					***************************************************
					1 - Criar Conta
					2 - Listar todas as contas
					3 - Bucar Conta por numero
					4 - Aualizar dados da conta
					5 - Apagar Conta
					6 - Sacar
					7 - Depositar
					8 - Transferir valores entre contas
					9 - Sair
					***************************************************
					""" + Cores.TEXT_RESET);

			System.out.print("Digite a opcao Desejada: ");
			opcao = scan.nextInt();
			scan.skip("\\R?");
			System.out.println();

			if (opcao == 9) {
				System.out.println("\nBanco do Brazil com Z - O seu Futuro começa aqui!");
				sobre();
				scan.close();
				break;
			}

			try {

				switch (opcao) {

					case 1 -> {
						criarConta();
						System.out.println(Cores.TEXT_GREEN + "Conta cadastrada com sucesso!");
					}
	
					case 2 -> listarContas();
	
					case 3 -> {
						input();
						listarContaPorNumero(agencia, numeroConta).visualizar();;			
					}
					case 4 -> {
						input();
						atualizarDados();
					}
	
					case 5 -> {
						input();
						apagar(agencia, numeroConta);
					}
	
					case 6 -> {
						input();
						float valorSaque = Float.parseFloat(ler("Digite o valor que deseja sacar: "));
	
						if (valorSaque < 0)
							throw new RegraDeNegocioException("O valor do Saque não pode ser negativo!");
	
						sacar(agencia, numeroConta, valorSaque);
						System.out.println(Cores.TEXT_GREEN + "Saque Realizado com sucesso!");
					}
	
					case 7 -> {
						
						input();
						float valorDeposito = Float.parseFloat(ler("Digite o valor que deseja depositar: "));
	
						if (valorDeposito < 0)
							throw new RegraDeNegocioException("O valor do deposito não pode ser negativo!");
	
						depositar(agencia, numeroConta, valorDeposito);
						System.out.println(Cores.TEXT_GREEN + "Deposito Realizado com sucesso!");
					}
	
					case 8 -> {
						
						float valorTransferencia = Float.parseFloat(ler("Digite o valor que deseja transferir: "));
	
						if (valorTransferencia < 0)
							throw new RegraDeNegocioException("O valor da transferencia não pode ser negativo!");
						
						System.out.println("CONTA REMETENTE");
						input();
						Conta contaSaida = listarContaPorNumero(agencia, numeroConta);
						
						System.out.printf("\nConta: %d | Agencia: %d | Saldo: %.2f | Titular: %s\n\n", 
								contaSaida.getNumero(), contaSaida.getAgencia(), contaSaida.getSaldo(), contaSaida.getTitular());
					
					
						System.out.println("CONTA DESTINO");
						input();
						
						Conta contaDestino = listarContaPorNumero(agencia, numeroConta);
						
						
						System.out.printf("\nConta: %d | Agencia: %d | Saldo: %.2f | Titular: %s\n", 
								contaDestino.getNumero(), contaDestino.getAgencia(), contaDestino.getSaldo(), contaDestino.getTitular());
						
						transferir(contaSaida, contaDestino, valorTransferencia);
					}
	
					default -> System.out.println("\nOpção Inválida!\n");

				}
			} catch (RegraDeNegocioException exception) {
				System.out.println(Cores.TEXT_RED + "Erro: " + exception.getMessage().toUpperCase());
				System.out.println("Pressione ENTER para voltar ao menu");
				scan.nextLine();
			}

		}

	}
	public static void input() {
		
		try {
			agencia = Integer.parseInt(ler("Digite o numero da agencia: "));
			numeroConta = Integer.parseInt(ler("Digite o numero da conta: "));
		} catch (NumberFormatException exception) {
			throw new RegraDeNegocioException("Entrada de dados invalida!");
		}
	}

	public static void criarConta() {

		int agencia, numero, tipo;	
		
		try {
			agencia = Integer.parseInt(ler("Digite a sua agência: "));
			numero = Integer.parseInt(ler("Digite o numero da conta: "));
			tipo = Integer.parseInt(ler("Digite o tipo da sua conta:\n1 - Conta Corrente\n2 - Conta Poupança\n"));
		} catch (NumberFormatException exception) {
			throw new RegraDeNegocioException("Entrada de dados invalida!");
		}
		
		String titular = ler("Digite o nome do titular da conta: ");
		
		Conta conta = new Conta(numero, agencia, tipo, titular, 1000f);
		
		if(contas.contains(conta)) {
			throw new RegraDeNegocioException("Conta já cadastrada!");
		}
		
		conta.visualizar();
		
		contas.add(conta);

	}

	public static void listarContas() {

		if (contas.isEmpty())
			throw new RegraDeNegocioException("Nenhuma conta cadastrada!");
		
		System.out.println("\nTotal de Contas Cadastradas: " + contas.size());
		contas.forEach(conta -> conta.visualizar());

	}

	public static Conta listarContaPorNumero(int agencia, int numeroConta) {

		for (Conta conta : contas) {
			if (conta.getNumero() == numeroConta && conta.getAgencia() == agencia) {
				return conta;
			}
		}
		throw new RegraDeNegocioException("Conta não encontrada!");
	}
	
	public static void atualizarDados() {
		
		Conta conta = listarContaPorNumero(agencia, numeroConta);
		int tipo;
		String titular;
		
		System.out.println("\nAtualizando dados");
		
		try {
			agencia = Integer.parseInt(ler("Digite o numero da agencia: "));
			tipo = Integer.parseInt(ler("Digite o tipo da conta: "));
			titular = ler("Digite o nome do titular: ");
			
			if(titular.isEmpty()) {
				throw new RegraDeNegocioException("Nome do titular não pode estar vazio!");
			}

		}catch(RegraDeNegocioException | NumberFormatException e) {
		
			throw new RegraDeNegocioException(e.getMessage());
		}
		
		conta.atualizar(agencia, tipo, titular);
		System.out.println(Cores.TEXT_GREEN + "Dados atualizados com sucesso!");
		
	}
	
	public static boolean apagar(int agencia, int numeroConta) {
		
		Conta conta = listarContaPorNumero(agencia, numeroConta);
		
		System.out.printf("\nConta: %d | Agencia: %d | Saldo: %.2f | Titular: %s\n", conta.getNumero(), conta.getAgencia(), conta.getSaldo(), conta.getTitular());
		
		if(conta.getSaldo() > 0.01f) {
			throw new RegraDeNegocioException("A conta não pode ser encerrada. Saque todo valor e tente novamente!");
		}
		System.out.printf(Cores.TEXT_RED + "\nTem certeza que deseja excluir a conta ?" + Cores.TEXT_RESET+
				"\nConta: %d | Agencia: %d | Titular: %s\n\n", conta.getNumero(), conta.getAgencia(), conta.getTitular());
		
		switch(Integer.parseInt(ler("Digite '1 - SIM' ou '2 - NAO': "))) {
			
			case 1-> {
				contas.remove(conta);
				System.out.println(Cores.TEXT_GREEN + "Conta encerrada com sucesso!");
				return true;
			}
			case 2 -> {
				System.out.println("Operacao cancelada!");
				return false;	
			}
			default -> {
				System.out.println(Cores.TEXT_RED + "Opcao invalida! Operacao cancelada");
				return false;
			}
		}
	}
	public static boolean sacar(int agencia, int numeroConta, float valor) {

		Conta conta = listarContaPorNumero(agencia, numeroConta);
		
		System.out.printf("\nConta: %d | Agencia: %d | Saldo: %.2f | Titular: %s\n", 
				conta.getNumero(), conta.getAgencia(), conta.getSaldo(), conta.getTitular());
		
		if (conta.getSaldo() >= valor) {
			conta.sacar(valor);
			return true;
		}
		System.out.printf("\nSaldo Atual: %.2f | Valor Solicitado: %.2f\n\n", conta.getSaldo(), valor);
		throw new RegraDeNegocioException("Saque não realizado. Saldo insulficiente!");
	}
	
	public static void depositar(int agencia, int numeroConta, float valor) {
		
		Conta conta = listarContaPorNumero(agencia, numeroConta);
		
		System.out.printf("\nConta: %d | Agencia: %d | Saldo: %.2f | Titular: %s\n", 
				conta.getNumero(), conta.getAgencia(), conta.getSaldo(), conta.getTitular());
		
		conta.depositar(valor);
			
	}
	
	public static void transferir(Conta saida, Conta destino, float valor) {
		
		sacar(saida.getAgencia(), saida.getNumero(), valor);
		depositar(destino.getAgencia(), destino.getNumero(), valor);
		
	}

	public static String ler(String mensagem) {
		System.out.printf(mensagem);
		return scan.nextLine();
	}

	public static void sobre() {

		System.out.println(Cores.TEXT_BLUE + """
		\n***************************************************
		Projeto Desenvolvido por: Andre de Brito Lins
		Generation Brasil - generation@generation.org
		github.com/conteudoGeneration
		****************************************************
		""");

	}
}
