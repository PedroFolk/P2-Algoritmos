
# Teoria Análise de Algoritmos P2

- **Código do GitHub do Bossini**
- **Resumo via GPT**: Resumo detalhado dos algoritmos apresentados abaixo.

---

## Algoritmo de Kruskal

### Definição
O **algoritmo de Kruskal** é um algoritmo clássico da Teoria dos Grafos utilizado para encontrar uma **Árvore Geradora Mínima (AGM)** de um grafo **não direcionado** e **conectado**.  
A **Árvore Geradora Mínima** é um subconjunto das arestas que:
- Conecta todos os vértices (sem ciclos, formando uma árvore).
- Possui o **menor peso total** possível (soma mínima dos pesos das arestas).

### Etapas do Algoritmo de Kruskal
1. **Ordenar as arestas**:
   - Colocar todas as arestas do grafo em uma lista.
   - Ordenar a lista em ordem **crescente** de peso.
2. **Inicializar o Union-Find**:
   - Cada vértice começa em seu próprio conjunto (componente desconectado).
   - Utilizar a estrutura **Union-Find** para rastrear conexões entre vértices.
3. **Percorrer as arestas em ordem crescente**:
   - Para cada aresta `(u, v)`:
     - Verificar se os vértices `u` e `v` estão em conjuntos diferentes (não conectados).
     - Se estiverem separados:
       - Adicionar a aresta à árvore.
       - Unir os conjuntos de `u` e `v` no Union-Find.
     - Se já estiverem conectados, ignorar a aresta para evitar ciclos.
4. **Parar quando tiver `n - 1` arestas**:
   - Uma árvore com `n` vértices tem exatamente `n - 1` arestas.
   - Ao atingir esse número, a AGM está completa.

### Características
- **Complexidade**:
  - Ordenação das arestas: `O(E log E)`.
  - Operações Union-Find com compressão de caminho: praticamente `O(1)` por operação.
  - **Complexidade total**: `O(E log E)`, onde `E` é o número de arestas.
- **Entrada esperada**: Grafo **não direcionado**, **ponderado** e **conectado**.
- **Saída**: Conjunto de arestas que formam a AGM e o peso total mínimo.

### Código de Exemplo
```java
package arvores_geradoras_minimas;
import java.util.*;

class Aresta implements Comparable<Aresta> {
    int u, v, peso;
    Aresta(int u, int v, int peso) {
        this.u = u;
        this.v = v;
        this.peso = peso;
    }
    @Override
    public int compareTo(Aresta o) {
        return Integer.compare(this.peso, o.peso);
    }
}

class UnionFind {
    int[] representantes;
    public UnionFind(int n) {
        representantes = new int[n];
        for (int i = 0; i < n; i++) {
            representantes[i] = i;
        }
    }
    public int find(int x) {
        while (representantes[x] != x) {
            x = representantes[x];
        }
        return x;
    }
    public void union(int x, int y) {
        int rX = find(x);
        int rY = find(y);
        representantes[rY] = rX;
    }
}

public class KruskalSimples {
    public List<Aresta> kruskal(int n, List<Aresta> arestas) {
        Collections.sort(arestas);
        var uf = new UnionFind(n);
        var arvoreResultante = new ArrayList<Aresta>();
        for (var a : arestas) {
            if (uf.find(a.u) != uf.find(a.v)) {
                uf.union(a.u, a.v);
                arvoreResultante.add(a);
            }
        }
        return arvoreResultante;
    }
    public static void main(String[] args) {
        int n = 4;
        List<Aresta> arestas = Arrays.asList(
            new Aresta(0, 1, 10),
            new Aresta(0, 2, 6),
            new Aresta(0, 3, 5),
            new Aresta(1, 3, 15),
            new Aresta(2, 3, 4)
        );
        var arvoreResultante = new KruskalSimples().kruskal(n, arestas);
        int total = 0;
        for (var a : arvoreResultante) {
            System.out.printf("(%d, %d, %d) ", a.u, a.v, a.peso);
            total += a.peso;
        }
        System.out.println("Peso total: " + total);
    }
}
```

---

## Algoritmo Heap Sort

### Definição
O **Heap Sort** é um algoritmo de ordenação baseado em uma estrutura de dados chamada **heap binário** (geralmente uma **max-heap**, onde o maior elemento está na raiz).  
É um algoritmo **in-place** (não requer memória extra significativa) com complexidade de tempo `O(n log n)` em todos os casos.

### Etapas do Heap Sort
1. **Construir um Max-Heap**:
   - Reorganizar o array para satisfazer a propriedade de max-heap (cada pai é maior que seus filhos).
   - Usa o procedimento `maxHeapify` de baixo para cima.
2. **Extrair o maior elemento**:
   - Trocar a raiz (maior elemento) com o último elemento do array.
   - Reduzir o tamanho da heap (excluir a última posição, já ordenada).
   - Aplicar `maxHeapify` para restaurar a propriedade de max-heap.
3. **Repetir até o heap ficar vazio**:
   - Continuar trocando a raiz com o último elemento do heap restante e heapificando.
   - O processo continua até todos os elementos estarem ordenados.

### Características
- **Complexidade de tempo**:
  - Construção do heap: `O(n)`.
  - Cada extração com reestruturação: `O(log n)`.
  - Total: `O(n log n)`.
- **Complexidade de espaço**: `O(1)` (in-place).
- **Estável?**: Não (elementos com valores iguais podem ter posições relativas trocadas).
- **Aplicações**:
  - Eficiência garantida em todos os casos.
  - Ideal para sistemas com restrição de memória.
  - Útil em filas de prioridade e ordenação de grandes volumes de dados.

### Código de Exemplo
```java
package heap;

public class HeapSort {
    public static int parent(int i) { return i / 2; }
    public static int left(int i) { return 2 * i; }
    public static int right(int i) { return 2 * i + 1; }
    private static void swap(int[] A, int i, int j) {
        var temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    public static void maxHeapify(int[] A, int i, int n) {
        var l = left(i);
        var r = right(i);
        int largest;
        if (l <= n && A[l] > A[i]) largest = l;
        else largest = i;
        if (r <= n && A[r] > A[largest]) largest = r;
        if (largest != i) {
            swap(A, i, largest);
            maxHeapify(A, largest, n);
        }
    }
    public static void buildMaxHeap(int[] A, int n) {
        for (int i = n / 2; i >= 1; i--) {
            maxHeapify(A, i, n);
        }
    }
    public static void heapSort(int[] A) {
        int n = A.length - 1;
        buildMaxHeap(A, n);
        for (int i = n; i >= 2; i--) {
            swap(A, 1, n);
            n--;
            maxHeapify(A, 1, n);
        }
    }
}
```

---

## Algoritmo de Menor Caminho com BFS (Busca em Largura)

### Definição
A **Busca em Largura (BFS)** é um algoritmo de travessia em grafos que visita vértices em camadas, começando por um vértice de origem e explorando todos os vizinhos antes de avançar.  
Em grafos **não ponderados**, a BFS encontra o **menor caminho** (menor número de arestas) entre dois vértices.

### Objetivo
Determinar o **número mínimo de arestas** necessárias para ir de um vértice origem a um vértice destino em um grafo **não ponderado**.

### Etapas do Algoritmo BFS
1. **Inicializar estruturas auxiliares**:
   - Vetor `distancias[]`: armazena a distância de cada vértice até a origem.
   - Vetor `visitados[]`: marca vértices já visitados.
   - Fila `fila` (FIFO): processa vértices por nível.
2. **Configurar o ponto de partida**:
   - Marcar a origem como visitada.
   - Adicionar a origem à fila.
   - Definir `distancias[origem] = 0`.
3. **Executar a BFS**:
   - Enquanto a fila não estiver vazia:
     - Retirar o próximo vértice da fila.
     - Para cada vizinho:
       - Se não visitado:
         - Marcar como visitado.
         - Adicionar à fila.
         - Atualizar `distancias[vizinho] = distancias[atual] + 1`.
4. **Finalizar**:
   - `distancias[destino]` contém o número mínimo de arestas.

### Características
- **Tipo de grafo**: Não direcionado ou direcionado, **não ponderado**.
- **Complexidade de tempo**: `O(V + E)` (V = vértices, E = arestas).
- **Complexidade de espaço**: `O(V)` (para distâncias, visitados e fila).
- **Resultado**: Distância mínima em número de arestas.

### Código de Exemplo
```java
package menor_caminho;
import java.util.*;

public class Grafo {
    private int vertices;
    private LinkedList<Integer>[] adjacencias;
    Grafo(int vertices) {
        this.vertices = vertices;
        this.adjacencias = new LinkedList[this.vertices];
        for (int i = 0; i < this.vertices; i++) {
            this.adjacencias[i] = new LinkedList<>();
        }
    }
    void menorCaminho(int origem, int destino) {
        int[] distancias = new int[vertices];
        boolean[] visitados = new boolean[vertices];
        Queue<Integer> fila = new LinkedList<>();
        fila.offer(origem);
        distancias[origem] = 0;
        visitados[origem] = true;
        while (!fila.isEmpty()) {
            var atual = fila.poll();
            for (int vizinho : adjacencias[atual]) {
                if (!visitados[vizinho]) {
                    fila.offer(vizinho);
                    visitados[vizinho] = true;
                    distancias[vizinho] = distancias[atual] + 1;
                }
            }
        }
        System.out.printf("Distância de %d até %d: %d\n", origem, destino, distancias[destino]);
    }
    void adicionarAresta(int a1, int a2) {
        this.adjacencias[a1].add(a2);
        this.adjacencias[a2].add(a1);
    }
    public static void main(String[] args) {
        int vertices = Integer.parseInt(args[0]);
        Grafo grafo = new Grafo(vertices);
        for (int i = 1; i < args.length - 2; i++) {
            grafo.adicionarAresta(Integer.parseInt(args[i]), Integer.parseInt(args[i + 1]));
        }
        grafo.menorCaminho(Integer.parseInt(args[args.length - 2]), Integer.parseInt(args[args.length - 1]));
    }
}
```

---

## Algoritmo de Dijkstra — Menores Caminhos a Partir de Uma Única Origem

### Definição
O **algoritmo de Dijkstra** resolve o problema de **menores caminhos** a partir de uma única origem em um grafo **ponderado** e **direcionado** (ou não direcionado), com **pesos não negativos**.  
Calcula o **menor custo** (soma dos pesos) da origem para todos os outros vértices, armazenando a distância mínima e o caminho correspondente.

### Objetivo
- Encontrar o **menor caminho** (em soma de pesos) da origem para todos os vértices.
- Retornar a **distância mínima** e o **predecessor** de cada vértice no caminho.

### Etapas do Algoritmo de Dijkstra
1. **Inicialização**:
   - Definir distância da origem como `0` e dos outros vértices como **infinito**.
   - Inicializar predecessores como `null`.
   - Adicionar todos os vértices a um conjunto `Q` de não processados.
2. **Laço principal** (enquanto `Q` não estiver vazio):
   - Escolher o vértice `u` em `Q` com a menor distância estimada (`extrairMinimo`).
   - Para cada vizinho `v` de `u`:
     - Calcular `novaDistancia = distancia[u] + peso(u, v)`.
     - Se `novaDistancia < distancia[v]`:
       - Atualizar `distancia[v] = novaDistancia`.
       - Definir `predecessor[v] = u`.
3. **Repetir até processar todos os vértices**:
   - Cada vértice terá a menor distância desde a origem e o predecessor no caminho mínimo.

### Características
| **Propriedade**           | **Valor**                          |
|---------------------------|------------------------------------|
| Tipo de grafo             | Direcionado ou não, ponderado      |
| Peso das arestas          | Não negativos                      |
| Complexidade (lista)       | `O(V²)`                            |
| Complexidade (com heap)    | `O((V + E) log V)`                 |
| Tipo de algoritmo         | Guloso (greedy)                    |
| Resultado                 | Menor distância e predecessores    |

### Código de Exemplo
```java
package menores_caminhos_de_origem_unica;
import java.util.*;

class Grafo {
    private final Vertice[] vertices;
    private List<List<Aresta>> adjacencias;
    Grafo(String[] nomesVertices) {
        this.adjacencias = new ArrayList<>();
        this.vertices = new Vertice[nomesVertices.length];
        for (int i = 0; i < nomesVertices.length; i++) {
            this.adjacencias.add(new ArrayList<>());
            this.vertices[i] = new Vertice(nomesVertices[i], i);
        }
    }
    public void adicionarAresta(int origem, int destino, int peso) {
        adjacencias.get(origem).add(new Aresta(destino, peso));
    }
    public List<Aresta> vizinhos(int u) { return adjacencias.get(u); }
    public Vertice[] getVertices() { return vertices; }
    public int quantidadeVertices() { return vertices.length; }
    static class Vertice {
        String nome;
        int indice, distancia;
        Vertice predecessor;
        Vertice(String nome, int indice) {
            this.nome = nome;
            this.indice = indice;
            this.distancia = Integer.MAX_VALUE;
            this.predecessor = null;
        }
    }
    static class Aresta {
        int destino, peso;
        Aresta(int destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }
}

public class Dijkstra {
    public void executar(Grafo g, int s) {
        inicializarFonteUnica(g, s);
        List<Grafo.Vertice> q = new ArrayList<>(List.of(g.getVertices()));
        while (!q.isEmpty()) {
            var u = extrairMinimo(q);
            for (Grafo.Aresta aresta : g.vizinhos(u.indice)) {
                relaxar(u, g.getVertices()[aresta.destino], aresta.peso);
            }
        }
    }
    private void relaxar(Grafo.Vertice u, Grafo.Vertice v, int w) {
        if (u.distancia + w < v.distancia) {
            v.distancia = u.distancia + w;
            v.predecessor = u;
        }
    }
    private void inicializarFonteUnica(Grafo g, int s) {
        for (Grafo.Vertice v : g.getVertices()) {
            v.distancia = Integer.MAX_VALUE;
            v.predecessor = null;
        }
        g.getVertices()[s].distancia = 0;
    }
    private Grafo.Vertice extrairMinimo(List<Grafo.Vertice> q) {
        int indiceMin = 0;
        for (int i = 1; i < q.size(); i++) {
            if (q.get(i).distancia < q.get(indiceMin).distancia) {
                indiceMin = i;
            }
        }
        return q.remove(indiceMin);
    }
    public static void main(String[] args) {
        String[] nomes = {"s", "t", "x", "y", "z"};
        Grafo g = new Grafo(nomes);
        g.adicionarAresta(0, 1, 10);
        g.adicionarAresta(0, 3, 5);
        g.adicionarAresta(1, 2, 1);
        g.adicionarAresta(1, 3, 2);
        g.adicionarAresta(2, 4, 4);
        g.adicionarAresta(3, 1, 3);
        g.adicionarAresta(3, 2, 9);
        g.adicionarAresta(3, 4, 2);
        g.adicionarAresta(4, 2, 6);
        var dijkstra = new Dijkstra();
        dijkstra.executar(g, 0);
        for (Grafo.Vertice v : g.getVertices()) {
            System.out.printf("Distância de s a %s: %d\n", v.nome, v.distancia);
            System.out.printf("%s\n", v.predecessor != null ? "Predecessor de " + v.nome + ": " + v.predecessor.nome : "Não tem predecessor");
        }
    }
}
```

---

## Algoritmo de Bellman-Ford — Menores Caminhos com Pesos Negativos

### Definição
O **algoritmo de Bellman-Ford** resolve o problema de **menores caminhos** a partir de uma única origem em grafos **direcionados** e **ponderados**, incluindo **pesos negativos**.  
Diferente de Dijkstra, suporta **arestas com pesos negativos** e detecta **ciclos negativos** (ciclos com soma de pesos menor que zero).

### Objetivo
- Encontrar o **menor caminho** (menor soma de pesos) da origem para todos os vértices.
- Identificar a presença de **ciclos negativos** no grafo.

### Etapas do Algoritmo Bellman-Ford
1. **Inicializar**:
   - Definir distância da origem como `0` e dos outros vértices como **infinito**.
   - Inicializar predecessores como `null`.
2. **Relaxar todas as arestas (V - 1) vezes**:
   - Para cada vértice `u`:
     - Para cada aresta `u → v` com peso `w`:
       - Se `dist[u] + w < dist[v]`:
         - Atualizar `dist[v] = dist[u] + w`.
         - Definir `predecessor[v] = u`.
3. **Verificar ciclos negativos**:
   - Para cada aresta `u → v` com peso `w`:
     - Se `dist[u] + w < dist[v]`, existe um ciclo negativo acessível.

### Características
| **Propriedade**           | **Valor**                          |
|---------------------------|------------------------------------|
| Tipo de grafo             | Direcionado e ponderado            |
| Suporta pesos negativos   | Sim                                |
| Detecta ciclos negativos  | Sim                                |
| Complexidade de tempo     | `O(V × E)`                         |
| Complexidade de espaço    | `O(V)`                             |
| Tipo de algoritmo         | Programação dinâmica               |
| Estável                   | Sim (para caminhos reais)          |

### Código de Exemplo
```java
package menores_caminhos_de_origem_unica;
import java.util.*;

class Grafo {
    private final Vertice[] vertices;
    private List<List<Aresta>> adjacencias;
    Grafo(String[] nomesVertices) {
        this.adjacencias = new ArrayList<>();
        this.vertices = new Vertice[nomesVertices.length];
        for (int i = 0; i < nomesVertices.length; i++) {
            this.adjacencias.add(new ArrayList<>());
            this.vertices[i] = new Vertice(nomesVertices[i], i);
        }
    }
    public void adicionarAresta(int origem, int destino, int peso) {
        adjacencias.get(origem).add(new Aresta(destino, peso));
    }
    public List<Aresta> vizinhos(int u) { return adjacencias.get(u); }
    public Vertice[] getVertices() { return vertices; }
    public int quantidadeVertices() { return vertices.length; }
    static class Vertice {
        String nome;
        int indice, distancia;
        Vertice predecessor;
        Vertice(String nome, int indice) {
            this.nome = nome;
            this.indice = indice;
            this.distancia = Integer.MAX_VALUE;
            this.predecessor = null;
        }
    }
    static class Aresta {
        int destino, peso;
        Aresta(int destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }
}

public class BellmanFord {
    public boolean executar(Grafo g, int s) {
        inicializarFonteUnica(g, s);
        for (int i = 0; i < g.quantidadeVertices() - 1; i++) {
            for (var u : g.getVertices()) {
                for (var a : g.vizinhos(u.indice)) {
                    var v = g.getVertices()[a.destino];
                    relaxar(u, v, a.peso);
                }
            }
        }
        for (var u : g.getVertices()) {
            for (var a : g.vizinhos(u.indice)) {
                var v = g.getVertices()[a.destino];
                if (v.distancia > u.distancia + a.peso) return false;
            }
        }
        return true;
    }
    private void relaxar(Grafo.Vertice u, Grafo.Vertice v, int w) {
        if (u.distancia + w < v.distancia) {
            v.distancia = u.distancia + w;
            v.predecessor = u;
        }
    }
    private void inicializarFonteUnica(Grafo g, int s) {
        for (Grafo.Vertice v : g.getVertices()) {
            v.distancia = Integer.MAX_VALUE;
            v.predecessor = null;
        }
        g.getVertices()[s].distancia = 0;
    }
    public static void main(String[] args) {
        String[] nomes = {"s", "t", "x", "y", "z"};
        Grafo g = new Grafo(nomes);
        g.adicionarAresta(0, 1, 10);
        g.adicionarAresta(0, 3, 5);
        g.adicionarAresta(1, 2, 1);
        g.adicionarAresta(1, 3, 2);
        g.adicionarAresta(2, 4, 4);
        g.adicionarAresta(3, 1, 3);
        g.adicionarAresta(3, 2, 9);
        g.adicionarAresta(3, 4, 2);
        g.adicionarAresta(4, 2, 6);
        var bellmanFord = new BellmanFord();
        boolean sucesso = bellmanFord.executar(g, 0);
        if (sucesso) {
            for (Grafo.Vertice v : g.getVertices()) {
                System.out.printf("Distância de s a %s: %d\n", v.nome, v.distancia);
                System.out.printf("%s\n", v.predecessor != null ? "Predecessor de " + v.nome + ": " + v.predecessor.nome : "Não tem predecessor");
            }
        } else {
            System.out.println("O grafo contém um ciclo de peso negativo.");
        }
    }
}


---

**Nota**: O link para o repositório do Bossini deve ser atualizado com o URL correto, caso disponível.
```
