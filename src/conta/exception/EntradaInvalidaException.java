package conta.exception;

public class EntradaInvalidaException extends RegraDeNegocioException{

	public EntradaInvalidaException(String mensagem) {
		super(mensagem);
	}
	
	public EntradaInvalidaException(float valor) {
		super("O valor não pode ser negativo! Valor digitado: " + valor);
	}
	
	public EntradaInvalidaException() {
		super("Entrada de dados invalida! Digite apenas numeros");
	}
}
