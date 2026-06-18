public class Aresta {
    public Object valor;
    public Vertice origem;
    public Vertice destino;
    public boolean direcionada;

    public Aresta(Vertice origem, Vertice destino, Object valor, boolean direcionada) {
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
        this.direcionada = direcionada;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public Vertice getOrigem() {
        return origem;
    }

    public Vertice getDestino() {
        return destino;
    }

    public boolean isDirecionada() {
        return direcionada;
    }
}