programa gencodigo2;
  entero i, j;
  
  accion q(ref entero m);
  principio
    escribir(m);
    m := 0;
  fin
  
  accion r(val entero k; ref entero l);
  principio
    escribir(k,l);
    l := 0;
    q(l);
    q(l);
  fin

principio
  r(i,j);
fin
