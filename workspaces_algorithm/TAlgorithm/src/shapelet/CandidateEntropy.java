package shapelet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CandidateEntropy {

	public static  double CheckCandidate(HashMap<String[],Integer> dataset, String[] Shapelet) {
		Set<String[]> keSet=dataset.keySet();  
		List<String[]> datalist=new ArrayList<String[]>();
		for (Iterator<String[]> iterator = keSet.iterator(); iterator.hasNext();) {  
	            String[] str = iterator.next(); 
	            datalist.add(str);
	        }
		 
		double[] objects_hist =new double[dataset.size()];
		HashMap<String[], Double> map = new HashMap<String[], Double>(); 
		for(int i=0;i<datalist.size();i++){
			objects_hist[i]=DistPruning.SubsequenceDist(datalist.get(i),Shapelet);
			map.put(datalist.get(i), objects_hist[i]);
		}
		
		double Gain=CalculateInformationGain(dataset,datalist,map);
		return Gain;
	}

	public static double CalculateInformationGain(HashMap<String[], Integer> dataset, List<String[]> datalist, HashMap<String[], Double> map) {
		List<Double> re=getdouble(map);
		List<Integer> re1=getInteger(dataset);
		double split_dist=OptimalSplitPoint(dataset, re);
		
		HashMap<String[], Integer> D1 = new HashMap<String[], Integer>(); 
		HashMap<String[], Integer> D2 = new HashMap<String[], Integer>(); 
		
		for (int i=0; i<re.size();i++){
			if (re.get(i)<split_dist){
				D1.put(datalist.get(i),re1.get(i));
			}else{
				D2.put(datalist.get(i), re1.get(i));
			}
		}
		double Gain=ComputeEntropyGain(dataset,D1,D2);
		return Gain;
	}

	private static List<Integer> getInteger(HashMap<String[], Integer> dataset) {	
		Collection<Integer> values1 = dataset.values() ;// 得到全部的value
		Iterator<Integer> iter1= values1.iterator() ;
		List<Integer> re1 =new ArrayList<Integer>();
		while(iter1.hasNext()){
			re1.add(iter1.next());
		}
		return re1;
	}

	private static List<Double> getdouble(HashMap<String[], Double> map) {
		Collection<Double> values = map.values() ;// 得到全部的value
		Iterator<Double> iter = values.iterator() ;
		List<Double> re =new ArrayList<Double>();
		while(iter.hasNext()){
			re.add(iter.next());
		}
		return re;
	}

	private static double ComputeEntropyGain(HashMap<String[], Integer> dataset, HashMap<String[], Integer> D1,
			HashMap<String[], Integer> D2) {
		List<Integer> re0=getInteger(dataset);
		List<Integer> re1=getInteger(D1);
		List<Integer> re2=getInteger(D2);
		
		int m0=0,m1=0,m2=0;
		for (int i=0;i<re0.size();i++){
			if (re0.get(i)==1)
				++m0;		
		}
		for (int i=0;i<re1.size();i++){
			if (re1.get(i)==1)
				++m1;		
		}
		for (int i=0;i<re2.size();i++){
			if (re2.get(i)==1)
				++m2;		
		}
		
		double entropy0=-(m0/re0.size()) * Math.log(m0/re0.size())-((re0.size()-m0)/re0.size())*Math.log((re0.size()-m0)/re0.size()) ;
		double entropy1=-(m1/re1.size()) * Math.log(m1/re1.size())-((re1.size()-m1)/re1.size())*Math.log((re1.size()-m1)/re1.size()) ;
		double entropy2=-(m2/re2.size()) * Math.log(m2/re2.size())-((re2.size()-m2)/re2.size())*Math.log((re2.size()-m2)/re2.size()) ;

		double Gain=entropy0-(m1+m2)* entropy1/re0.size()-(re1.size()+re2.size()-m1-m2)* entropy2/re0.size();
		
		return Gain;
	}

	private static double OptimalSplitPoint(HashMap<String[],Integer> dataset,List<Double> re) {
		double[] Gainlist = new double[re.size()];
		for (int i = 0; i < re.size(); i++){
//			Gainlist[i] = CalculateInformationGain(dataset,re,re.get(i));
		}
		return 0;
	}



}
