import java.util.ArrayList;
import java.util.List;

public class Grafo {
    private ArrayList<Vertice> vertices;
    private ArrayList<ArrayList<ArrayList<Aresta>>> matrizAdj;
    private int numVertices;
    private int numArestas;

    public Grafo() {
        vertices = new ArrayList<>();
        matrizAdj = new ArrayList<>();
        numVertices = 0;
        numArestas = 0;
    }

    public int numVertices() {
        return numVertices;
    }

    public int numArestas() {
        return numArestas;
    }

    public boolean isEmpty() {
        if (numVertices == 0) {
            return true;
        }
        return false;
    }

    public List<Vertice> vertices() {
        List<Vertice> lista = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i) != null) {
                lista.add(vertices.get(i));
            }
        }
        return lista;
    }

    public Vertice insertVertice(Object valor) {
        Vertice novo = new Vertice(valor, numVertices);
        vertices.add(novo);

        for (int i = 0; i < numVertices; i++) {
            matrizAdj.get(i).add(new ArrayList<>());
        }

        ArrayList<ArrayList<Aresta>> novaLinha = new ArrayList<>();
        for (int j = 0; j <= numVertices; j++) {
            novaLinha.add(new ArrayList<>());
        }
        matrizAdj.add(novaLinha);

        numVertices++;
        return novo;
    }

   public void removeVertice(Vertice v) {
    if (!vertices.contains(v)) {
        throw new GrafoExcecao("Vértice não encontrado no grafo.");
    }
    int idx = v.getIndice();

    for (int j = 0; j < numVertices; j++) {
        ArrayList<Aresta> celula = matrizAdj.get(idx).get(j);
        for (int k = 0; k < celula.size(); k++) {
            Aresta a = celula.get(k);
            if (a.isDirecionada() || idx <= j) {
                numArestas--;
            }
        }
    }
    for (int i = 0; i < numVertices; i++) {
        if (i == idx) continue;
        ArrayList<Aresta> celula = matrizAdj.get(i).get(idx);
        for (int k = 0; k < celula.size(); k++) {
            if (celula.get(k).isDirecionada()) {
                numArestas--;
            }
        }
    }

    matrizAdj.remove(idx);
    for (int i = 0; i < matrizAdj.size(); i++) {
        matrizAdj.get(i).remove(idx);
    }
    vertices.remove(idx);
    for (int i = idx; i < vertices.size(); i++) {
        vertices.get(i).setIndice(i);
    }

    numVertices--;
}

    public Object replaceVertice(Vertice v, Object novoValor) {
        if (!vertices.contains(v)) {
            throw new GrafoExcecao("Vértice não encontrado no grafo.");
        }
        Object valorAntigo = v.getValor();
        v.setValor(novoValor);
        return valorAntigo;
    }

    public List<Aresta> arestas() {
        List<Aresta> lista = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                ArrayList<Aresta> celula = matrizAdj.get(i).get(j);
                for (int k = 0; k < celula.size(); k++) {
                    Aresta a = celula.get(k);
                    if (a.isDirecionada() || i <= j) {
                        lista.add(a);
                    }
                }
            }
        }
        return lista;
    }

    public Aresta insertAresta(Vertice origem, Vertice destino, Object valor, boolean direcionada) {
        if (!vertices.contains(origem) || !vertices.contains(destino)) {
            throw new GrafoExcecao("Um ou ambos os vértices não pertencem ao grafo.");
        }
        int i = origem.getIndice();
        int j = destino.getIndice();

        Aresta nova = new Aresta(origem, destino, valor, direcionada);
        matrizAdj.get(i).get(j).add(nova);
        if (!direcionada) {
            Aresta simetrica = new Aresta(destino, origem, valor, false);
            matrizAdj.get(j).get(i).add(simetrica);
        }

        numArestas++;
        return nova;
    }

    public void removeAresta(Aresta a) {
        int i = a.getOrigem().getIndice();
        int j = a.getDestino().getIndice();

        boolean removeu = matrizAdj.get(i).get(j).remove(a);
        if (!removeu) {
            throw new GrafoExcecao("Aresta não encontrada no grafo.");
        }
        if (!a.isDirecionada()) {
            ArrayList<Aresta> simetrica = matrizAdj.get(j).get(i);
            for (int k = 0; k < simetrica.size(); k++) {
                Aresta candidata = simetrica.get(k);
                if (candidata.getOrigem() == a.getDestino() && candidata.getDestino() == a.getOrigem()) {
                    simetrica.remove(k);
                    break;
                }
            }
        }
        numArestas--;
    }

    public Object replaceAresta(Aresta a, Object novoValor) {
        Object valorAntigo = a.getValor();
        a.setValor(novoValor);
        return valorAntigo;
    }

    public Vertice[] endVertices(Aresta a) {
        Vertice[] extremos = new Vertice[2];
        extremos[0] = a.getOrigem();
        extremos[1] = a.getDestino();
        return extremos;
    }

    public Vertice opposite(Vertice v, Aresta a) {
        if (a.getOrigem() == v) {
            return a.getDestino();
        }
        if (a.getDestino() == v) {
            return a.getOrigem();
        }
        throw new GrafoExcecao("O vértice não é extremo desta aresta.");
    }

    public boolean areAdjacent(Vertice u, Vertice v) {
        if (!vertices.contains(u) || !vertices.contains(v)) {
            throw new GrafoExcecao("Um ou ambos os vértices não pertencem ao grafo.");
        }
        int i = u.getIndice();
        int j = v.getIndice();
        if (matrizAdj.get(i).get(j).size() > 0) {
            return true;
        }
        return false;
    }

    public boolean isIncident(Vertice v, Aresta a) {
        if (a.getOrigem() == v || a.getDestino() == v) {
            return true;
        }
        return false;
    }

    public List<Aresta> incidentEdges(Vertice v) {
        if (!vertices.contains(v)) {
            throw new GrafoExcecao("Vértice não encontrado no grafo.");
        }
        List<Aresta> lista = new ArrayList<>();
        int idx = v.getIndice();

        for (int j = 0; j < numVertices; j++) {
            ArrayList<Aresta> celula = matrizAdj.get(idx).get(j);
            for (int k = 0; k < celula.size(); k++) {
                lista.add(celula.get(k));
            }
        }
        return lista;
    }

    public int degree(Vertice v) {
        if (!vertices.contains(v)) {
            throw new GrafoExcecao("Vértice não encontrado no grafo.");
        }
        int grau = 0;
        int idx = v.getIndice();
        for (int j = 0; j < numVertices; j++) {
            grau += matrizAdj.get(idx).get(j).size();
        }
        return grau;
    }

    public List<Vertice> adjacentVertices(Vertice v) {
        if (!vertices.contains(v)) {
            throw new GrafoExcecao("Vértice não encontrado no grafo.");
        }
        List<Vertice> lista = new ArrayList<>();
        int idx = v.getIndice();
        for (int j = 0; j < numVertices; j++) {
            if (matrizAdj.get(idx).get(j).size() > 0) {
                lista.add(vertices.get(j));
            }
        }
        return lista;
    }

    public void mostrarMatriz() {
        System.out.print("     ");
        for (int j = 0; j < numVertices; j++) {
            System.out.printf("%-6s", vertices.get(j).getValor());
        }
        System.out.println();

        for (int i = 0; i < numVertices; i++) {
            System.out.printf("%-5s", vertices.get(i).getValor());
            for (int j = 0; j < numVertices; j++) {
                int qtd = matrizAdj.get(i).get(j).size();
                System.out.printf("%-6s", qtd);
            }
            System.out.println();
        }
    }

    public void mostrarGrafo() {
        System.out.println("Vértices (" + numVertices + "): ");
        for (int i = 0; i < vertices.size(); i++) {
            System.out.println("  [" + i + "] " + vertices.get(i).getValor());
        }
        System.out.println("Arestas (" + numArestas + "): ");
        List<Aresta> lista = arestas();
        for (int i = 0; i < lista.size(); i++) {
            Aresta a = lista.get(i);
            String tipo = a.isDirecionada() ? " --> " : " --- ";
            System.out.println("  " + a.getOrigem().getValor() + tipo + a.getDestino().getValor() + " [peso: " + a.getValor() + "]");
        }
    }
}