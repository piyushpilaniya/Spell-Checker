import java.io.*;
import java.io.IOException;
import java.util.*;
import java.util.zip.*;


public class read {

static final int BUFFER = 2048;

public static int zip(String d,String f){
int target=0;
try {
         BufferedOutputStream dest = null;
         BufferedInputStream is = null;
         ZipEntry entry;
         ZipFile zipfile = new ZipFile(d);
         Enumeration e = zipfile.entries();
         while(e.hasMoreElements()) {
            entry = (ZipEntry) e.nextElement();
            //System.out.println("Extracting: " +entry);
            is = new BufferedInputStream
              (zipfile.getInputStream(entry));

Scanner s = new Scanner(is).useDelimiter(" ");
String result = s.hasNext() ? s.next() : "";
//System.out.print(result+" ");


StringTokenizer t = new StringTokenizer(result);
String word ="";
while(t.hasMoreTokens())
{
    word = t.nextToken();
String word1 = word + ',';
String word2 = word + '.';
    //System.out.println(word);
   if(word.equals(f)==true || word1.equals(f)==true || word2.equals(f)==true){target++;}
}


            int count;
            byte data[] = new byte[BUFFER];
            FileOutputStream fos = new 
              FileOutputStream(entry.getName());
            dest = new 
              BufferedOutputStream(fos, BUFFER);
            while ((count = is.read(data, 0, BUFFER)) 
              != -1) {
               dest.write(data, 0, count);
            }
            dest.flush();
            dest.close();
            is.close();
         }
      } catch(Exception e) {
         e.printStackTrace();
      }
return target;
}



public static void main (String argv[]) {
String a = argv[0];
int icw=0; 
int nofw=0;
int nofl=0;       
System.out.println("Words with errors : ");
BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(argv[1]));
			String line = reader.readLine();
                        
			while (line != null) {nofl++;
				StringTokenizer t = new StringTokenizer(line);
String word ="";
        while(t.hasMoreTokens())
{
    word = t.nextToken();
nofw++;
int got = zip(a,word);
if(got==0){icw++;}
if(got==0){
// int fooo=nofl-1;
    System.out.println("Line #" + nofl + ":" + word);}
}
				
				line = reader.readLine();
                          			
}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

System.out.println("Checked "+ nofw + " words in " + nofl + " lines.");
System.out.println("Dictionary Used: " + argv[0]);
if(icw==0){System.out.println("No words with error");}
if(icw!=0){System.out.println(icw + " spelling errors were found.");}
    

}
  
}



