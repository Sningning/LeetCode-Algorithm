import java.util.List;

/**
 * @Author: Song Ningning
 * @Date: 2020-05-01 14:00
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
