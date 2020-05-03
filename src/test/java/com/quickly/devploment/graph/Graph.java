package com.quickly.devploment.graph;


/**
 * 深度优先实现的图
 *
 * @author Administrator
 */
public class Graph {

	/**
	 * 最大的顶点个数
	 */
	private final int MAX_VERTS = 20;

	/**
	 * 顶点元素List
	 */
	private Vertex[] vertexList;

	/**
	 * 首先这是一个二维数组
	 */
	private int adjMat[][];

	/**
	 * 坐标
	 */
	public int nVerts;

	/**
	 * 这是一个栈
	 */
	private StackX theStack;

	public Graph() {
		vertexList = new Vertex[MAX_VERTS];
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		for (int i = 0; i < MAX_VERTS; i++) {
			for (int j = 0; j < MAX_VERTS; j++) {
				adjMat[i][j] = 0;
			}
		}
		theStack = new StackX();
	}

	public void addVertex(char lab) {
		vertexList[nVerts++] = new Vertex(lab);
	}

	/**
	 * 二维数组
	 *
	 * @param start 邻接坐标
	 * @param end
	 */
	public void addEdage(int start, int end) {
		adjMat[start][end] = 1;
		adjMat[end][start] = 1;
	}

	public void displayVertex(int v) {
		System.out.print(vertexList[v].lable + " ");
	}

	public void dfs() {
		vertexList[0].wasvisited = true;
		displayVertex(0);
		theStack.push(0);
		while (!theStack.isEmpty()) {
			int v = getAdjUnvisitedVertex(theStack.peek());
			if (v == -1)
				theStack.pop();
			else {
				vertexList[v].wasvisited = true;
				displayVertex(v);
				theStack.push(v);
			}
		}
		for (int i = 0; i < nVerts; i++)
			vertexList[i].wasvisited = false;
	}

	public int getAdjUnvisitedVertex(int v) {
		for (int i = 0; i < nVerts; i++)
			if (adjMat[v][i] == 1 && vertexList[i].wasvisited == false)
				return i;
		return -1;
	}
}