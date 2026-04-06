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
    arvore.mostrar();
    }
}