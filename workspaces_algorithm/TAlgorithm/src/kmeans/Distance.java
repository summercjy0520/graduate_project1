package kmeans;

public class Distance implements Comparable<Distance> {  
    private Point source;  
    private Point dest;  
    private double dis;  
    private AbstractDistance distance;  
  
    public Distance(Point source, Point dest, AbstractDistance distance) {  
        this.source = source;  
        this.dest = dest;  
        this.distance = distance;  
        dis = distance.getDis(source, dest);  
    }  
  
    public Point getSource() {  
        return source;  
    }  
  
    public Point getDest() {  
        return dest;  
    }  
  
    public double getDis() {  
        return dis;  
    }  
  
    @Override  
    public int compareTo(Distance o) {  
        if (o.getDis() > dis)  
            return -1;  
        else  
            return 1;  
    }  
}