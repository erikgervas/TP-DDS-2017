package Modelo.Metodologias.Condiciones;

import java.math.BigDecimal;
import java.util.List;
import Modelo.Indicadores.Indicador;
import Modelo.Metodologias.Try;
import Modelo.Metodologias.Condiciones.Comportamiento;

public class SiempreCreciente extends Comportamiento {

	public SiempreCreciente(Indicador indicador, int anios) {
		super(indicador, anios);
	}

	@Override
	protected boolean satisface(List<Boolean> comportamiento) {
		
		return comportamiento.stream().allMatch(bool -> bool);
	}

	@Override
	protected boolean comparacion(Try<BigDecimal> try1, Try<BigDecimal> try2) {
		
		return this.esMenor(try1, try2);
	}
	
	@Override
	public String mostrarCadena() {
		return "El indicador " + indicador.getNombre() + " es siempre creciente en " + String.valueOf(anios) + " a�os";
	}

}
