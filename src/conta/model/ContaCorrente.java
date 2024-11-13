package conta.model;

import conta.exception.SaldoInsuficienteException;

public class ContaCorrente extends Conta {

	private float limite = 150.0f;

	public ContaCorrente(int numero, int agencia, int tipo, String titular, float saldo) {
		super(numero, agencia, tipo, titular, saldo);
	}	
	public ContaCorrente(ContaDTO dto) {		
	
		super(dto.numero(), dto.agencia(), 1, dto.titular(), dto.saldo(), dto.totalTransacoes(), 
				dto.extrato().stream().map(e-> new Transacao(e)).toList());	
	}
	
	@Override
	public boolean sacar(float valor) {

		if (this.getSaldo() + this.getLimite() < valor) {
			throw new SaldoInsuficienteException(this.getSaldo(), valor);
		}
		float saldoArredondado = (float) Math.round((this.getSaldo() - valor )* 100)/100f;
		
		this.setSaldo(saldoArredondado);
		
		this.setTotalTransacoes();
		
		return true;
	}

	@Override
	public void visualizar() {
		super.visualizar();
		System.out.printf("Limite de Crédito: %.2f | Disponível para Saque: %.2f\n\n", this.limite, (this.getSaldo() + this.limite));
	}
	
	public float getLimite() {
		return limite;
	}

	public void setLimite(float limite) {
		this.limite = limite;
	}

}