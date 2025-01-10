package modelos;

import java.util.Stack;

public class Pila {

	private Stack<Integer> discos;
	private String nombre;

	// Constructor
	public Pila(String nombre) {
		this.nombre = nombre;
		this.discos = new Stack<>();
	}

	
	public void apilar(int disco) {
		
		if (!discos.isEmpty() && discos.peek() < disco) {
			
			throw new IllegalStateException("No se puede colocar un disco mas grande sobre uno mas pequeño");
			
		}
		
		discos.push(disco);
		
	}

	public int desapilar() {
		
		if (discos.isEmpty()) {
			
			throw new IllegalStateException("No hay discos para desapilar");
			
		}
		
		return discos.pop();
		
	}

	public boolean esVacia() {
		
		return discos.isEmpty();
		
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return nombre + " " + discos.toString();
	}
	
}