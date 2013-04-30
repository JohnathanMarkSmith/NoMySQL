package com.johnathanmarksmith.noMySQL.dao;

import com.johnathanmarksmith.noMySQL.model.Message;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;


/**
 * Date:   3/11/13 / 11:44 AM
 * Author: Johnathan Mark Smith
 * Email:  john@johnathanmarksmith.com
 * <p/>
 * Comments:
 * This is the interface to the DAO for Message Database
 */


@Transactional
@Repository
public class MessageDaoImpl implements MessageDao {
    private Log log = log = LogFactory.getLog(MessageDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @PostConstruct
    public void setup() throws Throwable {
        System.out.println("setup()");
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<Message> listMessages() {
        try {
            return (List<Message>) sessionFactory.getCurrentSession()
                    .createCriteria(Message.class).list();

        } catch (Exception e) {
            log.fatal(e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void SaveOrUpdateMessage(Message message) {
        try {
            Session mySession = sessionFactory.getCurrentSession();
            mySession.save(message);
            mySession.flush();
        } catch (Exception e) {
            log.fatal(e.getMessage());
        }
    }


}

