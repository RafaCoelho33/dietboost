package service;

import dao.AlimentoDAO;
import model.Alimento;
import spark.Request;
import spark.Response;

import java.io.File;
import java.util.List;
import java.util.Scanner;


public class AlimentoService {

	private AlimentoDAO alimentoDAO = new AlimentoDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_NOME = 2;

	private final int FORM_ORDERBY_GORDURAS = 3;
	private final int FORM_ORDERBY_CARBOIDRATOS = 4;
	private final int FORM_ORDERBY_PROTEINAS = 5;
	private final int FORM_ORDERBY_CALORIAS = 6;

	
	public AlimentoService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Alimento(), FORM_ORDERBY_NOME);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Alimento(), orderBy);
	}

	
	public void makeForm(int tipo, Alimento alimento, int orderBy) {
		String nomeArquivo = "form3.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umAlimento = "";
		if(tipo != FORM_INSERT) {
			umAlimento += "\t<table width=\"80%\" align=\"center\">";
			umAlimento += "\t\t<tr>";
			umAlimento += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/alimento/list/1\">Novo Alimento</a></b></font></td>";
			umAlimento += "\t\t</tr>";
			umAlimento += "\t</table>";
			umAlimento += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/alimento/";
			String title, nome, buttonLabel, buttonLabel2;
			if (tipo == FORM_INSERT){
				action += "insert";
				title = "Inserir Alimento";
				nome = "Insira o nome do seu alimento";
				buttonLabel = "Inserir";
				buttonLabel2 = "Voltar";
			} else {
				action += "update/" + alimento.getID();
				title = "Atualizar Alimento (ID " + alimento.getID() + ")";
				nome = alimento.getNome();
				buttonLabel = "Atualizar";
				buttonLabel2 = "Voltar";
			}
			umAlimento += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umAlimento += "\t<table width=\"80%\" align=\"center\">";
			umAlimento += "\t\t<tr>";
			umAlimento += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>" + title + "</b></font></td>";
			umAlimento += "\t\t</tr>";
			umAlimento += "\t\t<tr>";
			umAlimento += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umAlimento += "\t\t</tr>";
			umAlimento += "\t\t<tr>";
			umAlimento += "\t\t\t<td>&nbsp;Nome: <input class=\"input--register\" autocomplete=\"off\" type=\"text\" name=\"nome\" placeholder=\""+ nome +"\"></td>";
			umAlimento += "\t\t\t<td>Gorduras: <input class=\"input--register\" type=\"text\" name=\"gorduras\" value=\""+ alimento.getGorduras() +"\"></td>";
			umAlimento += "\t\t\t<td>Carboidratos: <input class=\"input--register\" type=\"text\" name=\"carboidratos\" value=\""+ alimento.getCarboidratos() +"\"></td>";
			umAlimento += "\t\t\t<td>Proteinas: <input class=\"input--register\" type=\"text\" name=\"proteinas\" value=\""+ alimento.getProteinas() +"\"></td>";
			umAlimento += "\t\t\t<td>Calorias: <input class=\"input--register\" type=\"text\" name=\"calorias\" value=\""+ alimento.getCalorias() +"\"></td>";
			umAlimento += "\t\t</tr>";
			umAlimento += "\t\t<tr>";
			umAlimento += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umAlimento += "\t\t\t<a class=\"btn btn-light\" href=\"/./frontend2.html\">" + buttonLabel2 + "</a>";
			umAlimento += "\t\t</tr>";
			umAlimento += "\t</table>";
			umAlimento += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umAlimento += "\t<table width=\"80%\" align=\"center\" color=\"white\">";
			umAlimento += "\t\t<tr>";
			umAlimento += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar alimento (ID " + alimento.getID() + ")</b></font></td>";
			umAlimento += "\t\t</tr>";
			umAlimento += "\t\t<tr>";
			umAlimento += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umAlimento += "\t\t</tr>";
			umAlimento += "\t\t<tr>";
			umAlimento += "\t\t\t<td>&nbsp;Descrição: "+ alimento.getNome() +"</td>";
			umAlimento += "\t\t\t<td>Gorduras: "+ alimento.getGorduras() +"</td>";
			umAlimento += "\t\t\t<td>Carboidratos: "+ alimento.getCarboidratos() +"</td>";
			umAlimento += "\t\t\t<td>Proteinas: "+ alimento.getProteinas() +"</td>";
			umAlimento += "\t\t\t<td>Calorias: "+ alimento.getCalorias() +"</td>";
			umAlimento += "\t\t</tr>";
			umAlimento += "\t\t<tr>";
			umAlimento += "\t\t\t<td>&nbsp;</td>";
			umAlimento += "\t\t</tr>";
			umAlimento += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-ALIMENTO>", umAlimento);
		
		String list = new String("<table width=\"80%\" align=\"center\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>Tabela de Alimentos</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/alimento/list/" + FORM_ORDERBY_ID + "\"><b>ID</b></a></td>\n" +
				"\t<td><a href=\"/alimento/list/" + FORM_ORDERBY_NOME + "\"><b>Nome</b></a></td>\n" +
				"\t<td><a href=\"/alimento/list/" + FORM_ORDERBY_GORDURAS + "\"><b>Gorduras</b></a></td>\n" +
        		"\t<td><a href=\"/alimento/list/" + FORM_ORDERBY_CARBOIDRATOS + "\"><b>Carboidratos</b></a></td>\n" +
				"\t<td><a href=\"/alimento/list/" + FORM_ORDERBY_PROTEINAS + "\"><b>Proteinas</b></a></td>\n" +
				"\t<td><a href=\"/alimento/list/" + FORM_ORDERBY_CALORIAS + "\"><b>Calorias</b></a></td>\n" +
				"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Alimento> alimentos;
		if (orderBy == FORM_ORDERBY_ID) {                 	alimentos = alimentoDAO.getOrderByID();
		} else if (orderBy == FORM_ORDERBY_NOME) {		alimentos = alimentoDAO.getOrderByNome();
		} else if (orderBy == FORM_ORDERBY_CARBOIDRATOS) {			alimentos = alimentoDAO.getOrderByCarboidrato();
		} else {											alimentos = alimentoDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Alimento p : alimentos) {
//			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr>\n" +
            		  "\t<td>" + p.getID() + "</td>\n" +
            		  "\t<td>" + p.getNome() + "</td>\n" +
            		  "\t<td>" + p.getGorduras() + "</td>\n" +
					  "\t<td>" + p.getCarboidratos() + "</td>\n" +
					  "\t<td>" + p.getProteinas() + "</td>\n" +
					  "\t<td>" + p.getCalorias() + "</td>\n" +
					"\t<td align=\"center\" valign=\"middle\"><a href=\"/alimento/" + p.getID() + "\"><i class=\"fa-solid fa-magnifying-glass\"></i></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/alimento/update/" + p.getID() + "\"><i class=\"fa-regular fa-pen-to-square\"></i></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteAlimento('" + p.getID() + "');\"><i class=\"fa-solid fa-trash\"></i></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-ALIMENTO>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
		String nome = request.queryParams("nome");
		float gorduras = Float.parseFloat(request.queryParams("gorduras"));
		float carboidratos = Float.parseFloat(request.queryParams("carboidratos"));
		float proteinas = Float.parseFloat(request.queryParams("proteinas"));
		float calorias = Float.parseFloat(request.queryParams("calorias"));
		
		String resp = "";
		
		Alimento alimento = new Alimento(-1, nome, gorduras, carboidratos, proteinas, calorias);
		
		if(alimentoDAO.insert(alimento) == true) {
            resp = "Alimento (" + nome + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Alimento (" + nome + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" title=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" title=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Alimento alimento = (Alimento)alimentoDAO.get(id);
		
		if (alimento != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, alimento, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Alimento " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" title=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" title=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Alimento alimento = (Alimento) alimentoDAO.get(id);
		
		if (alimento != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, alimento, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Alimento " + id + " não encontrado.";
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
		Alimento alimento = alimentoDAO.get(id);
        String resp = "";       

        if (alimento != null) {
        	alimento.setNome(request.queryParams("nome"));
        	alimento.setGorduras(Float.parseFloat(request.queryParams("gorduras")));
        	alimento.setCarboidratos(Float.parseFloat(request.queryParams("carboidratos")));
			alimento.setProteinas(Float.parseFloat(request.queryParams("proteinas")));
			alimento.setCalorias(Float.parseFloat(request.queryParams("calorias")));
        	alimentoDAO.update(alimento);
        	response.status(200); // success
            resp = "Alimento (ID " + alimento.getID() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Alimento (ID \" + alimento.getId() + \") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" title=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" title=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Alimento alimento = alimentoDAO.get(id);
        String resp = "";       

        if (alimento != null) {
            alimentoDAO.delete(id);
            response.status(200); // success
            resp = "Alimento (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Alimento (" + id + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" title=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" title=\"msg\" value=\""+ resp +"\">");
	}
}