%-------------------------------------------------------------------
programa errorSintactico; %3
%-------------------------------------------------------------------

 caracter respuesta, letra, min, max;
 booleano headivinado;
 caracter c, d;
 entero e;

%-------------------------------------------------------------------
accion abuelo;
%-------------------------------------------------------------------
 entero j;
 
 accion padre();
  entero j;
  
  accion hijo(ref entero j);
   principio
    j := j + 1;
   fin
   
  principio
   hijo(j);
  fin
  
 principio
  escribir("a");
 fin
 
%-------------------------------------------------------------------
principio
%-------------------------------------------------------------------
 abuelo();
fin