package shapelet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DistPruning {
	static double SubsequenceDist(String[] T, String[] shapelet) {
		int n=T.length;
		int m=shapelet.length;
		double[] Disttemp =new double[n-m+1];
		for (int i=0; i<Disttemp.length; i++){
			String[] Ttemp= new String[m];
			for (int j=0;j<m;j++){
				Ttemp[j]=T[i];
			}
			Disttemp[i]=sim_distance(Ttemp,shapelet);
		}
		doubleSort(Disttemp);		
		return Disttemp[0];
}
	static double SubsequenceDistanceEarlyAbandon(double[] T, double[] shapelet) {
		double min_dist=Double.MAX_VALUE;
		int n=T.length;
		int m=shapelet.length;
		boolean stop=false;
		for (int i=0; i<n-m+1; i++){
			double sum_dist=0;
			double[] Tr = new double[m];
			for(int h=0; h<n; h++){
				Tr[h]=T[h+m-1];
			}
			for (int j=0;j<m; j++){
				sum_dist=sum_dist+Math.pow(((T[j]) -( shapelet[j])), 2);
				if (sum_dist>=min_dist){
					stop=true;
				break;
				}
			}
		
		if ( stop==false){
				min_dist=sum_dist;
				}
		}
		return min_dist;
}


	 private static void doubleSort(double[] disttemp) {
		double[] newdist=new double[disttemp.length];
		newdist[0]=disttemp[0];
		for (int i=1;i<disttemp.length;i++){
			if (disttemp[i]<disttemp[i-1]){
				double temp=newdist[i-1];
				newdist[i-1]=disttemp[i];
				newdist[i]=temp;
			}else
				newdist[i]=disttemp[i];
		}
	}

	public static double sim_distance(String[] ttemp, String[] shapelet) {  
	        double distance = 0;  
	          
	        if (ttemp.length == shapelet.length) {  
	            for (int i = 0; i < ttemp.length; i++) {  
	                double temp =Math.pow((Double.valueOf(ttemp[i]) -Double.valueOf( shapelet[i])), 2);  
	                distance += temp;  
	            }  
	            distance = Math.sqrt(distance);  
	        }  
	        return distance;  
	    }
	
	
	public static boolean EntropyEarlyPrune (HashMap<String[],Integer> dataset,List<String[]> datalist, double bsf_gain,List<Double> dist_hist,
			List<String[]> cA,List<String[]>  cB){
		boolean bl =true;
		double max=getMax(dist_hist);
		List<String[]> pre_dist_hist=new ArrayList<String[]>();
		
		HashMap<String[], Double> map = new HashMap<String[], Double>(); 
		for(int i=0;i<cA.size();i++){
			map.put(cA.get(i),(double)0);
		}
		
		for(int i=0;i<datalist.size();i++){
			map.put(datalist.get(i),dist_hist.get(i));
		}
		
		for(int i=0;i<cB.size();i++){
			map.put(cB.get(i),max);
		}
		
		pre_dist_hist.addAll(cA);
		pre_dist_hist.addAll(datalist);
		pre_dist_hist.addAll(cB);
		
		if (CandidateEntropy.CalculateInformationGain(dataset,pre_dist_hist,map)>bsf_gain)
			bl=false;
		
       List<String[]> pre_dist_hist2=new ArrayList<String[]>();
		
		HashMap<String[], Double> map2 = new HashMap<String[], Double>(); 
		for(int i=0;i<cB.size();i++){
			map2.put(cB.get(i),(double)0);
		}
		
		for(int i=0;i<datalist.size();i++){
			map2.put(datalist.get(i),dist_hist.get(i));
		}
		
		for(int i=0;i<cB.size();i++){
			map2.put(cB.get(i),max);
		}
		
		pre_dist_hist2.addAll(cB);
		pre_dist_hist2.addAll(datalist);
		pre_dist_hist2.addAll(cA);
		
		if (CandidateEntropy.CalculateInformationGain(dataset,pre_dist_hist2,map2)>bsf_gain)
			bl=false;
		return bl;
	}
	
    public static double getMax(List<Double> arr) {  
        
        double max = arr.get(0);  
//        int index=0;  
        for (int x = 1; x < arr.size(); x++) {  
            if (arr.get(x) > max)  {
                max = arr.get(x);  
//                index=x;
            }
        }
        return max;  
    } 
}
