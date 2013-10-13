import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MyReader {
	
	protected BufferedReader in;
	protected boolean reafy;
	protected boolean debug;

	public MyReader(String path){
		File texte = new File(ClassLoader.getSystemResource(path).getFile());
		FileReader fr = null;
		try{
			fr = new FileReader (texte);
		}catch(FileNotFoundException e) {System.out.println("Ce fichier n'existe pas \n");}
		if (texte.isFile()){
			in = new BufferedReader(fr);
			reafy = true;
			debug = false;
		}
		else {System.out.println("Le path n'est pas bon \n");}
	}
	

	
	public boolean isDebug() {
		return debug;
	}



	public void setDebug(boolean debug) {
		this.debug = debug;
	}



	public boolean isReady() {
		return reafy;
	}

	public char read(){
		char letter = (char) -1;
		try {
			letter =  (char) in.read();
		} catch (IOException e) {System.out.println("Problème de lecture\n");	}
		if(debug){System.out.println("Lecture de : " + letter);}
		return letter;
	}
	
	public char readLetter(){
		char letter = (char) -1;
		if(reafy){			
				letter = read();					
		}
		if (letter == -1) {System.out.println("Letter = -1\n");}
		return letter;
	}
	
	public String readNextWord(){
	String word = "error";

	if(reafy){
		word = "";
		char letter = skipSeparator();
		
		for (; (letter != ' ') && (letter != (char) -1) && (letter != '\n');letter = read()){
			word = word + letter;
		}
		
	}	
	return word;
	}
	
	public String readLine(){
		String line = "error";
		if(reafy){
			line = "";
			try {
				line = in.readLine();
			} catch (IOException e) {System.out.println("Problème de lecture");	}
		}
		return line;
	}
	
	/*
	 * Lis le fichier in jusqu'à tomber sur un caractère visible et renvoie ce caratère.
	 * retourne -1 si la lecture a connu des problèmes
	 */
	public char skipSeparator(){
		char letter = (char) -1;
		for (letter = read(); (letter == ' ') || (letter == '\n'); letter = read()){}
		return letter;
	}
	
	public void close(){
		try {
			in.close();
			reafy = false;
		} catch (IOException e) {System.out.println("Le fichier n'a pas pu etre fermé");}
	}
	
}

