package conta.util;

import java.util.Scanner;
import conta.exception.EntradaInvalidaException;
import conta.exception.EntradaVaziaException;

public class Leitura {

	private static Scanner scan = new Scanner(System.in);

	private static String leitura(String mensagem) {
		System.out.printf(Cores.ANSI_BLACK_BACKGROUND + Cores.TEXT_YELLOW + mensagem + Cores.TEXT_RESET);
		return scan.nextLine();
	}
	
	
	public static int lerInteiro(String mensagem) {

		try {
			return Integer.parseInt(leitura(mensagem));
		} catch (NumberFormatException e) {
			throw new EntradaInvalidaException();
		}

	}

	public static float lerFloat(String mensagem) {

		float numero;
		
		try {
			numero = Float.parseFloat(leitura(mensagem));
		} catch (NumberFormatException e) {
			throw new EntradaInvalidaException();
		}
		
		if(numero < 0.0f) {
			throw new EntradaInvalidaException(numero);
		}
		
		return numero;

	}

	public static String lerString(String mensagem) {
		
		String variavel = leitura(mensagem);
		
		if (variavel.isEmpty()) {
			throw new EntradaVaziaException();
		}
		return variavel;
		
	}
	public static void voltarMenu() {
		scan.nextLine();
	}

}
