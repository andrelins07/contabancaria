package conta.service;

import java.util.ArrayList;

import conta.exception.*;
import conta.model.Conta;
import conta.repository.ContaRepository;

public class ContaService implements ContaRepository{

	private ArrayList<Conta> contas = new ArrayList<Conta>();
	
	// CRUD da Conta
	
	public void cadastrar(Conta conta) {

		if (contas.contains(conta)) {
			throw new ContaJaCadastradaException();
		}
		contas.add(conta);
	}

	public void listarTodas() {

		if (contas.isEmpty())
			throw new ContaNaoEncontradaException("Nenhuma conta cadastrada!");

		System.out.println("\nTotal de Contas Cadastradas: " + contas.size());

		contas.forEach(conta -> conta.visualizar());

	}

	public Conta procurarPorNumero(int agencia, int numeroConta) {

		for (Conta conta : contas) {
			if (conta.getNumero() == numeroConta &&
					conta.getAgencia() == agencia) {
				return conta;
			}
		}
		throw new ContaNaoEncontradaException("Conta não encontrada!");
	}

	public void atualizar(Conta conta, int agencia, String titular) {

		conta.atualizar(agencia, titular);
	}

	public void deletar(Conta conta) {
		
		float saldoArredondado = (float) Math.round(conta.getSaldo() * 100)/100f;
		
		conta.setSaldo(saldoArredondado);
	
		if (conta.getSaldo() > 0f) {
			throw new RegraDeNegocioException("A conta não pode ser encerrada. Saque todo valor e tente novamente!");
		}
		if(conta.getSaldo() < 0f) {
			throw new RegraDeNegocioException("A conta não pode ser encerrada. Regularize os debitos pendentes: " + conta.getSaldo());
		}
		
		contas.remove(conta);
	}
	
	// Métodos Bancários
	
	public void sacar(Conta conta, float valorSaque) {
		
		conta.sacar(valorSaque);
		
	}

	public void depositar(Conta conta, float valorDeposito) {
		conta.depositar(valorDeposito);
	}

	public void transferir(Conta contaOrigem, Conta contaDestino, float valor) {
		sacar(contaOrigem, valor);
		depositar(contaDestino, valor);
	}

}
