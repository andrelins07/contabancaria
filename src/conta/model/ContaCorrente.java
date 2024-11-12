package conta.model;

import conta.exception.SaldoInsuficienteException;

public class ContaCorrente extends Conta {

	private float limite;

	public ContaCorrente(int numero, int agencia, int tipo, String titular, float saldo, float limite) {
		super(numero, agencia, tipo, titular, saldo);
		this.limite = limite;
	}

	public float getLimite() {
		return limite;
	}

	public void setLimite(float limite) {
		this.limite = limite;
	}
	
	@Override
	public boolean sacar(float valor) {

		if (this.getSaldo() + this.getLimite() < valor) {
			throw new SaldoInsuficienteException(this.getSaldo(), valor);
		}
		
		float saldoArredondado = (float) Math.round((this.getSaldo() - valor )* 100)/100f;

		this.setSaldo(saldoArredondado);
		return true;
	}

	@Override
	public void visualizar() {
		super.visualizar();
		System.out.printf("Limite de Crédito: %.2f | Disponível para Saque: %.2f\n\n", this.limite, (this.getSaldo() + this.limite));
	}

}