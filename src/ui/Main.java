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
	
	public final static String SPLIT=" ";
	public final static BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
	public final static BufferedWriter bw = new BufferedWriter (new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		
		bw.write("indique la cantidad de libros disponibles");
		bw.flush();
		String books=br.readLine();
		
		if (books!=null) {
			int numberBooks=Integer.parseInt(books);		
		}		
		
		receiveBooksPrices();
		
		bw.write("indique la cantidad de dinero disponibles");
		bw.flush();
		int money = Integer.parseInt(br.readLine());
	}
	
	
	public static void receiveBooksPrices() {
		String line;
		try {
			
			line=br.readLine();
			if (line!=null) {
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
				//System.out.println("string to integer: "+bPrices[i]);				
			}
			
			sortPrices(bPrices);
		}
		
	}
	
	
	public static void sortPrices(Integer []bookPrices) {
		List <Integer> prices = new ArrayList <Integer>();
		prices = Arrays.asList(bookPrices);
		
		Collections.sort(prices);
		//System.out.println(Arrays.toString(prices.toArray()));	 
		
	}

}
