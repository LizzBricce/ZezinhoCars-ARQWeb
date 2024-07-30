package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import model.Carro;

public class CarroDao {
	
	private final String arquivoCSV = "C:\\Users\\lucas\\dev\\dados\\carros.csv";

	
	public void addCarro(Carro c) {
		try {
			FileWriter fw = new FileWriter(arquivoCSV, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(c);
			pw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Carro> getCarros() {
		List<Carro> carros = new ArrayList<>();

		try {
			FileReader fr = new FileReader(arquivoCSV);
			BufferedReader reader = new BufferedReader(fr);
			String linha;

			while ((linha = reader.readLine()) != null) {
				String[] partes = linha.split(";");
				if (partes.length == 13) {
					
					Carro.Builder builder = new Carro.Builder()
											.id(Integer.parseInt(partes[0]))
											.avaliacao(Integer.parseInt(partes[1]))
											.preco(Double.parseDouble(partes[2]))
											.km(Long.parseLong(partes[3]))
											//.categoria(Integer.parseInt(partes[4]))
											.marca(partes[5])
											.modelo(partes[6])
											.anoFabricacao(partes[7])
											.cor(partes[8]);
											//.tipoCombustivel()
											//.destaque(false)
											//.lancamento(false)
											//.oferta();
											
											
					
					carros.add(builder.build());
				}
			}

			reader.close();
			fr.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return carros;
	}
}
