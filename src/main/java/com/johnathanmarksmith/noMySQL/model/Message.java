package com.johnathanmarksmith.noMySQL.model;

import javax.persistence.*;


/**
 * Date:   5/24/13 / 7:54 AM
 * Author: Johnathan Mark Sm
 * Email:  john@johnathanmarksmith.com
 * <p/>
 * Comments:
 *
 *  This bean is used to insert and list a message object.
 *
 */


@Entity

@Table(name = "messages")
public class Message
{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "message")
    private String message;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    @Override
    public String toString()
    {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
