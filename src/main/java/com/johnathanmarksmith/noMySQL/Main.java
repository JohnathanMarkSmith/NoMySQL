package com.johnathanmarksmith.noMySQL;


import com.johnathanmarksmith.noMySQL.model.Message;
import com.johnathanmarksmith.noMySQL.service.MessageService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.apache.log4j.Logger.getLogger;


/**
 * Date:   4/29/13 / 8:46 AM
 * Author: Johnathan Mark Smith
 * Email:  john@johnathanmarksmith.com
 * <p/>
 * Comments:
 * <p/>
 * This is just a example on how to use Spring, Maven, JavaConfig
 */


public class Main
{

    private static final Logger LOGGER = getLogger(Main.class);

    public static void main(String[] args)
    {
        // in this setup, both the main(String[]) method and the JUnit method both specify that
        ApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfiguration.class);

        MessageService mService = context.getBean(MessageService.class);

        /**
         *   Saving Message to database
         */
        Message message = new Message();
        message.setMessage("Hello World");
        mService.SaveMessage(message);


        /**
         * Saving 2nd Message in database.
         */
        message.setMessage("I love NYC");
        mService.SaveMessage(message);

        /**
         * Getting messages from database
         *    - display number of message(s)
         *    - display each message in database
         */
        List<Message> myList = mService.listMessages();
        LOGGER.debug("You Have " + myList.size() + " Message(s) In The Database");

        for (Message i : myList)
        {
            LOGGER.debug("Message: ID: " + i.getId() + ", Message: " + i.getMessage() + ".");
        }
    }
}
