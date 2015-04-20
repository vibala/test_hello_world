import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

class ScrabbleConsole{
	
	public ScrabbleConsole(){
		System.out.println("Welcome to the Scrabble assistant");		
	}
	
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		String word, letter_list;
		LinkedList<String> liste = new LinkedList<String>();
		try {
			new ScrabbleConsole();
			Dictionary a = new Dictionary("dictionnaire.txt");
			//System.out.println("Please enter a word");
			//word = new String(scan.next());
			System.out.println("Please enter a letter list");
			letter_list = new String(scan.next());			
			char[] myArray = letter_list.toCharArray();
			/*
			if(a.mayBeComposed(word,myArray)){				
				System.out.println(word + " may be composed with letters : " + Arrays.toString(letter_list.toCharArray()));
			}else{
				System.out.println(word + " may NOT be composed with letters : " + Arrays.toString(letter_list.toCharArray()));
			}
			*/
			
			ScrabbleComparator sc = new ScrabbleComparator(myArray);
			liste = a.getWordsThatCanBeComposed(myArray);
			Collections.sort(liste, sc);
			char [] tab;
			System.out.println(liste.size() + " matching word(s) found : "+ liste.toString() );
			System.out.println();
			for(int i = 0; i < 20; i++){
				tab = a.getCompositon(liste.get(i),myArray);
				System.out.println(tab);
			}
			
					
			/*liste = a.getWordsThatCanBeComposed(myArray);
			System.out.println(liste.size() + " matching word(s) found : "+ liste.toString());*/
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
}