import java.util.*;
import java.lang.*;
import java.io.*;

public class Dijkstra {
	public static String s = null; // This will be our return string to print in JFrame UI
	static int[] parents;
	public static int[] shortestDistances;

	private static final int NO_PARENT = -1;

	// Function that implements Dijkstra's
	// single source shortest path
	// algorithm for a graph represented
	// using adjacency matrix
	// representation
	public static void dijkstra(AdjacencyMatrix a, int startVertex) {
		double[][] matrix = a.matrix;
		int nVertices = matrix[0].length;

		// shortestDistances[i] will hold the
		// shortest distance from src to i
		shortestDistances = new int[nVertices];

		// added[i] will true if vertex i is
		// included / in shortest path tree
		// or shortest distance from src to
		// i is finalized
		boolean[] added = new boolean[nVertices];

		// Initialize all distances as
		// INFINITE and added[] as false
		for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
			shortestDistances[vertexIndex] = Integer.MAX_VALUE;
			added[vertexIndex] = false;
		}

		// Distance of source vertex from
		// itself is always 0
		shortestDistances[startVertex] = 0;

		// Parent array to store shortest
		// path tree
		parents = new int[nVertices];

		// The starting vertex does not
		// have a parent
		parents[startVertex] = NO_PARENT;

		// Find shortest path for all
		// vertices
		for (int i = 1; i < nVertices; i++) {

			// Pick the minimum distance vertex
			// from the set of vertices not yet
			// processed. nearestVertex is
			// always equal to startNode in
			// first iteration.
			int nearestVertex = -1;
			int shortestDistance = Integer.MAX_VALUE;
			for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
				if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) {
					nearestVertex = vertexIndex;
					shortestDistance = shortestDistances[vertexIndex];
				}
			}

			// Mark the picked vertex as
			// processed
			added[nearestVertex] = true;

			// Update dist value of the
			// adjacent vertices of the
			// picked vertex.
			for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
				int edgeDistance = (int) matrix[nearestVertex][vertexIndex];

				if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[vertexIndex])) {
					parents[vertexIndex] = nearestVertex;
					shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
				}
			}
		}

		
	}

	// Function to print shortest path from Source to currentVertex
	public static void printOnePath(int currentVertex, int[] parents, AdjacencyMatrix a) {
		// Base case : Source node has
		// been processed
		if (currentVertex == NO_PARENT) {
			return;
		}
		printOnePath(parents[currentVertex], parents, a);
		s +=a.findNode(currentVertex) + " -> ";
		
	}
}
