import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ScrabbleGUI implements ActionListener {

	private Dictionary dico;
	private JTextField letterTextField;
	private JButton searchButton;
	private JTextArea wordListTextArea;
	JFrame window;

	public ScrabbleGUI(){

		// Mise en place de la fenetre
		window = new JFrame("Scrabble GUI");
		// Mise en place du container
		Container c = window.getContentPane();
		c.setLayout(new BorderLayout());
		
		try {
			dico = new Dictionary("dictionnaire.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			
		}

		letterTextField = new JTextField(50);
		searchButton = new JButton("Search");
		searchButton.setActionCommand("search");
		searchButton.addActionListener(this);
		wordListTextArea = new JTextArea();

		// Creation du panel p1
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		p1.add(letterTextField);
		p1.add(searchButton);
		c.add(p1,BorderLayout.NORTH);
		

		// Creation du panel p2
		JPanel p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		p2.add(wordListTextArea, BorderLayout.CENTER);
		c.add(p2,BorderLayout.CENTER);
		
		// Mise en place du scrollPane
		JScrollPane scroll = new JScrollPane(wordListTextArea);
		p2.add(scroll);
		
		// Default configuration of the window
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.window.setMinimumSize(new Dimension(1080, 640));

		// Resizing the windows considering the new elements
		this.window.pack();

		// Showing the windows
		this.window.setVisible(true);
	}

	public static void main(String[] args) {
		new ScrabbleGUI();

	}

	public StringBuilder place (LinkedList<String> liste){
		
		StringBuilder sb = new StringBuilder();
		System.out.println(sb.capacity());
		int size = 0;
		char [] tab;
		for (String mot : liste) {
			sb.append(mot).append("[");
			tab = dico.getCompositon(mot, mot.toCharArray());
			
			if(tab==null){System.out.println("NUll");}
			else{System.out.println(tab.toString());}
			
			//size = (tab == null) ? 0 : tab.length;
			/*for(int i = 0; i < size; i++){
				sb.append(tab[i] + ", ");
			}
			sb.append("]\n");*/
			
		}
		return sb;
			
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String text;
		if(arg0.getActionCommand().contentEquals("search")){
			text = letterTextField.getText();			
			wordListTextArea.append(dico.getWordsThatCanBeComposed(text.toCharArray()).size()+" words found\n");
			ScrabbleComparator sc = new ScrabbleComparator(text.toCharArray());
			Collections.sort(dico.getWordsThatCanBeComposed(text.toCharArray()), sc);
			//this.place(dico.getWordsThatCanBeComposed(text.toCharArray()));
			wordListTextArea.append(this.place(dico.getWordsThatCanBeComposed(text.toCharArray())).toString());
			//.append(" ]").append(sc.wordValue(text) + ")").append("\n")
			
		}		    		
	}
	
	
}