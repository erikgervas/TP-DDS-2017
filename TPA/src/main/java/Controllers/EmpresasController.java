package Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import DB.Repositorios.RepositorioEmpresas;
import Modelo.Empresa.Cuenta;
import Modelo.Empresa.Empresa;
import Modelo.Empresa.Periodo;
import Modelo.Usuarios.GestorDeUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class EmpresasController {


    public ModelAndView show(Request request, Response response){

        Map<Object, Object> modelo = GestorDeUsuarios.getInstance().obtenerMapa(request);
        modelo.put("empresas", RepositorioEmpresas.getInstancia().buscarListaDeObjetos());

        return new ModelAndView(modelo,"empresas.hbs");

    }

    public Void seleccionarEmpresa(Request request, Response response) {

        String nombreEmpresaElegida = request.queryParams("nombreEmpresaElegida");
        response.redirect("/empresas/" + nombreEmpresaElegida);

        return null;
    }

    public Void seleccionarPeriodo(Request request,Response response) {

        String nombreEmpresaElegida = request.params(":nombreEmpresaElegida");
        String nombrePeriodoElegido = request.queryParams("nombrePeriodoElegido");
        response.redirect("/empresas/" + nombreEmpresaElegida + "/" + nombrePeriodoElegido);

        return null;

    }

    public ModelAndView redireccionarPeriodoElegido(Request request, Response response) {

        Map<Object, Object> modelo = GestorDeUsuarios.getInstance().obtenerMapa(request);

        String nombreEmpresa = request.params(":nombreEmpresaElegida");
        String nombrePeridoElegido = request.params(":nombrePeriodoElegido");
        Empresa empresaElegida = RepositorioEmpresas.getInstancia().buscarObjeto(nombreEmpresa);
        Periodo periodoElegido = empresaElegida.getPeriodos().stream().filter(p -> p.getAnio() == Integer.parseInt(nombrePeridoElegido)).collect(Collectors.toList()).get(0);

        List<Cuenta> listaCuentasDelPeriodo = new ArrayList<>();
        listaCuentasDelPeriodo = periodoElegido.getCuentas();

        modelo.put("listaDeCuentas",listaCuentasDelPeriodo);
        modelo.put("nombreEmpresaElegida",nombreEmpresa);
        modelo.put("nombrePeriodoElegido",nombrePeridoElegido);

        return new ModelAndView(modelo,"empresas.hbs");

    }

    public ModelAndView redireccionarEmpresaElegida(Request request, Response response) {

        Map<Object, Object> modelo = GestorDeUsuarios.getInstance().obtenerMapa(request);

        String nombreEmpresa = request.params(":nombreEmpresaElegida");
        modelo.put("nombreEmpresaElegida",nombreEmpresa);
        modelo.put("periodos",RepositorioEmpresas.getInstancia().buscarObjeto(nombreEmpresa).getPeriodos());


        return  new ModelAndView(modelo,"empresas.hbs");

    }

    public ModelAndView creacionEmpresas(Request request, Response response) {
        Map<Object, Object> mapa = GestorDeUsuarios.getInstance().obtenerMapa(request);

        if (mapa.get("email") == null) {

            response.redirect("/login");
            return null;

        }

        return new ModelAndView(mapa, "empresasCreacion.hbs");
    }

}
