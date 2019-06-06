//*****************************************************************
// File:   TablaDispersion.java
// Author: Andrés Gavín Murillo 716358
// Date:   6/6/2019
// Coms:   Procesadores de lenguajes - Compilador de MiniLengCompiler V4.0
//         JavaCC plugin 1.5.28+ wizard for JavaCC 1.5.0+
//*****************************************************************

package lib.semantico;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import lib.semantico.Simbolo.Tipo_simbolo;

public class TablaDispersion {
    private final int M = 251; // Numero primo cercano a una potencia de 2 (256) -- 251
    private List<Integer> T;
    private LinkedList<Simbolo>[] tabla = new LinkedList[M];
    private boolean primer_toString;
    
    public TablaDispersion() {
        T = new ArrayList<Integer>();
        for (int i=0; i<=255; i++) {
            T.add(i);
        }
        java.util.Collections.shuffle(T);
        
        for (int i=0; i<M; i++) {
            tabla[i] = new LinkedList<Simbolo>();
        }
        
        primer_toString = true;
    }
    
    // Introduce el símbolo s sin comprobaciones previas
    public void introducir_simbolo(Simbolo s) {
        tabla[hash(s.getNombre())].addFirst(s); // Nombres nuevos al comienzo, orden nivel descendente
    }
    
    // Devuelve el símbolo con nombre s en primera posición, y si no existe lanza 
    // SimboloNoEncontradoException
    public Simbolo buscar_simbolo(String nombre) throws SimboloNoEncontradoException {
        int h = hash(nombre);
        int size = tabla[h].size();
        Simbolo aux = null;
        
        for (int i=0; i<size; i++) {
            aux = tabla[h].get(i);
            if (aux.getNombre().equals(nombre) && aux.isVisible()) {
                return aux;
            }
        }
        
        throw new SimboloNoEncontradoException("El simbolo introducido no existe");          
    }
    
    // Si existe el símbolo s en el nivel n, lanza SimboloYaExistenteException
    public void existe_simbolo(String nombre, Integer n) throws SimboloYaExistenteException {
        int h = hash(nombre);
        int size = tabla[h].size();
        Simbolo aux = null;
        
        for (int i=0; i<size; i++) {
            aux = tabla[h].get(i);
            
            if (aux.getNombre().equals(nombre) && aux.getNivel().equals(n) && aux.isVisible()) {
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
                
                if (aux.getNivel().equals(n) && aux.getTipo() == simbolo) {
                    it.remove();
                }
            }
        }
    }
    
    // Elimina el símbolo con nombre nombre del nivel n
    public void eliminar(Integer n, String nombre) {
        Simbolo aux;
        ListIterator<Simbolo> it;
        boolean fin = false;
        
        for (int i=0; !fin && i<M; i++) {
            it = tabla[i].listIterator();
            
            while (!fin && it.hasNext()) {
                aux = it.next();
                
                if (aux.getNivel().equals(n) && aux.getNombre().equals(nombre)) {
                    it.remove();
                    fin = true;
                }
            }
        }
    }
    
    // Marca todos los parámetros de un nivel como ocultos para que no puedan
    // ser encontrados, pero se mantenga la definición completa de la
    // acción donde están declarados para verificación de
    // invocaciones a la acción.
    public void ocultar_parametros(Integer n) {
        Simbolo aux;
        ListIterator<Simbolo> it;
        
        for (int i=0; i<M; i++) {
            it = tabla[i].listIterator();
            
            while (it.hasNext()) {
                aux = it.next();
                
                if (aux.getNivel().equals(n) && aux.getTipo() == Tipo_simbolo.PARAMETRO) {
                    aux.setVisible(false);
                }
            }
        }
    }

    // Elimina de la tabla todas los parámetros que hayan sido ocultados
    // previamente. LOS PROCEDIMIENTOS Y FUNCIONES DONDE ESTABAN DECLARADOS
    // DEBEN SER ELIMINADOS TAMBIEN PARA MANTENER LA COHERENCIA DE LA TABLA.
    public void eliminar_parametros_ocultos(Integer n) {
        Simbolo aux;
        LinkedList<Simbolo> parametros;
        ListIterator<Simbolo> it;
        
        for (int i=0; i<M; i++) {
            it = tabla[i].listIterator();
            
            while (it.hasNext()) {
                aux = it.next();
                
                if (aux.getNivel().equals(n-1) && aux.getTipo() == Tipo_simbolo.ACCION) {
                    parametros = aux.getLista_parametros();
                    if (!parametros.isEmpty() && !parametros.getFirst().isVisible()) {
                        it.remove();
                        
                        for (int j=0; j<parametros.size(); j++) {
                            this.eliminar(n, parametros.get(j).getNombre());
                        } // Los parámetros van antes en la tabla que su acción, por tanto el iterador avanza
                        
                        it = tabla[i].listIterator();
                    }
                }
            }
        }
    }
    
    public String toString() {
        String linea = new String(new char[111]).replace("\0", "-") + "\n";
        String resul = linea;
        if (primer_toString) {
            resul += String.format("| %-43s  %-17s  %-43s |", "", "TABLA DE SÍMBOLOS", 
                    "") + "\n";
            resul += linea;
            resul += String.format("| %-4s | %-20s | %-5s | %-9s | %-8s | %-11s | %-5s | %-18s | %-3s |", 
                    "Hash", "Simbolo", "Nivel", "Tipo", "Visible", "Variable", "Clase", "Parametros", "@") + "\n";
            resul += linea;
            primer_toString = false;
        }
        
        LinkedList<Simbolo> fila;
        Simbolo s;
        
        for (int i=0; i<M; i++) {
            fila = tabla[i];
            for (int j=0; j<fila.size(); j++) {
                s = fila.get(j);
                resul += String.format("| %-4s | %-20s | %-5s | %-9s | %-8s | %-11s | %-5s | %-18s | %-3s |", i, 
                        s.getNombre(), s.getNivel(), s.getTipo(), s.strVisible(), s.strVariable(),
                        s.strClase(), s.strParametros(), s.strDir()) + "\n";
            }
        }
        
        resul += linea;
        
        return resul;
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
