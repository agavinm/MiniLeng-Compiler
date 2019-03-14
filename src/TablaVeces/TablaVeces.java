package TablaVeces;

public class TablaVeces {
	private static final int N = 45;
	private static int tabla[] = new int[N];
	private static final String[] nombres = 
		{
				"programa", "principio", "fin", "si", "ent",
				"si_no", "fsi", "mq", "fmq", "accion",
				";", ",", "var", "entero", "booleano",
				"caracter", "val", "ref", "and", "or",
				"not", ">", "<", "=", ">=",
				"<=", "<>", "true", "false", "escribir",
				"leer", "entacar", "caraent", "mod", "div",
				":=", "+", "-", "*", "(",
				")", "Constantes enteras", "Caracteres", "Cadenas", "Identificadores"
		};
	
	public TablaVeces() {
		for (int i=0; i<N; i++) {
			tabla[i] = 0;
		}
	}
	
	public static void incrementarValor(int indice) {
		tabla[indice]++;
	}
	
	public static void print() {
		String s = " Opción verbose: Número de apariciones de:\n";
		
		for (int i=0; i<N; i++) {
			if (i==0 || i==18 || i==29 || i==33 || i==41) {
				s += "  ---------------------------------------\n";
			}
			s += "  ";
			if (i<=17) {
				s += String.format("%-33s", "Palabras reservadas '" + nombres[i] + "': ");
			}
			else if (i>17 && i<=28) {
				s += String.format("%-33s", "Operadores lógicos '" + nombres[i] + "': ");
			}
			else if (i>28 && i<=32) {
				s += String.format("%-33s", "Operaciones '" + nombres[i] + "': ");
			}
			else if (i>32 && i<=40) {
				s += String.format("%-33s", "Operadores aritméticos '" + nombres[i] + "': ");
			}
			else {
				s += String.format("%-33s", nombres[i] + ": ");
			}
			
			s += String.format("%3s", Integer.toString(tabla[i])) + "\n";
		}
		s += "  ---------------------------------------\n";
		
		System.out.println(s);
	}
}
