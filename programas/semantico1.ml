%-------------------------------------------------------------------
programa semantico; %1 Comprobar tabla de símbolos
%-------------------------------------------------------------------

 caracter letra;
 booleano cierto;
 entero dato;

%-----------------------------------------------------------
accion accion1 (ref entero dato);
%-----------------------------------------------------------

 caracter letra;
 booleano cierto;

%-----------------------------------------------------------
accion accion2 (val caracter letra);
%-----------------------------------------------------------

 booleano cierto;
 entero dato;

%-----------------------------------------------------------
accion accion3 (ref booleano cierto);
%-----------------------------------------------------------

 caracter letra;
 entero dato;

%-----------------------------------------------------------
accion accion4 (ref booleano cierto; val caracter letra; ref entero dato);
%-----------------------------------------------------------

%-----------------------------------------------------------
accion accion5 (ref booleano cierto; val caracter letra; val entero dato);
%-----------------------------------------------------------

Principio
  escribir (entacar (13), entacar (10));
Fin

Principio
  escribir (entacar (13), entacar (10));
Fin

Principio
  escribir (entacar (13), entacar (10));
Fin

Principio
  escribir (entacar (13), entacar (10));
Fin

Principio
  escribir (entacar (13), entacar (10));
Fin
 
%-------------------------------------------------------------------
principio
%-------------------------------------------------------------------
  escribir (entacar (13), entacar (10));
fin
