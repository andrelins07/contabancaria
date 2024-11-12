package conta.model;

import java.util.Objects;

import conta.exception.SaldoInsuficienteException;

public abstract class Conta {

	private int numero;
	private int agencia;
	private int tipo;
	private String titular;
	private float saldo;

	public Conta(int numero, int agencia, int tipo, String titular, float saldo) {
		this.numero = numero;
		this.agencia = agencia;
		this.tipo = tipo;
		this.titular = titular;
		this.saldo = saldo;
	}
	public void atualizar(int agencia, String titular) {
		
		if(this.agencia != agencia) {
			this.agencia = agencia;
		}
		if(!this.titular.equals(titular)) {
			this.titular = titular;
		}
	}

	public int getNumero() {
		return numero;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public int getTipo() {
		return tipo;
	}

	public String getTitular() {
		return titular;
	}

	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	public boolean sacar(float valor) {
		
		if(this.saldo >= valor) {
			float saldoArredondado = (float) Math.round((this.getSaldo() - valor )* 100)/100f;
			this.setSaldo(saldoArredondado);
			return true;
		}
		throw new SaldoInsuficienteException(this.getSaldo(), valor);
	}

	public void depositar(float valor) {
		
		float saldoArredondado = (float) Math.round((this.getSaldo() + valor )* 100)/100f;
		this.setSaldo(saldoArredondado);
	
	}

	public void visualizar() {

		String tipo = "";

		switch (this.tipo) {
		case 1:
			tipo = "CC";
			break;
		case 2:
			tipo = "CP";
			break;
		}
		System.out.printf("""
		\n****************************************************************
		Dados da Conta:
		****************************************************************
		Numero da conta: %d | Agência: %d | Tipo da Conta: %s
		Saldo: %.2f | Titular: %s\n""", this.numero, this.agencia, tipo, this.saldo, this.titular);
	}

	@Override
	public int hashCode() {
		return Objects.hash(agencia, numero, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		return agencia == other.agencia && numero == other.numero;
	}
	
}