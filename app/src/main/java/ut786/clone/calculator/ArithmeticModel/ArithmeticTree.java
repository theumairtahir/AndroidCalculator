package ut786.clone.calculator.ArithmeticModel;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import ut786.clone.calculator.ParserModel.Token;

public class ArithmeticTree {
    private Node root;
    private List<Token> lstTokens;
    private List<Token> lstTokensPostfix;
    public ArithmeticTree(List<Token> lstTokens){
        this.lstTokens=lstTokens;
        root=null;
        MakeTree();
    }
    private void MakeTree() {
        Token lowestPrecedence = lstTokens.get(0);
        for (Token t : lstTokens) {
            if (t.getPrecedence() < lowestPrecedence.getPrecedence()) {
                lowestPrecedence = t;
            }
        }
        root = new Node(lowestPrecedence, lstTokens.indexOf(lowestPrecedence));
        newNode(root);
    }

    private void newNode(Node node) {
        addLeft(node,0,node.getTokenIndex());
        addRight(node,node.getTokenIndex()+1,lstTokens.size());
    }

    private void addLeft(Node node, int startIndex, int endIndex) {
        boolean tokenFlag=false;
        int index=startIndex;
        if(node.getLeft()==null && startIndex<lstTokens.size()){
            Token lowestPrecedence =lstTokens.get(startIndex);
            for (int i=startIndex; i<endIndex; i++){
                tokenFlag=true;
                if(lstTokens.get(i).getPrecedence()<lowestPrecedence.getPrecedence()) {
                    lowestPrecedence=lstTokens.get(i);
                    index=i;
                }
            }
            if(tokenFlag){
                node.setLeft(new Node(node,lowestPrecedence,index));
                addLeft(node.getLeft(),startIndex,node.getLeft().getTokenIndex());
                addRight(node.getLeft(),node.getLeft().getTokenIndex()+1,node.getTokenIndex());
            }
        }
    }

    private void addRight(Node node, int startIndex, int endIndex) {
        boolean tokenFlag=false;
        int index=startIndex;
        if(node.getRight()==null && startIndex<lstTokens.size()){
            Token lowestPrecedence =lstTokens.get(startIndex);
            for (int i=startIndex; i<endIndex; i++){
                tokenFlag=true;
                if(lstTokens.get(i).getPrecedence()<lowestPrecedence.getPrecedence()) {
                    lowestPrecedence=lstTokens.get(i);
                    index=i;
                }
            }
            if(tokenFlag){
                node.setRight(new Node(node,lowestPrecedence,index));
                addLeft(node.getRight(),startIndex,node.getRight().getTokenIndex());
                addRight(node.getRight(),node.getRight().getTokenIndex()+1,endIndex);
            }
        }
    }
    public List<Token> getPostFix(){
        lstTokensPostfix=new ArrayList<>();
        generatePostFix(root);
        return lstTokensPostfix;
    }

    private Token generatePostFix(Node node) {
        Token temp=null;
        if(node!=null){
            generatePostFix(node.getLeft());
            generatePostFix(node.getRight());
            temp=node.getValue();
            if (temp!=null){
                lstTokensPostfix.add(temp);
            }
        }
        return temp;
    }
    public double Calculate(){
        getPostFix();
        Stack<Double> doubleStack=new Stack<>();
        boolean flag=false;
        for (Token t: lstTokensPostfix) {
            flag=true;
            if(t.getName().equalsIgnoreCase("OPERATOR")) {
                double val1, val2;
                switch (t.getValue()){
                    case "+":
                        val2=doubleStack.pop();
                        val1=doubleStack.pop();
                        doubleStack.push(val1+val2);
                        break;
                    case "-":
                        val2=doubleStack.pop();
                        val1=doubleStack.pop();
                        doubleStack.push(val1-val2);
                        break;
                    case "*":
                        val2=doubleStack.pop();
                        val1=doubleStack.pop();
                        doubleStack.push(val1*val2);
                        break;
                    case "/":
                        val2=doubleStack.pop();
                        val1=doubleStack.pop();
                        doubleStack.push(val1/val2);
                }
            } else {
                doubleStack.push(Double.parseDouble(t.getValue()));
            }
        }
        if(!flag){
            return 0.0;
        }
        return doubleStack.peek();
    }
}
