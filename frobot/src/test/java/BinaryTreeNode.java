import lombok.Data;

/**
 * 二叉树节点
 * @author lijiafu
 * @date 2020/3/16 10:16
 * @since 1.0
 */
@Data
public class BinaryTreeNode {
    private int data;
    private BinaryTreeNode leftChildNode;
    private BinaryTreeNode rightChildNode;

    public BinaryTreeNode(int data){
        this.data = data;
    }

    public BinaryTreeNode addLeftChildNode(int leftData){
        BinaryTreeNode leftChild = new BinaryTreeNode(leftData);
        this.setLeftChildNode(leftChild);
        return leftChild;
    }
    public BinaryTreeNode addRightChildNode(int rightData){
        BinaryTreeNode rightChild = new BinaryTreeNode(rightData);
        this.setRightChildNode(rightChild);
        return rightChild;
    }

}
