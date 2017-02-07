package com.dodoi.demo.dataStructure.tree;

/**
 * 
 * @author liujia 树是节点的有限集合 名词：孩子 双亲 度 叶子 根 有序树 无序树 祖先 子孙 森林 前序遍历:根 左 右 中序遍历:左 根
 *         右 后续遍历:左 右 根
 * 
 *         关于数组与树之间的算法转换 int tree[] = {3,5,8,2,6,9,7} ; 父节点下标＊2+1 左节点 ;父节点下标＊2+2
 *         右节点
 * 
 *         3(9) 5(1) 8(2) 2(3) 6(4) 9(5) 7(6)
 * 
 *         1.树的创建销毁 2.节点的搜索 3.节点的添加删除 4.树的遍历
 */
public class ArrayTree {

	private Node[] tree;
	private int size;

	public ArrayTree(int size, Node root) {
		this.size = size;
		tree = new Node[size];
		tree[0] = root;
	}

	public Node searchNode(int index) {
		if (index <= 0 || index > tree.length) {
			return null;
		}
		return tree[index];
	}

	/**
	 * 添加节点
	 * 
	 * @param nodeIndex
	 *            索引
	 * @param direction
	 *            左节点：left;右节点：right
	 * @param node
	 *            节点
	 * @return
	 */
	public boolean addNode(int nodeIndex, String direction, Node node) {

		if (nodeIndex < 0 || nodeIndex > tree.length) {
			return false;
		}

		if ("left".equals(direction)) {
			if (nodeIndex * 2 + 1 >= size) {
				return false;
			}
			if (tree[nodeIndex * 2 + 1] != null) {
				return false;
			}
			tree[nodeIndex * 2 + 1] = node;
		}

		if ("right".equals(direction)) {
			if (nodeIndex * 2 + 2 >= size) {
				return false;
			}
			if (tree[nodeIndex * 2 + 2] != null) {
				return false;
			}
			tree[nodeIndex * 2 + 2] = node;
		}
		return false;
	}

	/**
	 * 删除节点
	 * 
	 * @param nodeIndex
	 * @return
	 */
	public Node deleteNode(int nodeIndex) {

		if (nodeIndex <= 0 || nodeIndex > tree.length) {
			return null;
		}
		if (tree[nodeIndex] == null) {
			return null;
		}
		Node temp = tree[nodeIndex];
		tree[nodeIndex] = null;
		return temp;
	}

	/**
	 * 遍历
	 */
	public void traverse() {
		if (tree.length <= 0) {
			return;
		}

		System.out.print(" " + tree);
	}

	public static void main(String[] args) {
		Node root = new Node(3);
		ArrayTree tree = new ArrayTree(10, root);
		tree.addNode(0, "left", new Node(5));
		tree.addNode(0, "right", new Node(8));

		tree.addNode(1, "left", new Node(2));
		tree.addNode(1, "right", new Node(6));
		tree.addNode(2, "left", new Node(9));
		tree.addNode(2, "right", new Node(7));
		tree.traverse();
		Node node = tree.searchNode(2);
		System.out.println(node.getDate());
		node = tree.deleteNode(2);
		System.out.println("delete node is:" + node.getDate());
		tree.traverse();
	}
}

class Node {
	int data;

	public Node(int data) {
		this.data = data;
	}

	public int getDate() {
		return data;
	}

	public void setDate(int data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "" + this.data;
	}
}