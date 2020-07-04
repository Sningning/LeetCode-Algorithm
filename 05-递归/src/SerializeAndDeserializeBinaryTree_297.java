import java.util.LinkedList;
import java.util.Queue;

/**
 * 297. 二叉树的序列化与反序列化
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
 * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。
 * 这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构
 *
 * https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-02 16:35
 */
public class SerializeAndDeserializeBinaryTree_297 {

    static class Solution1 {

        /**
         * 题目没有限定执行逻辑，他的调用方法是：
         * Codec codec = new Codec();
         * codec.deserialize(codec.serialize(root));
         * 因此只要求能用写的函数序列化和反序列化即可。
         *
         * 考虑几种遍历方式：前序、中序、后序、层序、
         * 如果二叉树的序列化是从根结点开始，那么反序列化的时候读到第一个元素时就可以进行操作，
         * 因此考虑 前序遍历 或者 层序遍历。
         *
         * 注意的是，遇到空结点不能直接返回，需要标注为 null。
         */
    
        /**
         * 层序遍历
         *     1
         *    / \
         *   2   3
         *      / \
         *     4   5
         * 序列化结果：1,2,3,null,null,4,5,null,null,null,null,
         */
    
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {

            if (root == null) {
                return "null";
            }
            StringBuilder res = new StringBuilder();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node == null) {
                    res.append("null,");
                } else {
                    res.append(node.val).append(",");
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
            return res.toString();
        }
    

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {

            if (data.equals("null")) {
                return null;
            }
            // "1,2,3,null,null,4,5,null,null,null,null," 分割后变成
            // ["1" "2" "3" "null" "null" "4" "5" "null" "null" "null" "null"]
            String[] values = data.split(",");
            Queue<TreeNode> queue = new LinkedList<>();
            TreeNode root = new TreeNode(Integer.parseInt(values[0]));
            queue.offer(root);
            int index = 1;
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (!values[index].equals("null")) {
                    node.left = new TreeNode(Integer.parseInt(values[index]));
                    queue.offer(node.left);
                }
                index++;
                if (!values[index].equals("null")) {
                    node.right = new TreeNode(Integer.parseInt(values[index]));
                    queue.offer(node.right);
                }
                index++;
            }
            return root;
        }
    }


    static class Solution2 {

        /**
         * 前序遍历
         *     1
         *    / \
         *   2   3
         *      / \
         *     4   5
         * 序列化结果：1,2,null,null,3,4,null,5,null,
         */
    
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "null";
            }
            StringBuilder res = new StringBuilder();
            preOrder(root, res);
            return res.toString();
        }
    
        private void preOrder(TreeNode node, StringBuilder builder) {
            if (node == null) {
                builder.append("null,");
                return;
            }
            builder.append(node.val).append(",");
            preOrder(node.left, builder);
            preOrder(node.right, builder);
        }
    
        
        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.equals("null")) {
                return null;
            }
            String[] values = data.split(",");
            return deserialize(values);
        }
    
        /**
         * 此处 index 应该定义为全局变量，因为在 deserialize 递归函数中，index 应该是始终连续的
         * 比如 root.right = deserialize(values) 递归完后，index 为 5，那么在执行 root.right = deserialize(values)
         * 时，index 也必须为 5
         */
        int index = 0;
        private TreeNode deserialize(String[] values) {
            if (values[index].equals("null")) {
                index++;
                return null;
            }
            TreeNode root = new TreeNode(Integer.parseInt(values[index]));
            index++;
            root.left = deserialize(values);
            root.right = deserialize(values);
            return root;
        }
    }

}
