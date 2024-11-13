package conta;

import java.util.ArrayList;

import conta.model.Conta;
import conta.util.ManipularJson;

public class Teste {

	public static void main(String[] args) {

			
		ManipularJson manipularJson = new ManipularJson();
		ArrayList<Conta> contas = manipularJson.carregarList();
		
		contas.forEach(c->c.visualizar());
		
		manipularJson.escreverJson(contas);
		
		ArrayList<Conta> contas2 = manipularJson.carregarList();
		
	}
}
