package shapelet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Shapelet_test {

	public static void main(String[] args) throws IOException {
		File file = new File("");
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String temp = null;
		ArrayList<String> data = new ArrayList<String>();
		temp=br.readLine();
		while(temp != null){
			data.add(temp);
			temp = br.readLine();
		}
		
		int maxlen = 275;
		int minlen = 3;
		HashMap<String[],Integer>  input = new HashMap<String[],Integer> ();
		String[] result =FindingShapeletBF.FindingShapeletbf(input,maxlen,minlen);
	}

}
