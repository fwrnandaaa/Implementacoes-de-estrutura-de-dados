package EDNL.AVL;
import java.util.ArrayList;
import java.util.List;

public class AVLTeste{
    public static void main(String[] args){
    AVL arvore = new AVL();
    AVL.Node aux1 = new AVL.Node(6);
    AVL.Node aux2 = new AVL.Node(8);
    AVL.Node aux3 = new AVL.Node(9);
    arvore.insert(aux1);
    arvore.insert(aux2);
    arvore.insert(aux3);
    System.out.println("Teste rotação esquerda");
    arvore.mostrar();
    System.out.println("Teste rotação direita");
    AVL.Node aux4 = new AVL.Node(5);
    AVL.Node aux5 = new AVL.Node(3);
    arvore.insert(aux4);
    arvore.insert(aux5);
    arvore.mostrar();
    System.out.println("Teste rotação dupla direita");
    AVL.Node aux6 = new AVL.Node(4);
    arvore.insert(aux6);
    arvore.mostrar();
    System.out.println("Teste rotação dupla esquerda");
    AVL.Node aux7 = new AVL.Node(7);
    arvore.insert(aux7);
    arvore.mostrar();
    }
}