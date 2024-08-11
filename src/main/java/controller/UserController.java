package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;

@WebServlet("/user")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    public UserController() {
        super();
        userDao = new UserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.equals("")) {
            action = "list";
        }

        switch (action) {
            case "list":
                listarUsers(request, response);
                break;
            case "edit":
                mostrarFormularioEdicao(request, response);
                break;
            case "delete":
                deletarUser(request, response);
                break;
            default:
                listarUsers(request, response);
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
                adicionarUser(request, response);
                break;
            case "edit":
                atualizarUser(request, response);
                break;
            default:
                listarUsers(request, response);
                break;
        }
    }

    private void listarUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users = userDao.getUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("user-list.jsp").forward(request, response);
    }

    private void mostrarFormularioEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userDao.getById(id);
        request.setAttribute("user", user);
        request.getRequestDispatcher("user-form.jsp").forward(request, response);
    }

    private void adicionarUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        Boolean adm = request.getParameter("adm") != null;

        User user = new User.Builder()
                .nome(nome)
                .email(email)
                .senha(senha)
                .adm(adm)
                .build();

        userDao.addUser(user);
        response.sendRedirect("user?action=list");
    }

    private void atualizarUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = construirUserAPartirDoRequest(request);
        userDao.editUser(user);
        response.sendRedirect("user?action=list");
    }

    private void deletarUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userDao.getById(id);
        if (user != null) {
            userDao.deleteUser(id);
        }
        response.sendRedirect("user?action=list");
    }

    private User construirUserAPartirDoRequest(HttpServletRequest request) {
        int id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : 0;
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        boolean adm = request.getParameter("adm") != null;

        User.Builder builder = new User.Builder()
                .nome(nome)
                .email(email)
                .senha(senha)
                .adm(adm);

        return builder.build();
    }
}
