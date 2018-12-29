import java.util.Arrays;

public class AdjacencyMatrix {
	double[][] matrix;
	public String names[];
	int counter = 0;

	public AdjacencyMatrix(int n) {
		double[][] temp = new double[n][n];
		String[] tname = new String[n];
		matrix = temp;
		names = tname;

	}

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

	public void insertNode(String name, String edge, double weight) {
		matrix[insertNode(name)][findNode(edge)] = weight*10;
		matrix[findNode(edge)][insertNode(name)] = weight*10;
		

	}
	
	public int findNode(String name) {
		for(int i =0; i<names.length; i++) {
			if(names[i].equals(name)) {
				return i;
			}
		}
		System.out.println("Edge node not found");
		return -1;
	}
	
	public String findNode(int i) {
		return names[i];
	}
	public void printMatrix() {
		for(int i =0; i<matrix.length; i++) {
			for(int j =0; j<matrix.length; j++) {
				System.out.print(matrix[i][j]/10.0 + " ");
			}
			System.out.println();
		}
	}
}