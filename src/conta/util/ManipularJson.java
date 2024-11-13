package conta.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import conta.exception.RegraDeNegocioException;
import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.model.ContaDTO;
import conta.model.ContaPoupanca;

public class ManipularJson {

	private String jsonToString(String source) {

		StringBuilder jsonTemp = null;

		try {
			FileReader fileReader = new FileReader(source);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			List<String> collect = bufferedReader.lines().toList();
			jsonTemp = new StringBuilder();
			for (String s : collect) {
				jsonTemp.append(s);
			}
			fileReader.close();
			bufferedReader.close();
		} catch (IOException e) {
			throw new RegraDeNegocioException("Não foi possível ler o arquivo JSON");
		}
		return jsonTemp.toString();
	}

	public ArrayList<Conta> carregarList() {

		String json = jsonToString("src/resources/extrato.json");

		ObjectMapper mapper = new ObjectMapper();
		List<ContaDTO> contasDto;
		try {
			contasDto = mapper.readValue(json, new TypeReference<List<ContaDTO>>() {
			});
		} catch (JsonProcessingException e) {
			throw new RegraDeNegocioException("Não foi possível ler o arquivo JSON");
		}

		ArrayList<Conta> contas = new ArrayList<>();

		contasDto.forEach(dto -> {
			if (dto.tipo().equals("CC"))
				contas.add(new ContaCorrente(dto));
			else
				contas.add(new ContaPoupanca(dto));
		});

		return contas;
	}

	public void escreverJson(ArrayList<Conta> contas) {
		try {

			ObjectMapper mapper = new ObjectMapper();

			File arquivoJson = new File("src/resources/extrato.json");
			mapper.writeValue(arquivoJson, contas);

			System.out.println("JSON gravado no arquivo com sucesso!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
