public class BST {
    public static class Node {
        int data;
        Node left, right;
        public Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    public static int findNodes(Node root) {
        if (root == null) {
            return 0;
        }
        return findNodes(root.left) + findNodes(root.right) + 1;
    }

    public static int findLeafs(Node root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return findLeafs(root.left) + findLeafs(root.right);
    }

    public static Node mirror(Node root) {
        if (root == null) {
            return null;
        }
        Node node = new Node(root.data);
        if(root.left == null && root.right == null) {
            return node;
        }
        node.left = mirror(root.left);
        node.right = mirror(root.right);
        return node;
    }

    public static void insert(Node root, int data){
        if(root == null || root.data == data){
            return;
        }
        if(root.data > data){
            if(root.left == null)
                root.left = new Node(data);
            else
                insert(root.left, data);
        }else{
            if(root.right == null)
                root.right = new Node(data);
            else
                insert(root.right, data);
        }
        return;
    }

    public static void main(String[] args) {
        Node node = new Node(25);
        insert(node, 20);
        insert(node, 30);
        insert(node, 15);
        insert(node, 23);
        insert(node, 28);
        insert(node, 35);
        insert(node, 10);
        insert(node, 18);
        insert(node, 29);
        insert(node, 33);
        insert(node, 45);
        insert(node, 29);

        System.out.println(findNodes(node));
        System.out.println(findLeafs(node));
        System.out.println(findNodes(mirror(node)));
        System.out.println(findLeafs(mirror(node)));
    }
}
