package conta.repository;

import conta.model.Conta;

public interface ContaRepository {


	public Conta procurarPorNumero(int agencia, int numero);//
	public void listarTodas();//
	public void cadastrar(Conta conta);//
	public void atualizar(Conta conta, int agencia, int tipo, String titular);
	public void deletar(Conta numero);//
	
	// Métodos Bancários
	public void sacar(Conta conta, float valor);
	public void depositar(Conta conta, float valor);
	public void transferir(Conta contaOrigem, Conta contaDestino, float valor);
	
}