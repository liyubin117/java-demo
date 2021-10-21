package org.rick.algorithm;

public class Main {
	private static final int a[] = { 10, 40, 30, 60, 90, 70, 20, 50, 80 };
	private static final boolean mDebugInsert = true; // "插入"动作的检测开关(false，关闭；true，打开)
	private static final boolean mDebugDelete = true; // "删除"动作的检测开关(false，关闭；true，打开)
	// 演示代码

	public static void main(String[] args) {
		SimpleBinaryTree bt = new SimpleBinaryTree();
		bt.add(1);
		bt.add(10);
		bt.add(8);
		bt.add(-1);
		bt.add(9);
		bt.print();
		System.out.println();
		bt.printPreOrder();
		System.out.println();

		BinaryTree t = new BinaryTree();
		t.insert(10, 10.1);
		t.insert(1, 20);
		t.insert(3, 8.5);
		t.insert(15, 20.5);
		t.traverse(2);
		t.delete(10);
		t.traverse(2);

		RBTree<Integer> rb = new RBTree<>();
		rb.insert(10);
		rb.insert(40);
		rb.insert(30);
		rb.insert(60);
		rb.insert(90);
		rb.print();
		System.out.println("删除节点30 10后：");
		//有问题。。。因为remove代码只考虑两子节点的情况，无子节点和一子节点的情况未考虑
		rb.remove(30);
		rb.print();

		GeneralHashFunctionLibrary hfl = new GeneralHashFunctionLibrary();
		System.out.println(hfl.RSHash("liyubin LIYUBIN "));

		//红黑树效果代码
		int i, ilen = a.length;
		RBTree<Integer> tree = new RBTree<Integer>();

		System.out.printf("== 原始数据: ");
		for (i = 0; i < ilen; i++)
			System.out.printf("%d ", a[i]);
		System.out.printf("\n");

		for (i = 0; i < ilen; i++) {
			tree.insert(a[i]);
			// 设置mDebugInsert=true,测试"添加函数"
			if (mDebugInsert) {
				System.out.printf("== 添加节点: %d\n", a[i]);
				System.out.printf("== 树的详细信息: \n");
				tree.print();
				System.out.printf("\n");
			}
		}

		System.out.printf("== 前序遍历: ");
		tree.preOrder();

		System.out.printf("\n== 中序遍历: ");
		tree.inOrder();

		System.out.printf("\n== 后序遍历: ");
		tree.postOrder();
		System.out.printf("\n");

		System.out.printf("== 最小值: %s\n", tree.minValue());
		System.out.printf("== 最大值: %s\n", tree.maxValue());
		System.out.printf("== 树的详细信息: \n");
		tree.print();
		System.out.printf("\n");

		// 设置mDebugDelete=true,测试"删除函数"
		if (mDebugDelete) {
			for (i = 0; i < ilen; i++) {
				tree.remove(a[i]);

				System.out.printf("== 删除节点: %d\n", a[i]);
				System.out.printf("== 树的详细信息: \n");
				tree.print();
				System.out.printf("\n");
			}
		}

	}
}
