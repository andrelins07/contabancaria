package conta;

import java.util.Scanner;

import conta.exception.EntradaInvalidaException;
import conta.util.Leitura;

public class Testes {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numero = Leitura.lerInteiro("Digite o numero: ");
		
		if (numero != 1 && numero != 2) {
			throw new EntradaInvalidaException("Tipo de conta inválido!");
		}
	}

}
