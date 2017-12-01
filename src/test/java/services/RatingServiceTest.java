package services;

import model.Message;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;

public class RatingServiceTest {
    private final String allMessages = "";
    private final List<Message> messageList1= new LinkedList();
    private final Map<Integer, String> topWordsExp = new HashMap<>();
    private final Map<Integer, String> topMessagesExp = new HashMap<>();
    private final String userName = "one";

    @Before
    public void setValue () {
        messageList1.add(new Message(" привет как дела. привет, приветик, ходил сегодня на работу и такой дай думаю зайду куда-то, зашёл в магазин ", "one", "two", (byte)1));
        messageList1.add(new Message("дела у меня идут хорошо привет  кусать лежать лежать лежать лежать лежать, объект объект объект ", "one", "two", (byte)1));
        messageList1.add(new Message("объект объект ы объект объект ы солнце солнце ", "one", "two", (byte)1));
        topWordsExp.put(1, "на");
        topWordsExp.put(2, "дела");
        topWordsExp.put(3, "сегодня");
        topWordsExp.put(4, "хорошо");
        topWordsExp.put(5, "лежать");
        topWordsExp.put(6, "объект");
        topMessagesExp.put(16, "объект объект ы объект объект ы солнце солнце ");
        topMessagesExp.put(5, " привет как дела. привет, приветик, ходил сегодня на работу и такой дай думаю зайду куда-то, зашёл в магазин");
        topMessagesExp.put(29, "дела у меня идут хорошо привет  кусать лежать лежать лежать лежать лежать, объект объект объект ");

    }

    @Test
    public void testTopWords(){
        RatingService ratingService = new RatingService();
        ratingService.setMessageList(messageList1);
        ratingService.setUserName(userName);
        ratingService.getRatingMessages(allMessages);

        Map<Integer, String> topWords = ratingService.getTopWords();
        for (Map.Entry entry1 : topWords.entrySet()) {
            for(Map.Entry entry2 : topWordsExp.entrySet())
            assertEquals("Error", entry1.getValue(), entry2.getValue());
            continue;
        }

    }

    @Test
    public void testTopMessages() {
        RatingService ratingService = new RatingService();
        ratingService.setMessageList(messageList1);
        ratingService.setUserName(userName);
        ratingService.getRatingMessages(allMessages);

        Map<Integer, String> topMessages = ratingService.getTopMessages();
        for (Map.Entry entry1 : topMessages.entrySet()) {
            for(Map.Entry entry2 : topMessagesExp.entrySet())
                assertEquals("Error", entry1.getValue(), entry2.getValue());
            continue;
        }
    }


}