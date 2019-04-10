package lib;

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
    private Simbolo[] lista_parametros;
    private Long dir;
    
    public void introducir_programa(String nombre, Long dir) {
        this.nombre = nombre;
        this.nivel = new Integer(0);
        this.tipo = Tipo_simbolo.PROGRAMA;
        this.dir = dir;
    }
    
    public void introducir_variable(String nombre, Tipo_variable variable, Integer nivel, 
            Long dir) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.tipo = Tipo_simbolo.VARIABLE;
        this.variable = variable;
        this.dir = dir;
    }
    
    public void introducir_accion(String nombre, Integer nivel, Long dir, Simbolo[] lista_parametros) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.tipo = Tipo_simbolo.ACCION;
        this.lista_parametros = lista_parametros;
        this.dir = dir;
    }
    
    public void introducir_parametro(String nombre, Tipo_variable tipo_var, 
            Clase_parametro clase_param, Integer nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.tipo = Tipo_simbolo.PARAMETRO;
        this.variable = tipo_var;
        this.parametro = clase_param;
    }
    
    public boolean es_variable() {
        return this.tipo == Tipo_simbolo.VARIABLE;
    }
    
    public boolean es_parametro() {
        return this.tipo == Tipo_simbolo.PARAMETRO;
    }
    
    public boolean es_accion() {
        return this.tipo == Tipo_simbolo.ACCION;
    }
    
    public boolean es_valor() {
        return this.tipo == Tipo_simbolo.PARAMETRO && this.parametro == Clase_parametro.VAL;
    }
    
    public boolean es_referencia() {
        return this.tipo == Tipo_simbolo.PARAMETRO && this.parametro == Clase_parametro.REF;
    }
    
    /* Constructores */
    
    public Simbolo(String nombre, Integer nivel, Tipo_simbolo tipo, Tipo_variable variable, 
            Clase_parametro parametro, Boolean visible, Simbolo[] lista_parametros, Long dir) {
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
        this.nombre = null;
        this.nivel = null;
        this.tipo = null;
        this.variable = null;
        this.parametro = null;
        this.visible = null;
        this.lista_parametros = null;
        this.dir = null;
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
    
    public Simbolo[] getLista_parametros() {
        return lista_parametros;
    }
    
    public void setLista_parametros(Simbolo[] lista_parametros) {
        this.lista_parametros = lista_parametros;
    }
    
    public Long getDir() {
        return dir;
    }
    
    public void setDir(Long dir) {
        this.dir = dir;
    }
}
