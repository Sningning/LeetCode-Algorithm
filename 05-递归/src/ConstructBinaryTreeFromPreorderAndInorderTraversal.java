import java.util.HashMap;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-04 11:21
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    HashMap<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        // 1. 递归
        // 前序遍历：根-左-右；中序遍历：左-根-右
        // 前序遍历数组的第一个元素肯定是根节点 root，
        // 在中序遍历数组中找到 root 的位置，左边就是 root 的左子树，右边就是 root 的右子树
        // 在中序遍历数组中找到 左子树数组范围，然后对应到前序遍历数组中，那么前序遍历数组中左子树数组的第一个元素又是 root 左子树的根节点
        // 因此可以依次递归查找，右子树也是相同操作。
        // 对于查找左子树的情况，用 preL 和 preR，记录在前序遍历数组中，当前节点左子树的范围，pivot 记录根节点，
        // pivotIdx 记录 根节点在中序遍历数组中的位置，当 preL == preR 时，说明左子树只有一个节点了，再递归一层，让其左右子树为 null，
        // 递归终止条件即为：preL > preR，右子树同理。

        // 关键是怎样根据左子树在 中序遍历数组 中的范围，找到其在 前序遍历数组 中的范围，
        // 由于节点个数是一样的，因此只需要在 preL 基础上加上左子树长度即可确定 preR
        // 右子树在 前序遍历数组 中的左边界就是 preR + 1


        int preLen = preorder.length;
        int inLen = inorder.length;

        if (preLen != inLen)
            throw new RuntimeException("Error");

        for (int i = 0; i < inLen; i++)
            map.put(inorder[i], i);

        return build(preorder, 0, preLen - 1, inorder, 0, inLen - 1);
    }

    /**
     *  使用数组 preorder 在索引区间 [preL, preR] 中的所有元素
     *  和数组 inorder 在索引区间 [inL, inR] 中的所有元素构造二叉树
     *
     * @Param: preorder   二叉树前序遍历结果
     * @Param: preL       当前二叉树前序遍历结果的左边界
     * @Param: preR       当前二叉树前序遍历结果的右边界
     * @Param: inorder    二叉树中序遍历结果
     * @Param: inL        当前二叉树中序遍历结果的左边界
     * @Param: inR        当前二叉树中序遍历结果的右边界
     * @Return: TreeNode  当前二叉树的根节点
     * @Author: Song Ningning
     * @Date: 2020-05-04 14:08
     */
    private TreeNode build(int[] preorder, int preL, int preR,
                           int[] inorder, int inL, int inR) {

        if (preL > preR || inL > inR)
            return null;

        int pivot = preorder[preL];
        TreeNode root = new TreeNode(pivot);
        // 找到 pivot 在中序遍历数组中的位置
        int pivotIdx = map.get(pivot);

        root.left = build(preorder, preL + 1, pivotIdx - inL + preL,
                inorder, inL, pivotIdx - 1);
        root.right = build(preorder, pivotIdx - inL + preL + 1, preR,
                inorder,pivotIdx + 1, inR);
        return root;

    }
}
