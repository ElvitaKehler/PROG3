package EntregableTP2;

public class main {
	
	public static Integer[] arreglo = {10,5,2,14,11,7,17,3,1,4,16,19,18};
	
	

	public static void main(String[] args) {
	
//		//Tree arbol1 = new Tree(10);
////		
////		arbol1.add(5);
////		arbol1.add(2);
////		arbol1.add(14);
////		arbol1.add(11);
////		arbol1.add(7);
////		arbol1.add(17);
////		arbol1.add(3);
////		arbol1.add(1);
////		arbol1.add(4);
////		arbol1.add(16);
////		arbol1.add(19);
////		arbol1.add(18);
//		
//		Tree arbol1 = new Tree (arreglo);
		
		
//		System.out.println(arbol1.printPreOrder());
//		System.out.println(arbol1.printPosOrder());
//		System.out.println(arbol1.printInOrder());
//		
//		System.out.println(arbol1.hasElem(1));
//		
//		System.out.println(arbol1.getMaxElem());
//		
//  	System.out.println(arbol1.getHeight());
//	//	System.out.println(arbol1.delete(10)); 		
//	//	
//	//	System.out.println(arbol1.printInOrder());
//	//	System.out.println(arbol1.printPreOrder());
//	//	System.out.println(arbol1.isEmpty());
//		
//	//		System.out.println(arbol1.getLongestBranch());
//		
//	//	System.out.println(arbol1.getFrontera());
//	//	System.out.println(arbol1.getElemAtLevel(4));
//		
//		System.out.println(arbol1.diferenciasEntreHojas());
		
		
	//MAIN del enunciado del TP-------------------------------------
		
	Integer[] valoresIniciales = new Integer[] {10, 5, 12, 2, 1, 3, 8, 6, 11, 25, 22, 30};
	//	Integer[] valoresIniciales = new Integer[] {10,5, 2, 1, 3, 8, 6 };
	//  Integer[] valoresIniciales = new Integer[] {10,12, 11, 25, 22, 30 };
		Tree miArbol = new Tree(valoresIniciales);

		System.out.println(miArbol.printPreOrder());
		
		System.out.println( "Max: "+miArbol.getMaxElem() );
		System.out.println( "Altura: "+miArbol.getHeight() );
		System.out.println( "camino más largo: "+miArbol.getLongestBranch() );  
		System.out.println( "elementos del nivel n: "+miArbol.getElementAtLevel(2) );
		System.out.println( "elementos hojas: "+miArbol.getFrontera() );

		miArbol.add(23);
		miArbol.add(4);
		miArbol.delete(11);
		miArbol.delete(25);
	
		System.out.println("Preorder: "+miArbol.printPreOrder());   
		System.out.println(miArbol.printInOrder());   
		System.out.println( miArbol.getMaxElem() );
		System.out.println( miArbol.getHeight() );
		System.out.println( miArbol.getLongestBranch() );    
		System.out.println( miArbol.getElementAtLevel(2) );
		System.out.println( miArbol.getFrontera() );  

		miArbol.add(65);
		miArbol.delete(8);
		System.out.println(miArbol.printPreOrder());   
		System.out.println(miArbol.delete(10));
				
		miArbol.add(55);

		System.out.println(miArbol.printInOrder());
		System.out.println(miArbol.printPreOrder());
		
		
		System.out.println( miArbol.getMaxElem() );
		System.out.println( miArbol.getHeight() );
		System.out.println( miArbol.getLongestBranch() );
		System.out.println( miArbol.getElementAtLevel(2) );
		System.out.println( miArbol.getFrontera() );
		System.out.println(miArbol.diferenciasEntreHojas());
	}

}
