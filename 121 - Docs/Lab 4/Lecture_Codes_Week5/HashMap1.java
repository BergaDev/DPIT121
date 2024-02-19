import java.util.*;

class Program
{
    
    public static void main(String[] args) 
    {
        String [] colors={"red","blue","Red","blue","black","orange","red","blue","red"};
//        
//        ArrayList<String> names=new ArrayList<String>();
//        
//        for (int i=0;i<colors.length;i++) //Populate the list of distint colors
//        {
//            Boolean found=false;
//            for (String name: names) 
//            {
//               if (name.compareToIgnoreCase(colors[i])==0) 
//               {
//                   found=true;
//                   break;
//               }
//            }
//            if(!found)
//            {
//                names.add(colors[i]);
//            }
//        }
//        for (String name: names)
//        {
//            System.out.println(name);
//        }
//        
//        ArrayList<Integer> counts=new ArrayList<Integer>();
//        
//        for(String name:names) // data aggregation
//        {
//            int count=0;
//            for(int i=0;i<colors.length;i++)
//            {
//                if(name.compareToIgnoreCase(colors[i])==0)
//                {
//                    count++;   
//                }
//            }
//            counts.add(count);
//        }
//        
//        for ( int i=0;i<names.size();i++)
//        {
//           System.out.println(names.get(i)+ "\t"+counts.get(i)); 
//        }
//        
            
        
        HashMap<String,Integer> colorCount=new HashMap<String,Integer>();
        for ( int i=0;i<colors.length; i++) //data aggregation by using hashmap
        {
            String color=colors[i].toLowerCase();
            if (colorCount.get(color)==null)
                colorCount.put(color,1);
            else
                colorCount.put(color,colorCount.get(color)+1);
        }
        for (String color:colorCount.keySet())
        {
            System.out.println(color+ " "+colorCount.get(color));
        }
        
    }
}