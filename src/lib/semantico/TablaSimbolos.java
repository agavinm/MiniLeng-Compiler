package lib.semantico;

import lib.semantico.Simbolo.Tipo_simbolo;
import lib.semantico.Simbolo.Tipo_variable;
import lib.semantico.Simbolo.Clase_parametro;

public class TablaSimbolos {
    private TablaDispersion tabla;
    private Simbolo ultima_accion;
    
    public TablaSimbolos() {
        inicializar_tabla();
    }
    
    // Crea una tabla de símbolos vacía. Este procedimiento debe invocarse
    // antes de hacer ninguna operación con la tabla de símbolos.
    public void inicializar_tabla() {
        tabla = new TablaDispersion();
        ultima_accion = null;
    }

    // Busca en la tabla el símbolo de mayor nivel cuyo nombre coincida
    // con el del parámetro (se distinguen minúsculas y mayúsculas). Si
    // existe, devuelve un puntero como resultado, de lo contrario
    // lanza una excepción.
    public Simbolo buscar_simbolo(String nombre) throws SimboloNoEncontradoException {
        return tabla.buscar_simbolo(nombre);
    }

    // Introduce en la tabla un simbolo PROGRAMA, con el nombre
    // del parametro, de nivel 0, con la direccion del parametro. Dado que debe
    // ser el primer simbolo a introducir, NO SE VERIFICA QUE EL SIMBOLO YA
    // EXISTA.
    public Simbolo introducir_programa(String nombre, Long dir) {
        Simbolo s = new Simbolo();
        s.introducir_programa(nombre, dir);
        tabla.introducir_simbolo(s);
        return s;
    }

    // Si existe un símbolo en la tabla del mismo nivel y con el mismo
    // nombre, lanza una excepción. De lo contrario, introduce un símbolo VARIABLE (simple)
    // con los datos de los argumentos.
    public Simbolo introducir_variable(String nombre, Tipo_variable variable, int nivel, 
            Long dir) throws SimboloYaExistenteException {
        tabla.existe_simbolo(nombre, nivel);
        
        Simbolo s = new Simbolo();
        s.introducir_variable(nombre, variable, nivel, dir);
        tabla.introducir_simbolo(s);
        return s;
    }

    // Si existe un símbolo en la tabla del mismo nivel y con el mismo
    // nombre, lanza una excepción. De lo contrario, introduce un símbolo
    // ACCION con los datos de los argumentos.
    public Simbolo introducir_accion(String nombre, int nivel, Long dir) 
            throws SimboloYaExistenteException {
        tabla.existe_simbolo(nombre, nivel);
        
        ultima_accion = new Simbolo();
        ultima_accion.introducir_accion(nombre, nivel, dir);
        tabla.introducir_simbolo(ultima_accion);
        return ultima_accion;
    }

    // Si existe un símbolo en la tabla del mismo nivel y con el mismo
    // nombre, lanza una excepción. De lo contrario, introduce un símbolo
    // PARAMETRO con los datos de los argumentos.
    public Simbolo introducir_parametro(String nombre, Tipo_variable variable, 
            Clase_parametro parametro, int nivel, Long dir) throws SimboloYaExistenteException {
        tabla.existe_simbolo(nombre, nivel);
        
        Simbolo s = new Simbolo();
        s.introducir_parametro(nombre, variable, parametro, nivel, dir);
        tabla.introducir_simbolo(s);
        
        ultima_accion.setParametro(s);
        
        return s;
    }

    // Elimina de la tabla todos los símbolos PROGRAMA de nivel 0 (debería
    // haber uno solo).
    public void eliminar_programa() {
        tabla.eliminar_nivel(0, Tipo_simbolo.PROGRAMA);
    }

    // Elimina de la tabla todas las variables que sean del nivel del argumento.
    // NO ELIMINA PARÁMETROS.
    public void eliminar_variables(int nivel) {
        tabla.eliminar_nivel(nivel, Tipo_simbolo.VARIABLE);
    }

    // Marca todos los parámetros de un nivel como ocultos para que no puedan
    // ser encontrados, pero se mantenga la definición completa de la
    // acción donde están declarados para verificación de
    // invocaciones a la acción.
    public void ocultar_parametros(int nivel) {
        tabla.ocultar_parametros(nivel);
    }

    // Elimina de la tabla todas los parámetros que hayan sido ocultados
    // previamente. LOS PROCEDIMIENTOS Y FUNCIONES DONDE ESTABAN DECLARADOS
    // DEBEN SER ELIMINAODS TAMBIEN PARA MANTENER LA COHERENCIA DE LA TABLA.
    public void eliminar_parametros_ocultos(int nivel) {
        tabla.eliminar_parametros_ocultos(nivel);
    }

    // Elimina de la tabla todas los procedimientos de un nivel.
    // LOS PARAMETROS DE ESTAS ACCIONES
    // DEBEN SER ELIMINADOS TAMBIEN PARA MANTENER LA COHERENCIA DE LA TABLA.
    public void eliminar_acciones(int nivel) {
        tabla.eliminar_nivel(nivel, Tipo_simbolo.ACCION);
        tabla.eliminar_nivel(nivel+1, Tipo_simbolo.PARAMETRO);
    }
}
