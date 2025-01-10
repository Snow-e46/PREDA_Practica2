package servicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import modelos.Hanoi;
import utilidades.Lector;

public class Principal {

	public static void main(String[] args) {

		// Compruebo si se ha pasado algun argumento
		if (args.length < 1) {

			System.err.println("Cantidad de argumentos insuficientes");

		} else {

			// Organizacion segun la cantidad de parametros adjuntos

			// Lógica para 1 parámetro
			if (args.length == 1) {

				// Posibilidads: -t // -h // fichero_entrada

				// Condicional para mostrar ayuda
				if (args[0].equalsIgnoreCase("-t")) {

					System.err.println("Faltan argumentos, puede solicitar ayuda introduciendo \"-h\"");

				} else if (args[0].equalsIgnoreCase("-h")) {

					imprimirAyuda();

				} else {

					Lector.fichero = new File(args[0]);

					ejecutarLogica(" ", false); // Sin trazas

				}

				// Lógica para 2 parámetros
			} else if (args.length == 2) {

				// Posibilidades: -t -h // -t fichero_entrada // -h fichero_entrada //
				// fichero_entrada fichero_salida

				if ((args[0].equalsIgnoreCase("-t")) && (args[1].equalsIgnoreCase("-h"))) {

					imprimirAyuda();

				} else if ((args[0].equalsIgnoreCase("-h")) && (args[1].equalsIgnoreCase("-t"))) {

					System.err.println("Argumentos erroneos, puede solicitar ayuda introduciendo \"-h\"");

				} else if (args[0].equalsIgnoreCase("-t")) {
					// Lógica trazada con fichero_entrada
					Lector.fichero = new File(args[1]);
					ejecutarLogica(" ", true);

				} else if (args[0].equalsIgnoreCase("-h")) {

					imprimirAyuda();
					Lector.fichero = new File(args[1]);
					ejecutarLogica(" ", false);

				} else {

					Lector.fichero = new File(args[0]);
					ejecutarLogica(args[1], false);

				}

				// Lógica para 3 parámetros
			} else if (args.length == 3) {

				// Posibilidades: -t -h fichero_entrada // -t fichero_entrada fichero_salida //
				// -h fichero_entrada fichero_salida

				if ((args[0].equalsIgnoreCase("-t")) && (args[1].equalsIgnoreCase("-h"))) {

					imprimirAyuda();
					Lector.fichero = new File(args[2]);
					ejecutarLogica(" ", true);

				} else if ((args[0].equalsIgnoreCase("-h")) && (args[1].equalsIgnoreCase("-t"))) {

					System.err.println("Argumentos erroneos, puede solicitar ayuda introduciendo \"-h\"");

				} else if (args[0].equalsIgnoreCase("-t")) {
					// Lógica trazada con fichero_entrada
					Lector.fichero = new File(args[1]);
					ejecutarLogica(args[2], true);

				} else if (args[0].equalsIgnoreCase("-h")) {

					imprimirAyuda();
					Lector.fichero = new File(args[1]);
					ejecutarLogica(args[2], false);

				}

				// Lógica para 4 parámetros
			} else if (args.length == 4) {

				// Implementacion logica para los 4 argumentos

				if (args[1].equalsIgnoreCase("-h")) {

					imprimirAyuda();

					Lector.fichero = new File(args[2]);

					ejecutarLogica(args[3], true);

				} else {

					System.err.println("Argumentos erroneos, puede solicitar ayuda introduciendo \"-h\"");
				}

			} else {

				System.err.println("Argumentos erroneos, puede solicitar ayuda introduciendo \"-h\"");

			}

		}

	}

	public static void imprimirAyuda() {

		System.out.println("SINTAXIS: hanoi [-t] [-h] [fichero entrada] [fichero salida]");
		System.out.println("-t \t \t \t Traza el algoritmo");
		System.out.println("-h \t \t \t Muestra esta ayuda");
		System.out.println("[fichero entrada] \t Nombre del fichero de entrada");
		System.out.println("[fichero salida] \t Nombre del fichero de salida");

	}

	public static void ejecutarLogica(String ficheroSalida, boolean trazar) {

		try {

			// Leer parámetros desde el archivo de entrada
			int[] parametros = Lector.leerParametros();
			int posteOrigen = parametros[0];
			int posteDestino = parametros[1];
			int numDiscos = parametros[2];

			// Inicializar Hanoi y resolver
			Hanoi hanoi = new Hanoi(numDiscos, posteOrigen);
			ResolvedorHanoi resolver = new ResolvedorHanoi(trazar);

			resolver.resolver(hanoi, numDiscos, posteOrigen, posteDestino, 6 - posteOrigen - posteDestino);

			// Salida de resultados
			if (!ficheroSalida.equals(" ")) {
				
				Lector.escribirSalida(ficheroSalida, resolver.getMovimientos());
				
			} else {
				
				resolver.imprimirMovimientos();
				
			}

		} catch (FileNotFoundException e) {
			
			// Logica para pedir los datos por entrada estandar
			System.err.println("No se ha encontrado " + Lector.fichero.getName() + " como [fichero entrada]");

			Scanner sc = new Scanner(System.in);

			System.out.println("Introduzca el poste de origen: ");
			int posteOrigen = sc.nextInt();

			System.out.println("Introduzca el poste de destino: ");
			int posteDestino = sc.nextInt();

			System.out.println("Introduzca el numero de discos: ");
			int numDiscos = sc.nextInt();

			// Inicializar Hanoi y resolver
			Hanoi hanoi = new Hanoi(numDiscos, posteOrigen);
			ResolvedorHanoi resolver = new ResolvedorHanoi(trazar);

			resolver.resolver(hanoi, numDiscos, posteOrigen, posteDestino, 6 - posteOrigen - posteDestino);

			// Salida de resultados
			if (!ficheroSalida.equals(" ")) {
				
				Lector.escribirSalida(ficheroSalida, resolver.getMovimientos());
				
			} else {
				
				resolver.imprimirMovimientos();
				
			}

		} catch (IOException e) {

			System.err.println("Error al leer el fichero de entrada: " + e.getMessage());

		} catch (Exception e) {

			System.err.println("Error: " + e.getMessage());

		}

	}

}
