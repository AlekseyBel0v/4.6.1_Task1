import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.text.StringContainsInOrder.stringContainsInOrder;
// TODO: 17.01.2023
//  Почему я не могу импортировать сразу все классы из какого-нибудь одного пакета hamcrest? как это сделать?
//  например: org.hamcrest.beans.*;

public class GraphTest {
    int vertices = 6;
    Graph graph = new Graph(vertices);

    @Test
    public void testSize() {
        int expected = vertices;

        Assertions.assertEquals(expected, graph.size());
    }

    @Test
    public void testAddEdgeAndAdjacent() {
        int a1 = 1, b1 = 2;
        int a2 = 3, b2 = 3, a3 = vertices + 1, b3 = -1;

        graph.addEdge(a1, b1);
        String vertex1 = graph.adjacent(1).toString();
        String vertex2 = graph.adjacent(2).toString();

        Assertions.assertTrue(vertex1.contains("2") && vertex2.contains("1"));
        Assertions.assertThrows(RuntimeException.class, () -> graph.addEdge(a2, b2));
        Assertions.assertThrows(RuntimeException.class, () -> graph.addEdge(a3, b3));
    }

    @Test
    public void testCalcPaths() {
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        String expected =
                "Из города №0 достижимо 2 города.\n" +
                "Из города №1 достижимо 2 города.\n" +
                "Из города №2 достижимо 2 города.\n" +
                "Из города №3 достижимо 1 города.\n" +
                "Из города №4 достижимо 1 города.\n" +
                "Из города №5 достижимо 0 города.\n";

        graph.calcPaths();

        Assertions.assertTrue(graph.paths.toString().contains(expected));
    }

    // TODO: 17.01.2023
    //  Не смог разобраться, почему не компилируется
//    @Test
//    public void testSizeByHamcrest() {
//        int expected = vertices;
//
//        assertThat(graph, hasProperty("vertices", assertThat(6, is(6))));
//    }

    @Test
    public void testAddEdgeAndAdjacentByHamcrest() {
        int a1 = 1, b1 = 2;
        int a2 = 3, b2 = 3, a3 = vertices + 1, b3 = -1;

        graph.addEdge(a1, b1);
        String vertex1 = graph.adjacent(1).toString();
        String vertex2 = graph.adjacent(2).toString();

        assertThat(vertex1, containsString("2"));
        assertThat(vertex2, containsString("1"));

        // TODO: 17.01.2023 Не нашел, как с помощью мэтчерсов переписать ассерты, которые проверяют тип брошенного исключения.
        //  Помогите, пожалуйста, разобраться.
    }

    @Test
    public void testCalcPathsByHamcrest() {
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        String[] expected = {"0", "2", "1", "2", "2", "2", "3", "1", "4", "1", "5", "0"};

        graph.calcPaths();

        assertThat(graph.paths.toString(), stringContainsInOrder(Arrays.asList(expected)));
    }
}