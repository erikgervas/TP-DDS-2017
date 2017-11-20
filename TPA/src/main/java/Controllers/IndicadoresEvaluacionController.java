package Controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import DB.Excepciones.NoEstaEnCacheException;
import DB.Repositorios.RepositorioEmpresas;
import DB.Repositorios.RepositorioIndicadores;
import DB.Repositorios.RepositorioPrecalculados;
import DB.Repositorios.RepositorioUsuarios;
import Modelo.Empresa.Empresa;
import Modelo.Excepciones.Indicadores.NoTieneLaCuentaException;
import Modelo.Indicadores.Indicador;
import Modelo.Indicadores.Precalculado;
import Modelo.Indicadores.Query;
import Modelo.Usuarios.GestorDeUsuarios;
import Modelo.Usuarios.Usuario;
import com.mongodb.client.model.Filters;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadoresEvaluacionController {

	   public ModelAndView show(Request request, Response response){

		   Map<Object, Object> mapa = GestorDeUsuarios.getInstance().obtenerMapa(request);

		   if (mapa.get("email") != null){

			   Usuario usuario = RepositorioUsuarios.getInstancia().buscarObjeto((String) mapa.get("email"));

			   List<Indicador> indicadores = RepositorioIndicadores.getInstancia().buscarListaDeObjetosDeUsuario(usuario);

			   mapa.put("indicadores", indicadores);

			   return new ModelAndView(mapa,"indicadoresEvaluacion.hbs");

		   } else {
			   response.redirect("/login");
			   return null;
		   }
	   }

	public Void seleccionarIndicador(Request request, Response response) {

		String nombreIndicador = request.queryParams("nombreIndicador");

		response.redirect("/indicadores/evaluacion/" + nombreIndicador);

		return null;

	}

	public ModelAndView redireccionarIndicadorElegido(Request request, Response response) {

		Map<Object, Object> mapa = GestorDeUsuarios.getInstance().obtenerMapa(request);
		
		String nombreIndicador = request.params(":nombreIndicador");

		Indicador indicadorElegido =  RepositorioIndicadores.getInstancia().buscarObjeto(nombreIndicador);

		mapa.put("nombreIndicadorSeleccionado", nombreIndicador);
		mapa.put("formula", indicadorElegido.imprimirFormula());
		mapa.put("empresas", RepositorioEmpresas.getInstancia().buscarListaDeObjetos());

		return new ModelAndView(mapa, "indicadoresEvaluacion.hbs");

	}
	
	public Void seleccionarEmpresa(Request request, Response response) {

		String nombreIndicador = request.params(":nombreIndicador");
		String nombreEmpresa = request.queryParams("nombreEmpresa");

		response.redirect("/indicadores/evaluacion/" + nombreIndicador + "/"+ nombreEmpresa);

		return null;

	}
	
	public ModelAndView redireccionarEmpresaElegida(Request request, Response response) {
		
		Map<Object, Object> mapa = GestorDeUsuarios.getInstance().obtenerMapa(request);
		
		String nombreEmpresa = request.params(":nombreEmpresa");
		String nombreIndicador = request.params(":nombreIndicador");
		
		Indicador indicadorElegido = RepositorioIndicadores.getInstancia().buscarObjeto(nombreIndicador);
		
		Empresa empresaElegida = RepositorioEmpresas.getInstancia().buscarObjeto(nombreEmpresa);
		
		mapa.put("nombreIndicadorSeleccionado", nombreIndicador);
		mapa.put("nombreEmpresaSeleccionada", nombreEmpresa);
		mapa.put("formula", indicadorElegido.imprimirFormula());
		mapa.put("periodos", empresaElegida.getPeriodos());
		
		return new ModelAndView(mapa, "indicadoresEvaluacion.hbs");
	}
	
	public Void seleccionarPeriodo(Request request, Response response) {

		String nombreIndicador = request.params(":nombreIndicador");
		String nombreEmpresa = request.params(":nombreEmpresa");
		String anio = request.queryParams("periodo");

		response.redirect("/indicadores/evaluacion/" + nombreIndicador +  "/" + nombreEmpresa + "/" + anio);

		return null;

	}
	
	public ModelAndView redireccionarPeriodoElegido(Request request, Response response) {
		
		Map<Object, Object> mapa = GestorDeUsuarios.getInstance().obtenerMapa(request);
		
		String nombreEmpresa = request.params(":nombreEmpresa");
		String nombreIndicador = request.params(":nombreIndicador");
		int periodo = Integer.parseInt(request.params(":periodo"));
		
		Indicador indicadorElegido = RepositorioIndicadores.getInstancia().buscarObjeto(nombreIndicador);
		Empresa empresaElegida = RepositorioEmpresas.getInstancia().buscarObjeto(nombreEmpresa);

		BigDecimal resultado;
		
		mapa.put("nombreIndicadorSeleccionado", nombreIndicador);
		mapa.put("nombreEmpresaSeleccionada", nombreEmpresa);
		mapa.put("periodoSeleccionado", periodo);
		mapa.put("formula", indicadorElegido.imprimirFormula());/*

		try {

			String emailUsuario = (String)mapa.get("email");
			//Estoy seleccionando el id del periodo o que? Tambien esta query es gigante, es horrible, lo debo meter en el gestor de cache aunque no haga nada mas que pedirle cosas al repo?
<<<<<<< HEAD
			resultado = new BigDecimal(RepositorioPrecalculados.getInstancia().buscarObjetoPorQuery(Filters.and(Filters.eq("nombreIndicador",nombreIndicador),Filters.eq("nombreEmpresa",nombreEmpresa),Filters.eq("idPeriodo",periodo),Filters.eq("nombreUsuario",emailUsuario))).getValor());
=======
<<<<<<< HEAD
//			resultado = RepositorioPrecalculados.getInstancia().buscarObjetoPorQuery(Filters.and(Filters.eq("nombreIndicador",nombreIndicador),Filters.eq("nombreEmpresa",nombreEmpresa),Filters.eq("idPeriodo",periodo),Filters.eq("nombreUsuario",emailUsuario)));
=======
			resultado = RepositorioPrecalculados.getInstancia().buscarObjetoPorQuery(Filters.and(Filters.eq("nombreIndicador",nombreIndicador),Filters.eq("nombreEmpresa",nombreEmpresa),Filters.eq("idPeriodo",periodo),Filters.eq("nombreUsuario",emailUsuario))).getValor();
>>>>>>> 3fbc7eb4462e0ebdfdbfced2025832761a4c302a
			mapa.put("resultado",resultado);
			System.out.println("Lo saque de cache");
			return new ModelAndView(mapa,"indicadoresEvaluacion.hbs");
>>>>>>> 69c4835ec9a5ea66e6bcaa11f56f60657c8652bd

		}

		catch(NoEstaEnCacheException e) {

			try {
				resultado = indicadorElegido.calcular(new Query(empresaElegida,periodo));
				mapa.put("resultado", resultado);
				System.out.println("No lo saque de cache");
				agregarACache(mapa);
				return new ModelAndView(mapa, "indicadoresEvaluacion.hbs");
			}
			catch(NoTieneLaCuentaException e2) {

				return new ModelAndView(mapa, "indicadoresEvaluacionError.hbs");

			}

		}

	}

<<<<<<< HEAD
		public void agregarACache(Map<Object, Object> mapa){

			Precalculado objetoPrecalculado = obtenerObjetoPrecalculado(mapa);

			RepositorioPrecalculados.getInstancia().agregarObjeto(objetoPrecalculado);

		}

		private Precalculado obtenerObjetoPrecalculado(Map<Object, Object> mapa){

			String emailUsuario = (String)mapa.get("email");
			Usuario usuario = RepositorioUsuarios.getInstancia().buscarObjeto(emailUsuario);

			String nombreIndicador = (String)mapa.get("nombreIndicadorSeleccionado");
			Indicador indicador = RepositorioIndicadores.getInstancia().buscarObjeto(nombreIndicador);

			String nombreEmpresa = (String)mapa.get("nombreEmpresaSeleccionada");
			Empresa empresa = RepositorioEmpresas.getInstancia().buscarObjeto(nombreEmpresa);

			int periodoSeleccionado = (int)mapa.get("periodoSeleccionado");

			BigDecimal resultado = (BigDecimal)mapa.get("resultado");

			Precalculado objetoPrecalculado = new Precalculado(usuario.getId(),indicador.getId(),empresa.getId(),empresa.buscarPeriodo(periodoSeleccionado).getId(),resultado);

			return  objetoPrecalculado;

		}
=======
>>>>>>> 3fbc7eb4462e0ebdfdbfced2025832761a4c302a

	}




