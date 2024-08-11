package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.Carro;
import model.CategoriaEnum;
import model.CombustivelEnum;

public class CarroDao {

    private final String arquivoCSV = "C:\\Users\\lucas\\dev\\dados\\carros.csv";

    public void addCarro(Carro c) {
        try {
            FileWriter fw = new FileWriter(arquivoCSV, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(c.toCsv());
            pw.close();
            fw.close();
        } catch (IOException e) {
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
                if (partes.length == 14) {
                    Carro.Builder builder = new Carro.Builder()
                            .id(Integer.parseInt(partes[0]))
                            .avaliacao(Integer.parseInt(partes[1]))
                            .preco(Double.parseDouble(partes[2]))
                            .km(Long.parseLong(partes[3]))
                            .categoria(CategoriaEnum.values()[Integer.parseInt(partes[4])])
                            .marca(partes[5])
                            .modelo(partes[6])
                            .anoFabricacao(partes[7])
                            .cor(partes[8])
                            .tipoCombustivel(CombustivelEnum.values()[Integer.parseInt(partes[9])])
                            .destaque(Integer.parseInt(partes[10]) == 1)
                            .lancamento(Integer.parseInt(partes[11]) == 1)
                            .oferta(Integer.parseInt(partes[12]) == 1)
                            .imagem(partes[13]);  // Novo campo para a imagem Base64

                    carros.add(builder.build());
                }
            }

            reader.close();
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return carros;
    }

    public Carro getById(int id) {
        List<Carro> carros = getCarros();

        for (Carro carro : carros) {
            if (carro.getId() == id) {
                return carro;
            }
        }

        return null;
    }

    public void editCarro(Carro carro) {
        List<Carro> carros = getCarros();

        for (Carro c : carros) {
            if (c.getId() == carro.getId()) {
                c.setAnoFabricacao(carro.getAnoFabricacao());
                c.setCategoria(carro.getCategoria());
                c.setAvaliacao(carro.getAvaliacao());
                c.setCor(carro.getCor());
                c.setDestaque(carro.getDestaque());
                c.setKm(carro.getKm());
                c.setLancamento(carro.getLancamento());
                c.setMarca(carro.getMarca());
                c.setModelo(carro.getModelo());
                c.setOferta(carro.getOferta());
                c.setPreco(carro.getPreco());
                c.setTipoCombustivel(carro.getTipoCombustivel());
                c.setImagemBase64(carro.getImagemBase64());
                break;
            }
        }

        writeCarros(carros);
    }

    private void writeCarros(List<Carro> carros) {
        try {
            FileWriter fw = new FileWriter(arquivoCSV, false);
            PrintWriter pw = new PrintWriter(fw);
            for (Carro c : carros) {
                pw.println(c.toCsv());
            }
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteCarro(int id) {
        List<Carro> carros = getCarros();

        List<Carro> carrosAtualizados = new ArrayList<>();

        for (Carro carro : carros) {
            if (carro.getId() != id) {
                carrosAtualizados.add(carro);
            }
        }

        writeCarros(carrosAtualizados);
    }
}
