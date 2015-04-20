import java.util.Comparator;

import org.omg.CORBA.CharSeqHolder;

class ScrabbleComparator implements Comparator<String> {

	
	char[] letters;
	
	public ScrabbleComparator(char[] letters){
		this.letters = letters;		
	}
	public static int letterValue(char letter) {

		switch (letter) {

		case '*':
			return 0;
		case 'e':
		case 'a':
		case 'i':
		case 'n':
		case 'o':
		case 'r':
		case 's':
		case 't':
		case 'u':
		case 'l':
			return 1;
		case 'd':
		case 'm':
		case 'g':
			return 2;
		case 'b':
		case 'c':
		case 'p':
			return 3;
		case 'f':
		case 'h':
		case 'v':
			return 4;
		case 'j':
		case 'q':
			return 8;
		case 'k':
		case 'w':
		case 'x':
		case 'y':
		case 'z':
			return 10;

		}
		return -1;
	}
	
	public static int lettersValue(char[] letters){
		int value = 0;
		// static devant une methode alors pour appeler cette méthode on l'appele directement et depuis une autre classe on appelle en mettant la classe qui contient la méthode statique.nom_methode, tu ne pe pas associer avec l'instance courante this
		
		for (char c : letters) {
			value += letterValue(c);
		}
		
		return value;
	}
	
	public int wordValue(String word){
		
		int word_value = 0;
		
		for(int i = 0; i < word.length(); i++){
			
			word_value += word.charAt(i);
			
		}
		return word_value;
	}

	@Override
	public int compare(String o1, String o2) {
		// TODO Auto-generated method stub
		if(this.wordValue(o1) > this.wordValue(o2)){
			return -1;
		}else if(this.wordValue(o1) == this.wordValue(o2)){
			return 0;
		}	
			return 1;
	}
}