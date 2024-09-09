package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import dao.CarroDao;
import model.Carro;
import model.Carro.Builder;
import model.CategoriaEnum;
import model.CombustivelEnum;

@WebServlet("/carro")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
public class CarroController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CarroDao carroDao;

    public CarroController() {
        super();
        carroDao = new CarroDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.equals("")) {
            action = "list";
        }

        switch (action) {
            case "list":
                listarCarros(request, response);
                break;
            case "view":
                visualizarCarro(request, response);
                break;
            case "searchByModel":  
                buscarCarrosPorModelo(request, response);
                break;
            default:
                listarCarros(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.equals("")) {
            action = "add";
        }

        switch (action) {
            case "add":
            case "edit":
                salvarCarro(request, response);
            default:
                listarCarros(request, response);
                break;
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        deletarCarro(request, response);
    }
    
    protected void listarCarros(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Carro> carros = carroDao.getCarros();
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(carros);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }
    
    protected void visualizarCarro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Carro carro = carroDao.getById(id);
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(carro);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }

    protected void buscarCarrosPorModelo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String modelo = request.getParameter("modelo");
        List<Carro> carros = carroDao.getByModelo(modelo);
        
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(carros);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }

    protected void deletarCarro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean success = carroDao.deleteCarro(id);

        String jsonResponse;
        if (success) {
            jsonResponse = "{\"message\": \"Carro deletado com sucesso.\"}";
        } else {
            jsonResponse = "{\"error\": \"Erro ao deletar o carro.\"}";
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }

    protected void salvarCarro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : 0;
        int avaliacao = Integer.parseInt(request.getParameter("avaliacao"));
        double preco = Double.parseDouble(request.getParameter("preco"));
        long km = Long.parseLong(request.getParameter("km"));
        CategoriaEnum categoria = CategoriaEnum.values()[Integer.parseInt(request.getParameter("categoria"))];
        String marca = request.getParameter("marca");
        String modelo = request.getParameter("modelo");
        String anoFabricacao = request.getParameter("anoFabricacao");
        String cor = request.getParameter("cor");
        CombustivelEnum tipoCombustivel = CombustivelEnum.values()[Integer.parseInt(request.getParameter("tipoCombustivel"))];
        boolean destaque = request.getParameter("destaque") != null;
        boolean lancamento = request.getParameter("lancamento") != null;
        boolean oferta = request.getParameter("oferta") != null;

        Part filePart = request.getPart("imagem");
        String imagemBase64 = null;
        if (filePart != null && filePart.getSize() > 0) {
            InputStream fileContent = filePart.getInputStream();
            byte[] imagemBytes = fileContent.readAllBytes();
            imagemBase64 = Base64.getEncoder().encodeToString(imagemBytes);
        }

        if (imagemBase64 == null && id != 0) {
            Carro infos = carroDao.getById(id);
            imagemBase64 = infos.getImagemBase64();
        }

        Carro carro = new Carro.Builder()
                .id(id)
                .avaliacao(avaliacao)
                .preco(preco)
                .km(km)
                .categoria(categoria)
                .marca(marca)
                .modelo(modelo)
                .anoFabricacao(anoFabricacao)
                .cor(cor)
                .tipoCombustivel(tipoCombustivel)
                .destaque(destaque)
                .lancamento(lancamento)
                .oferta(oferta)
                .imagem(imagemBase64)
                .build();

        boolean success;
        if (id == 0) {
            success = carroDao.addCarro(carro);
        } else {
            success = carroDao.editCarro(carro);
        }

        String jsonResponse;
        if (success) {
            jsonResponse = "{\"message\": \"Carro salvo com sucesso.\"}";
        } else {
            jsonResponse = "{\"error\": \"Erro ao salvar o carro.\"}";
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }
}


