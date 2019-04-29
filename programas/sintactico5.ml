%-------------------------------------------------------------------
programa errorSintactico; %5
%-------------------------------------------------------------------

 caracter respuesta, letra, min, max;
 booleano headivinado;
 caracter c, d;
 entero e;
 
%-------------------------------------------------------------------
principio
%-------------------------------------------------------------------
 si e = 5 ent
  si NOT headivinado ent
   si (letra <> respuesta) ent
    si ((((e)))) ent
     c := e;
    si_no
     d := e;
    fsi
   si_no
    si e <= true mod 5 div 7 ent
     min := NOT max;
    fsi
   fsi
  fsi 
 fsi
fin
