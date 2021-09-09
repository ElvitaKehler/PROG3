package EntregableTP2;

import java.util.ArrayList;


public class Tree { 	// clase arbol de arboles

	private Integer value;
	private Tree left;
	private Tree right;

	//--------CONSTRUCTOR con parámetro Integer----------------------------------------------------------//
	//--------Complejidad O(h) donde h es la altura del arbol--------------------------------------------//
	
	public Tree(Integer value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}
	
	//--------CONSTRUCTOR con parámetro Array-----------------------------------------------------------------------//
	//--------Complejidad O(n*h) donde h es la altura del arbol y n es el tamaño del arreglo, para cada elemento del //
	//--------arreglo busca la posición en el árbol----------------------------------------------------------------//
	
	public Tree(Integer[] nuevo) {
		Integer[] aux = nuevo;
		
		this.left = null;
		this.right = null;
		
		for (int i = 0; i <aux.length; i++) {   //O(n)
			add(aux[i]);   						//O(h)
		}
	}

	public Integer getValue() {
		return this.value;

	}
	//---------SETEA el valor entero en el arbol actual-------------//
	//---------Complejidad O(1)-----------------
	
	public void setValue(Integer valor) {
		this.value = valor;
	}

	//---------Retorna False si el árbol actual tiene hijos a derecha e izquierda y True si no los tiene-------------//
	//---------Complejidad O(1)-----------------------------//

	public boolean esHoja() {
		return (this.right == null && this.left == null);
	}

	// ----------- Inserta un nuevo árbol en la posición correcta según el ABB -----------------
	//--------------O(h), donde h es la altura del ABB
	
	public void add(Integer newValue) {
		if (this.value == null)
			this.value = newValue;
		else {
			if (this.value > newValue) {
				if (this.left == null)
					this.left = new Tree(newValue);
				else
					this.left.add(newValue);
			} else if (this.value < newValue) {
				if (this.right == null)
					this.right = new Tree(newValue);
				else
					this.right.add(newValue);
			}
		}
	}

	//---------RETORNA el valor entero en el arbol actual------------------------------------------------//
	//---------Complejidad O(1)-------------------------------------------------------------------------//
	
	public Integer getRoot() {
		return this.value;
	}

	//---------Retorna True si el ABB contiene el elemento valor introducido en el parámetro-------------//
	//---------Complejidad O(h), donde h es la altura del ABB------------------------------------------
	
	public boolean hasElem(Integer valor) {
		boolean encontrado = false;

		if (this.isEmpty()) {
			encontrado = false;
		} else {
			if (valor == this.value) {
				encontrado = true;
			} 
			else if (valor > this.value && this.right != null)
				encontrado = this.right.hasElem(valor);
			else if (this.left != null)
				encontrado = this.left.hasElem(valor);
		}
		return encontrado;

	}

	//---------Retorna True si el árbol actual contiene solo la raíz y su valor Integer es null-------------//
	//---------Complejidad O(1),-----------------------------------------------
	
	public boolean isEmpty() {
		return this.value == null;
	}

	//---------Retorna True si borró el valor ingresado por parámetro, del árbol actual.-------------------//
	//---------Complejidad O(h), donde h es la altura del ABB----------------------------------------------
	
	public boolean delete(Integer valorABorrar) { 
		int nuevoValor = 0;
		boolean borrado = false;

		if (!this.isEmpty()) {

			if ((this.left != null) && (this.getValue() > valorABorrar)) {
				
				if (this.left.getValue() == valorABorrar) {
					if (this.left.esHoja()) {			//O(1)
						this.left = null;				//O(1)
						borrado = true;
					} else {
						// cuando no es hoja, verifica si tiene hijos
						
						if (this.left.tieneIzquierda() && this.left.tieneDerecha()) {	
							nuevoValor = this.left.buscarNMISD();		// O(h) Busca el valor más a la izquierda del subárbol derecho
							this.left.delete(nuevoValor);     			 //O(h)
							this.left.setValue(nuevoValor);					//O(1)
							borrado = true;
						} else if (!this.left.tieneIzquierda() && this.left.tieneDerecha()) {	
							this.left = this.left.right;
							borrado = true;						
						} else if (this.left.tieneIzquierda() && !this.left.tieneDerecha()) {	
							this.left = this.left.left;
							borrado = true;	
						}
					}
				} else
					borrado = this.left.delete(valorABorrar);
				
			} else if ((this.right != null) && (this.getValue() < valorABorrar)) {

				if (this.right.getValue() == valorABorrar) {
					if (this.right.esHoja()) {
						this.right = null;
						borrado = true;
					} else {
						if (this.right.tieneIzquierda() && !this.right.tieneDerecha()) {
							this.right = this.right.left;
							borrado = true;
						} else if (!this.right.tieneIzquierda() && this.right.tieneDerecha()) {
							this.right = this.right.right;
							borrado = true;
						}
						  else if (this.right.tieneIzquierda() && this.right.tieneDerecha()) {
							nuevoValor = this.right.right.buscarNMISD();
							this.right.delete(nuevoValor);
							this.right.setValue(nuevoValor);
							borrado = true;
							
						}
					}
				} else
					borrado = this.right.delete(valorABorrar);

			} else if (this.getValue() == valorABorrar) {
					if (this.esHoja()){
						setValue(null);
					}
					else if (this.tieneDerecha()) {
						nuevoValor = this.right.buscarNMDSI();
						this.delete(nuevoValor);
						this.setValue(nuevoValor);
						borrado = true;
					} else {
						nuevoValor = this.left.buscarNMISD();
						this.delete(nuevoValor);
						this.setValue(nuevoValor);
						borrado = true;

					} 
			}	
				

		}
		return borrado;

	}	
	
		

	//---------Retorna el valor entero, sucesor del valor a borrar, ----//
	//---------Complejidad O(h), donde h es la altura del ABB----------------------------------------------
	
	private int buscarNMISD() { 
		Integer valor = 0;
		
		if (this.right == null)
			valor = this.getValue();
		else if (this.right != null) {
			valor = this.right.buscarNMISD();
		}

		return valor;
	}
	
	//---------Retorna el valor entero, sucesor del valor a borrar, ----//
		//---------Complejidad O(h), donde h es la altura del ABB----------------------------------------------
	private int buscarNMDSI() { 
		Integer valor = 0;
		
		if (this.left == null)
			valor = this.getValue();
		else if (this.left != null) {
			valor = this.left.buscarNMDSI();
		}

		return valor;
	}
	
	
	//-------------Retorna true si el árbol actual tiene un árbol a su izquierda---------------------------------
	//-------------O(1), ----------------------------------------------------------------------------------------

	private boolean tieneIzquierda() {
		return this.left != null;
	}

	//-------------Retorna true si el árbol actual tiene un árbol a su derecha---------------------------------
	//-------------O(1), ----------------------------------------------------------------------------------------
	
	private boolean tieneDerecha() {
		return this.right != null;
	}

	//---------Retorna un valor entero que representa la longitud del camino desde la raíz a la hoja más lejana---------//
	//---------Complejidad O(n), donde n es la cantidad de árboles----------------------------------------------------
	
	public int getHeight() { 
		int caminoMasLargo = -1; 		// para el caso que solo tenga la raíz
		int altura;
		if (!this.isEmpty()) {
			if (this.left != null) {
				altura = this.left.getHeight();
				if (altura > caminoMasLargo)
					caminoMasLargo = altura;
			}
			if (this.right != null) {
				altura = this.right.getHeight();
				if (altura > caminoMasLargo)
					caminoMasLargo = altura;
			}
		}
		return caminoMasLargo + 1;
	}
	
	
	//-------------Muestra por pantalla el contenido del atributo value de todos los árboles del ABB actual, en el 
	//-------------orden izquierda, derecha, raiz.----------------------------------------------------------------
	//-------------O(n), donde n es la cantidad de árboles que contiene el ABB------------------------------------
	
	public String printPosOrder() { // izq, dcha, raiz
		String salida = "";

		if (this.isEmpty()) {
			salida = "null";
		} else {

			if (this.left != null)
				salida += this.left.printPosOrder();
		
			if (this.right != null)
				salida += this.right.printPosOrder();
		
			salida += " " + this.value;

		}
		return salida;
	}

	//-------------Muestra por pantalla el contenido del atributo value de todos los árboles del ABB actual, en el 
	//-------------orden raíz, izquierda, derecha.----------------------------------------------------------------
	//-------------O(n), donde n es la cantidad de árboles que contiene el ABB------------------------------------
	
	public String printPreOrder() { 
		String salida = "";

		if (this.isEmpty()) {
			salida = "null";
		} else {

			salida += " " + this.value;
			if (this.left != null)
				salida += this.left.printPreOrder();
			else
				salida += "-";
			if (this.right != null)
				salida += this.right.printPreOrder();
			else
				salida += "-";

		}
		return salida;

	}

	//-------------Muestra por pantalla el contenido del atributo value de todos los árboles del ABB actual, en el 
	//-------------orden izquierda, raiz, derecha, resultando en orden ascendente---------------------------------
	//-------------O(n), donde n es la cantidad de árboles que contiene el ABB------------------------------------
	
	public String printInOrder() { 
		String salida = "";

		if (this.isEmpty()) {
			salida = "null";
		} else {
			if (this.left != null)
				salida += this.left.printInOrder();
		
			salida += " " + this.value;

			if (this.right != null)
				salida += this.right.printInOrder();
			}
		return salida;

	}
	
	//-------------Retorna una lista con los valores enteros correspondientes al camino de la rama más alrga del ABB 
	//-------------O(n), donde n es la cantidad de árboles que contiene el ABB--------------------------------------
	
	public ArrayList<Integer> getLongestBranch() { 
		ArrayList<Integer> longest = new ArrayList<Integer>();
		ArrayList<Integer> aux = new ArrayList<Integer>();
		ArrayList<Integer> aux2 = new ArrayList<Integer>();

		if (!this.isEmpty()) {
			aux.add(this.value);
			aux2.add(this.value);
			if (this.right != null) {
				aux.addAll(this.right.getLongestBranch());

			}
			if (this.left != null) {
				aux2.addAll(this.left.getLongestBranch());

			}
		}
		if (aux.size() > longest.size())
			longest = aux;
		if (aux2.size() > longest.size())
			longest = aux2;

		return longest;
	}
	
	//-------------Retorna una Lista, con los árboles del ABB que no tienen hijos, vistas de izquierda a derecha
	//-------------O(n), donde n es la cantidad de árboles que contiene el ABB------------------------------------

	public ArrayList<Integer> getFrontera() { 
		ArrayList<Integer> frontera = new ArrayList<Integer>();

		if (!this.isEmpty()) {
			if (this.esHoja()) {
				frontera.add(this.getValue());
				}
			if (this.left != null)
				frontera.addAll(this.left.getFrontera());
			if (this.right != null)
				frontera.addAll(this.right.getFrontera());
		}

		return frontera;
	}

	//-------------Retorna un Entero con el valor más grande encontrado en los árboles del ABB actual.
	//-------------O(h), donde h es la altura del árbol.
	
	public Integer getMaxElem() { 
		Integer max = null;
		if (!this.isEmpty()) {
			if (this.right == null)
				max = this.value;
			else
				max = this.right.getMaxElem();
		}

		return max;
	}

	//-------------Retorna una lista con los valores enteros encontrados en el nivel, pasado por parámetro, en el ABB 
	//-------------O(n), donde n es la cantidad de árboles del ABB, en el peor de los casos busca los valores del último nivel----
	
	public ArrayList<Integer> getElementAtLevel(int nivel) {
		ArrayList<Integer> salida = new ArrayList<Integer>();

		if (!this.isEmpty()) {
			if (nivel == 0)
				salida.add(this.value);
			if (this.right != null)
				salida.addAll(this.right.getElementAtLevel(nivel - 1));
			if (this.left != null)
				salida.addAll(this.left.getElementAtLevel(nivel - 1));

		}
		return salida;
	}

	//-------------Retorna una lista con los valores enteros resultado de la diferencia entre los valores de las hojas del ABB 
	//-------------resuelta de derecha a izquierda--------------------------------------------------------------------------
	//-------------O(n), n es la cantidad de árboles del ABB----------------------------------------------------------------
	
	private ArrayList<Integer> diferenciasEntreHojas(ArrayList<Integer> auxiliar) {
		ArrayList<Integer> salida = new ArrayList<Integer>();
		ArrayList<Integer> aux = auxiliar;

		if (!this.isEmpty()) {

			if (this.esHoja()) {
				aux.add(0, this.getValue());
				if (aux.size() >= 2) {
					salida.add(aux.get(1) - aux.get(0));
				}
			}

			if (this.right != null)
				salida.addAll(this.right.diferenciasEntreHojas(aux));

			if (this.left != null)
				salida.addAll(this.left.diferenciasEntreHojas(aux));
		}

		return salida;
	}
	//O(h)
	public ArrayList<Integer> diferenciasEntreHojas() {

		ArrayList<Integer> aux = new ArrayList<Integer>();

		return this.diferenciasEntreHojas(aux);
	}

}
