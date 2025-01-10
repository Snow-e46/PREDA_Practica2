package servicios;

import java.util.ArrayList;

import modelos.Hanoi;

public class ResolvedorHanoi {

	private boolean trazar;
    private ArrayList<String> movimientos;

    // Constructor
    public ResolvedorHanoi(boolean trazar) {
        this.trazar = trazar;
        this.movimientos = new ArrayList<>();
    }

    // Método recursivo para resolver las Torres de Hanoi
    public void resolver(Hanoi hanoi, int n, int origen, int destino, int auxiliar) {
        if (n == 0) return;

        // Mover n-1 discos al poste auxiliar
        resolver(hanoi, n - 1, origen, auxiliar, destino);

        // Mover el disco más grande al poste destino
        hanoi.mover(origen, destino);
        movimientos.add(origen + " " + destino);

        // Trazar el estado si está habilitado
        if (trazar) {
            System.out.println("Mover disco de Poste " + origen + " a Poste " + destino);
            hanoi.imprimirEstado();
        }

        // Mover los n-1 discos desde el auxiliar al destino
        resolver(hanoi, n - 1, auxiliar, destino, origen);
    
        
    }

    // Obtener la lista de movimientos
    public ArrayList<String> getMovimientos() {
        return movimientos;
    }

    // Imprimir movimientos por consola
    public void imprimirMovimientos() {
        for (String movimiento : movimientos) {
            System.out.println(movimiento);
        }
    }
	
}
