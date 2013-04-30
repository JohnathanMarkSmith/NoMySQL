package com.johnathanmarksmith.noMySQL.dao;

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


public interface MessageDao {
    public List<Message> listMessages();

    public void SaveOrUpdateMessage(Message message);

}