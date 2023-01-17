import java.util.ArrayList;
public class Graph {
    private ArrayList<ArrayList<Integer>> verticesInfo = new ArrayList<>(); // список списков соседних вершин
    private int vertices; // кол-во вершин в графе
    private Integer[] visitedVertices; //Если вершина не посещена, то налл. Иначе присваивается номер связ.компоненты
    public StringBuilder paths = new StringBuilder();

    public Graph(int vertices) {
        // конструктор создает граф с переданным кол-вом вершин
        this.vertices = vertices;
        for (int i = 0; i < vertices; i++) verticesInfo.add(new ArrayList<>());
        visitedVertices = new Integer[this.size()];
    }

    public void addEdge(int a, int b) {
        // добавление соседней вершины
        if (a == b) {
            throw new RuntimeException("Мост должен быть из одного города в другой!");
        }
        if (a > vertices | b > vertices | a < 0 | b < 0) {
            throw new RuntimeException("Такого номера города не существует!");
        }
        verticesInfo.get(a).add(b);
        verticesInfo.get(b).add(a);
    }

    public ArrayList<Integer> adjacent(int v) {
        // показывает список всех соседних вершин
        return verticesInfo.get(v);
    }

    public int size() {
        // возвращает кол-во вершин
        return vertices;
    }

    void calcPaths() {
        int binderComponent = 0;    //Номер связующей компоненты
        for (int i = 0; i < this.size(); i++) {
            if (visitedVertices[i] == null) {
                visitedVertices = this.depthFirstSearch(i, binderComponent);
                binderComponent++;
            }
        }
        print();
    }

    Integer[] depthFirstSearch(int vertex, int binderComponent) {
        visitedVertices[vertex] = binderComponent; // текущей вершине присваивается текущий номер связ.компоненты
        if (this.adjacent(vertex) != null) { // если есть сосдние вершины, то смотрим в цикле каждую соседнюю
            for (int i = 0; i < this.adjacent(vertex).size(); i++)
                if (visitedVertices[this.adjacent(vertex).get(i)] == null) // если она еще не посещена (null), то вызываем рекрсию на ней.
                    visitedVertices = depthFirstSearch(this.adjacent(vertex).get(i), binderComponent);
        }
        return visitedVertices;
    }

    private void print() {
        for (int i = 0; i < this.size(); i++) {
            int count = 0;
            int binderComponent = visitedVertices[i];
            for (int j = 0; j < visitedVertices.length; j++) {
                if (visitedVertices[j] == binderComponent & i != j) ++count;
            }
            paths.append("Из города №" + i + " достижимо " + count + " города." + '\n');
        }
        System.out.println(paths);
    }
}
