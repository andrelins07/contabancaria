package conta.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TransacaoDTO(
		@JsonProperty(value = "idTransacao") int idTransacao,
		@JsonProperty(value = "tipoTransacao") String tipoTransacao,
		@JsonProperty(value = "valor") float valor,
		@JsonProperty(value = "data") String data) {}
