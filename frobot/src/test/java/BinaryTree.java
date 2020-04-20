import lombok.Data;

/**
 * 二叉树
 * @author lijiafu
 * @date 2020/3/16 10:18
 * @since 1.0
 */
@Data
public class BinaryTree {
    private BinaryTreeNode root;

    public BinaryTree(BinaryTreeNode root){
        this.root = root;
    }

    public void clear(BinaryTreeNode node){
        if(null != node){
            clear(node.getLeftChildNode());
            clear(node.getRightChildNode());
            node.setLeftChildNode(null);
            node.setRightChildNode(null);
        }
    }

    //清空树
    public void clear(){
        clear(root);
    }

    public void perOrder(BinaryTreeNode node){
        if(node != null){
            System.out.println(node.getData());
            perOrder(node.getLeftChildNode());
            perOrder(node.getRightChildNode());
        }

    }


    public void inOrder(BinaryTreeNode node){
        if(node != null) {
            inOrder(node.getLeftChildNode());
            System.out.println(node.getData());
            inOrder(node.getRightChildNode());
        }
    }


    public void postOrder(BinaryTreeNode node){
        if(node != null) {
            postOrder(node.getLeftChildNode());
            postOrder(node.getRightChildNode());
            System.out.println(node.getData());
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode  root = new BinaryTreeNode(5);
        BinaryTreeNode left = root.addLeftChildNode(3);
        BinaryTreeNode right = root.addRightChildNode(7);
        left.addLeftChildNode(2);
        left.addRightChildNode(4);
        right.addLeftChildNode(6);
        right.addRightChildNode(8);

        BinaryTree tree = new BinaryTree(root);
        tree.clear(left);
        tree.getRoot().setLeftChildNode(null);
        tree.perOrder(root);
    }
}
