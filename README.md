# Assignment 4: Hashing Poetry


## Introduction:
I will create a program that will generate a new paragraph of poetry based on the patterns seen in previous
paragraphs.

I will use a bi-word model of English to generate a new paragraph. A uni-word model of English consists of single 
probability distribution P(W) over a set of words. A bi-word model consists of two probability distributions:
P(W) and P(W<sub>i+1</sub>|W<sub>i</sub>). The first distribution is the probability of the word in the document,
the second distribution is the probability of seeing word W<sub>i+1</sub> give that the previous
word was W<sub>i</sub>.

### Example:
Apple Mango Apple Mango Apple  
P(W)= P(Apple)=3/5  
P(W<sub>i+1</sub>|W<sub>i</sub>)=P(Mango)=2/5=P(Apple Mango)/P(Apple)

## Pseudocode(Phase 1):

### Example output:

The value associated with one is: 1         
The value associated with two is: 2     
The value associated with three is: 3       
The value associated with four is: 4        
The value associated with five is: 5        
The value associated with six is: 6     
15: four[4]     
16: three[3]        
24: five[5]     
35: two[2]      
63: six[6]        
92: one[1]      
### HashTable CLASS:
    //Change to take two generic parameters(key,value)
### HashEntry CLASS:
    //Change to take two generic parameters(key,value)
### HashTable CLASS:
    //Change to take two generic parameters
#### public x find(K x) {
    int currentPos = findPos(x);
        if (!isActive(currentPos)) {
            return null;
        } else {
            return storage[currentPos].value;
        }
}
## Pseudocode(Phase 2):
### WritePoetry CLASS:
#### public String writePoem(String file, String startWord, int length, boolean printHashtable){
    // file: document to read
    // startWord: first word to generate
    // length: how many words to generate
    // printHashtable: print out hash table if true
    File file = filename;
    String[] writtenPoem = new String[];
    String fileString = fileReader(file);
    if(startWord is !, ?, ., ,){
        startWord+=\n;
    }
    if(last word is not !, ?, ., ,){
        last word += .;
    }
}
#### private HashTable tableBuilder(String file, int count){
    // remove all \n from file.
    // remove all " " from file == String array.
    // for(int i=0; i< count; i++){
        word.split("," or "." or "!" or "?");
        if array[i] is not in table{
            WordFreqInfo info = new WordFreqInfo(array[i], 0);
            table.insert(array[i], info)
        }else{
            WordFreqInfo info = table.find(array[i]);
        }
    }
    
}
#### private String poemBuilder()
### WordFreqInfo CLASS:
#### public String getFollowWord(int count){
    // count: randomly generated during poem generation 0 - (n-1)
    // n: number of times a word follows the key from WordFreqInfo
    // retrieve followList from info
    // int prevCount = 0;
    // for word in followList{
        if(word.followCount>=count && count> prevCount){
            return word;
        }
        prevCount+=word.followCount;
    }
}