public class RBTestes {
    public static void main(String[] args) {
        RB rb = new RB();
        RB.Node n1 = new RB.Node(10);
        RB.Node n2 = new RB.Node(20);
        RB.Node n3 = new RB.Node(5);
        RB.Node n4 = new RB.Node(15);
        RB.Node n5 = new RB.Node(25);
        RB.Node n6 = new RB.Node(3);
        RB.Node n7 = new RB.Node(7);

        rb.insert(n1);
        rb.insert(n2);
        rb.insert(n3);
        rb.insert(n4);
        rb.insert(n5);
        rb.insert(n6);
        rb.insert(n7);

        System.out.println("=== Árvore inicial ===");
        rb.mostrar();
        System.out.println(" Teste remover nó com 2 filhos (5)");
        rb.remove(n3);
        rb.mostrar();
        System.out.println(" Teste remover nó folha (3) ");
        rb.remove(n6);
        rb.mostrar();
        System.out.println(" Teste remover nó com 1 filho (20)");
        rb.remove(n2);
        rb.mostrar();
    }
}