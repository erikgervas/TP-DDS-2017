package TestMetodologias.TestCondiciones;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

import DB.Repositorios.EmpresasRepository;
import DB.Repositorios.IndicadoresRepository;
import Modelo.Empresa.Empresa;
import Modelo.Indicadores.Indicador;
import Modelo.Metodologias.Condiciones.Condicion;
import Modelo.Metodologias.Condiciones.CondicionFactory;

public class TestMenorAValorEnPeriodos extends TestCondiciones {
	
	@Test
	public void CumpleSi_LaEmpresaTiene_TodasLasCuenta() {
		
		Indicador indicador = IndicadoresRepository.getInstancia().getIndicadores().get(4);
		Condicion condicion = new CondicionFactory().crearMenorAEnPeriodos(indicador, new BigDecimal(20000), 1);
		Empresa empresa = EmpresasRepository.getInstancia().buscarEmpresa("Rolito");
		
		Assert.assertTrue(condicion.cumple(empresa));
	}
	
	
	@Test
	public void noCumpleSi_LaEmpresaNoTiene_AlgunaCuenta() {
		
		Indicador indicador = IndicadoresRepository.getInstancia().getIndicadores().get(4);
		Condicion condicion = new CondicionFactory().crearMenorAEnPeriodos(indicador, new BigDecimal(1), 1);
		Empresa empresa = EmpresasRepository.getInstancia().buscarEmpresa("Rip SA");
		
		Assert.assertFalse(condicion.cumple(empresa));
	}
	
	@Test
	public void fallaSi_NoTieneLaCantidadDePeriodos_QueElUsuarioQuiereEvaluar() {
		
		Indicador indicador = IndicadoresRepository.getInstancia().getIndicadores().get(4);
		Condicion condicion = new CondicionFactory().crearMenorAEnPeriodos(indicador, new BigDecimal(1), 15);
		Empresa empresa = EmpresasRepository.getInstancia().buscarEmpresa("Rolito");
		
		Assert.assertFalse(condicion.cumple(empresa));
	}
	
	@Test
	public void cumpleSiElValor_DelIndicadorQueSoloTieneUnNumero_EsMenorAlQuePusoElUsuario(){
		Indicador indicador = IndicadoresRepository.getInstancia().getIndicadores().get(3);
		Condicion condicion = new CondicionFactory().crearMenorAEnPeriodos(indicador, new BigDecimal(20000), 1);
		Empresa empresa = EmpresasRepository.getInstancia().buscarEmpresa("Rolito");
		
		Assert.assertTrue(condicion.cumple(empresa));
	}
	
	@Test
	public void noCumpleSiElValor_DelIndicadorQueSoloTieneUnNumero_EsMayorAlQuePusoElUsuario(){
		Indicador indicador = IndicadoresRepository.getInstancia().getIndicadores().get(3);
		Condicion condicion = new CondicionFactory().crearMenorAEnPeriodos(indicador, new BigDecimal(1), 1);
		Empresa empresa = EmpresasRepository.getInstancia().buscarEmpresa("Rolito");
		
		Assert.assertFalse(condicion.cumple(empresa));
	}
	
	@Test
	public void cumpleSiElValor_DelIndicadorQueTieneOtroIndicadorDentro_EsMenorAlQuePusoElUsuario(){
		Indicador indicador = IndicadoresRepository.getInstancia().getIndicadores().get(2);
		Condicion condicion = new CondicionFactory().crearMenorAEnPeriodos(indicador, new BigDecimal(20000), 1);
		Empresa empresa = EmpresasRepository.getInstancia().buscarEmpresa("Rolito");
		
		Assert.assertTrue(condicion.cumple(empresa));
	}
	
	@Test
	public void noCumpleSiElValor_DelIndicadorQueTieneOtroIndicadorDentro_EsMayorAlQuePusoElUsuario(){
		Indicador indicador = IndicadoresRepository.getInstancia().getIndicadores().get(2);
		Condicion condicion = new CondicionFactory().crearMenorAEnPeriodos(indicador, new BigDecimal(1), 1);
		Empresa empresa = EmpresasRepository.getInstancia().buscarEmpresa("Rolito");
		
		Assert.assertFalse(condicion.cumple(empresa));
	}
	
	@Test
	public void cumpleSiElValor_DelIndicadorQueTieneOperacionesrDentro_EsMenorAlQuePusoElUsuario(){
		Indicador indicador = IndicadoresRepository.getInstancia().getIndicadores().get(5);
		Condicion condicion = new CondicionFactory().crearMenorAEnPeriodos(indicador, new BigDecimal(80000), 1);
		Empresa empresa = EmpresasRepository.getInstancia().buscarEmpresa("Rolito");
		
		Assert.assertTrue(condicion.cumple(empresa));
	}
	
	@Test
	public void noCumpleSiElValor_DelIndicadorQueTieneOperacionesrDentro_EsMayorAlQuePusoElUsuario(){
		Indicador indicador = IndicadoresRepository.getInstancia().getIndicadores().get(5);
		Condicion condicion = new CondicionFactory().crearMenorAEnPeriodos(indicador, new BigDecimal(1), 1);
		Empresa empresa = EmpresasRepository.getInstancia().buscarEmpresa("Rolito");
		
		Assert.assertFalse(condicion.cumple(empresa));
	}

}
