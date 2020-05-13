/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bms.ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

/**
 *
 * @author kkk
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/bms")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class AddBookMDB implements MessageListener {
    
    public AddBookMDB() {
        
    }
    
    @Override
    public void onMessage(Message message) {
        ObjectMessage msg = null;
        try{
            if(message instanceof ObjectMessage) {
                msg = (ObjectMessage) message;
                Book e = (Book)msg.getObject();
                e.insertToDB();
                System.out.println("**Add: " + e.getISBN());
            }
            if(message instanceof TextMessage) {
                TextMessage msg2 = (TextMessage) message;
                String isbn = msg2.getText();
                Book e = new Book();
                e.deleteByISBN(isbn);
                System.out.println("**Delete: " + isbn);
            }
        } catch (JMSException e){
            e.printStackTrace();
        }
    }
}
