package modelos;

public class Hanoi {

	private Pila[] postes;
	private int numDiscos;

	// Constructor
	public Hanoi(int numDiscos, int posteInicial) {
		this.numDiscos = numDiscos;
		postes = new Pila[3];
		for (int i = 0; i < 3; i++) {
			postes[i] = new Pila("Poste " + (i + 1));
		}
		inicializar(numDiscos, posteInicial);
	}

	// Inicializa los discos en el poste inicial
	private void inicializar(int numDiscos, int posteInicial) {
		for (int i = numDiscos; i > 0; i--) {
			postes[posteInicial - 1].apilar(i);
		}
	}

	// Mueve un disco de un poste a otro
	public void mover(int origen, int destino) {
		int disco = postes[origen - 1].desapilar();
		postes[destino - 1].apilar(disco);
	}

	// Imprime el estado actual de los postes
	public void imprimirEstado() {
		for (Pila poste : postes) {
			System.out.println(poste);
		}
		System.out.println();
	}

}
