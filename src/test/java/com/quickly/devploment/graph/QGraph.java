package com.quickly.devploment.graph;

/**
 * 广度优先搜索----队列实现
 *
 * @author Administrator
 */
public class QGraph {
	private final int MAX_VERTS = 20;
	private Vertex[] vertexList;
	private int adjMat[][];
	public int nVerts;
	private Queue theQueue;

	public QGraph() {
		vertexList = new Vertex[MAX_VERTS];
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		for (int i = 0; i < MAX_VERTS; i++)
			for (int j = 0; j < MAX_VERTS; j++)
				adjMat[i][j] = 0;
		theQueue = new Queue();
	}

	public void addVertex(char lab) {
		vertexList[nVerts++] = new Vertex(lab);
	}

	public void addEdage(int start, int end) {
		adjMat[start][end] = 1;
		adjMat[end][start] = 1;
	}

	public void displayVertex(int v) {
		System.out.print(vertexList[v].lable + " ");
	}

	public void bfs() {
		vertexList[0].wasvisited = true;
		displayVertex(0);
		theQueue.insert(0);
		int v2;
		while (!theQueue.isEmpty()) {
			int v = theQueue.remove();
			while ((v2 = getAdjUnvisitedVertex(v)) != -1) {
				vertexList[v2].wasvisited = true;
				displayVertex(v2);
				theQueue.insert(v2);
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
