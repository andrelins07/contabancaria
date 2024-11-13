package conta.model;

import java.util.*;
import conta.exception.SaldoInsuficienteException;

public abstract class Conta {

	private int numero;
	private int agencia;
	private int tipo;
	private String titular;
	private float saldo;
	private ArrayList<Transacao> extrato = new ArrayList<>();;
	private int totalTransacoes;

	public Conta(int numero, int agencia, int tipo, String titular, float saldo) {
		this.numero = numero;
		this.agencia = agencia;
		this.tipo = tipo;
		this.titular = titular;
		this.saldo = saldo;
		this.totalTransacoes = 0;
	}
	public Conta(int numero, int agencia, int tipo, String titular, float saldo, int totalTransacoes, List<Transacao> transacoes) {
		this.numero = numero;
		this.agencia = agencia;
		this.tipo = tipo;
		this.titular = titular;
		this.saldo = saldo;
		this.totalTransacoes = totalTransacoes;
		extrato.addAll(transacoes);
	}

	public boolean sacar(float valor) {

		if (this.saldo >= valor) {
			float saldoArredondado = (float) Math.round((this.getSaldo() - valor) * 100) / 100f;
			this.setSaldo(saldoArredondado);
			setTotalTransacoes();
			return true;
		}
		throw new SaldoInsuficienteException(this.getSaldo(), valor);
	}

	public void depositar(float valor) {

		float saldoArredondado = (float) Math.round((this.getSaldo() + valor) * 100) / 100f;
		this.setSaldo(saldoArredondado);
		setTotalTransacoes();
	}

	public void atualizar(int agencia, String titular) {

		if (this.agencia != agencia) {
			this.agencia = agencia;
		}
		if (!this.titular.equals(titular)) {
			this.titular = titular;
		}
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

	public void novaTranscao(Transacao transacao) {
		extrato.add(transacao);
	}

	public ArrayList<Transacao> getExtrato() {
		return extrato;
	}

	public void setExtrato(List<Transacao> extrato) {
		this.extrato.addAll(extrato);
	}

	public int getTotalTransacoes() {
		return totalTransacoes;
	}

	public void setTotalTransacoes() {
		totalTransacoes++;
	}

}