package datamining_cart;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Value{
private int id;
private int parent;
private int cost;
public Value(int id,int parent,int cost) {
this.id = id;
this.parent = parent;
this.cost = cost;
// TODO Auto-generated constructor stub
}
public int getParent() {
return parent;
}
public void setParent(int parent) {
this.parent = parent;
}
public int getCost() {
return cost;
}
public void setCost(int cost) {
this.cost = cost;
}
public int getId() {
return id;
}
public void setId(int id) {
this.id = id;
}
}
public class Main {
static Map map = new HashMap();
public static void main(String[] args) {


ArrayList _ids = new ArrayList();
ArrayList _parents = new ArrayList();
ArrayList _costs = new ArrayList();

Scanner in = new Scanner(System.in);
String line = in.nextLine();


while(line != null && !line.isEmpty()) {
if(line.trim().equals("0")) break;
String []values = line.trim().split(" ");
if(values.length != 3) {
break;
}
_ids.add(Integer.parseInt(values[0]));
_parents.add(Integer.parseInt(values[1]));
_costs.add(Integer.parseInt(values[2]));
Value value = new Value(Integer.parseInt(values[0]),Integer.parseInt(values[1]),Integer.parseInt(values[2]));
map.put(Integer.parseInt(values[0]), value);
line = in.nextLine();
}
int res = resolve(_ids, _parents, _costs);


System.out.println(String.valueOf(res));
}


// write your code here
public static int resolve(ArrayList ids, ArrayList parents, ArrayList costs) {
int max = 0;
for (int i = 0; i < ids.size(); i++) {
int groupMax = getMax((Value) map.get(ids.get(i)));
max = groupMax>max?groupMax:max;
}
return max;
}
//递归求取从每个节点到根节点路径值
public static int getMax(Value value){
int parent = value.getParent();
if(parent==0){
return value.getCost();
}
return getMax((Value) map.get(parent))+value.getCost();
}
} 