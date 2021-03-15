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
		//System.out.println(Arrays.toString(bookPrices));
		
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
				int d1=0;
				
				int n1=0;
				int n2=0;
			
				int menor=0;
				int nm1=0;
				int nm2=0;
				
				int a=0;
				int b=0;
				//int c=0;
				
				for (int j=0;j<bookPricesInOrder.length && !found;j++) {
					
					num1 = bookPricesInOrder[j];
					
					for (int k=1;k<bookPricesInOrder.length && !found && k!=j;k++) {
						
						if (num1+ bookPricesInOrder[k]==money) {
							found=true;
							num2=bookPricesInOrder[k];
							
							if (num1>num2) {
								difference= num1-num2;
							}else if (num1<num2){
								difference = num2-num1;
							}else {
								difference=num1-num2;
							}
							d1=difference;
						}
					}			
				}
				
				//System.out.println("***Asignación de num1: "+num1);
				//System.out.println("***Asignación de num2: "+num2);
				//System.out.println("***Asignación de difference: "+difference);
				
				boolean exit=false;
				boolean foundPrices=false;
				int add=0;
				for (int i=0;i<bookPricesInOrder.length && !exit;i++) {
					
					foundPrices=false;
					int start=0;
					int end=bookPricesInOrder.length-1;
					nm1 = bookPricesInOrder[i];
					//System.out.println("Asignacion nm1:"+nm1);
										
					while (start<=end && !foundPrices) {
						
						int middle=(start+end)/2;
						
						if (middle!=i) {
							nm2 = bookPricesInOrder[middle];
							//System.out.println("Asignacion nm2:"+nm2);
							
							if (nm1+nm2>money) {
								end=middle-1;
								
							}else if (nm1+nm2<money) {
								start=middle+1;
								
							}else if (nm1+nm2==money) {
								//System.out.println("Números que dan la suma");
								foundPrices=true;
								//String message;
								n1=nm1;
								n2=nm2;
								
								if (n1>n2) {
									menor=n1-n2;
									//message="Peter should buy books whose prices are "+n2+" and "+n1+".";
								}else if (n2>n1) {
									menor=n2-n1;
									//message="Peter should buy books whose prices are "+n1+" and "+n2+".";
								}else {
									menor=n2-n1;
									//message="Peter should buy books whose prices are "+n1+" and "+n2+".";
								}
								

								if (menor<difference) {
									difference=menor;
									a=n1;
									b=n2;									
									add+=1;

								}else if (d1<menor) {									
									exit=true;
									String dMessage="";
									if (num1>num2) {
										dMessage+="Peter should buy books whose prices are "+num2+" and "+num1+".";
									}else if (num1<num2) {
										dMessage+="Peter should buy books whose prices are "+num1+" and "+num2+".";
									}else {
										dMessage+="Peter should buy books whose prices are "+num1+" and "+num2+".";
									}
									output.add(dMessage);
									
																		
								}else if (d1==menor) {
									difference=d1;									
								}
							}
							
						}else if (middle==i) {
							//System.out.println("middle es iguaal a i");
							foundPrices=true;							
						}
					}
				}					
				
				if (menor==difference && add==0) {
					String eMessage="";
					if (num1>num2) {
						eMessage+="Peter should buy books whose prices are "+num2+" and "+num1+".";
					}else if (num1<num2) {
						eMessage+="Peter should buy books whose prices are "+num1+" and "+num2+".";
					}else {
						eMessage+="Peter should buy books whose prices are "+num1+" and "+num2+".";
					}
					output.add(eMessage);
				}else if (add!=0) {					
					if (a<b) {
						output.add("Peter should buy books whose prices are "+a+" and "+b+".");
					}else if (a>b) {
						output.add("Peter should buy books whose prices are "+b+" and "+a+".");
					}else if (a==b) {
						output.add("Peter should buy books whose prices are "+a+" and "+b+".");
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
