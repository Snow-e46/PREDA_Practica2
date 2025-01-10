package utilidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Lector {

	public static File fichero;

	// Leer parametros desde un archivo
	public static int[] leerParametros() throws IOException, FileNotFoundException {

		try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
			
			String linea = br.readLine();
			
			if (linea == null || linea.isEmpty()) throw new IOException("El fichero de entrada esta vacio o tiene formato incorrecto");

			String[] partes = linea.split(" ");
			
			if (partes.length != 3) throw new IOException("El fichero debe contener exactamente tres numeros enteros");

			int posteOrigen = Integer.parseInt(partes[0]);
			int posteDestino = Integer.parseInt(partes[1]);
			int numDiscos = Integer.parseInt(partes[2]);

			if (posteOrigen < 1 || posteOrigen > 3 || posteDestino < 1 || posteDestino > 3 || posteOrigen == posteDestino) {
				
				throw new IOException("Los postes deben estar entre 1 y 3, y ser diferentes entre si");
			}

			if (numDiscos <= 0) throw new IOException("El numero de discos debe ser un entero positivo");

			return new int[] { posteOrigen, posteDestino, numDiscos };
			
		}
		
	}

	// Escribir movimientos en un archivo
	public static void escribirSalida(String ficheroSalida, ArrayList<String> movimientos) {
		
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroSalida))) {
	    	
	        for (String movimiento : movimientos) {
	        	
	            bw.write(movimiento + "\n");
	            
	        }
	        
	        System.out.println("Resultado guardado en " + ficheroSalida);
	        
	    } catch (IOException e) {
	    	
	        System.err.println("Error al escribir el archivo de salida: " + e.getMessage());
	        
	    }
	    
	}
	
}
