homework: algoritm greedy luand mereu cel mai apropiat client si verificand ca ajung in intervalul sau orar, distantele sunt generate random si nu sunt stocate. Incep un tur nou, aleg dintr-un depou un vehicul care inca nu a facut niciun tur, iar dintre clientii care inca nu au fost vizitati il aleg pe cel mai apropiat de locatia curenta. Daca nu mai pot vizita niciunul in intervalul sau, incep un alt tur.\
(sunt foarte multe fisiere de la generarea javadoc-ului)\
(compulsory este comentat deoarece Vehicle devine clasa abstracta si nu mai poate fi instantiata)\
bonus: distantele nu mai sunt generate random, trebuie stocate si vor fi sub forma unui grid graf\ am matrice de cost pe care pot calcula shortest path\
generez random instante mari, iar pentru matricea de cost ma folosesc de o alta matrice de adiacenta generata random cu niste constrangeri pentru a pastra proprietatea de grid graf\
dupa ce am grid graful pot genera random costurile pentru fiecare muchie din el\
pot exista probleme care nu au solutii din cauza intervalelor clientilor si distantelor fata de depouri plus lipsa de vehicule suficiente\
pentru a evita aceasta, generam cat mai multe vehicule, dar nu le folosim pe toate, ci cat mai putine tururi posibile