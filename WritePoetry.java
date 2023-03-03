import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
public class WritePoetry {
    String file;
    String startWord;
    int length;
    boolean printHashTable;

    public WritePoetry(){
        file="";
        startWord="";
        length=0;
        printHashTable=false;
    }

    public String writePoem(String file, String startWord, int length, boolean printHashTable){
        String ogPoem = fileReader(file);
        //ogPoem = ogPoem.replace("\n","");
        HashTable<String, WordFreqInfo> table = new HashTable<>();
        String[] sentences = ogPoem.split("(?=[.,!?;:])|\\s");
        int index = getIndex(sentences, startWord);
        //System.out.println(Arrays.toString(sentences));
        for(int i =index; i<length; i++){
           if (table.find(sentences[i].toLowerCase()) == null) {
               WordFreqInfo info = new WordFreqInfo(sentences[i].toLowerCase(), 1);
               table.insert(sentences[i].toLowerCase(), info);
               info.updateFollows(sentences[i + 1].toLowerCase());
           } else {
               WordFreqInfo info = table.find(sentences[i].toLowerCase());
               table.insert(sentences[i].toLowerCase(), info);
               info.updateFollows(sentences[i + 1].toLowerCase());
           }
        }
        if(printHashTable){
            System.out.println(table.toString(length));
        }
        return " ";
    }
    private boolean contains(String[]sentence, String word){
        for(String test:sentence){
            if(test.equalsIgnoreCase(word)){
                return true;
            }
        }
        return false;
    }
    private int getIndex(String[]sentence, String word){
        for(int i =0; i< sentence.length-1; i++){
            if(sentence[i].equalsIgnoreCase(word)){
                return i;
            }else if(sentence[i].substring(0,sentence[i].length()-1).equalsIgnoreCase(word)){
                return i;
            }
        }
        return -1;
    }
    private String fileReader(String file){
        String fileArray ="";
        try{
            File fileObj = new File(file);
            Scanner reader = new Scanner(fileObj);
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                fileArray+=line+"\n";
            }
        }catch(FileNotFoundException e){
            System.out.println("File Not Found");
            e.printStackTrace();
        }
        return fileArray;
    }
}
