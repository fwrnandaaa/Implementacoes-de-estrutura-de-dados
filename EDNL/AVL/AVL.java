package EDNL.AVL;
import EDL.Arvore.ArvoreBinaria;

public class AVL extends ArvoreBinaria {
    int fb;
    public Node(int O){
        super(O);
        this.fb = 0;
    }
    public void rotacaoEsquerda(Node O){
        Node current = O.right;
        current.parent = O.parent;
        if (root == O){
            root = current
        }
        else{
            O.parent.right = current;
        }
        current.left = O;
        O.parent = current;

       }
    }
    public void rotacaDireita(Node O){
       return;
    }
    public void duplaEsquerda(Node O){
       return;
    }
    public void duplaEsquerda(Node O){
       return;
    }
    public void rebalancear(Node O){
       return;
    }
    @Override
    public void insert(Node O){
        return;
    }
    @Override
    public void remove(Node O){
        return;
    }
}