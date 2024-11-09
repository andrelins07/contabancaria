package conta.service;

import java.util.ArrayList;
import conta.exception.*;
import conta.model.Conta;

public class ContaService {

	private ArrayList<Conta> contas = new ArrayList<Conta>();

	public void criarConta(Conta conta) {

		if (contas.contains(conta)) {
			throw new ContaJaCadastradaException("Conta já cadastrada!");
		}
		if (conta.getAgencia() < 0 || conta.getNumero() < 0) {
			throw new EntradaInvalidaException("O numero da agencia e da conta não pode ser negativo!");
		}
		if (conta.getTitular().isEmpty()) {
			throw new EntradaVaziaException("Titular");
		}
		if (conta.getTipo() != 1 || conta.getTipo() != 2) {
			throw new EntradaInvalidaException("Tipo de conta inválido!");
		}
		contas.add(conta);
	}

	public void listarContas() {

		if (contas.isEmpty())
			throw new ContaNaoEncontradaException("Nenhuma conta cadastrada!");

		System.out.println("\nTotal de Contas Cadastradas: " + contas.size());

		contas.forEach(conta -> conta.visualizar());

	}

	public Conta listarContaPorNumero(int agencia, int numeroConta) {

		for (Conta conta : contas) {
			if (conta.getNumero() == numeroConta && conta.getAgencia() == agencia) {
				return conta;
			}
		}
		throw new ContaNaoEncontradaException("Conta não encontrada!");
	}

	public void atualizarDadosConta(Conta conta, int agencia, int tipo, String titular) {

		if (agencia < 0) {
			throw new EntradaInvalidaException("O numero da agencia e da conta não pode ser negativo!");
		}
		if (conta.getTitular().isEmpty()) {
			throw new EntradaVaziaException("Titular");
		}
		if (tipo != 1 || tipo != 2) {
			throw new EntradaInvalidaException("Tipo de conta inválido!");
		}

		conta.atualizar(agencia, tipo, titular);
	}

	public void apagarConta(Conta conta) {
		
		float saldoArredondado = (float) Math.round(conta.getSaldo() * 100)/100f;
		
		conta.setSaldo(saldoArredondado);
	
		if (conta.getSaldo() > 0f) {
			throw new RegraDeNegocioException("A conta não pode ser encerrada. Saque todo valor e tente novamente!");
		}
		
		contas.remove(conta);
	}

	public void sacar(Conta conta, float valorSaque) {

		if(valorSaque < 0) {
			throw new EntradaInvalidaException(valorSaque);
		}
		
		if (conta.getSaldo() < valorSaque) {
			throw new SaldoInsuficienteException(conta.getSaldo(), valorSaque);
		}
		conta.sacar(valorSaque);
		
	}

	public void depositar(Conta conta, float valorDeposito) {

		if(valorDeposito < 0) {
			throw new EntradaInvalidaException(valorDeposito);
		}

		conta.depositar(valorDeposito);
	}

	public void transferir(Conta saida, Conta destino, float valor) {

		sacar(saida, valor);
		depositar(destino, valor);

	}
}
