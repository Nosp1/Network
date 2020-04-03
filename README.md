# Oppgave 1
Oppgavetekst: Write a program that uses the data in the table below, and finds the cheapest way of connecting all locations. The output of the program shall be the connections that are needed specified with their endpoints in brackets (e.g. [A B]), and the total cost of the connections. 

|	|A	|B	|C	|D	|E	|F|
|------|------|------|------|------|------|------|
|A	|0	|10	|5	|9999	|3	|12|
|B	|	|0	|17	|9	|17	|12|
|C	|	|	|0	|35	|3	|12|
|D	|	|	|	|0	|999	|12|
|E	|	|	|	|	|0	|12|
|F	|	|	|	|	|	|0|

Vi startet å kikke på ulike algoritmer for å løse denne obligatoriske oppgaven. Vi tenkte på både Dijkstra's og Floyd-Warshall, men endte opp med å bruke Prim's algoritme, da denne finner den billigste veien innom alle noder uten å være innom en node flere ganger. Vi løste deler av oppgaven først for hånd, og misforstod litt av hvordan algoritmen fungerte, og trodde både node E og F kom til å skape problemer da den hadde duplikate verdier av den minste Edge-verdien. For eksempel trodde vi at det ville være ulike utfall om du startet med [F A] fra om du startet med [F B]. Dette tok mye tid frem mot innlevering, og vi måtte starte ganske fra bunnen av i oppagave 1. Dette førte til at vi måtte finne og ta mye inspirasjon fra andre ferdig-laga løsninger på nettet. For å se hva vi tenkte på kan dere se i branchene update_psuedo og backup_19500204.

Løsningen vi sitter igjen med finner den billigste måten å være innom alle noder, og printer resultatet "alfabetisk", fra node A til B til C osv. 

A --- 10.0 --> B

A --- 12.0 --> F

A --- 3.0 --> E

B --- 9.0 --> D

C --- 3.0 --> E
