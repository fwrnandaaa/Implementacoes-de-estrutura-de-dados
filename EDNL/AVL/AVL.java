package EDNL.AVL;
import EDL.Arvore.ArvoreBinaria;

public class AVL extends ArvoreBinaria {
    public static class Node extends ArvoreBinaria.Node {
        int fb;
        public Node(int O){
            super(O);
            this.fb = 0;
        }
    }
    public void rotacaoEsquerda(Node O){ // 6
        Node current = (Node) O.right; //8
        current.parent =  O.parent; 
        if (root == O){
            root = current; 
        }
        else{
            O.parent.right = current;
        }
        O.right = current.left; 
        if(current.left != null){
            current.left.parent = O; 
        }
        current.left = O;
        O.parent = current;
        int novo_fb_current = (current.fb + 1) - Math.max(O.fb, 0); 
        int novo_fb_O = (O.fb + 1) + Math.max(novo_fb_current, 0);
        current.fb = novo_fb_current;
        O.fb = novo_fb_O;
       }
    
    public void rotacaoDireita(Node O){
        Node current = (Node) O.left;
        current.parent = O.parent;
        if (root == O){
            root = current;
        }
        else{
            O.parent.left = current;
        }
        O.left = current.right;
    if(current.right != null){
        current.right.parent = O;
        }
        current.right = O;
        O.parent = current;
        int novo_fb_current = (current.fb - 1) - Math.max(O.fb, 0);
        int novo_fb_O= (O.fb - 1) + Math.min(current.fb, 0);
        current.fb = novo_fb_current;
        O.fb = novo_fb_O;
       }
    
    public void duplaEsquerda(Node O){
      rotacaoDireita((Node) O.right);
      rotacaoEsquerda(O);
    }
    public void duplaDireita(Node O){
       rotacaoEsquerda((Node) O.left);
       rotacaoDireita(O);
    }
    public void rebalancear(Node O){
       if(O.fb == 2){
        if(((Node) O.left).fb >= 0){
            rotacaoDireita(O);
        }
        else if(((Node) O.left).fb < 0){
            duplaDireita(O);
        }
       }
       if(O.fb == -2){
        if(((Node) O.right).fb<= 0){
            rotacaoEsquerda(O);
        }
        else if(((Node) O.right).fb > 0){
            duplaEsquerda(O);
        }
       }
    }

    public void insert(Node O){
        super.insert(O);
        Node current = (Node) O.parent;
        while (current != null) {
            if(O == current.left){
                    current.fb += 1;
                }
            else{
                    current.fb += -1;
                    
                }
            if(current.fb == 2 || current.fb == -2){
                rebalancear(current);
                break;
            }
            O = current;
            current = (Node) current.parent;
        }
    }
    public void mostrar() {
        int h = height(root) + 1;
        int[][] matriz = new int[h][(int) Math.pow(2, h)];
        int[][] fbs = new int[h][(int) Math.pow(2, h)];
        preencheMatriz(root, matriz, fbs, 0, 0, (int) Math.pow(2, h) / 2);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < (int) Math.pow(2, h); j++) {
                if (matriz[i][j] != 0) {
                    System.out.print(matriz[i][j] + "[" + fbs[i][j] + "]");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    private void preencheMatriz(ArvoreBinaria.Node O, int[][] matriz, int[][] fbs, int nivel, int esq, int dir) {
        if (O == null) return;
        int meio = (esq + dir) / 2;
        matriz[nivel][meio] = O.value;
        fbs[nivel][meio] = ((Node) O).fb;
        preencheMatriz(O.left, matriz, fbs, nivel + 1, esq, meio);
        preencheMatriz(O.right, matriz, fbs, nivel + 1, meio, dir);
    }
        public void remove(Node O){
            return;
        }
    }