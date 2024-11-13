package conta.model;

import java.time.LocalDate;

public class Transacao {

	private int idTransacao;
	private String tipoTransacao;
	private float valor;
	private LocalDate data;

	public Transacao(TransacaoDTO dto) {
		this.tipoTransacao = dto.tipoTransacao();
		this.valor = dto.valor();
		this.data = LocalDate.parse(dto.data());
		this.idTransacao = dto.idTransacao();
	}

	public Transacao(int idTransacao, String tipoTransacao, float valor, LocalDate data) {
		this.tipoTransacao = tipoTransacao;
		this.valor = valor;
		this.data = data;
		this.idTransacao = idTransacao;
	}

	public void getTransacao() {
		System.out.printf("Id: %d | Transacao: %s | Data: %s | Valor: %.2f\n", idTransacao, tipoTransacao,
				data.toString(), valor);
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
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
}
