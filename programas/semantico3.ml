%-------------------------------------------------------------------
programa semantico; %3
%-------------------------------------------------------------------

 caracter a, b; % error
 entero ww;
 
%-----------------------------------------------------------
accion accion1 (val entero j);
%-----------------------------------------------------------

%-----------------------------------------------------------
accion accion2 (ref entero j);
%-----------------------------------------------------------

Principio
  j := 5;
Fin

Principio
  dato := 5;
  dato := 6+3;
  j := 5;
  leer(j,c,c);
  accion2(r);
Fin

%-------------------------------------------------------------------
principio
%-------------------------------------------------------------------
  a := a + b;
  a := a;
  a := dd;
  a := (z > t) < z;
  y := -5;
  ww := -5;
  ww := -ww;
  a := -5;
  y := (y=true);
  y := -a;
  a := -y;
  a := 5 > 3;
  
  si (a = "b") ent
    a:= true;
  fsi
  
  mq (a <> "b")
    a:= "b";
  fmq
  
  ww := 5 + 6;
  ww := 5 - 6;
  ww := 5 OR 6;
  ww := 5 MOD 6;
  ww := 5 DIV 6;
  ww := 5 * 6;
  ww := 5 AND 6;
fin
