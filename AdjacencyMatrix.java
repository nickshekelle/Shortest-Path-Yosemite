import java.util.Arrays;

public class AdjacencyMatrix {
	double[][] matrix;
	public String names[]; //to store the string names of nodes

	public AdjacencyMatrix(int n) {
		double[][] temp = new double[n][n];
		String[] tname = new String[n];
		matrix = temp;
		names = tname;

	}
	//Function for inserting nodes into graph
	public int insertNode(String name) {
		for (int i = 0; i < names.length; i++) {
			if (names[i] == name) {
				return i;
			}
			if(names[i] == null) {
				names[i] = name;
				return i;
			}
		}
		return -1;
	}
	//function to insert nodes and edges into graph
	public void insertNode(String name, String edge, double weight) {
		matrix[insertNode(name)][findNode(edge)] = weight*10;
		matrix[findNode(edge)][insertNode(name)] = weight*10;
		

	}
	//return node index in names array
	public int findNode(String name) {
		for(int i =0; i<names.length; i++) {
			if(names[i].equals(name)) {
				return i;
			}
		}
		System.out.println("Edge node not found");
		return -1;
	}
	//return node name given index
	public String findNode(int i) {
		return names[i];
	}
	//prints the matrix
	public void printMatrix() {
		for(int i =0; i<matrix.length; i++) {
			for(int j =0; j<matrix.length; j++) {
				System.out.print(matrix[i][j]/10.0 + " ");
			}
			System.out.println();
		}
	}
}