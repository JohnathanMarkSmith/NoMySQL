package com.johnathanmarksmith.noMySQL;


import com.johnathanmarksmith.noMySQL.model.Message;
import com.johnathanmarksmith.noMySQL.service.MessageService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.apache.log4j.Logger.getLogger;

/**
 * Date:   5/24/13 / 7:55 AM
 * Author: Johnathan Mark Smith
 * Email:  john@johnathanmarksmith.com
 *
 * Comments:
 *
 *    This is a example on how to use Spring's Java Configuration (JavaConfig) style with Maven,
 *    JUnit, Log4J, Hibernate and HyperSQL (hsqldb).
 *
 */
public class Main
{

    private static final Logger LOGGER = getLogger(Main.class);

    public static void main(String[] args)
    {
        /**
         *
         * This is going to setup the database configuration in the applicationContext
         * you can see that I am using the new Spring's Java Configuration style and not some OLD XML file
         *
         */
        ApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfiguration.class);

        MessageService mService = context.getBean(MessageService.class);

        /**
         *
         *   This is going to create a message object and set the message to "Hello World" then pass the object to t
         *   the service layer for inserting into the database
         *
         */
        Message message = new Message();
        message.setMessage("Hello World");
        mService.SaveMessage(message);


        /**
         *
         * This is going to do a 2nd Message in database.
         *
         */
        message.setMessage("I love Regan");
        mService.SaveMessage(message);

        /**
         *
         * This is going to get the messages from database and do the following:
         *    - display number of message(s)
         *    - display each message in database
         *
         */
        List<Message> myList = mService.listMessages();
        LOGGER.debug("You Have " + myList.size() + " Message(s) In The Database");

        for (Message i : myList)
        {
            LOGGER.debug("Message: ID: " + i.getId() + ", Message: " + i.getMessage() + ".");
        }

        /**
         *
         * This is the end!!!
         *
         */
        LOGGER.debug("This is the end!!!!");
    }
}
