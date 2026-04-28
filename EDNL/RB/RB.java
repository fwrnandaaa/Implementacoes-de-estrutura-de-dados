import java.util.List;
import java.util.ArrayList;
public class RB {
    public Node root;
    public int size;

    public RB() {
        root = null;
        size = 0;
    }

    public Node root() {
        return root;
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;
        public String color;

        public Node(int O) {
            this.value = O;
            this.left = null;
            this.right = null;
            this.parent = null;
            this.color = "V";
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public boolean isRoot(Node O) {
        if (O.parent == null) {
            return true;
        }
        return false;
    }

    public boolean isExternal(Node O) {
        if ((O.left == null) && (O.right == null)) {
            return true;
        }
        return false;
    }

    public boolean isInternal(Node O) {
        if ((O.left != null) || (O.right != null)) {
            return true;
        }
        return false;
    }

    public Integer leftChild(Node O) {
        if (O.left == null) {
            throw new RBExcecao("O nó informado não possui filho esquerdo.");
        }
        return O.left.value;
    }

    public Integer rightChild(Node O) {
        if (O.right == null) {
            throw new RBExcecao("O nó informado não possui filho direito.");
        }
        return O.right.value;
    }

    public int depth(Node O) {
        if (isRoot(O)) {
            return 0;
        }
        return 1 + depth(O.parent);
    }

    public boolean hasLeft(Node O) {
        if (O.left == null) {
            return false;
        }
        return true;
    }

    public boolean hasRight(Node O) {
        if (O.right == null) {
            return false;
        }
        return true;
    }

    public Integer parent(Node O) {
        if (isRoot(O)) {
            throw new RBExcecao("O nó informado é root.");
        }
        return O.parent.value;
    }

    public List children(Node O) {
        List<Integer> lista = new ArrayList<>();
        if (isExternal(O)) {
            throw new RBExcecao("O nó informado é nó folha.");
        }
        if (hasLeft(O)) {
            lista.add(O.left.value);
        }
        if (hasRight(O)) {
            lista.add(O.right.value);
        }
        return lista;
    }

    public int height(Node O) {
        int h = 0;
        if (isExternal(O)) {
            return 0;
        } else {
            if (O.left != null) {
                h = Math.max(h, height(O.left));
            }
            if (O.right != null) {
                h = Math.max(h, height(O.right));
            }
        }
        return 1 + h;
    }

    public Node busca(Node O, int chave) {
        if (isExternal(O)) {
            return O;
        }
        if (chave == O.value) {
            return null;
        }
        if (chave < O.value) {
            if (hasLeft(O) == false) {
                return O;
            } else {
                return busca(O.left, chave);
            }
        } else {
            if (hasRight(O) == false) {
                return O;
            } else {
                return busca(O.right, chave);
            }
        }
    }

    public void insert(Node O) {
        if (root == null) {
            root = O;
            O.color = "P";
            size++;
            return;
        }
        Node p = busca(root, O.value);
        if (O.value < p.value) {
            p.left = O;
        } else {
            p.right = O;
        }
        O.parent = p;
        size++;
        recolorirInserir(O);
    }

    public Node uncle(Node O) {
        if(O.parent == null || O.parent.parent == null){
            return null;
        }
        Node pai = O.parent;
        Node avo = pai.parent;
        if (avo.left == pai) {
            return avo.right;
        } else {
            return avo.left;
        }
    }

    public void recolorirInserir(Node O) {
        // caso 1
        if (O.parent == null || O.parent.color.equals("P")) {
            root.color = "P";
            return;
        }
        //caso 2
        Node uncle = uncle(O);
        if (uncle != null && uncle.color.equals("V")) {
            O.parent.parent.color = "V";
            O.parent.color = "P";
            uncle.color = "P";
            recolorirInserir(O.parent.parent);
            return;
        }
        //caso 3
        if (uncle == null || uncle.color.equals("P")) {
            //subcaso 3a
            if(O.parent.left == O && O.parent.parent.left == O.parent){
                Node avo = O.parent.parent;
                Node pai = O.parent;
                rotacaoDireita(avo);
                pai.color = "P"; 
                avo.color = "V"; 
            }
            //subcaso 3b
            if(O.parent.right == O && O.parent.parent.right == O.parent){
                Node avo = O.parent.parent;
                Node pai = O.parent;
                rotacaoEsquerda(avo);
                pai.color = "P";
                avo.color = "V";
            }
            //subcaso 3c
            if(O.parent.right == O && O.parent.parent.left == O.parent){
                Node avo = O.parent.parent;
                rotacaoEsquerda(O.parent);
                rotacaoDireita(avo);
                O.color = "P"; 
                avo.color = "V";
            }
            //subcaso 3d
            if(O.parent.left == O && O.parent.parent.right == O.parent){
                Node avo = O.parent.parent;
                rotacaoDireita(O.parent);
                rotacaoEsquerda(avo);
                O.color = "P"; 
                avo.color = "V";
            }
        }
    }

    public void replace(Node O, int n) {
        O.value = n;
    }

    public void elements(Node O) {
        if (O == null) {
            return;
        }
        if (O.left != null) {
            elements(O.left);
        }
        System.out.println(" value: " + O.value);
        if (O.parent == null) {
            System.out.print(" É root");
        } else {
            System.out.print("Pai: " + O.parent.value);
        }
        if (O.right != null) {
            elements(O.right);
        }
    }

    public void rotacaoEsquerda(Node O) {
        Node current = O.right;
        current.parent = O.parent;
        if (root == O) {
            root = current;
        } else {
            if (O == O.parent.left) {
                O.parent.left = current;
            } else {
                O.parent.right = current;
            }
        }
        O.right = current.left;
        if (current.left != null) {
            current.left.parent = O;
        }
        current.left = O;
        O.parent = current;
    }

    public void rotacaoDireita(Node O) {
        Node current = O.left;
        current.parent = O.parent;
        if (root == O) {
            root = current;
        } else {
            if (O == O.parent.left) {
                O.parent.left = current;
            } else {
                O.parent.right = current;
            }
        }
        O.left = current.right;
        if (current.right != null) {
            current.right.parent = O;
        }
        current.right = O;
        O.parent = current;
    }
    public private Node irmao(Node O){
        if(rightChild(O.parent) == O){
            return O.parent.left;
        }
        else{
            return O.parent.right;
        }
    }
    public void recolorirRemover(Node removido, Node substituto) {
        if (removido != null) {
        //caso 1
        if (removido.color.equals("V") && substituto.color.equals("V")) {
        return;
         }
        //caso 2: removido é negro e substituto é vermelho
        if(removido.color.equals("P") && substituto.color.equals("V")){
            substituto.color = "P";
            return;
        }
        }
        //caso 3: removido é negro e substituto é negro
        if(removido == null ||removido.color.equals("P") && substituto.color.equals("P")){
            //caso 3a: substituto negro, substituto.parent negro e irmao rubro
            irmao = irmao(substituto);
            if(!substituto.parent.color.equals("V") && irmao.color.equals("V")){
                substituto.color = "2P";
                rotacaoEsquerda(substituto.parent);
                irmao.color = "P";
                substituto.parent.color = "V";
                recolorirRemover(null, substituto);
            }

        
        }
    }

    private void removeDuploNegro(Node O){
        if(O == root){
            O.color = "P";
        }
    }

    public void remove(Node O) {
        if(isExternal(O)){
            if(O.value > O.parent.value){
                O.parent.right = null
            }
            else{
                O.parent.left = null
            }
        }
    }

    public Node sucessor(Node O){
        Node current = O.right;
        while(current.left != null){
            current = current.left;
        }
        return current;
    }

}