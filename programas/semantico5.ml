%-------------------------------------------------------------------
programa semantico; %5 Comprobar parte 2 (11-14)
%-------------------------------------------------------------------

 entero e1, e2, e3;
 caracter c1, c2, c3;
 booleano b1, b2, b3;
 
 caracter a, b;
 entero ww;
 
%-------------------------------------------------------------------
principio
%-------------------------------------------------------------------
  a := a + b; % error
  a := true * true; % error
  a := true + true; % error
  a := a;
  a := dd; % error
  a := (z > t) < z; % error
  y := -5; % error
  ww := -5;
  ww := -ww;
  a := -5; % error
  y := (y=true);
  y := -a; % error
  a := -y;
  a := 5 > 3; % error
  
  si (a = "b") ent
    a:= true; % error
  fsi
  
  mq (a <> "b")
    a:= "b";
  fmq
  
  ww := 5 + 6;
  ww := 5 - 6;
  ww := 5 OR 6; % error
  b1 := 5 OR 6; % error
  ww := 5 MOD 6;
  ww := 5 DIV 6;
  ww := 5 * 6;
  ww := 5 AND 6; % error
  ww := 5 + "a"; % error
  
  e1 := e2 + e3;
  e1 := 5 * e6; % error
  e1 := b1 + b2; % error
  e1 := b3; % error
  e1 := b3 + e6; % error
  c1 := e6;
  c1 := e7; % error
  c1 := e1; % error
  c1 := c1 + c2; % error
  c1 := c3;
  c1 := c1 <> c2; % error
  c1 := b1 * c1; % error
  
  e1 := 0;
  e2 := 5 / e1;
  e2 := 5 / 0; % error
  e2 := 5 / (7 - 7); % error
  e2 := 5 mod 7 * 0 + 1;
  e2 := 5 mod (7 * 0 + 3 - 1 * 3); % error
  
  e2 := caraent("a");
  e2 := entacar(350); % error
  e2 := caraent(350); % error
  e2 := entacar("a"); % error
  c1 := caraent("a"); % error
  c1 := entacar(5);
fin
