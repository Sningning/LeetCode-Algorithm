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

        int preLen = preorder.length;
        int inLen = inorder.length;

        if (preLen != inLen)
            throw new RuntimeException("Error");

        for (int i = 0; i < inLen; i++)
            map.put(inorder[i], i);

        return build(preorder, 0, preLen - 1, inorder, 0, inLen - 1);
    }

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
