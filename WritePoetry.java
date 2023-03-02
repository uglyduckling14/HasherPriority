import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
        String[] split = ogPoem.split("\n");
        HashTable<String, WordFreqInfo> table = new HashTable<>();
        for(String sentence:split){
            String[] secondSplit = sentence.split(" ");
            table = tableBuilder(secondSplit,startWord,table);
        }

        if(printHashTable){
            System.out.println(table.toString(length));
        }
        return " ";
    }
    private HashTable<String, WordFreqInfo> tableBuilder(String[]sentence,String startWord,HashTable<String,WordFreqInfo> table ){
        if(sentence.length==0||!contains(sentence,startWord)){
            return table;
        }
        int index = getIndex(sentence, startWord);
        if(startWord.contains(".")||startWord.contains(",")||startWord.contains("?")||startWord.contains("!")){
            String temp = startWord.substring(startWord.length()-1);
            startWord = startWord.substring(0, startWord.length()-1);
            if(table.find(startWord)==null){
                WordFreqInfo info = new WordFreqInfo(sentence[index],1);
                table.insert(startWord.toLowerCase(),info);
                info.updateFollows(temp);
            }
            return tableBuilder(sentence,temp,table);
        }
        if(table.find(startWord)==null){
            WordFreqInfo info = new WordFreqInfo(sentence[index],1);
            table.insert(startWord,info);
            info.updateFollows(sentence[index+1].toLowerCase());
            return tableBuilder(sentence, sentence[index+1], table);
        }else{
            WordFreqInfo info = table.find(startWord);
            info.occurCount++;
            info.updateFollows(sentence[index+1].toLowerCase());
            table.insert(startWord.toLowerCase(),info);
            return tableBuilder(sentence,sentence[index+1],table);
        }
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
        for(int i =0; i< sentence.length; i++){
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
