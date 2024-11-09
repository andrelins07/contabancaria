package conta.exception;

public class ContaJaCadastradaException extends RegraDeNegocioException {

	public ContaJaCadastradaException(String mensagem) {
		super("Conta já cadastrada no sistema!");
	}
}
