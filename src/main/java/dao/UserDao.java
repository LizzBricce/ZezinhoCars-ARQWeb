package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDao {

	private final String arquivoCSV = "C:\\Users\\lucas\\dev\\dados\\users.csv";
	
    public void addUser(User user) {
        try {
            FileWriter fw = new FileWriter(arquivoCSV, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(user.toCsv());
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsers() {

        List<User> users = new ArrayList<>();

        try {
            FileReader fr = new FileReader(arquivoCSV);
            BufferedReader reader = new BufferedReader(fr);
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 5) {
                    User.Builder builder = new User.Builder()
                            .nome(partes[1])
                            .email(partes[2])
                            .senha(partes[3])
                            .adm(Boolean.parseBoolean(partes[4]));

                    User user = builder.build();
                    users.add(user);
                }
            }

            reader.close();
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User getById(int id) {
        List<User> users = getUsers();

        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }

        return null;
    }

    public void editUser(User user) {
        List<User> users = getUsers();

        for (User u : users) {
            if (u.getId() == user.getId()) {
                u.setNome(user.getNome());
                u.setEmail(user.getEmail());
                u.setSenha(user.getSenha());
                u.setAdm(user.getAdm());
                break;
            }
        }

        writeUsers(users);
    }

    private void writeUsers(List<User> users) {
        try {
            FileWriter fw = new FileWriter(arquivoCSV, false);
            PrintWriter pw = new PrintWriter(fw);
            for (User u : users) {
                pw.println(u.toCsv());
            }
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        List<User> users = getUsers();

        List<User> usersAtualizados = new ArrayList<>();

        for (User user : users) {
            if (user.getId() != id) {
                usersAtualizados.add(user);
            }
        }

        writeUsers(usersAtualizados);
    }
    
    public User authenticate(String email, String senha) {
        List<User> users = getUsers();

        for (User user : users) {
        	System.out.println(user.toCsv());
            if (user.getEmail().equals(email) && user.getSenha().equals(User.hashSenha(User.hashSenha(senha)))) {
                return user;
            }
        }

        return null;
    }
}
