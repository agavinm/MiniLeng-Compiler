//*****************************************************************
// File:   Simbolo.java
// Author: Andrés Gavín Murillo 716358
// Date:   5/6/2019
// Coms:   Procesadores de lenguajes - Compilador de MiniLengCompiler V4.0
//         JavaCC plugin 1.5.28+ wizard for JavaCC 1.5.0+
//*****************************************************************

package lib.semantico;

import java.util.LinkedList;

public class Simbolo {
    public enum Tipo_simbolo {PROGRAMA, VARIABLE, ACCION, PARAMETRO};
    public enum Tipo_variable {DESCONOCIDO, ENTERO, BOOLEANO, CHAR, CADENA};
    public enum Clase_parametro {VAL, REF};
    
    private String nombre;
    private Integer nivel;
    private Tipo_simbolo tipo;
    private Tipo_variable variable;
    private Clase_parametro parametro;
    private Boolean visible;
    private LinkedList<Simbolo> lista_parametros;
    private Long dir;
    
    // Restablece el símbolo
    private void reset() {
        this.nombre = null;
        this.nivel = null;
        this.tipo = null;
        this.variable = null;
        this.parametro = null;
        this.visible = true;
        this.lista_parametros = null;
        this.dir = null;
    }
    
    // Genera un símbolo programa
    public void introducir_programa(String nombre, Long dir) {
        reset();
        this.nombre = nombre;
        this.nivel = new Integer(0);
        this.tipo = Tipo_simbolo.PROGRAMA;
        this.dir = dir;
    }

    // Genera un símbolo variable
    public void introducir_variable(String nombre, Tipo_variable variable, Integer nivel, 
            Long dir) {
        reset();
        this.nombre = nombre;
        this.nivel = nivel;
        this.tipo = Tipo_simbolo.VARIABLE;
        this.variable = variable;
        this.dir = dir;
    }

    // Genera un símbolo acción
    public void introducir_accion(String nombre, Integer nivel, Long dir) {
        reset();
        this.nombre = nombre;
        this.nivel = nivel;
        this.tipo = Tipo_simbolo.ACCION;
        this.lista_parametros = new LinkedList<Simbolo>();
        this.dir = dir;
    }

    // Genera un símbolo parámetro
    public void introducir_parametro(String nombre, Tipo_variable tipo_var, 
            Clase_parametro clase_param, Integer nivel, Long dir) {
        reset();
        this.nombre = nombre;
        this.nivel = nivel;
        this.tipo = Tipo_simbolo.PARAMETRO;
        this.variable = tipo_var;
        this.parametro = clase_param;
        this.dir = dir;
    }
    
    // Tipo de símbolo
    public boolean es_variable() {
        return this.tipo == Tipo_simbolo.VARIABLE;
    }
    
    public boolean es_parametro() {
        return this.tipo == Tipo_simbolo.PARAMETRO;
    }
    
    public boolean es_accion() {
        return this.tipo == Tipo_simbolo.ACCION;
    }
    
    // Clase de parámetro
    public boolean es_valor() {
        return this.parametro == Clase_parametro.VAL;
    }
    
    public boolean es_referencia() {
        return this.parametro == Clase_parametro.REF;
    }
    
    // Tipo de variable
    public boolean es_desconocido() {
        return this.variable == Tipo_variable.DESCONOCIDO;
    }
    
    public boolean es_entero() {
        return this.variable == Tipo_variable.ENTERO;
    }
    
    public boolean es_booleano() {
        return this.variable == Tipo_variable.BOOLEANO;
    }
    
    public boolean es_caracter() {
        return this.variable == Tipo_variable.CHAR;
    }
    
    // Para que un símbolo sea asignable debe ser variable o parámetro referencia
    public boolean es_asignable() {
        return this.es_variable() || (this.es_parametro() && this.es_referencia());
    }
    
    /* Constructores */
    
    public Simbolo(String nombre, Integer nivel, Tipo_simbolo tipo, Tipo_variable variable, 
            Clase_parametro parametro, Boolean visible, LinkedList<Simbolo> lista_parametros, 
            Long dir) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.tipo = tipo;
        this.variable = variable;
        this.parametro = parametro;
        this.visible = visible;
        this.lista_parametros = lista_parametros;
        this.dir = dir;
    }
    
    public Simbolo() {
        reset();
    }
    
    /* Getters and setters */
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Integer getNivel() {
        return nivel;
    }
    
    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }
    
    public Tipo_simbolo getTipo() {
        return tipo;
    }
    
    public void setTipo(Tipo_simbolo tipo) {
        this.tipo = tipo;
    }
    
    public Tipo_variable getVariable() {
        return variable;
    }
    
    public void setVariable(Tipo_variable variable) {
        this.variable = variable;
    }
    
    public Clase_parametro getParametro() {
        return parametro;
    }
    
    public void setParametro(Clase_parametro parametro) {
        this.parametro = parametro;
    }
    
    public Boolean isVisible() {
        return visible;
    }
    
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    
    public LinkedList<Simbolo> getLista_parametros() {
        return lista_parametros;
    }
    
    public void setLista_parametros(LinkedList<Simbolo> lista_parametros) {
        this.lista_parametros = lista_parametros;
    }
    
    public void setParametro(Simbolo parametro) {
        this.lista_parametros.add(parametro);
    }
    
    public Long getDir() {
        return dir;
    }
    
    public void setDir(Long dir) {
        this.dir = dir;
    }
    
    // To string
    public String strVisible() {
        String resul = "OCULTO";
        if (this.visible) {
            resul = "VISIBLE";
        }
        
        return resul;
    }

    public String strVariable() {
        String resul = "";
        if (this.variable != null) {
            resul = this.variable.toString();
        }
        
        return resul;
    }

    public String strClase() {
        String resul = "";
        if (this.parametro != null) {
            resul = this.parametro.toString();
        }
        
        return resul;
    }

    public String strParametros() {
        String resul = "";
        if (this.lista_parametros != null) {
            for (int i=0; i<this.lista_parametros.size(); i++) {
                if (i>0)
                    resul += ", ";
                resul += this.lista_parametros.get(i).nombre;
            }
        }
        
        return resul;
    }

    public String strDir() {
        String resul = "";
        if (this.dir != null) {
            resul = this.dir.toString();
        }
        
        return resul;
    }
}
