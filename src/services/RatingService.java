package services;

import model.Message;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RatingService {
    private List<Message> messageList;
    private String allMessages;
    private Map<Integer, String> topWords;
    private Map<Integer, String> topMessages;
    private String userName;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
    public void getRatingMessages(String allMessages) {
        this.allMessages = allMessages;
        ratingWords();
        swapCountToWeight();
        setTopList();
        showTopMessages();

    }

    public List<Message> getMessageList() {
        return messageList;
    }

    private void showTopMessages() {
        for (Map.Entry entry : topMessages.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
        }
    }

    public Map<Integer, String> getTopWords() {
        return topWords;
    }

    private void ratingWords() {

        for (Message message : messageList) {
            if (message.getSender().equals(userName)) {
                allMessages += message.getMessage();
            }
        }

        String[] allWords = new String[10];
        Matcher matcher = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS).matcher(allMessages);
        int counter = 0;
        while (matcher.find()) {
            allWords[counter++] = matcher.group();
            allWords = resizeArray(allWords, counter);
        }
        String[] fullFilledArray = new String[counter];
        System.arraycopy(allWords, 0, fullFilledArray, 0, fullFilledArray.length);//discard nulls
        frequentlyWord(fullFilledArray);
    }


    private void frequentlyWord(String [] array) {
        if(array.length == 0) {
            throw new IllegalStateException();
        }
        Arrays.sort(array);
        int [] fqArray = new int[array.length];
        Arrays.fill(fqArray, -1);
        int fq = 0;
        int max = 0;
        for (int i = 0; i < array.length - 1; i++) {
            if(array[i].equals(array[i+1])) {
                fq++;
            } else {
                fqArray[i] = fq + 1;
                if(max < fq) {
                    max = fq;
                    fq = 0;
                }
            }
        }

        Map<Integer, String> hashmap = new HashMap<Integer, String>();

        for (int i = 0; i < array.length; i++) {
            if(fqArray[i] != -1) {
                hashmap.put(fqArray[i],  array[i]);
            }
        }
        hashmap = new TreeMap<Integer, String>(hashmap);
        hashmap.putAll(hashmap);
        this.topWords = hashmap;

    }
    private String [] resizeArray(String [] array, int currentIdx) {
        if(currentIdx >= array.length) {
            String [] newArray = new String[array.length + array.length/2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            return newArray;
        }
        return array;
    }

    private void setTopList(){
        int count = 0;

        for (Message message : messageList) {
            if (message.getSender().equals(userName)) {
                for(Map.Entry entry : topWords.entrySet()) {
                    if(message.getMessage().indexOf(entry.getValue().toString()) != -1) {
                        count += (int) entry.getKey();
                    }
                }
                topMessages.put(count, message.getMessage());
                count = 0;
            }

        }
    }

    private void swapCountToWeight(){
        int i = 0;
        String[] s = new String[10];
        for (Map.Entry entry : topWords.entrySet()) {
            s[i] = entry.getValue().toString();
            i++;
            if (i == 10) break;
        }

        Map<Integer, String> tmp = new HashMap<Integer, String>(); //вспомогательная

        i=1;
        for (String word : s) {
            tmp.put(i, word);
            i++;
        }

        topWords = tmp; // тут слова имеют вес, вместо счётчика
    }



}
