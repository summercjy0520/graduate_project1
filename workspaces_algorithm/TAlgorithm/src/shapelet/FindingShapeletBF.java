package shapelet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class FindingShapeletBF {
	
	public static  String[] FindingShapeletbf(HashMap<String[],Integer> dataset,int MAXLEN,int MINLEN){
		 Set<String[]> keSet=dataset.keySet();  
		 List<String[]> datalist=new ArrayList<String[]>();
	        for (Iterator<String[]> iterator = keSet.iterator(); iterator.hasNext();) {  
	            String[] str = iterator.next(); 
	            datalist.add(str);
	        }
		
		List<String[]> candidates=GenerateCandidate(datalist,MAXLEN,MINLEN);
		double bsf_gain=0;
		String[] bsf_shapelet=null;
		for (int i=0;i<candidates.size();i++){
			double gain=CandidateEntropy.CheckCandidate(dataset,candidates.get(i));
			if (gain > bsf_gain){
				bsf_gain=gain;
				bsf_shapelet=candidates.get(i);
			}
		}
		return bsf_shapelet;
	}


	private static List<String[]> GenerateCandidate(List<String[]> datalist, int MAXLEN, int MINLEN) {
		List<String[]> pool=new ArrayList<String[]>();
		double l=MAXLEN;
		while(l>MINLEN){
			for (int i=0; i<datalist.size(); i++){
				String[] re=datalist.get(i);
				for (int j=0; j<re.length-l+2;j++){
					pool.add(re);
				}
			}
			l--;
		}	
		return pool;
	}
}
