package lib;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TablaDispersion {
    private final int M = 251; // Numero primo cercano a una potencia de 2 (256)
    private List<Integer> T;
    private LinkedList<Simbolo>[] tabla = new LinkedList[M];
    
    public TablaDispersion() {
        T = new ArrayList<Integer>();
        for (int i=0; i<=255; i++) {
            T.add(i);
        }
        java.util.Collections.shuffle(T);
        
        for (int i=0; i<M; i++) {
            tabla[i] = new LinkedList<Simbolo>();
        }
    }
    
    public void introducir(Simbolo s) {
        tabla[hash(s.getNombre())].add(s);
    }
    
    public Simbolo obtener(String s) throws Exception {
        int h = hash(s);
        int i=0;
        while (i<tabla[h].size() && tabla[h].get(i).getNombre() != s) {
            i++;
        }
        if (i<tabla[h].size() && tabla[h].get(i).getNombre() == s)
            return tabla[h].get(i);
        else
            throw new IllegalArgumentException("El simbolo introducido no existe");
    }
    
    public void borrar(String s) throws Exception {
        int h = hash(s);
        int i=0;
        while (i<tabla[h].size() && tabla[h].get(i).getNombre() != s) {
            i++;
        }
        if (i<tabla[h].size() && tabla[h].get(i).getNombre() == s)
            tabla[h].remove(i);
        else
            throw new IllegalArgumentException("El simbolo introducido no existe");
    }

    // Función hash de Pearson, 90.
    // Se ha utilizado debido a que es la que utilizan los compiladores modernos.
    private int hash(String s) {
        int h = 0, n = s.length();
        
        for (int i=0; i<n; i++) {
            h = T.get(h ^ s.charAt(i));
        }
        
        return h % M;
    }
}
