import java.text.DecimalFormat;
import java.util.Random;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Matrix {
	
	public double[][] matrix;
	private String[][] smatrix;
	public boolean done = false;
	private DecimalFormat num = new DecimalFormat("0.###");
	
	
	public Matrix()
	{
		matrix = new double[0][0];
		for(int i=0;i<matrix.length;i++)
		{
			for(int j=0;j<matrix[i].length;j++)
			{
				matrix[i][j] = 0;
			}
		}
	}
		
	public Matrix(int rows, int cols)
	{
		matrix = new double[rows][cols];
		smatrix = new String[rows][cols];
		for(int i=0;i<matrix.length;i++)
		{
			for(int j=0;j<matrix[i].length;j++)
			{
				matrix[i][j] = 0;
				smatrix[i][j] = "0";
			}
		}
	}
	
	//fill operations
	
	public void fillMatrix()
	{
		Scanner inp = new Scanner(System.in);
		String temp;
		String[] input;
		for(int i=0;i<matrix.length;i++)
		{
			for(int j=0;j<matrix[i].length;j++)
			{
				try{
				smatrix[i][j] = "_";
				printSMatrix();
				System.out.print((i+1)+", "+(j+1)+": ");
				temp = inp.nextLine();
				if(temp.contains("/"))
				{
					double frac;
					input = temp.split("/");
					frac = round(Double.parseDouble(input[0])/Double.parseDouble(input[1]));
					matrix[i][j] = frac;
					smatrix[i][j] = frac+"";
				}
				else
				{
					matrix[i][j] = Double.parseDouble(temp);
					smatrix[i][j] = temp;
				}
				
				}
				catch(NumberFormatException e)
				{
					System.out.print("Please enter a number!");
					j-=1;
				}
			}
		}
	}
	public void fillMatrix(String[] numbers)
	{
		
		String temp;
		String[] input;
		int place = 0;
		for(int i=0;i<matrix.length;i++)
		{
			for(int j=0;j<matrix[i].length;j++)
			{
				try{
				
				if(numbers[place].matches("^[(-9)-9]{1,5}"))
				{
					temp = numbers[place];
				}
				else
				{
					temp = "0";
				}
				
				if(temp.contains("/"))
				{
					double frac;
					input = temp.split("/");
					frac = round(Double.parseDouble(input[0])/Double.parseDouble(input[1]));
					matrix[i][j] = frac;
					smatrix[i][j] = frac+"";
				}
				else
				{
					matrix[i][j] = Double.parseDouble(temp);
					smatrix[i][j] = temp;
				}
				place++;
				}
				catch(NumberFormatException e)
				{
					System.out.print("Please enter a number!");
					j-=1;
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					temp = "0";
					matrix[i][j] = Double.parseDouble(temp);
					smatrix[i][j] = temp;
				}
			}
		}
	}
	public void fillRandom()
	{
		Random random = new Random();
		
		for(int i=0;i<matrix.length;i++)
		{
			for(int j=0;j<matrix[i].length;j++)
			{
				if(random.nextInt(3)==1)
				{
					matrix[i][j] = -Math.round(Math.random()*10);
				}
				else
				{
					matrix[i][j] = Math.round(Math.random()*10);
				}
			}
		}
	}
	
	//get command
	public void runCommand(String command)
	{
		Scanner input = new Scanner(System.in);

		if(command.equalsIgnoreCase("addrows"))
		{
			int row1, row2;
			System.out.print("row1?: ");
			row1 = input.nextInt();
			System.out.print("row2?: ");
			row2 = input.nextInt();
			input.nextLine();
			addRows(row1, row2);				
			System.out.println();
			printMatrix();
		}
		
		else if(command.equalsIgnoreCase("mulrow"))
		{
			int row;
			String[] factor1;
			String factor2;
			double factor;
			System.out.print("row?: ");
			row = input.nextInt();
			input.nextLine();
			System.out.print("factor?: ");
			factor2 = input.nextLine();
			if(factor2.contains("/"))
			{
				factor1 = factor2.split("/");
				factor = Double.parseDouble(factor1[0])/Double.parseDouble(factor1[1]);
			}
			else
			{
				factor = Double.parseDouble(factor2);
			}
			mulRow(row, factor);
			System.out.println();
			printMatrix();
		}
		
		else if(command.equalsIgnoreCase("muladdrow"))
		{
			int row1, row2;
			String[] factor1;
			String factor2;
			double factor;
			System.out.print("row1?: ");
			row1 = input.nextInt();
			input.nextLine();
			System.out.print("factor?: ");
			factor2 = input.nextLine();
			if(factor2.contains("/"))
			{
				factor1 = factor2.split("/");
				factor = Double.parseDouble(factor1[0])/Double.parseDouble(factor1[1]);
			}
			else
			{
				factor = Double.parseDouble(factor2);
			}
			System.out.print("row2?: ");
			row2 = input.nextInt();
			input.nextLine();
			muladdRow(row1, factor, row2);
			System.out.println();
			printMatrix();
		}
		
		else if(command.equalsIgnoreCase("switchrows"))
		{
			int row1, row2;
			System.out.print("row1?: ");
			row1 = input.nextInt();
			System.out.print("row2?: ");
			row2 = input.nextInt();
			input.nextLine();
			switchRows(row1, row2);
			System.out.println();
			printMatrix();
		}
		
		else if(command.equalsIgnoreCase("getval"))
		{
			int row, col;
			System.out.print("row?: ");
			row = input.nextInt();
			System.out.print("col?: ");
			col = input.nextInt();
			input.nextLine();
			System.out.println(matrix[row-1][col-1]);
		}
		
		else if(command.equalsIgnoreCase("getdet"))
		{
			if(getDeterminant() != null)
			{
				System.out.println("D="+getDeterminant());
			}
			else if(Double.parseDouble(getDeterminant()) < 0)
			{
				System.out.println("D=("+getDeterminant()+")");
			}
			else
			{
				
			}
		}
		
		else if(command.equalsIgnoreCase("solve"))
		{
			solve();
		}
		
		else if(command.equalsIgnoreCase("solvemat"))
		{
			solvemat();
			printMatrix();
		}
		else if(command.equalsIgnoreCase("solvemat reduce"))
		{
			solvemat();
		}
		
		else if(command.equalsIgnoreCase("printdet"))
		{
			printDet();
		}
		
		else if(command.equalsIgnoreCase("help"))
		{
			System.out.println("Commands: addrows, mulrow, muladdrow, switchrows, printdet, getdet, getval, solve, solvemat, quit, help.");
		}
		
		else if(command.equalsIgnoreCase("quit"))
		{
			done = true;
		}
	else
		{
			System.out.println("I don't know how to do that!");
		}
		
	}
	
	//Row operations
	public void addRows(int row1, int row2)
	{
		try
		{
			if((row1-1) > matrix.length){}
			if((row2-1) > matrix.length){}
			for(int i=0;i<matrix[row1-1].length;i++)
			{
				matrix[row2-1][i]+=matrix[row1-1][i];
				if(matrix[row2-1][i] < 0.0001 && matrix[row2-1][i] > -0.0001)
				{
					matrix[row2-1][i] = 0;
				}
			}
			System.out.print("R"+row1+" = r"+row2+" + r"+row1);
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.print("Whoops, row "+(Integer.parseInt(e.getMessage()))+" doesn't exist!");
		}
		
	}
	
	public void mulRow(int row, double factor)
	{
		try
		{
			if((row-1) > matrix.length){}
			for(int i=0;i<matrix[row-1].length;i++)
			{
				matrix[row-1][i]=round(matrix[row-1][i]*factor);
				if(matrix[row-1][i] < 0.0001 && matrix[row-1][i] > -0.0001)
				{
					matrix[row-1][i] = 0;
				}
			}
			System.out.print("R"+row+" = "+factor+"*r"+row);
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.print("Whoops, row "+(Integer.parseInt(e.getLocalizedMessage())+1)+" doesn't exist!");
		}
	}
	
	public void muladdRow(int row1, double factor, int row2)
	{
		
		try
		{
			double nothing = matrix[row2-1][0];
			if(nothing==0){}
			nothing = matrix[row1-1][0];

		for(int i=0;i<matrix[0].length;i++)
		{
			matrix[row2-1][i]+=round(matrix[row1-1][i]*factor);
			if(matrix[row2-1][i] < 0.0001 && matrix[row2-1][i] > -0.0001)
			{
				matrix[row2-1][i] = 0;
			}
		}
		System.out.print("R"+row2+" = "+round(factor)+"*r"+row1+" + r"+row2);
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.print("Whoops, row "+(Integer.parseInt(e.getLocalizedMessage())+1)+" doesn't exist!");
		}
	}
	
	public void switchRows(int row1,int row2)
	{
		try
		{
			if((row1-1) > matrix.length){}
			if((row2-1) > matrix.length){}
			double[] temp = new double[matrix[0].length];
			
			for(int i=0;i<matrix[0].length;i++)
			{
				temp[i] = matrix[row1-1][i];
				matrix[row1-1][i] = matrix[row2-1][i];
				matrix[row2-1][i] = temp[i];
			}
			System.out.print("R"+row1+" <--> "+"R"+row2);
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.print("Whoops, row "+(Integer.parseInt(e.getLocalizedMessage())+1)+" doesn't exist!");
		}
	}
	
	public void solve()
	{
		double x = 0,y = 0,z = 0;
		double det;
		try
		{
			det = Double.parseDouble(getDeterminant());
			if(det == 0)
			{
				System.out.println("This system either has infinite solutions or no solutions!\n" +
						"The determinant equals 0");
			}
			else
			{
				if(matrix[0].length > 1)
				{
					x = Double.parseDouble(getDeterminant(1));
					x = round(Double.parseDouble(num.format(x/det)));
					if(matrix[0].length > 2)
						System.out.print("x: "+x);
					else
						System.out.println("x: "+x);
				}
				if(matrix[0].length > 2)
				{
					y = Double.parseDouble(getDeterminant(2));
					y = round(Double.parseDouble(num.format(y/det)));
					if(matrix[0].length > 3)
						System.out.print(", y: "+y);
					else
						System.out.println(", y: "+y);
				}
				if(matrix[0].length > 3)
				{
					z = Double.parseDouble(getDeterminant(3));
					z = round(Double.parseDouble(num.format(z/det)));
					System.out.println(", z: "+z);
				}
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("The array index was out of bounds at "+e.getLocalizedMessage()+"!");
		}
		catch(InputMismatchException e)
		{
			System.out.print("The kind of input was wrong!");
		}
		catch(NullPointerException e)
		{
			System.out.println("There was an unexpected error.. We don't know why.");
		}
	}
	
	public void solve(int col)
	{
		double det;
		double var;
		det = Double.parseDouble(getDeterminant());
		var = Double.parseDouble(getDeterminant(col-1))/det;
		System.out.println("variable: "+var);
	}
	
	int control = 0;
	public void solvemat()
	{
		
		if(control < matrix.length)
		{
			if(matrix[control][control]==1)
			{
				for(int i=0;i<matrix.length-(control+1);i++)
				{
					muladdRow(control+1,-(matrix[matrix.length-(i+1)][control]),(matrix.length-i));
					System.out.println();
					
				}
				control++;
				solvemat();
			}
			else
			{
				
				if(matrix[(matrix.length-control)-1][control] == -1)
				{
					//System.out.println("the number at "+(matrix.length-control)+", "+(control+1)+" is a -1");
					mulRow(control+1,-1);
					System.out.println();
					solvemat();
				}
				else
				{
					int rowNot0 = -1;
					
						for(int i=0;i<matrix.length-(control+1);i++)
						{
							if(matrix[matrix.length-(i+1)][control] != 0)
							{
								rowNot0 = matrix.length-(i+1);
								
							}
							else
							{
								if(matrix[matrix.length-(i+1)][control]==1)
								{
									switchRows(control+1,rowNot0+1);
									System.out.println();
								}
								else
								{
									switchRows(control+1,rowNot0+1);
									System.out.println();
								}
							}
						}
											
					
					int rowOf1=-1;
					for(int i=0;i<matrix.length-(control+1);i++)
					{
						
						if(matrix[matrix.length-(i+1)][control] == 1 || matrix[matrix.length-(i+1)][control] == -1)
						{
							rowOf1 = matrix.length-(i+1);
							System.out.println(rowOf1);
						}
						
					}
					if(rowOf1 != -1)
					{
						switchRows(control+1,rowOf1+1);
						
						System.out.println();
						solvemat();
					}
					else
					{
						//System.out.println("the number at "+(matrix.length-control)+", "+(control+1)+" is not -1");
						if(matrix[control][control] < 0)
						{
							mulRow(control+1,1/(matrix[control][control]));
							System.out.println();
							solvemat();
						}
						else
						{
							mulRow(control+1,-1/(matrix[control][control]));
							System.out.println();
							solvemat();
						}
					}	
				}
			}
		}
		
		if(control >= matrix.length && foundDone == true)
		{
			System.out.println("This is as far as \"solvemat\" goes!");
		}
		
	}
	
	public String getDeterminant()
	{
		double a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0,i=0,determinant;
		if(matrix.length == 2)
		{
			a = matrix[0][0];
			b = matrix[0][1];
			c = matrix[1][0];
			d = matrix[1][1];
			determinant = (a*d)-(b*c);
			return determinant+"";
		}
		if(matrix.length == 3)
		{
			a = matrix[0][0];
			b = matrix[0][1];
			c = matrix[0][2];
			d = matrix[1][0];
			e = matrix[1][1];
			f = matrix[1][2];
			g = matrix[2][0];
			h = matrix[2][1];
			i = matrix[2][2];
			determinant = ((a*e*i)+(b*f*g)+(c*d*h))-((c*e*g)+(a*f*h)+(b*d*i));
			return determinant+"";
		}
		else
		{
			System.out.println( "Cannot be determined at this time.. this is currently only written for 2x2 and 3x3 matracies.");
			return null;
		}
	}
	
	public String getDeterminant(int col)
	{
		double[][] fordet = new double[matrix.length][matrix[0].length];
		for(int i=0;i<matrix.length;i++)
		{
			for(int j=0;j<matrix[i].length;j++)
			{
				fordet[i][j] = matrix[i][j];
				if(j==(col-1))
				{
					fordet[i][j] = matrix[i][matrix[i].length-1];
				}
			}
		}
		double a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0,i=0,determinant;
		if(fordet.length == 2)
		{
			a = fordet[0][0];
			b = fordet[0][1];
			c = fordet[1][0];
			d = fordet[1][1];
			determinant = (a*d)-(b*c);
			return determinant+"";
		}
		if(fordet.length == 3)
		{
			a = fordet[0][0];
			b = fordet[0][1];
			c = fordet[0][2];
			d = fordet[1][0];
			e = fordet[1][1];
			f = fordet[1][2];
			g = fordet[2][0];
			h = fordet[2][1];
			i = fordet[2][2];
			determinant = ((a*e*i)+(b*f*g)+(c*d*h))-((c*e*g)+(a*f*h)+(b*d*i));
			return determinant+"";
		}
		else
		{
			return "Cannot be determined at this time.. this is currently only written for 2x2 and 3x3 matracies.";
		}
	}
	
	private double round(double num)
	{
		double num0 = num * 1e3;
		double num1 = Math.floor(num0)/1e3;
		//0.0005
		return num1;
	}
	
	//display matrix
	public void printMatrix()
	{		
		System.out.print("\n  ┌");
		for(int l=0;l<matrix[0].length;l++)
		{
			System.out.print("\t");
		}
		System.out.print("\t┐\n");
		
		for(int i=0;i<matrix.length;i++)
		{
			if(i>8)
			{
				System.out.print((i+1)+"│\t");
			}
			else
			{
				System.out.print((i+1)+" │\t");
			}
			for(int j=0;j<matrix[0].length;j++)
			{
				if(j==matrix[0].length-1)
				{
					if(matrix[i][j] == -0 || matrix[i][j] == 0)
					{
						matrix[i][j] = 0;
					}
					System.out.print(num.format(matrix[i][j])+"\t│");
				}
				else
				{
					if(matrix[i][j] == -0 || matrix[i][j] == 0)
					{
						matrix[i][j] = 0;
					}
					System.out.print(num.format(matrix[i][j])+"\t");
				}
			}
			if(i == matrix.length-1)
			{
				System.out.print("\n  └");
				for(int l=0;l<matrix[0].length;l++)
				{
					System.out.print("\t");
				}
				System.out.print("\t┘\n");
			}
			else
			{
				System.out.print("\n  │");
				for(int l=0;l<matrix[0].length;l++)
				{
					System.out.print("\t");
				}
				System.out.print("\t│\n");
			}
			
		}
		checkGaussian();
		checkGaussJordan();
	}
	
	public void printSMatrix()
	{
		System.out.print("\n  ┌");
		for(int l=0;l<matrix[0].length;l++)
		{
			System.out.print("\t");
		}
		System.out.print("\t┐\n");
		
		for(int i=0;i<smatrix.length;i++)
		{
			if(i>8)
			{
				System.out.print((i+1)+"│\t");
			}
			else
			{
				System.out.print((i+1)+" │\t");
			}
			for(int j=0;j<smatrix[0].length;j++)
			{
				if(j==smatrix[0].length-1)
				{
					if(smatrix[i][j].equals("-0") || smatrix[i][j].equals("0"))
					{
						smatrix[i][j] = "0";
					}
					System.out.print(smatrix[i][j]+"\t│");
				}
				else
				{
					if(smatrix[i][j].equals("-0") || smatrix[i][j].equals("0"))
					{
						smatrix[i][j] = "0";
					}
					System.out.print(smatrix[i][j]+"\t");
				}
			}
			if(i == smatrix.length-1)
			{
				System.out.print("\n  └");
				for(int l=0;l<smatrix[0].length;l++)
				{
					System.out.print("\t");
				}
				System.out.print("\t┘\n");
			}
			else
			{
				System.out.print("\n  │");
				for(int l=0;l<matrix[0].length;l++)
				{
					System.out.print("\t");
				}
				System.out.print("\t│\n");
			}
			
		}
	}
	
	public void printDet()
	{		
			System.out.print("\n  │");
			for(int l=0;l<matrix[0].length-1;l++)
			{
				System.out.print("\t");
			}
			System.out.print("\t│\n");
			
			for(int i=0;i<matrix.length;i++)
			{
				if(i>8)
				{
					System.out.print((i+1)+"│\t");
				}
				else
				{
					System.out.print((i+1)+" │\t");
				}
				for(int j=0;j<matrix[0].length-1;j++)
				{
					if(j==matrix[0].length-2)
					{
						if(matrix[i][j] == -0 || matrix[i][j] == 0)
						{
							matrix[i][j] = 0;
						}
						System.out.print(num.format(matrix[i][j])+"\t│");
					}
					else
					{
						if(matrix[i][j] == -0 || matrix[i][j] == 0)
						{
							matrix[i][j] = 0;
						}
						System.out.print(num.format(matrix[i][j])+"\t");
					}
				}
				if(i == matrix.length-1)
				{
					System.out.print("\n  │");
					for(int l=0;l<matrix[0].length-1;l++)
					{
						System.out.print("\t");
					}
					System.out.print("\t│\n");
				}
				else
				{
					System.out.print("\n  │");
					for(int l=0;l<matrix[0].length-1;l++)
					{
						System.out.print("\t");
					}
					System.out.print("\t│\n");
				}
				
			}
		}
	
	
	boolean foundDone = false;
	public void checkGaussian()
	{
		
		int check = 0;
		double tempTot1=0,tempTot2=matrix[0][0];
		if(tempTot2 == 1)
		{
			for(int i=1;i<matrix.length+1;i++)
			{
				
				for(int j=i-2;j>-1;j--)
				{
					tempTot1 += Math.abs(matrix[i-1][j]);
					tempTot2 = matrix[i-1][i-1];
					//System.out.println(i+": "+tempTot1+", "+tempTot2);
				}
				//System.out.println(i+": "+tempTot1+", "+tempTot2);
				//System.out.println(check);
				
				if(tempTot1 == 0 && tempTot2 == 1)
				{
					check += 1;
					
				}
				else
				{
					break;
				}
				tempTot1=0;
				tempTot2=0;
				
			}
		}
		else
		{
			
		}
		
		if(check == matrix.length)
		{
			if(!foundDone)
			{
				foundDone = true;
				String choice="";
				Scanner inp = new Scanner(System.in);
				
				System.out.print("This is in Gauss row echelon form! Continue?: ");
				choice = inp.nextLine();
				if(choice.equalsIgnoreCase("yes"))
				{
					
				}
				else if(choice.equalsIgnoreCase("no"))
				{
					System.out.println("Done!");
					System.exit(0);
				}
				else
				{
					
				}
			}
			else
			{
				
			}
		}
	}
	/*
	 public void checkGaussian()
	{
		
		int check = 0;
		int tempTot1=0,tempTot2=0;
		for(int i=1;i<matrix.length+1;i++)
		{
			tempTot1=0;
			tempTot2=0;
			for(int j=0;j<(matrix[i-1].length-matrix[i-1].length)+i;j++)
			{
				if(j<(matrix[i-1].length-matrix[i-1].length))
				{
					tempTot1 += matrix[i-1][j];
				}
				else
				{
					tempTot2 += matrix[i-1][j];
				}
			}
			//System.out.println(i+": "+tempTot1+", "+tempTot2);
			if(tempTot1 == 0 && tempTot2 == 1)
			{
				check += 1;
			}
			
		}
		
		if(check == matrix.length)
		{
			if(!foundDone)
			{
				foundDone = true;
				String choice="";
				Scanner inp = new Scanner(System.in);
				
				System.out.print("This is in Gauss row echelon form! Continue?: ");
				choice = inp.nextLine();
				if(choice.equalsIgnoreCase("yes"))
				{
					
				}
				else if(choice.equalsIgnoreCase("no"))
				{
					System.out.println("Done!");
					System.exit(0);
				}
				else
				{
					
				}
			}
			else
			{
				
			}
		}
	}
	 */
	
	public void checkGaussJordan()
	{
		
	}

}//end class
