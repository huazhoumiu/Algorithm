package algorithmPractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
	private int n; //number of vertices
	private int[][] adjacnetMatrix;
	public Graph(int n, int[][] adjacnetMatrix) {
		this.n = n;
		this.adjacnetMatrix = adjacnetMatrix;
	}
	
	public int getdeg(int i) {
		int sum = 0;
		for(int j = 0; j < n; j++)
			sum += this.adjacnetMatrix[i][j];
		return sum;
	}
	
	//if there is no zero value in the array
	// then the array is Full
	int pickVertice(int[] vertices) {
		for(int i = 0; i < n; i++)
			if(vertices[i] == 0)
				return i;
		return -1;
	}
	
	boolean isNeighbor(int cur, int vertice) {
		return adjacnetMatrix[cur][vertice] == 1;
	}
	
	public int[] dfs() {
		int component = 0;
		int[] vertices = new int[n];
		int[] stack = new int[n];
		boolean[] visited = new boolean[n];
		int top = -1;
		int cur = pickVertice(vertices);
		while(cur != -1) {
			stack[++top] = cur; 
			vertices[cur] = ++component;
			visited[cur] = true;
			while(top != -1) {
				int ver = stack[top];
				int pos = top;
				for(int i = 0; i < n; i++) {
					if(isNeighbor(ver, i) && !visited[i]) {
						visited[i] = true;
						stack[top++] = i;
						vertices[i] = component;
					}
				}
				if(top == pos)
					top--;			
			}
			cur = pickVertice(vertices);
		}
		return vertices;
	}
	
	public int[] bfs() {
		int component = 0;
		int[] vertices = new int[n];
		int[] queue = new int[n];
		int front, rear;
		front = 0;
		rear = 0;
		boolean[] visited = new boolean[n];
		int cur = pickVertice(vertices);
		while(cur != -1) {
			//due to the length of queue is the number of vertices
			//rear will not exceed the n-1
			//no need to check the bound
			queue[rear++] = cur;
			vertices[cur] = ++component;
			visited[cur] = true;
			while(rear > front) {
				int ver = queue[front];
				for(int i = 0; i < n; i++) {
					if(isNeighbor(ver, i) && !visited[i]) {
						visited[i] = true;
						queue[rear++] = i;
						vertices[i] = component;
					}
				}
				//dequeue;
				front++;
			}
			cur = pickVertice(vertices);
		}
		
		
		return vertices;
	}
	
	public void analysis(int[] a) {
		Map<Integer, List<Integer>> hash = new HashMap<>();
		for(int i = 0; i < n; i ++) {
			if(!hash.containsKey(a[i]))
				hash.put(a[i], new ArrayList<>());
			hash.get(a[i]).add(i);
		}
		
		int components = hash.size();
		System.out.println("There is " + components + " components");
		for(Integer key : hash.keySet()) {
			int size = hash.get(key).size();
			System.out.println("There is " + size + " vertices in: " + key);
			int deg = 0;
			int cycle = 0;
			List<Integer> vertices = hash.get(key);
			for(Integer vertex : vertices) {
				int tmp = getdeg(vertex); 
				deg += tmp;
				if(tmp == 2)
					cycle += 2;				
			}
			int m = deg/2;
			System.out.println("There is " + m + " edges");
			if(m == size && cycle == 2*size)
				System.out.println("Each vertex has 2 degrees, " + key + " is a cycle");
			else if (m >= size)
				System.out.println("Edges more than vertex, " + key + " has cycles");
			
		}
		
//		ArrayList tmp = hash.get(1).forEach(null);

	}
	
	
	
	public static void main(String[] args) {
		
		int n = 9;
		int[][] adjacnetMatrix = {
				   //A,B,C,D,E,F,G,H,I
					{0,1,1,0,0,1,0,0,0}, // A	
					{1,0,0,0,0,1,0,0,0}, // B
					{1,0,0,0,0,1,1,0,0}, // C
					{0,0,0,0,1,0,0,0,1}, // D
					{0,0,0,1,0,0,0,0,1}, // E
					{1,1,1,0,0,0,0,1,0}, // F
					{0,0,1,0,0,0,0,1,0}, // G
					{0,0,0,0,0,1,1,0,0}, // H
					{0,0,0,1,1,0,0,0,0}, // I
					};
		
		
		Graph g1 = new Graph(n, adjacnetMatrix);
//		System.out.println(Arrays.toString(g1.dfs()));
//		System.out.println(Arrays.toString(g1.bfs()));
		g1.analysis(g1.bfs());

		
//		System.out.println("hello world!");
	}

}
