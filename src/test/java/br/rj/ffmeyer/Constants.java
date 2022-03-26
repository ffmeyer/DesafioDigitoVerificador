package br.rj.ffmeyer;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Constants {

	public final static String INPUT_FILENAME = "matriculasSemDV.txt";
	public final static String OUTPUT_FILENAME = "matriculasComDV.txt";
	
	public final static String BASE_PATH = System.getProperty("user.dir");	
	public final static String RESOURCE_PATH = BASE_PATH + File.separator + "resource" + File.separator + INPUT_FILENAME;
	public final static String RESOURCE_FOLDER = BASE_PATH + File.separator + "resource";
	public final static String OUTPUT_PATH = BASE_PATH + File.separator + "resource" + File.separator + OUTPUT_FILENAME;
	
	
	public static String buildMatricula(String id, String verificador) {
		return id + "-" + verificador;
	}
	
	public static List<String> getInputData(){
		return Arrays.asList("1026", "1118", "3609", "4687", "5107", "6168", "6751", "7708", "7937", "8553");
	}
	
	public static List<String> getOutPutData(){
		return Arrays.asList("1026-7", "1118-C", "3609-9", "4687-2", "5107-B", "6168-4", "6751-B", "7708-F", "7937-E", "8553-1");
	}
	
}

