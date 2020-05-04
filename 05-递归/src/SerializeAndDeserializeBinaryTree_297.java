import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return serialize(root, "");
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] data_array = data.split(",");
        List<String> data_list = new LinkedList<>(Arrays.asList(data_array));
        return deserialize(data_list);
    }


    private String serialize(TreeNode node, String str) {

        if (node == null) return str += "null,";

        str += node.val + ",";
        str = serialize(node.left, str);
        str = serialize(node.right, str);
        return str;
    }


    private TreeNode deserialize(List<String> list) {

        if (list.size() == 0) return null;
        if (list.get(0).equals("null")) {
            list.remove(0);
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(list.get(0)));
        list.remove(0);
        node.left = deserialize(list);
        node.right = deserialize(list);
        return node;
    }


    public static void main(String[] args) {

        SerializeAndDeserializeBinaryTree_297 tree = new SerializeAndDeserializeBinaryTree_297();
        TreeNode node = tree.deserialize("1,null,2");
        String s = tree.serialize(node);
        System.out.println(s);
    }
}
