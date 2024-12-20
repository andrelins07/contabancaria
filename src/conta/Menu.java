package conta;

import conta.util.Cores;
import conta.util.Leitura;
import conta.controller.ContaController;
import conta.exception.RegraDeNegocioException;

public class Menu {

	public static void main(String[] args) {

		ContaController contaController = new ContaController();

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
					9 - Consultar Extrato
					0 - Sair
					***************************************************
					""" + Cores.TEXT_RESET);
			try {
				
				int opcao = Leitura.lerInteiro(Cores.ANSI_BLACK_BACKGROUND + Cores.TEXT_YELLOW + "Digite a opcao Desejada: " + Cores.TEXT_RESET);
				System.out.println();
				if (opcao == 0) {
					System.out.println("\nBanco do Brazil com Z - O seu Futuro começa aqui!");
					sobre();
					break;
				}

				switch (opcao) {

				case 1 -> contaController.criarConta();

				case 2 -> contaController.listarContas();

				case 3 -> contaController.listarContaPorNumero();

				case 4 -> contaController.atualizarDadosConta();

				case 5 -> contaController.apagarConta();

				case 6 -> contaController.sacar();

				case 7 -> contaController.depositar();

				case 8 -> contaController.transferir();
				
				case 9 -> contaController.imprimirExtrato();

				default -> System.out.println("\nOpção Inválida!\n");

				}
			} catch (RegraDeNegocioException exception) {
				System.out.println(Cores.TEXT_RED + "Erro: " + exception.getMessage().toUpperCase());
				System.out.println("Pressione ENTER para voltar ao menu");
				Leitura.voltarMenu();
			}

		}

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
