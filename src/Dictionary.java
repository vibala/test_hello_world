import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

class Dictionary {

	private String[] wordList;
	public int number_of_words;
	public static LinkedList<Character> liste = new LinkedList<Character>();;

	public Dictionary(String filename) throws FileNotFoundException {
		int i = 0;
		Scanner scan = new Scanner(new File(filename));
		number_of_words = scan.nextInt();
		System.out.println(number_of_words + " words loaded.");
		wordList = new String[number_of_words];

		try {
			while (scan.hasNext()) {
				wordList[i] = new String(scan.next());
				i++;
			}

		} catch (RuntimeException e) {
			System.err.println(e.getMessage());
			System.exit(-1);
		}
	}

	public boolean isValidWord(String word) {
		return false;
	}

	public static boolean mayBeComposed(String word, char[] letters) {

		word = new String(replaceFrenchCharacter(word.toLowerCase()));
		liste.clear();
		for (char c : letters) {
			liste.add(c);
		}
		
		for (int i = 0; i < word.length(); i++) {
			if (!liste.contains(word.charAt(i))) {
				
				if (!liste.contains('*')) {
					return false;
				
				} else {						
						liste.remove(liste.indexOf('*'));				
				}
			
			} else {
				liste.remove(liste.indexOf(word.charAt(i)));

			}
		}
		return true;
	}

	public static String replaceFrenchCharacter(String s) {

		// StringBuffer sb = new StringBuffer(s);

		for (int i = 0; i < s.length(); i++) {

			switch (s.charAt(i)) {
			case 'à':
			case 'â':
			case 'ä':
				s = s.replace(s.charAt(i), 'a');
				// sb.replace(i, i+1, "a");
				break;
			case 'é':
			case 'è':
			case 'ê':
			case 'ë':
				s = s.replace(s.charAt(i), 'e');
				// sb.replace(i, i+1, "e");
				break;
			case 'ç':
				s = s.replace(s.charAt(i), 'c');
				// sb.replace(i, i+1, "c");
				break;
			case 'î':
			case 'ï':
				s = s.replace(s.charAt(i), 'i');
				// sb.replace(i, i+1, "i");
				break;
			case 'ô':
			case 'ö':
				s = s.replace(s.charAt(i), 'o');
				// sb.replace(i, i+1, "o");
				break;
			case 'ù':
			case 'ü':
			case 'û':
				s = s.replace(s.charAt(i), 'u');
				// sb.replace(i, i+1, "u");
				break;
			case 'œ':
				s = s.replaceAll("œ", "oe");
				// sb.replace(i, i+1, "oe");
				break;
			case 'æ':
				s = s.replaceAll("æ", "ae");
				break;
			default:
				break;
			}
		}

		return s;
	}

	public LinkedList<String> getWordsThatCanBeComposed(char[] letters) {
		LinkedList<String> mot_possible;
		mot_possible = new LinkedList<String>();
		for (String word : wordList) {
			if (this.mayBeComposed(word, letters)) {
				mot_possible.add(word);
			}
		}
		return mot_possible;
	}
	
	public char[] getCompositon(String word, char[] letters){
		
		word = Dictionary.replaceFrenchCharacter(word);
		//System.out.println(word);
		char[] tab = new char[word.length()];
		boolean[] isUsed = new boolean[letters.length];
		Arrays.fill(isUsed, false);
		int test = 0;
		
		for(int i = 0; i < word.length(); i++){
				for (int j = 0 ; j < letters.length; j++) {
					if(word.charAt(i) == letters[j] && letters[j] != '*'){
						isUsed[j] = true;
						break;
					}
				}
		}
		
		
		
		for(int i = 0; i < word.length(); i++){
			if(isUsed[i] == true){
				tab[i] = letters[i];
			}else{
				tab[i] = '*';
				test++;
			}
		}
		
		if(test == word.length()){
			System.out.println("mort");
			return null;
		}
		return tab;
	}
}
