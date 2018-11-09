package com.example.mike.week3exam.organization_management;

import java.util.LinkedList;

public class OrginzationManager {
    
    LinkedList<Node> fullNodes = new LinkedList<>();
    Node masterNode;
    
    OrginzationManager( String input[] ){
        for (int i = 0; i < input.length; i++) {
            fullNodes.add(new Node(input[i]));
        }
        masterNode = compileNodes();
    }

    public void print(){
        masterNode.print();
    }

    public static void printRepresentation( String input[] ){
        OrginzationManager om = new OrginzationManager(input);
        om.print();
    }
    
    private class Node{
        String value;
        Node nodes[];
        
        Node( String s ){
            String a[] = s.split(",");
            value = a[0];
            nodes = new Node[a.length-1];
            for (int i = 1; i < a.length; i++) {
                nodes[i-1] = new Node(a[i]);
            }
        }

        private boolean has(String s){
            for (Node node : nodes) {
                if (node.value.equals(s) || node.has(s)){
                    return true;
                }
            }
            return false;
        }

        private void replaceNode(Node n){
            for (int i = 0; i < nodes.length; i++) {
                if (nodes[i].value.equals(n.value)){
                    nodes[i] = n;
                }
                nodes[i].replaceNode(n);
            }
        }

        private void print(){
            printOffset("");
        }

        private void printOffset(String offset){
            // print boss first
            String offsetAdd = "    ";
            System.out.println( offset+this.value );
            for (Node node : nodes) {
                node.printOffset(offset+offsetAdd);
            }
        }

    }
    
    private Node compileNodes(){
        for (int i = 0; i < fullNodes.size(); i++) {
            Node currentNode = fullNodes.get( i );

            boolean existsAsChild = false;

            for (int i1 = 0; i1 < fullNodes.size(); i1++) {
                if ( fullNodes.get(i1) == currentNode ){
                    // Do not check current node
                    continue;
                }
                if ( fullNodes.get(i1).has( currentNode.value ) ){
                    existsAsChild = true;
                    fullNodes.get(i1).replaceNode( currentNode );
                }
            }

            if ( existsAsChild ){
                fullNodes.remove( currentNode );
                i--;
            }
        }
        return fullNodes.get(0);
    }

}

class main{
    public static void main(String[] args) {
        String testCase[] = {
                "B2,E5,F6",
                "A1,B2,C3,D4",
                "D4,G7,I9",
                "G7,H8"
        };
        OrginzationManager.printRepresentation( testCase );

    }
}
