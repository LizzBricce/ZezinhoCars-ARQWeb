package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
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
            case "edit":
                mostrarFormularioEdicao(request, response);
                break;
            case "delete":
                deletarCarro(request, response);
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
                adicionarCarro(request, response);
                break;
            case "edit":
                atualizarCarro(request, response);
                break;
            default:
                listarCarros(request, response);
                break;
        }
    }

    private void listarCarros(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Carro> carros = carroDao.getCarros();
        request.setAttribute("carros", carros);
        request.getRequestDispatcher("carro-list.jsp").forward(request, response);
    }

    private void mostrarFormularioEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Carro carro = carroDao.getById(id);
        request.setAttribute("carro", carro);
        request.getRequestDispatcher("carro-form.jsp").forward(request, response);
    }

    private void adicionarCarro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Carro carro = construirCarroAPartirDoRequest(request);
        System.out.println(carro.toString());
        carroDao.addCarro(carro);
        response.sendRedirect("carro?action=list");
    }

    private void atualizarCarro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Carro carro = construirCarroAPartirDoRequest(request);
        carroDao.editCarro(carro);
        response.sendRedirect("carro?action=list");
    }

    private void deletarCarro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Carro carro = carroDao.getById(id);
        if (carro != null) {
            carroDao.deleteCarro(id);
        }
        response.sendRedirect("carro?action=list");
    }

    private Carro construirCarroAPartirDoRequest(HttpServletRequest request) throws IOException, ServletException {
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

        Builder builder = new Carro.Builder()
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
                .imagem(imagemBase64);

        return builder.build();
    }
}
