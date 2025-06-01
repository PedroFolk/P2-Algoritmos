package menor_caminho;
import java.util.*;
public class Grafo {
  private int vertices;
  private LinkedList <Integer> [] adjacencias;

  Grafo(int vertices){
    //número de vertices (vertice = bola)
    this.vertices = vertices;
    //Adjancente é uma LinkedList e é igual ao número de vertices (uma LinkedList por vertice)
    this.adjacencias = new LinkedList [this.vertices];
    for(int i = 0; i < this.vertices; i++){
      this.adjacencias[i] = new LinkedList<Integer>();
    }
  }

  void menorCaminho(int origem, int destino){
    int [] distancias = new int[vertices]; //já começa inicializado por zero
    boolean [] visitados = new boolean[vertices];//já começa inicializado por false
    //Iniciamos uma fila de integers 
    Queue <Integer> fila = new LinkedList<>();
    //Começamos  a fila com a origem nesse caso 0 e definimos q a distancia da origem até ela mesma é 0 e que ela já foi visitada
    fila.offer(origem);
    //poderiamos usar o fila.add(origem) tambem
    distancias[origem] = 0;
    visitados[origem] = true;
    //Verificamos se a lista esta vazia
    while(!fila.isEmpty()){
      //fila.pick() não remove da fila, fila.poll() pega o atual e remove da fila 
      var atual = fila.poll();
      //pegamos os vizinhos do vertice atual
      for (int vizinho : adjacencias[atual]){
        //so adicionamos se o vizinho não foi visitado
        if(!visitados[vizinho]){
          //colocamos o vizinho no final da fila
          fila.offer(vizinho);
          //definimos que o vizinho já foi visitado
          visitados[vizinho] = true;
          //Calculamos o vizinho com a distancia do atual da origem + 1
          distancias[vizinho] = distancias[atual] + 1;
        }
      }
    }    
    //printamos a origem destino e a distancia nesse caso é Distância de 5 até 0: 1
    System.out.printf(
      "Distância de %d até %d: %d\n",
      origem, destino, distancias[destino]
    );

  }

  void adicionarAresta(int a1, int a2){
    this.adjacencias[a1].add(a2);
    this.adjacencias[a2].add(a1);
  }

  //java Grafo 7 0 1 0 2 0 5 1 3 2 4 4 6 5 6 0 5
  // traduzindo 7 vertices,"arestas" 0-1,0-2,0-5,1-3,2-4,4-6,5-6, 0 (origem) 5 (destino)
  public static void main(String[] args) {
    int vertices = Integer.parseInt(args[0]);
    //constroi o grafo sem as arestas
    Grafo grafo = new Grafo(vertices);
    //args são os valores passados na linha de comando neste caso da linha 46, removemos a posição 0 (número de vertices e iniciamos na posição 1) e depois removemos os dois ultimos 0 e 5
    for (int i = 1; i < args.length - 2; i++){
      grafo.adicionarAresta(
        Integer.parseInt(args[i]),
        Integer.parseInt(args[i + 1])
      );
    }
    grafo.menorCaminho(
      //length igual 17 para acessar o último valor usamos o [16] por isso -2 para acessar o 15 e -1 para acessar o 16
      Integer.parseInt(args[args.length - 2]),
      Integer.parseInt(args[args.length - 1])
    );  
  }
}
