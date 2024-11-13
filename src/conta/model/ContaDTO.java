package conta.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ContaDTO(
		@JsonProperty(value = "idConta") int numero, 
		@JsonProperty(value = "agencia") int agencia,
		@JsonProperty(value = "tipoConta") String tipo, 
		@JsonProperty(value = "nomeTitular") String titular,
		@JsonProperty(value = "saldo") float saldo, 
		@JsonProperty(value = "transacoes") ArrayList<TransacaoDTO> extrato,
		@JsonProperty(value = "quantidadeTransacoes") int totalTransacoes) 
{

	@Override
	public String toString() {
		return "ContaDTO [numero=" + numero + ", agencia=" + agencia + ", tipo=" + tipo + ", titular=" + titular
				+ ", saldo=" + saldo + ", extrato=" + extrato + ", totalTransacoes=" + totalTransacoes + "\n]";
	}
	
	
	
}
