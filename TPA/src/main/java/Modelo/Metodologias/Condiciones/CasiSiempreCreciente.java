package Modelo.Metodologias.Condiciones;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import Modelo.Empresa.Empresa;
import Modelo.Indicadores.Indicador;
import Modelo.Metodologias.Try;

public class CasiSiempreCreciente extends Comportamiento {

	public CasiSiempreCreciente(Indicador indicador, int anios) {
		super(indicador, anios);
	}

	@Override
	protected boolean satisface(List<Boolean> comportamiento) {
		
		return comportamiento.stream().filter(bool->bool == false).collect(Collectors.toList()).size() <= 1;
	}

	@Override
	protected boolean comparacion(Try<BigDecimal> try1, Try<BigDecimal> try2) {

		return this.esMenor(try1, try2);
	}

	@Override
	public String mostrarCadena() {
		return "El indicador " + indicador.getNombre() + " es casi siempre creciente en " + String.valueOf(anios) + " a�os";
	}
	
}