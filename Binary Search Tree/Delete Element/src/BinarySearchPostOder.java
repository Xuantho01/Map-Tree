import javax.xml.soap.Node;
import java.util.Stack;

public class BinarySearchPostOder {
    public static class TreeNode{
        int data;
        TreeNode left;
        TreeNode right;
        TreeNode(int data){
            this.data = data;
        }
    }
  //Recusive Solution
    public void postOder(TreeNode root){
        if (root != null){
            postOder(root.left);
            postOder(root.right);
            System.out.printf("%d ",root.data);
        }
    }
    // Preoreder
    public void PreOder(TreeNode root){
        if (root != null){
            System.out.printf("%d ",root.data);
            PreOder(root.left);
            PreOder(root.right);

        }
    }
    // InOrder
    public void InOrder(TreeNode root){
        if (root != null){
            InOrder(root.left);
            System.out.printf("%d ",root.data);
            InOrder(root.right);

        }
    }
    //interative solution
    public void postOderIter(TreeNode root){
        if(root == null) return;
        Stack<TreeNode> s = new Stack<>();
        TreeNode current = root;
        while(true){
            if (current != null){
                if (current.right != null){
                    s.push(current.right);
                }
                s.push(current);
                current = current.left;
                continue;
            }
            if (s.isEmpty())
                return;
            current = s.pop();
            if (current.right != null && !s.isEmpty() && current.right == s.peek()){
                s.pop();
                s.push(current);
                current = current.right;
            }else {
                System.out.print(current.data + " ");
                current = null;
            }

        }

    }
    public TreeNode delete(TreeNode node, int val){
        if (node == null) return null;
        if (val < node.data){
            node.left = delete(node.left,val);
        }else if (val > node.data){
            node.right = delete(node.right,val);
        }else {
            if (node.left == null && node.right == null){
                TreeNode temp = null;
                temp = node.left == null ? node.right : node.left;
                if (temp == null){
                    return null;
                }else{
                    return temp;
                }
            }else {
                TreeNode successor = getSucessor(node);
                node.data = successor.data;
                node.right = delete(node.right,4);
                return node;
            }
        }
        return node;
    }
    public TreeNode getSucessor(TreeNode node){
        if (node == null){
            return null;
        }
        TreeNode temp = node.right;
        while (temp.left != null){
            temp = temp.left;
        }
        return temp;
    }
    public  static TreeNode createBinaryTree(){
        TreeNode rootNode = new TreeNode(40);
        TreeNode Node20 = new TreeNode(20);
        TreeNode Node10 = new TreeNode(10);
        TreeNode Node30 = new TreeNode(30);
        TreeNode Node60 = new TreeNode(60);
        TreeNode Node50 = new TreeNode(50);
        TreeNode Node70 = new TreeNode(70);

        rootNode.left = Node20;
        rootNode.right = Node60;

        Node20.left = Node10;
        Node20.right = Node30;

        Node60.left = Node50;
        Node60.right = Node70;

        return rootNode;
    }
    public static void main(String[] args) {
        BinarySearchPostOder bi = new BinarySearchPostOder();
        // creating a binary tree

        TreeNode rootNode = createBinaryTree();
        System.out.println("Using Recursive solution(postOder): ");
        bi.postOder(rootNode);
        System.out.println("\nUsing Recursive solution(PreOrder): ");
        bi.PreOder(rootNode);
        System.out.println("\nUsing Recursive solution(InOder): ");
        bi.InOrder(rootNode);

        System.out.println();
        System.out.println("--------------------");
        System.out.println("using Interatuve solution: ");
        bi.postOderIter(rootNode);

        bi.delete(rootNode,30);
        System.out.println("\ndelete: ");
        bi.postOderIter(rootNode);
    }
}
