package com.johnathanmarksmith.noMySQL.test;

import com.johnathanmarksmith.noMySQL.DatabaseConfiguration;
import com.johnathanmarksmith.noMySQL.model.Message;
import com.johnathanmarksmith.noMySQL.service.MessageService;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Date:   4/25/13 / 9:52 AM
 * Author: Johnathan Mark Smith
 * Email:  john@johnathanmarksmith.com
 * <p/>
 * Comments:
 * <p/>
 * This is just a sample jUnit Test to show how to use jUnit and Spring to test my bean.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseConfiguration.class)
public class TestApp {

    @Autowired
    ApplicationContext context;


    @Test
    public void testDatabase() {
        /**
         * Get access to service laywer, create new message and insert it into database
         */
        MessageService mService = context.getBean(MessageService.class);

        Message message = new Message();
        message.setMessage("I Love Dogs");

        mService.SaveMessage(message);

        /**
         * Return a list of messages from database and count them
         */
        List<Message> myList = mService.listMessages();
        Assert.assertEquals(myList.size(), 1);
    }


}