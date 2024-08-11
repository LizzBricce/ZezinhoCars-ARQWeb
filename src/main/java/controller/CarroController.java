package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CarroDao;
import model.Carro;
import model.CategoriaEnum;
import model.CombustivelEnum;

@WebServlet("/carro")
public class CarroController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CarroDao carroDao;

    public CarroController() {
        this.carroDao = new CarroDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("list".equals(action)) {
            List<Carro> carros = carroDao.getCarros();
            request.setAttribute("carros", carros);
            request.getRequestDispatcher("/listaCarros.jsp").forward(request, response);
        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Carro carro = carroDao.getById(id);
            request.setAttribute("carro", carro);
            request.getRequestDispatcher("/editaCarro.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Carro carro = carroDao.getById(id);
            if (carro != null) {
                carroDao.editCarro(carro);
            }
            response.sendRedirect("carro?action=list");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            Carro carro = new Carro.Builder()
                .avaliacao(Integer.parseInt(request.getParameter("avaliacao")))
                .preco(Double.parseDouble(request.getParameter("preco")))
                .km(Long.parseLong(request.getParameter("km")))
                .categoria(CategoriaEnum.values()[Integer.parseInt(request.getParameter("categoria"))])
                .marca(request.getParameter("marca"))
                .modelo(request.getParameter("modelo"))
                .anoFabricacao(request.getParameter("anoFabricacao"))
                .cor(request.getParameter("cor"))
                .tipoCombustivel(CombustivelEnum.values()[Integer.parseInt(request.getParameter("tipoCombustivel"))])
                .destaque(request.getParameter("destaque") != null ? request.getParameter("destaque").equals("on") : false)
                .lancamento(request.getParameter("lancamento") != null ? request.getParameter("lancamento").equals("on") : false)
                .oferta(request.getParameter("oferta") != null ? request.getParameter("oferta").equals("on") : false)
                .build();
            carroDao.addCarro(carro);
            response.sendRedirect("carro?action=list");
        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Carro carro = new Carro.Builder()
                .id(id)
                .avaliacao(Integer.parseInt(request.getParameter("avaliacao")))
                .preco(Double.parseDouble(request.getParameter("preco")))
                .km(Long.parseLong(request.getParameter("km")))
                .categoria(CategoriaEnum.valueOf(request.getParameter("categoria")))
                .marca(request.getParameter("marca"))
                .modelo(request.getParameter("modelo"))
                .anoFabricacao(request.getParameter("anoFabricacao"))
                .cor(request.getParameter("cor"))
                .tipoCombustivel(CombustivelEnum.valueOf(request.getParameter("tipoCombustivel")))
                .destaque(Boolean.parseBoolean(request.getParameter("destaque")))
                .lancamento(Boolean.parseBoolean(request.getParameter("lancamento")))
                .oferta(Boolean.parseBoolean(request.getParameter("oferta")))
                .build();
            carroDao.editCarro(carro);
            response.sendRedirect("carro?action=list");
        }
    }
}
