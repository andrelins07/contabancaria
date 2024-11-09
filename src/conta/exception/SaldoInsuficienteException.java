package conta.exception;

public class SaldoInsuficienteException extends RegraDeNegocioException {

	public SaldoInsuficienteException(String mensagem) {
		super(mensagem);
	}
	
	public SaldoInsuficienteException(float saldoConta, float valorSolicitado) {
		super("Saldo insuficiente!\nValor solicitado: " + valorSolicitado + " | Saldo atual: " + saldoConta);	
	}
	
}
