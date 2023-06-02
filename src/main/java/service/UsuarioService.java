package service;

import java.util.Scanner;
import java.io.File;
import java.util.List;
import dao.UsuarioDAO;
import model.Usuario;
import spark.Request;
import spark.Response;


public class UsuarioService {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private String form;
    private final int FORM_INSERT = 1;
    private final int FORM_DETAIL = 2;
    private final int FORM_UPDATE = 3;
    private final int FORM_ORDERBY_ID = 1;
    private final int FORM_ORDERBY_NOME = 2;

    public UsuarioService() {
        makeForm();
    }

    public void makeForm() {
        makeForm(FORM_INSERT, new Usuario(), FORM_ORDERBY_NOME);
    }

    public void makeForm(int orderBy) {
        makeForm(FORM_INSERT, new Usuario(), orderBy);
    }

    public void makeForm(int tipo, Usuario usuario, int orderBy) {
        String nomeArquivo = "form.html";
        form = "";
        try {
            Scanner entrada = new Scanner(new File(nomeArquivo));
            while (entrada.hasNext()) {
                form += (entrada.nextLine() + "\n");
            }
            entrada.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String umUsuario = "";
        if (tipo != FORM_INSERT) {
            umUsuario += "\t<table width=\"80%\" align=\"center\">";
            umUsuario += "\t\t<tr>";
            umUsuario += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/usuario/list/1\">Novo Usuario</a></b></font></td>";
            umUsuario += "\t\t</tr>";
            umUsuario += "\t</table>";
            umUsuario += "\t<br>";
        }

        if (tipo == FORM_INSERT || tipo == FORM_UPDATE) {
            String action = "/usuario/";
            String title, nome, buttonLabel, buttonLabel2;
            if (tipo == FORM_INSERT) {
                action += "insert";
                title = "Cadastre seu Usuário";
                buttonLabel = "Inserir";
                buttonLabel2 = "Voltar";
            } else {
                action += "update/" + usuario.getCpf();
                title = "Atualizar Usuario (ID " + usuario.getID() + ")";
                nome = usuario.getNome();
                buttonLabel = "Atualizar";
                buttonLabel2 = "Voltar";
            }
            umUsuario += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
            umUsuario += "\t<table width=\"80%\" align=\"center\">";
            umUsuario += "\t\t<tr>";
            umUsuario += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>" + title + "</b></font></td>";
            umUsuario += "\t\t</tr>";
            umUsuario += "\t\t<tr>";
            umUsuario += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
            umUsuario += "\t\t</tr>";
            umUsuario += "\t\t<tr>";
            umUsuario += "\t\t\t<td>&nbsp;Nome: <input class=\"input--register\" autocomplete=\"off\" type=\"text\" name=\"nome\" placeholder=\"" + usuario.getNome() + "\"></td>";
            umUsuario += "\t\t\t<td>Email: <input class=\"input--register\" type=\"text\" autocomplete=\"off\" name=\"email\" value=\"" + usuario.getEmail() + "\"></td>";
            umUsuario += "\t\t\t<td>Senha: <input class=\"input--register\" type=\"password\" autocomplete=\"off\" name=\"senha\" value=\"" + usuario.getSenha() + "\"></td>";
            umUsuario += "\t\t\t<td>Telefone: <input class=\"input--register\" type=\"text\" autocomplete=\"off\" name=\"telefone\" value=\"" + usuario.getTelefone() + "\"></td>";
            umUsuario += "\t\t\t<td>Cidade: <input class=\"input--register\" type=\"text\" autocomplete=\"off\" name=\"cidade\" value=\"" + usuario.getCidade() + "\"></td>";
            umUsuario += "\t\t\t<td>Assinatura: <input class=\"input--register\" type=\"text\" autocomplete=\"off\" name=\"assinatura\" value=\"" + usuario.getAssinatura() + "\"></td>";
            umUsuario += "\t\t\t<td>CPF: <input class=\"input--register\" type=\"text\" name=\"cpf\" autocomplete=\"off\" value=\"" + usuario.getCpf() + "\"></td>";
            umUsuario += "\t\t</tr>";
            umUsuario += "\t\t<tr>";
            umUsuario += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\"" + buttonLabel + "\" class=\"input--main__style input--button\"></td>";
            umUsuario += "\t\t\t<a class=\"btn btn-light\" href=\"/./frontend.html\">" + buttonLabel2 + "</a>";
            umUsuario += "\t\t</tr>";
            umUsuario += "\t</table>";
            umUsuario += "\t</form>";
        } else if (tipo == FORM_DETAIL) {
            umUsuario += "\t<table width=\"80%\" align=\"center\" color=\"white\">";
            umUsuario += "\t\t<tr>";
            umUsuario += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar usuario (CPF " + usuario.getCpf() + ")</b></font></td>";
            umUsuario += "\t\t</tr>";
            umUsuario += "\t\t<tr>";
            umUsuario += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
            umUsuario += "\t\t</tr>";
            umUsuario += "\t\t<tr>";
            umUsuario += "\t\t\t<td>&nbsp;Descrição: " + usuario.getNome() + "</td>";
            umUsuario += "\t\t\t<td>Email: " + usuario.getEmail() + "</td>";
            umUsuario += "\t\t\t<td>Telefone: " + usuario.getTelefone() + "</td>";
            umUsuario += "\t\t\t<td>Cidade: " + usuario.getCidade() + "</td>";
            umUsuario += "\t\t\t<td>Assinatura: " + usuario.getAssinatura() + "</td>";
            umUsuario += "\t\t\t<td>CPF: " + usuario.getCpf() + "</td>";
            umUsuario += "\t\t</tr>";
            umUsuario += "\t\t<tr>";
            umUsuario += "\t\t\t<td>&nbsp;</td>";
            umUsuario += "\t\t</tr>";
            umUsuario += "\t</table>";
        } else {
            System.out.println("ERRO! Tipo não identificado " + tipo);
        }
        form = form.replaceFirst("<UM-USUARIO>", umUsuario);

        String list = new String("<table width=\"80%\" align=\"center\">");
        list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>Tabela de Usuarios</b></font></td></tr>\n" +
                "\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
                "\n<tr>\n" +
                "\t<td><a href=\"/usuario/list/\"><b>CPF</b></a></td>\n" +
                "\t<td><a href=\"/usuario/list/\"><b>Nome</b></a></td>\n" +
//                "\t<td><a href=\"/usuario/list/\"><b>Email</b></a></td>\n" +
//                "\t<td><a href=\"/usuario/list/\"><b>Senha</b></a></td>\n" +
                "\t<td><a href=\"/usuario/list/\"><b>Telefone</b></a></td>\n" +
                "\t<td><a href=\"/usuario/list/\"><b>Cidade</b></a></td>\n" +
                "\t<td><a href=\"/usuario/list/\"><b>Assinatura</b></a></td>\n" +
                "\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
                "\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
                "\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
                "</tr>\n";

        List<Usuario> usuarios;
        if (orderBy == FORM_ORDERBY_ID) {
            usuarios = usuarioDAO.getOrderByID();
        } else if (orderBy == FORM_ORDERBY_NOME) {
            usuarios = usuarioDAO.getOrderByNome();
        } else
            usuarios = usuarioDAO.get();


        int i = 0;
        for (Usuario p : usuarios) {
            list += "\n<tr>\n" +
                    "\t<td>" + p.getCpf() + "</td>\n" +
                    "\t<td>" + p.getNome() + "</td>\n" +
                    "\t<td>" + p.getTelefone() + "</td>\n" +
                    "\t<td>" + p.getCidade() + "</td>\n" +
                    "\t<td>" + p.getAssinatura() + "</td>\n" +
                    "\t<td align=\"center\" valign=\"middle\"><a href=\"/usuario/" + p.getID() + "\"><i class=\"fa-solid fa-magnifying-glass\"></i></a></td>\n" +
                    "\t<td align=\"center\" valign=\"middle\"><a href=\"/usuario/update/" + p.getID() + "\"><i class=\"fa-regular fa-pen-to-square\"></i></a></td>\n" +
                    "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteUsuario('" + p.getID() + "');\"><i class=\"fa-solid fa-trash\"></i></a></td>\n" +
                    "</tr>\n";
        }
        list += "</table>";
        form = form.replaceFirst("<LISTAR-USUARIO>", list);
    }


    public Object insert (Request request, Response response) {
        String nome = request.queryParams("nome");
        String email = request.queryParams("email");
        String senha = request.queryParams("senha");
        int telefone = Integer.parseInt(request.queryParams("telefone"));
        String cidade = request.queryParams("cidade");
        String assinatura = request.queryParams("assinatura");
        int cpf = Integer.parseInt(request.queryParams("cpf"));


        String resp = "";

        Usuario usuario = new Usuario(-1, nome, email, telefone, cidade, assinatura, cpf, senha);

        if(usuarioDAO.insert(usuario) == true) {
            resp = "Usuario (" + nome + ") cadastrado!";
            response.status(201); // 201 Created
        } else {
            resp = "Usuario (" + nome + ") não cadastrado!";
            response.status(404); // 404 Not found
        }

        makeForm();
        return form.replaceFirst("<input type=\"hidden\" id=\"msg\" title=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" title=\"msg\" value=\""+ resp +"\">");
    }


    public Object get(Request request, Response response) {
            int id = Integer.parseInt(request.params(":id"));
        Usuario usuario = (Usuario)usuarioDAO.get(id);

        if (usuario != null) {
            response.status(200); // success
            makeForm(FORM_DETAIL, usuario, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Usuario " + id + " não encontrado.";
            makeForm();
            form.replaceFirst("<input type=\"hidden\" id=\"msg\" title=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" title=\"msg\" value=\""+ resp +"\">");
        }

        return form;
    }


    public Object getToUpdate(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Usuario usuario = (Usuario) usuarioDAO.get(id);

        if (usuario != null) {
            response.status(200); // success
            makeForm(FORM_UPDATE, usuario, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Usuario " + id + " não encontrado.";
            makeForm();
            form.replaceFirst("<input type=\"hidden\" id=\"msg\" title=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" title=\"msg\" value=\""+ resp +"\">");
        }

        return form;
    }


    public Object getAll(Request request, Response response) {
        int orderBy = Integer.parseInt(request.params(":orderby"));
        makeForm(orderBy);
        response.header("Content-Type", "text/html");
        response.header("Content-Encoding", "UTF-8");
        return form;
    }

    public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Usuario usuario = usuarioDAO.get(id);
        String resp = "";

        if (usuario != null) {
            usuario.setNome(request.queryParams("nome"));
            usuario.setEmail(request.queryParams("email"));
            usuario.setTelefone(Integer.parseInt(request.queryParams("telefone")));
            usuario.setCidade(request.queryParams("cidade"));
            usuario.setAssinatura(request.queryParams("assinatura"));
            usuarioDAO.update(usuario);
            response.status(200); // success
            resp = "Usuario (CPF " + usuario.getID() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Usuario (CPF \" + usuario.getID() + \") não encontrado!";
        }
        makeForm();
        return form.replaceFirst("<input type=\"hidden\" id=\"msg\" title=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" title=\"msg\" value=\""+ resp +"\">");
    }


    public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Usuario usuario = usuarioDAO.get(id);
        String resp = "";

        if (usuario != null) {
            usuarioDAO.delete(id);
            response.status(200); // success
            resp = "Usuario (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Usuario (" + id + ") não encontrado!";
        }
        makeForm();
        return form.replaceFirst("<input type=\"hidden\" id=\"msg\" title=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" title=\"msg\" value=\""+ resp +"\">");
    }
}