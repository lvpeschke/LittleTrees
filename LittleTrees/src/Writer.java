import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe permettant d'écrire dans un fichier de sortie
 * 
 * @author Groupe 10, Goéric Huybrechts
 */
public class Writer {
	
	static BufferedWriter output;
	
	/**
	   * Méthode permettant d'ouvrir un fichier de sortie
	   *  
	   * @pre  -
	   * @post Ouvre un fichier de sortie
	   * @exception IOException si erreur lors de l'ouverture du fichier
	   * 
	   */  
	public static void open(String outputFile) {		
		try {
			output = new BufferedWriter(new FileWriter (new File(outputFile)));
		} catch (IOException e) {
			System.out.print("Error opening outputfile\n");
		}	
	}
	
	/**
	   * Méthode permettant d'écrire dans le fichier de sortie déjà ouvert
	   *  
	   * @pre  Le fichier de sortie "output" doit déjà avoir été ouvert préalablement
	   * @post Ecrit dans un fichier de sortie
	   * @exception IOException si erreur lors de l'écriture dans le fichier
	   * 
	   */ 
	public static void write(String message) {		
		try {
			output.write(message);
		} catch (IOException e) {
			System.out.print("Error writing in outputfile\n");
		}	
	}
	
	/**
	   * Méthode permettant de fermer le fichier de sortie
	   *  
	   * @pre  Le fichier de sortie "output" doit déjà avoir été ouvert préalablement
	   * @post Ferme le fichier de sortie
	   * @exception IOException si erreur lors de la fermeture du fichier
	   * 
	   */ 
	public static void close() {		
		try {
			output.close();
		} catch (IOException e) {
			System.out.print("Error closing outputfile\n");
		}	
	}
	
	/**
	   * Méthode permettant d'écrire dans un fichier de sortie pas encore ouvert et de refermer celui-ci juste après
	   *  
	   * @pre  -
	   * @post Ouverture du fichier de sortie. Ecriture du String passé en argument dans le fichier de sortie.
	   *  Finalement fermeture du fichier.
	   * @exception IOException si erreur lors de l'ouverture, de l'écriture ou de la fermeture du fichier
	   * 
	   */ 
	public static void write(String outputFile, String message) {		
		try {
			output = new BufferedWriter(new FileWriter (new File(outputFile)));
			output.write(message);
			output.close();
		} catch (IOException e) {
			System.out.print("Error related to outputfile\n");
		}
	}
}
