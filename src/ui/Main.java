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
		
		String books= br.readLine(); 
		@SuppressWarnings("unused")
		int numberBooks=1;
		
		
		while (books!=null) {//Busca the EOF (End of file)
			if (!books.isEmpty()) {
				numberBooks=Integer.parseInt(books);
				receiveBooksPrices();
				findBooks();			
			}
			//Se debe usar el atajo ctrl + z para indicarle a la consola que termino la entrada.
			books=br.readLine();					
		}
		
		System.out.println(showOut());
		
		br.close();
		bw.close();
	}
	
	
	public static void receiveBooksPrices() {
		String line;
		try {
			line=br.readLine();
			if (!line.isEmpty() && line!=null) {
				String []parts= line.split(SPLIT);
				stringToInteger(parts);				
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
	}
		
	public static void findBooks() {
		String amountMoney;
		
		try {
			amountMoney=br.readLine();
			
			if (!amountMoney.isEmpty()) {
				int money = Integer.parseInt(amountMoney);			

			
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
				
				boolean exit=false;
								
				for (int i=0;i<bookPricesInOrder.length && !exit ;i++) {
				
					found=false;
					int start =0;
					int end=bookPricesInOrder.length-1;			
					nm1= bookPricesInOrder[i];
					
					while (start<=end && !found) {
						
						int middle = (start+end)/2;
						nm2=bookPricesInOrder[middle];
					
						if (nm1+nm2>money) {
							end=middle-1;
							
						}else if (nm1+nm2<money) {
							start=middle+1;
							
						}else if (nm1+nm2==money) {
							
							found=true;						
							
							n1=nm1;
							n2=nm2;	
							
							if (n1>n2) {
								menor=n1-n2;
							}else {
								menor=n2-n1;
							}					
							
							
							if (menor<difference && menor!=0 ) {							
								difference=menor;
								output.add("Peter should buy books whose prices are "+n1+" and "+n2+".");										
							}else if (menor==difference) {								
								exit=true;
								output.add("Peter should buy books whose prices are "+n1+" and "+n2+".");								
							}else if (difference<menor) {								
								exit=true;
								output.add("Peter should buy books whose prices are "+num1+" and "+num2+".");
							}
						}
					}					
				}					
			}
			
		}catch (IOException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}		
	}	
	
	
	public static String showOut() {
		String message="";
		for (int i=0;i<output.size();i++) {
			message+="\n"+output.get(i)+"\n";
		}
		return message;
	}
}
