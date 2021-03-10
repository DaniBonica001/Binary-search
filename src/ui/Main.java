package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
	
	public static List<String> output;
	public static List<Integer> booksPrices;
	public final static String SPLIT=" ";
	public final static BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
	public final static BufferedWriter bw = new BufferedWriter (new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		output= new ArrayList<String>();
		booksPrices =new ArrayList<Integer>();
		
		//bw.write("indique la cantidad de libros disponibles");
		bw.flush();
		String books=br.readLine();
		
		if (books!=null) {
			int numberBooks=Integer.parseInt(books);		
		}		
		
		receiveBooksPrices();
		
		//bw.write("indique la cantidad de dinero disponibles");
		bw.flush();
		findBooks();
		
		br.close();
		bw.close();
	}
	
	
	public static void receiveBooksPrices() {
		String line;
		try {
			
			line=br.readLine();
			if (line!=null) {
				String []parts= line.split(SPLIT);
				stringToInteger(parts);
				System.out.println("Lo puse en el array de String");
			}		
			
		}catch (IOException e){
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
	}
	
	public static void stringToInteger(String []words) {
		if (words!=null) {
			Integer []bPrices = new Integer [words.length];
			for (int i=0;i<bPrices.length;i++) {
				bPrices[i]=Integer.parseInt(words[i]);
				System.out.println("string to integer: "+bPrices[i]);				
			}
			
			sortPrices(bPrices);
		}
		
	}
	
	
	public static void sortPrices(Integer []bookPrices) {
		List <Integer> prices = new ArrayList <Integer>();
		prices = Arrays.asList(bookPrices);
		
		Collections.sort(prices);
		booksPrices.clear();
		for (int i=0;i<prices.size();i++) {
			booksPrices.add(prices.get(i));
		}		
		
		System.out.println(Arrays.toString(prices.toArray()));		
	}
	
	
	/*
	 * Me falta el método de busqueda binaria
	 * la salida
	 * comprobar si funciona
	 */
	
	public static void findBooks() throws IOException{
		int money = Integer.parseInt(br.readLine()); 
		
		Integer [] bookPricesInOrder = new Integer[booksPrices.size()];
		bookPricesInOrder = booksPrices.toArray(bookPricesInOrder);
		
		
		boolean found =false;
	
		//Los números del primer recorrido
		int num1=0;
		int num2=0;
		int difference=0;
		
		int n1=0;
		int n2=0;
	
		
		
		
		int menor=0;
		int nm1=0;
		int nm2=0;
		
		
		
		for (int j=0;j<bookPricesInOrder.length && !found;j++) {
			
			num1 = bookPricesInOrder[j];
			
			for (int k=1;k<bookPricesInOrder.length && !found && k!=j;k++) {
				
				if (num1+ bookPricesInOrder[k]==money) {
					found=true;
					num2=bookPricesInOrder[k];
					
					if (num1>num2) {
						difference= num1-num2;
					}else {
						difference = num2-num1;
					}
				}
			}			
		}
		
		
		//System.out.println("Asignacion de num1= "+num1);
		//System.out.println("Asignacion de num2= "+num2);
		//System.out.println("Asignacion de difference= "+difference);
		
		for (int i=0;i<bookPricesInOrder.length ;i++) {
			//System.out.println("***Entre al ciclo");
			found=false;
			int start =0;
			int end=bookPricesInOrder.length-1;			
			nm1= bookPricesInOrder[i];
			
			//System.out.println("Asignacion de num1= "+n1+"\n");
			while (start<end && !found) {
				//System.out.println("Estaba true pero lo cambie a false"+"\n");
				int middle = (start+end)/2;
				nm2=bookPricesInOrder[middle];
				//System.out.println("Asignacion de middle= "+middle+"\n");
				if (nm1+nm2>money) {
					end=middle-1;
					
				}else if (nm1+nm2<money) {
					start=middle+1;
					
				}else if (nm1+nm2==money) {
					//System.out.println("***Encontre otra suma que da igual a money"+"\n");
					found=true;
					
					
					n1=nm1;
					n2=nm2;	
					
					//System.out.println("++++Asignacion de nm1= "+nm1+"\n");
					//System.out.println("++++Asignacion de nm2= "+nm2+"\n");
					if (n1>n2) {
						menor=n1-n2;
					}else {
						menor=n2-n1;
					}					
					
					
					if (menor<difference ) {
						//n1=nm1;
						//n2=nm2;
						difference=menor;
						output.add("Peter should buy books whose prices are "+n1+" and "+n2);
						System.out.println("Peter should buy books whose prices are "+n1+" and "+n2);		
					}
				}
			}
			
		}
		
		if (found == true) {
			output.add("Peter should buy books whose prices are "+n1+" and "+n2);
			System.out.println("Peter should buy books whose prices are "+n1+" and "+n2);			
		}
	}	

}
