import java.util.HashMap;

/**
 * @Author: Song Ningning
 * @Date: 2020-05-11 0:17
 */
public class Solution {
    int len;
    HashMap<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length)
            return null;
        len = preorder.length;
        for (int i = 0; i < len; i++)
            map.put(inorder[i], i);
        return build(preorder, 0, len - 1, inorder, 0, len - 1);
    }

    private TreeNode build(int[] pre, int preL, int preR, int[] in, int inL, int inR) {

        if (preL > preR || inL > inR)
            return null;

        int pivot = pre[preL];
        int pivotIdx = map.get(pivot);
        TreeNode root = new TreeNode(pivot);

        root.left = build(pre, preL + 1, (pivotIdx - inL) + preL, in, inL, pivotIdx - 1);
        root.right = build(pre, (pivotIdx - inL) + preL + 1, preR, in, pivotIdx + 1, inR);

        return root;
    }
}
