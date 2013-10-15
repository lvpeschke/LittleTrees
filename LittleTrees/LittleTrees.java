/**
* The main method creates objects of MyReader, RFormalExpressionTree and Writer classes 
* Uses these objects respectively :
* to read mathematical expressions from an input file, 
* to compute the derivation of these expressions 
* and to finally write the result of the derivation to an output file.
* 
* An array list is used to store the derivation results 
* before being written to the output file.
*/
import java.io.*;
import java.util.ArrayList;
public class LittleTrees{
	
	
public static void main(String[] args){
// create an empty array list with an initial capacity
ArrayList<String> aList = new ArrayList<String>(11);

//Initializes the variable path to the input file.
String chemin = "\\LittleTrees\\src\\inputFile.txt";

//Initializes a string variable that stores the output file's name
String fichierSortie = "outputFile.txt";


/**
 *Initializes a variable that will hold
 *the result from the derivation tree
 * */
String result;

/**
 *Creates instances of MyReader, Writer classes
 * */
	
MyReader mainReader = new MyReader(chemin);
Writer mainWriter = new Writer();


String stringLine = mainReader.readLine();
while(!stringLine.equals("")) {
	//creates an object of RFormalExpressionTree class
	RFormalExpression derivationTree = new RFormalExpressionTree(stringLine);
	//gets the derivation results using StringExpression method from RFormalExpressionTree class
	result = derivationTree.StringExpression(derivationTree);//????
	//adds the derivation result to the array list aList
	aList.add(result);
	// Writes array list's elements to the output file
    for (String element : aList) {
    	mainWriter.write(fichierSortie,element);
    } 
	
}
mainWriter.close();
mainReader.close();
}
}

