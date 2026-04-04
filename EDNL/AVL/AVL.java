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
            root = current;
        }
        else{
            O.parent.right = current;
        }
        current.left = O;
        O.parent = current;
        current.fb = current.fb + 1 - Math.min(O.fb, 0);
        O.fb = O.fb + 1 + Math.max(current.fb, 0);
       }
    
    public void rotacaoDireita(Node O){
        Node current = O.left;
        current.parent = O.parent;
        if (root == O){
            root = current;
        }
        else{
            O.parent.left = current;
        }
        current.right = O;
        O.parent = current;
        current.fb = current.fb - 1 - Math.max(O.fb, 0);
        O.fb = O.fb - 1 + Math.min(current.fb, 0);
       }
    
    public void duplaEsquerda(Node O){
      rotacaoDireita(O.right);
      rotacaoEsquerda(O);
    }
    public void duplaDireita(Node O){
       rotacaoEsquerda(O.left);
       rotacaoDireita(O);
    }
    public void rebalancear(Node O){
       if(O.fb >= -1 && O.fb <= 1){
        return;
       }
       if(O.fb == 2){
        if(O.right.fb <= 0){
            rotacaoEsquerda(O);
        }
        if(O.right.fb > 0){
            duplaEsquerda(O);
        }
       }
       if(O.fb == -2){
        if(O.left.fb >= 0){
            rotacaoDireita(O);
        }
        if(O.left.fb < 0){
            duplaDireita(O);
        }
       }
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