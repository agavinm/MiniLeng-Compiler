%-------------------------------------------------------------------
programa semantico; %4 Comprobar parte 2 (7-10)
%-------------------------------------------------------------------

 entero a;
 
%-----------------------------------------------------------
accion dato (val entero datos);
%-----------------------------------------------------------
 entero b;
 
%-----------------------------------------------------------
accion dato (ref entero datos);
%-----------------------------------------------------------

%-----------------------------------------------------------
accion valor (val entero datos);
%-----------------------------------------------------------

Principio %valor
  escribir(datos);
Fin

Principio %dato ref
  dato(datos);
  dato(b);
  datos := 5;
  leer(datos);
  leer(5); % error
  leer(datos +; % error
  escribir(datos);
Fin

Principio %dato val
  dato(datos); % error
  datos := 5; % error
  leer(datos); % error
  escribir(datos);
  escribir(datos + datos * 5);
  escribir(true OR ("a" <> "b"));
  escribir("a");
Fin
 
%-------------------------------------------------------------------
principio
%-------------------------------------------------------------------
  a := a + a;
  a := a;
  dato(a);
  dato(5 + 6 * 4);
  dato(a + 7);
  dato(true); % error
  dato("a"); % error
fin
