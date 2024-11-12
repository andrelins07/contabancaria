package conta.exception;

public class EntradaVaziaException extends RegraDeNegocioException{

	public EntradaVaziaException(String variavel) {
		super("Alguns dados não foram preenchidos: " + variavel + ".\nPreenchimento é obrigatório");
	}
	
	public EntradaVaziaException() {
		super("Dados obrigatórios não preenchidos!");
	}
	
}
