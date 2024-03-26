homework:\
Algoritm Greedy + generare random de instante mari\
Generare random: primesc nr de roads si nr de destinatii si generez random, apoi generez random persoanele primind numarul de persoane si road-urile\
Greedy: iau destinatiile driverilor si pentru fiecare incerc sa fac match cu un pasager daca are aceeasi destinatie sau daca destinatia se afla pe un road comun inainte de destinatia driverului\
Bonus: graf bipartit: o partitie cu driveri si o partitie cu pasageri, un driver are muchie cu pasagerii pe care i-ar putea lua in masina, se cauta un cuplaj maxim in graf si se face match\
se foloseste HOPCROFT-KARP pentru a gasi cuplajul maxim (GRAPH4J)\
pentru max independent set vom folosi cuplajul maxim si vom afla minimum vertex cover