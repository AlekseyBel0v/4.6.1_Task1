import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        graph.calcPaths();
    }
}