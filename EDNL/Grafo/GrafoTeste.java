public class GrafoTeste {
    public static void main(String[] args) {
        System.out.println("Grafo Simples Não Direcionado ");
        Grafo g1 = new Grafo();

        Vertice v1 = g1.insertVertice("A");
        Vertice v2 = g1.insertVertice("B");
        Vertice v3 = g1.insertVertice("C");
        Vertice v4 = g1.insertVertice("D");

        Aresta a1 = g1.insertAresta(v1, v2, 10, false);
        Aresta a2 = g1.insertAresta(v1, v3, 20, false);
        Aresta a3 = g1.insertAresta(v2, v4, 30, false);
        Aresta a4 = g1.insertAresta(v3, v4, 40, false);

        g1.mostrarGrafo();
        System.out.println();
        System.out.println("Matriz de Adjacência:");
        g1.mostrarMatriz();

        System.out.println();
        System.out.println("A e B são adjacentes? " + g1.areAdjacent(v1, v2));
        System.out.println("A e D são adjacentes? " + g1.areAdjacent(v1, v4));
        System.out.println("Grau de A: " + g1.degree(v1));
        System.out.println("Grau de D: " + g1.degree(v4));

        System.out.println();
        System.out.println("Vértice oposto de A na aresta A-B: " + g1.opposite(v1, a1).getValor());

        System.out.println();
        System.out.println("Arestas incidentes em D:");
        for (Aresta a : g1.incidentEdges(v4)) {
            System.out.println("  " + a.getOrigem().getValor() + " --- " + a.getDestino().getValor());
        }

        System.out.println();
        System.out.println("Vértices adjacentes a A:");
        for (Vertice v : g1.adjacentVertices(v1)) {
            System.out.println("  " + v.getValor());
        }

        System.out.println();
        System.out.println("Multigrafo Misto (arestas paralelas + direcionadas)");
        Grafo g2 = new Grafo();

        Vertice u1 = g2.insertVertice("X");
        Vertice u2 = g2.insertVertice("Y");
        Vertice u3 = g2.insertVertice("Z");

        // arestas paralelas entre X e Y (multigrafo)
        Aresta e1 = g2.insertAresta(u1, u2, "voo1", false);
        Aresta e2 = g2.insertAresta(u1, u2, "voo2", false);
        Aresta e3 = g2.insertAresta(u1, u2, "voo3", false);

        // aresta direcionada
        Aresta e4 = g2.insertAresta(u1, u3, "rua_sentido_unico", true);

        // aresta não direcionada
        Aresta e5 = g2.insertAresta(u2, u3, "estrada", false);

        g2.mostrarGrafo();
        System.out.println();
        System.out.println("Matriz de Adjacência (número de arestas por par):");
        g2.mostrarMatriz();

        System.out.println();
        System.out.println("X e Y são adjacentes? " + g2.areAdjacent(u1, u2));
        System.out.println("Grau de X: " + g2.degree(u1));

        System.out.println();
        System.out.println("Remoção");
        Grafo g3 = new Grafo();

        Vertice p = g3.insertVertice("P");
        Vertice q = g3.insertVertice("Q");
        Vertice r = g3.insertVertice("R");

        Aresta ap = g3.insertAresta(p, q, 1, false);
        Aresta aq = g3.insertAresta(q, r, 2, false);

        System.out.println("Antes da remoção:");
        g3.mostrarGrafo();

        g3.removeAresta(ap);
        System.out.println("\nApós remover aresta P-Q:");
        g3.mostrarGrafo();

        g3.removeVertice(q);
        System.out.println("\nApós remover vértice Q:");
        g3.mostrarGrafo();

        System.out.println();
        System.out.println("Replace");
        Grafo g4 = new Grafo();
        Vertice t1 = g4.insertVertice("antes");
        Aresta ta = g4.insertAresta(t1, g4.insertVertice("outro"), 99, false);

        System.out.println("Valor do vértice: " + t1.getValor());
        g4.replaceVertice(t1, "depois");
        System.out.println("Após replace: " + t1.getValor());

        System.out.println("Valor da aresta: " + ta.getValor());
        g4.replaceAresta(ta, 777);
        System.out.println("Após replace: " + ta.getValor());
    }
}