import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
        HashTable<String, WordFreqInfo> table = new HashTable<>();
        String[] sentences = ogPoem.split("(?=[.,!?;:])|\\s+");
        //System.out.println(Arrays.toString(sentences));
        for(int i =0; i< sentences.length-1; i++){
           if (table.find(sentences[i].toLowerCase()) == null) {
               WordFreqInfo info = new WordFreqInfo(sentences[i].toLowerCase(), 0);
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
        WordFreqInfo info = table.find(startWord);
        StringBuilder newPoem = new StringBuilder();
        Random rand = new Random();
        newPoem.append(" "+startWord);
        for(int i =0; i<length;i++){
            int count = rand.nextInt(info.getOccurCount()-1);
            String follow =info.getFollowWord(count);
            if(follow.contains(".")||follow.contains(",")||follow.contains("!")||follow.contains("?")){
                follow+=" \n";
            }else{
                follow= " "+follow;
            }
            newPoem.append(follow);
            info = table.find(info.getFollowWord(count));
        }
        if(!Objects.equals(newPoem.substring(newPoem.length() - 1), ".") || !Objects.equals(newPoem.substring(newPoem.length() - 1), ",") || !Objects.equals(newPoem.substring(newPoem.length() - 1), "!") || !Objects.equals(newPoem.substring(newPoem.length() - 1), "?")){
            newPoem.append(".");
        }
        return newPoem.toString();
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
