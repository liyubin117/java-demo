package org.rick.algorithm;
//排序二叉树（简单实现，只有插入、遍历功能）
public class SimpleBinaryTree {
	//声明节点类
	class Node{
		private Comparable data; //保存具体的内容
		private Node left; //保存左子树
		private Node right; //保存右子树	
		//构造器
		public Node(Comparable data){
			this.data=data;
		}
		//增加节点
		public void addNode(Node newNode){
			if(this.data.compareTo(newNode.data)>0){  //新节点较小，放在左子树
				if(this.left==null){
					this.left=newNode;
				}else{
					this.left.addNode(newNode);
				}
			}
			if(this.data.compareTo(newNode.data)<0){  //新节点较大，放在右子树
				if(this.right==null){
					this.right=newNode;
				}else{
					this.right.addNode(newNode);
				}
			}
		}
		//输出时中序遍历
		public void printNode(){
			if(this.left!=null){
				this.left.printNode();
			}
			System.out.print(this.data+"\t");
			if(this.right!=null){
				this.right.printNode();
			}
		}
		
		//输出时前序遍历
		public void printPreOrderNode(){
			System.out.print(this.data+"\t");
			if(this.left!=null){
				this.left.printPreOrderNode();
			}
			if(this.right!=null){
				this.right.printPreOrderNode();
			}
		}
	}
	
	private Node root;
	public void add(Comparable data){
		Node newNode=new Node(data);
		if(root==null){
			root=newNode;
		}else{
			root.addNode(newNode);
		}
	}
	public void print(){
		System.out.print("中序遍历：");
		this.root.printNode();
	}
	public void printPreOrder(){
		System.out.print("前序遍历：");
		this.root.printPreOrderNode();
	}
	
}
