package br.rj.ffmeyer;
import static br.rj.ffmeyer.Constants.buildMatricula;
import static digito.Main.generateCheckDigit;
import static digito.Main.readLines;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;


public class GeradorTest {
		
	
	@Test(expected=Exception.class) 
	public void validarDadosDeEntradaNULL() throws Exception {		
		String pathInvalido = null;
		assertEquals("Dados de entrada deveriam ser NULL", Constants.getInputData(), readLines(pathInvalido));		
	}
	
	@Test (expected=FileNotFoundException.class) 
	public void validarDadosDeEntradaInexistente() throws Exception {
		String invalidFilename = "invalidFileName.txt";
		String pathInvalido = Constants.RESOURCE_FOLDER + File.separator +  invalidFilename;
		assertEquals("Path Inexistente", Constants.getInputData(), readLines(pathInvalido));		
	}
	
	@Test (expected=AssertionError.class)  // o uso direto da aplicacao retorna "java.lang.Exception"
	public void validarDadosDeEntradaExtensaoInvalida() throws Exception {		
		String invalidFileExtension = "pdfTemplate.pdf";
		String path = Constants.RESOURCE_FOLDER + File.separator + invalidFileExtension;		
		assertEquals("Extensao invalida", Constants.getInputData(), readLines(path));		
	}
	
	@Test (expected=NumberFormatException.class) 
	public void validarDadosDeEntradaAlfanumerica() throws Exception {		
		String filename = "matriculaAlfanumerica.txt";
		String path = Constants.RESOURCE_FOLDER + File.separator + filename;
		//le o arquivo com conteudo ABCD
		ArrayList <String>  invalidID = (ArrayList<String>) readLines(path);
		//transforma em String para validacao
		String matricula = invalidID.get(0).toString();
		assertEquals("Matricula com dados alphanumericos",
				Constants.getOutPutData().get(0),
				buildMatricula(Constants.getInputData().get(0), generateCheckDigit(matricula)));
	}

	@Test (expected=Exception.class) 
	public void validarArquivoVazio() throws Exception {
		//tratado como string vazia para metodo que gera o digito verificador
		String vazia = "";
		assertEquals("Arquivo vazio",
				Constants.getOutPutData().get(0),
				buildMatricula(Constants.getInputData().get(0), generateCheckDigit(vazia)));
	}
		
	@Test 
	public void validarDadosDeEntrada() throws Exception {		
		assertEquals("Dados de entrada, diferentes dos dados de saida", Constants.getInputData(), readLines(Constants.RESOURCE_PATH));
		assertEquals("Quantidade de matriculas diferentes", Constants.getInputData().size(), readLines(Constants.RESOURCE_PATH).size());
	}
	
	@Test 
	public void validarGeracaoDigitoVerificador() throws Exception {
		for (int i = 0; i < Constants.getInputData().size(); i++) {			
			String out = Constants.getOutPutData().get(i);
			String in = Constants.getInputData().get(i);
			assertEquals("Digito Verficador Incorreto", out, buildMatricula(Constants.getInputData().get(i), generateCheckDigit(in)));
		}		
	}
	



	
	
	
}
