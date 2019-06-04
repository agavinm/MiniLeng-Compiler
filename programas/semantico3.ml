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
  z:=3;
Fin

Principio
  dato := 5;
  dato := 6+3;
  j := 5;
  leer(j,c,c);
  accion2(r);
  accion2(r);
  accion2(5);
  accion2(j);
  accion2(5, 5, 6);
Fin

accion a3;
Principio
  accion1(5);
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
  ww := 5 + "a";
  
  ww := 5 / (1 - 1 * 6 + 5);
  ww := 5 mod 0;
  
  ww := caraent("a");
  ww := entacar(350);
  ww := caraent(350);
  ww := entacar("a");
  
  a := caraent("a");
  a := entacar(5);
  
  accion1(5);
  ww(5);
  accion1(accion1);
  a3(5);
fin
