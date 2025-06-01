# P2_Algoritmos

representação gráfica do algoritmo de menor caminho 

![image](https://github.com/user-attachments/assets/1b427f17-148e-4261-862e-bf58951ee524)

representação gráfica do algoritmo de Dijkstra (grafo direcionado ou digrafo) 

V = vértices, E = arestas

Dijkstra tem como complexidade O(V²) com ArrayList (como nesse código); pode ser melhor com heap

problema

![image](https://github.com/user-attachments/assets/0ae549a9-0112-447f-9cf3-a2d942bcfd08)


resolução

![image](https://github.com/user-attachments/assets/e5693235-6795-4e9b-a62e-da2b57c16501)

Bellman-Ford funciona com pesos negativos e tem como complexidade O(V * E)

Ele itera V-1 vezes ou seja se tem 6 vertices ele itera 5 vezes sobre cada aresta ou seja se tivessemos 5 arestas teriamos 25 iterações

Nem todo valor negativo implica em ciclo negativo.


Lógica do algoritmo Bellman-Ford:
1.	Inicialize todas as distâncias com infinito, exceto a origem (distância 0).
2.	Repita V-1 vezes, sendo V o número de vértices do grafo:
o	Para cada aresta (u → v) com peso w, faça o relaxamento:
	Se dist[u] + w < dist[v], então atualize:
dist[v] = dist[u] + w
3.	Passo adicional (opcional): Verificar se existe ciclo negativo no grafo.
o	Verifique se após isso ainda é possível fazer relaxamentos.
o	Se sim, há um ciclo negativo (sinal de alerta!).

Semelhanças com o Dijkstra:

Ambos encontram o menor caminho de um vértice origem para todos os outros.

Ambos utilizam o conceito de relaxamento de arestas.

Ambos iniciam com uma distância infinita para todos os vértices, exceto a origem.

Diferenças com o Djkstra:

Dijkstra não funciona com pesos negativos, enquanto Bellman-Ford funciona.

Bellman-Ford consegue detectar ciclos negativos; Dijkstra não.

Arvore é um grafo sem ciclo 

Arvore geradora é uma que contém todos os vertices do grafo (todos estão conectados) 

Arvore geradora minima usar as arestas com o menor peso possível sem gerar um ciclo

Existem duas arvores geradores minimas nessa imagem 

![image](https://github.com/user-attachments/assets/ee5ec1b9-3174-4187-9705-fb7a4aa5ae1a)

Complexidade Kruskal e Prim 

se utilizar binary heap 

![image](https://github.com/user-attachments/assets/ba207e1d-16be-4297-a22e-5cec16a4757d)

Algoritmo de Prim roda em tempo de oque melhora com a implementação de binary heap se V (vertices) é bem menor que E (arestas)

![image](https://github.com/user-attachments/assets/7ed9c9fa-11e5-4367-8760-2bee78a08625)

Kruksal 
ordena as arestas de menor pesos primeiro de forma "não crescente" exemplo - 1 2 2 3 é crescente porem não sempre
verifica se não a ciclos 

4 componentes conexos 

![image](https://github.com/user-attachments/assets/78e3561b-66c0-44df-ae8c-02b7a14cd2d8)

passo a passo de kruskal 

![image](https://github.com/user-attachments/assets/de9310d3-ba42-4d44-8d4f-21fc5abc89c1)

heap sort e a complexidade de outros metodos 

![image](https://github.com/user-attachments/assets/1fc8e1cc-8fe7-40e5-af6f-7d83c676f6bf)

![image](https://github.com/user-attachments/assets/7d951f05-5b44-40f1-929d-8d5b61f1b073)

Em um min-heap, o valor de cada nó é menor ou igual ao valor dos seus filhos.
Ou seja, o menor valor está sempre na raiz (raiz é o topo).

max-heap é onde o valor de cada nó é maior ou igual ao valor de seus filhos.
Isso significa que o maior valor sempre está na raiz

![image](https://github.com/user-attachments/assets/b74336c4-b9bb-41af-925a-94c6f8ee3977)

7 maior que o seis, por isso é falso

![image](https://github.com/user-attachments/assets/9c3a16b1-5b0f-46ce-be9b-309a734f6483)

![image](https://github.com/user-attachments/assets/4f8d83be-b54a-4f2a-9647-63d37e52d5d8)

BuildMax heap vai funcionar na seguinte ordem 5,4,3,2,1 onde ele vai utilizar MaxHeapify

![image](https://github.com/user-attachments/assets/9586e246-7d56-4f49-b48f-58d090a763ae)

Como o heapsort funciona em imagens 

![image](https://github.com/user-attachments/assets/72706b71-4748-48ec-9fe7-37104f6dcc11)






