package com.johnathanmarksmith.noMySQL.service;

import com.johnathanmarksmith.noMySQL.model.Message;

import java.util.List;


/**
 * Date:   3/11/13 / 11:44 AM
 * Author: Johnathan Mark Smith
 * Email:  john@johnathanmarksmith.com
 * <p/>
 * Comments:
 * This is the interface to the DAO for Message Database
 */


public interface MessageService
{
    public List<Message> listMessages();

    public void SaveMessage(Message message);
}