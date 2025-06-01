
Algoritmo de Kruskal  
- Voltamos a considerar Grafos Não Direcionados; 
- Grafos Possuem Pesos nas Arestas; 
- Queremos extrair a Árvore Geradora Mínima de um Grafo.
  
No contexto de grafos, o que é Árvore?  
- Árvore é um Grafo sem ciclo:

Não é Árvore (Tem Ciclo) 

![image](https://github.com/user-attachments/assets/768ef61b-25db-49f2-9505-a10b94d1c007)


É Árvore (Não tem Ciclo) 

![image](https://github.com/user-attachments/assets/81a2db09-cbdb-437c-925e-b92da192c1ee)

No contexto de grafos, o que é Árvore Geradora? 
- Conjunto de arestas que conecta todos os vér ces, sem formar um ciclo. 

![image](https://github.com/user-attachments/assets/232538c8-7d8a-4d2d-bf67-ba0cd20d3370)


No contexto de grafos, o que é Árvore Geradora Mínima? 
- O conjunto de arestas de menor custo que conecta todos os vér ces, sem formar um 
ciclo.
- Um grafo pode ter duas árvores geradoras mínimas.
  
Algoritmo de Kruskal  
- Exemplo de Entrada:

![image](https://github.com/user-attachments/assets/1662a1ac-80df-42e6-8ce4-648c83ea7309)

- Exemplo de Saída: Encontra-se o custo da Árvore Geradora Mínima.
Neste caso, custo =  2 + 4 + 3 + 1 = 10

![image](https://github.com/user-attachments/assets/6d74f9d6-9aed-413a-971b-f4a345074f7e)

Pseudocódigo do Algoritmo de Kruskal: 

![image](https://github.com/user-attachments/assets/7dbb0318-ea70-415c-b6e2-729a2d18f438)

Make-Set(v): Cria um conjunto onde o elemento v é o único membro.  
- Inicialmente, cada vér ce é o seu próprio conjunto.  
- Na prá ca, pensando na implementação em código, criaremos uma lista e cada 
vér ce será uma das posições dessa lista.

- Find-Set(v):  Quando estamos avaliando uma aresta (por exemplo, A–B): 
o Perguntamos: "A e B estão no mesmo conjunto?" 
o Se FIND-SET(A) ≠ FIND-SET(B), significa que: Eles estão em conjuntos 
diferentes, então podemos adicionar essa aresta (não vai formar ciclo).

- Union(u, v): Une dois conjuntos diferentes, o conjunto de u com o conjunto de v. 
o Na prá ca, pensando na implementação em código, dizemos que o índice u (de 
um vetor de representantes) "aponta" para o conjunto de v.
