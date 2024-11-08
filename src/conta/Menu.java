package conta;

import java.util.Scanner;
import conta.util.Cores;
import conta.model.Conta;;

public class Menu {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int opcao;
		
		while(true) {
			System.out.print(Cores.TEXT_CYAN + """
			***************************************************
					BANCO DO BRASIL COM Z
			***************************************************
				0 - Testes
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
			System.out.println();
			
			if(opcao == 9) {
				System.out.println("\nBanco do Brazil com Z - O seu Futuro começa aqui!");
				sobre();
				scan.close();
				break;	
			}

			switch (opcao) {
			
				case 1 -> System.out.println("Criar Conta\n");

				case 2 -> System.out.println("Listar todas as Contas\n");

				case 3 -> System.out.println("Consultar dados da Conta - por número\n");
					
				case 4 -> System.out.println("Atualizar dados da Conta\n");

				case 5 -> System.out.println("Apagar a Conta\n");

				case 6 -> System.out.println("Saque\n");

				case 7 -> System.out.println("Depósito\n");

				case 8 -> System.out.println("Transferência entre Contas\n");
				case 0 -> testes();

				default -> System.out.println("\nOpção Inválida!\n");
					
			}
			
		}
		
	}
	public static void testes() {
	
		Conta conta = new Conta(2575, 47166, 1, "Andre Lins", 2400.00f);
		System.out.println("Conta criada");
		conta.visualizar();
		System.out.printf("\nSacando: %.2f\n", 130.0f);
		conta.sacar(130.0f);
		System.out.printf("\nNovo saldo: %.2f\n", conta.getSaldo());
		conta.visualizar();
		System.out.printf("\nDepositando: %.2f\n", 600.0f);
		conta.depositar(600f);
		System.out.printf("\nNovo saldo: %.2f", conta.getSaldo());
		conta.visualizar();
		
	
	}
	
	public static void sobre() {
		
		System.out.println(Cores.TEXT_RED  + """
		\n***************************************************
		Projeto Desenvolvido por: Andre de Brito Lins
		Generation Brasil - generation@generation.org
		github.com/conteudoGeneration
		****************************************************
		""");
		

	}
	
}
