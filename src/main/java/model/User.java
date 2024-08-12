package model;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private static int contador = 0;
		
	private int id;	
	private String nome;
	private String email;
	private String senha;
	private Boolean adm;
	
	private User() {
		this.id = this.contador++;
	}

	private User(String nome, String email, String senha, Boolean adm) {
		this();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.adm = adm;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static int getContador() {
		return contador;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public Boolean getAdm() {
		return adm;
	}
	
	public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setAdm(Boolean adm) {
        this.adm = adm;
    }

	
	public static class Builder {
		private String nome;
		private String email;
		private String senha;
		private Boolean adm;
		
		public Builder () {
			this.nome = "";
			this.email = "";
			this.senha = "";
			this.adm = false;
		}
		
		public Builder nome(String nome) {
			this.nome = nome;
			return this;
		}
		
		public Builder email(String email) {
			this.email = email;
			return this;
		}
		
		public Builder senha(String senha) {
			this.senha = senha;
			return this;
		}
		
		public Builder adm(boolean adm) {
			this.adm = adm;
			return this;
		}
		
		public User build() {
			return new User(this.nome, this.email, this.senha, this.adm);
		}
	}
	
    public String toCsv() {
        return id + ";" + nome + ";" + email + ";" + senha + ";" + adm;
    }
    public static String hashSenha(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(senha.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao criptografar a senha", e);
        }
    }

	
}
