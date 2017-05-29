package Archivo;

import java.util.List;

import Modelo.BolsaDeEmpresas;
import Modelo.Empresa;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Instanciador_Bolsa_Empresas {
	
	public BolsaDeEmpresas instanciar() throws IOException {
		BolsaDeEmpresas bolsaEmpresa = new BolsaDeEmpresas();
		bolsaEmpresa.setEmpresas(obtenerEmpresas());
		
		return bolsaEmpresa;
	}
	
	private List<Empresa> obtenerEmpresas() throws IOException {
	
		// Instancio el Lector de Archivos
		LectorDeArchivos miLector = new LectorDeArchivos();
		FileInputStream stream = (FileInputStream) miLector.getFile("cuentitasDeHector.csv");
		
		// Instancio el Parser
		CSVParser miParser = new CSVParser(",");
		List<Empresa> misEmpresas = new ArrayList<Empresa>();
		misEmpresas = miParser.parse(stream);
		
		return misEmpresas;
	}
	
}
