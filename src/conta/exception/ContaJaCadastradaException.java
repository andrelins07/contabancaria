package conta.exception;

public class ContaJaCadastradaException extends RegraDeNegocioException {

	public ContaJaCadastradaException() {
		super("Conta já cadastrada no sistema!");
	}
}
