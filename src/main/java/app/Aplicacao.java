package app;

import service.UsuarioService;
import service.AlimentoService;

import static spark.Spark.*;

public class Aplicacao {
    private static UsuarioService usuarioService = new UsuarioService();
    private static AlimentoService alimentoService = new AlimentoService();

    public static void main(String[] args) {
        port(6789);

        staticFiles.location("/public");

        post("/usuario/cadastro", (request, response) -> usuarioService.insert(request, response));

        get("/usuario/:id", (request, response) -> usuarioService.get(request, response));

        get("/usuario/list/:orderby", (request, response) -> usuarioService.getAll(request, response));

        get("/usuario/update/:id", (request, response) -> usuarioService.getToUpdate(request, response));

        post("/usuario/update/:id", (request, response) -> usuarioService.update(request, response));

        get("/usuario/delete/:id", (request, response) -> usuarioService.delete(request, response));

        get("/usuario/delete/:cpf", (request, response) -> usuarioService.delete(request, response));

        post("/alimento/insert", (request, response) -> alimentoService.insert(request, response));

        get("/alimento/:id", (request, response) -> alimentoService.get(request, response));

        get("/alimento/list/:orderby", (request, response) -> alimentoService.getAll(request, response));

        get("/alimento/update/:id", (request, response) -> alimentoService.getToUpdate(request, response));

        post("/alimento/update/:id", (request, response) -> alimentoService.update(request, response));

        get("/alimento/delete/:id", (request, response) -> alimentoService.delete(request, response));


    }
}