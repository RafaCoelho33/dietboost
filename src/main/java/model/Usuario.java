package model;

public class Usuario {
    private int id;
    public String nome;
    public String email;
    public int telefone;
    public String cidade;
    public String assinatura;
    public int cpf;
    public String senha;

    public Usuario() {
        id = -1;
        nome = "";
        email = "";
        telefone = 0;
        cidade = "";
        assinatura = "";
        cpf = 0;
        senha = "";
    }

    public Usuario (int id, String nome, String email, int telefone, String cidade, String assinatura, int cpf, String senha) {
        setId(id);
        setNome(nome);
        setEmail(email);
        setTelefone(telefone);
        setCidade(cidade);
        setAssinatura(assinatura);
        setCpf(cpf);
        setSenha(senha);
    }
    public int getID() {
        return id;
    }
    public void setId(int id) {this.id = id;}
    public String getNome() {
        return nome;
    }
    public void setNome (String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) { this.senha = senha; }

    public int getTelefone() {
        return telefone;
    }
    public void setTelefone(int telefone) { this.telefone = telefone; }

    public String getCidade() {
        return cidade;
    }
    public void setCidade (String cidade) { this.cidade = cidade; }

    public String getAssinatura() {
        return assinatura;
    }
    public void setAssinatura (String assinatura) { this.assinatura = assinatura; }

    public int getCpf() {
        return cpf;
    }
    public void setCpf (int cpf) { this.cpf = cpf; }

    @Override
    public String toString() {
        return "Usuario: " + nome + "   ID: " + id +  "   Email: " + email + "   Telefone: " + telefone + "   Cidade: "
                + cidade  + "   Assinatura: " + assinatura + "   CPF: " + cpf;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.getCpf() == ((Usuario) obj).getCpf());
    }
}
