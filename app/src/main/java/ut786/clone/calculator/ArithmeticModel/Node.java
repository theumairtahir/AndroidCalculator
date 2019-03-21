package ut786.clone.calculator.ArithmeticModel;

import ut786.clone.calculator.ParserModel.Token;

public class Node {
    private Node left,right,parent;
    private int tokenIndex;
    private Token value;
    public Node(Token t, int tokenIndex){
        this.tokenIndex=tokenIndex;
        left=null;
        right=null;
        parent=null;
        value=t;
    }
    public Node(Node parentNode,Token t,int tokenIndex){
        this(t,tokenIndex);
        parent=parentNode;
    }
    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Node getParent() {
        return parent;
    }

    public int getTokenIndex() {
        return tokenIndex;
    }

    public Token getValue() {
        return value;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
