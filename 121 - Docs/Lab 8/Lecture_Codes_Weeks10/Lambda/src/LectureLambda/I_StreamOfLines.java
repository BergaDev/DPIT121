package LectureLambda;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class I_StreamOfLines 
{
   public static void main(String[] args) throws IOException 
   {
      
      // Regex that match one or more consecutive whitespace characters
      Pattern p = Pattern.compile("\\s+");
      // count occurrences of each letter in a Stream<String> sorted by word
      Map<String, Long> wordCounts = 
         Files.lines(Paths.get("input.txt"))
              .map(line -> line.replaceAll("(?!'):\\p{P}", ""))
              .flatMap(line->p.splitAsStream(line))
              .collect(Collectors.groupingBy(String::toLowerCase,
                 TreeMap::new, Collectors.counting()));
 
      // display the letters grouped by starting letter
      wordCounts.entrySet()
         .stream()
         .collect(Collectors.groupingBy(e->e.getKey().charAt(0),
                 TreeMap::new, Collectors.toList()
                 ))
         .forEach((l,w) -> { 
               System.out.printf("%n %C %n",l);
               w.stream().forEach(wd->
               System.out.printf("%13s: %d%n", wd.getKey(),wd.getValue())       
               );            
         }); 
   }
}
