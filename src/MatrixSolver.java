import java.util.InputMismatchException;
import java.util.Scanner;

public class MatrixSolver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		System.out.print("┌           ┐"+"\n");
		System.out.print("│  1 -1  5  "+"│\n");
		System.out.print("│  6  0  4  "+"│\n");
		System.out.print("│  0  3  7  "+"│\n");
		System.out.print("└           ┘"+"\n");
		*/
		
		int rows = 3;
		int cols = rows+1;
		Matrix a = new Matrix(rows,cols);
		String[] numbers = new String[]{"3","5","3","-4","6","7","3","2","1","-6","4","3"};
		a.fillMatrix(numbers);
		a.fillRandom();
		Scanner input = new Scanner(System.in);
		String command="help";
		
		a.printMatrix();
		a.runCommand(command);
		while(!a.done)
		{
			System.out.print("Operation: ");
			command = input.nextLine();
			try{
			a.runCommand(command);
			}
			catch(InputMismatchException e)
			{
				System.out.println("There was an error in the row that was entered!");
			}
			
			
			command="";
		}
		

	}//end main
}//end class
