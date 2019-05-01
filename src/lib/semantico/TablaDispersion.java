package lib.semantico;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import lib.semantico.Simbolo.Tipo_simbolo;

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
    
    // Introduce el símbolo s sin comprobaciones previas
    public void introducir_simbolo(Simbolo s) {
        tabla[hash(s.getNombre())].add(s);
    }
    
    // Devuelve el símbolo con nombre s de mayor nivel, y si no existe lanza 
    // SimboloNoEncontradoException
    public Simbolo buscar_simbolo(String nombre) throws SimboloNoEncontradoException {
        int h = hash(nombre);
        int size = tabla[h].size();
        Simbolo aux = null, resul = null;
        
        for (int i=0; i<size; i++) {
            aux = tabla[h].get(i);
            if (aux.getNombre() == nombre) {
                if (resul == null || aux.getNivel() > resul.getNivel()) {
                    resul = aux;
                }
            }
        }
        
        if (resul != null) {
            return resul;
        }
        else {
            throw new SimboloNoEncontradoException("El simbolo introducido no existe");
        }            
    }
    
    // Si existe el símbolo s en el nivel n, lanza SimboloYaExistenteException
    public void existe_simbolo(String nombre, Integer n) throws SimboloYaExistenteException {
        int h = hash(nombre);
        int size = tabla[h].size();
        Simbolo aux = null;
        
        for (int i=0; i<size; i++) {
            aux = tabla[h].get(i);
            if (aux.getNombre() == nombre && aux.getNivel() == n) {
                throw new SimboloYaExistenteException("El simbolo introducido ya existe");
            }
        }       
    }
    
    // Elimina todos los símbolos de tipo simbolo del nivel n
    public void eliminar_nivel(Integer n, Tipo_simbolo simbolo) {
        Simbolo aux;
        ListIterator<Simbolo> it;
        
        for (int i=0; i<M; i++) {
            it = tabla[i].listIterator();
            while (it.hasNext()) {
                aux = it.next();
                if (aux.getNivel() == n && aux.getTipo() == simbolo) {
                    it.remove();
                }
            }
        }
    }

    // Función hash de Pearson, 90.
    // Se ha utilizado debido a que es la que utilizan los compiladores modernos.
    private int hash(String nombre) {
        int h = 0, n = nombre.length();
        
        for (int i=0; i<n; i++) {
            h = T.get(h ^ nombre.charAt(i));
        }
        
        return h % M;
    }
}
