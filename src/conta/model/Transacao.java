package conta.model;

import java.time.LocalDate;

import conta.util.Cores;

public class Transacao {

	private int idTransacao;
	private String tipoTransacao;
	private float valor;
	private LocalDate data;

	public Transacao(int idTransacao, String tipoTransacao, float valor, LocalDate data) {
		this.tipoTransacao = tipoTransacao;
		this.valor = valor;
		this.setValor(valor);
		this.idTransacao = idTransacao;
		this.data = LocalDate.now();
	}

	public void getTransacao() {

		if (tipoTransacao.replaceAll("\\s+", "").equals("Saque") || tipoTransacao.equals("Transf. Enviada"))
			System.out.println("Id: " + idTransacao + " | Transacao: " + tipoTransacao + " | Data: " + data.toString()
					+ " | Valor: " + Cores.TEXT_RED + valor + Cores.TEXT_RESET);
		else {
			System.out.println("Id: " + idTransacao + " | Transacao: " + tipoTransacao + " | Data: " + data.toString()
			+ " | Valor: " + Cores.TEXT_GREEN + valor + Cores.TEXT_RESET);
		}
	}

	public String getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(String tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {

		this.valor = (float) Math.round((valor * 100)) / 100f;
		;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
}
