//*****************************************************************
// File:   RegistroExpr.java
// Author: Andrés Gavín Murillo 716358
// Date:   5/6/2019
// Coms:   Procesadores de lenguajes - Compilador de MiniLengCompiler V4.0
//         JavaCC plugin 1.5.28+ wizard for JavaCC 1.5.0+
//*****************************************************************

package lib.semantico;

import lib.semantico.Simbolo.Clase_parametro;
import lib.semantico.Simbolo.Tipo_variable;

public class RegistroExpr {
    public enum TipoOperador {SUM, RES, OR, MOD, DIV, MUL, AND, MAY, MEN, IGU, MAI, MEI, NI};
    
    private Tipo_variable variable;
    private Clase_parametro parametro;
    
    private Integer valorEnt;
    private Boolean valorBool;
    private Character valorChar;
    
    // Constructores
    private void reset() {
        this.variable = null;
        this.parametro = null;
        this.valorEnt = null;
        this.valorBool = null;
        this.valorChar = null;
    }
    
    public RegistroExpr() {
        reset();
    }
    
    public RegistroExpr(Tipo_variable variable, Clase_parametro parametro, Integer valorEnt, 
            Boolean valorBool, Character valorChar) {
        this.variable = variable;
        this.parametro = parametro;
        this.valorEnt = valorEnt;
        this.valorBool = valorBool;
        this.valorChar = valorChar;
    }
    
    public RegistroExpr(Simbolo s) {
        this.variable = s.getVariable();
        
        if (!s.es_desconocido()) {
            if (s.es_parametro()) {
                this.parametro = s.getParametro();
            }
            else {
                this.parametro = Clase_parametro.REF;
            }
        }
        else {
            this.parametro = null;
        }

        this.valorEnt = null;
        this.valorBool = null;
        this.valorChar = null;
    }
    
    private void copy(RegistroExpr exp) {
        this.variable = exp.variable;
        this.parametro = exp.parametro;
        this.valorEnt = exp.valorEnt;
        this.valorBool = exp.valorBool;
        this.valorChar = exp.valorChar;
    }
    
    public RegistroExpr(RegistroExpr exp) {
        copy(exp);
    }
    
    public RegistroExpr(RegistroExpr exp1, RegistroExpr exp2, TipoOperador op) {
        if (exp1 == null && exp2 == null) {
            reset();
        }
        else if (exp1 != null && exp2 == null) {
            copy(exp1);
        }
        else if (exp1 == null && exp2 != null) {
            copy(exp2);
        }
        else {
            reset();
            this.variable = exp1.getVariable();
            this.parametro = Clase_parametro.VAL;
            
            if (op != null) {
                switch (op) {
                    case SUM :
                        if (exp1.getValorEnt() != null && exp2.getValorEnt() != null) {
                            this.valorEnt = exp1.getValorEnt() + exp2.getValorEnt();
                        }
                        break;
                        
                    case RES :
                        if (exp1.getValorEnt() != null && exp2.getValorEnt() != null) {
                            this.valorEnt = exp1.getValorEnt() - exp2.getValorEnt();
                        }
                        break;
                        
                    case OR :
                        if (exp1.getValorBool() != null && exp2.getValorBool() != null) {
                            this.valorBool = exp1.getValorBool() || exp2.getValorBool();
                        }
                        break;
                        
                    case MOD :
                        if (exp1.getValorEnt() != null && exp2.getValorEnt() != null) {
                            if (!exp2.getValorEnt().equals(0)) {
                                this.valorEnt = exp1.getValorEnt() % exp2.getValorEnt();
                            }
                        }
                        break;
                        
                    case DIV :
                        if (exp1.getValorEnt() != null && exp2.getValorEnt() != null) {
                            if (!exp2.getValorEnt().equals(0)) {
                                this.valorEnt = exp1.getValorEnt() / exp2.getValorEnt();
                            }
                        }
                        break;
                        
                    case MUL :
                        if (exp1.getValorEnt() != null && exp2.getValorEnt() != null) {
                            this.valorEnt = exp1.getValorEnt() * exp2.getValorEnt();
                        }
                        break;
                        
                    case AND :
                        if (exp1.getValorBool() != null && exp2.getValorBool() != null) {
                            this.valorBool = exp1.getValorBool() && exp2.getValorBool();
                        }
                        break;
                        
                    default :
                        this.variable = Tipo_variable.BOOLEANO;
                        break;
                }
            }
        }        
    }
    
    // Clase de parámetro
    public boolean es_valor() {
        return this.parametro == Clase_parametro.VAL;
    }
    
    public boolean es_referencia() {
        return this.parametro == Clase_parametro.REF;
    }

    public void set_valor() {
        this.parametro = Clase_parametro.VAL;
    }
    
    public void set_referencia() {
        this.parametro = Clase_parametro.REF;
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

    public void set_desconocido() {
        this.variable = Tipo_variable.DESCONOCIDO;

        this.valorEnt = null;
        this.valorBool = null;
        this.valorChar = null;
    }
    
    public void set_entero() {
        this.variable = Tipo_variable.ENTERO;

        this.valorBool = null;
        this.valorChar = null;
    }
    
    public void set_booleano() {
        this.variable = Tipo_variable.BOOLEANO;

        this.valorEnt = null;
        this.valorChar = null;
    }
    
    public void set_caracter() {
        this.variable = Tipo_variable.CHAR;

        this.valorEnt = null;
        this.valorBool = null;
    }
    
    // Getters and setters
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

    public Integer getValorEnt() {
        return valorEnt;
    }

    public void setValorEnt(Integer valorEnt) {
        this.valorEnt = valorEnt;
    }

    public Boolean getValorBool() {
        return valorBool;
    }

    public void setValorBool(Boolean valorBool) {
        this.valorBool = valorBool;
    }

    public Character getValorChar() {
        return valorChar;
    }

    public void setValorChar(Character valorChar) {
        this.valorChar = valorChar;
    }
}
