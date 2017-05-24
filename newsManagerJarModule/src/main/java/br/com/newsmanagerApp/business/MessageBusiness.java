package br.com.newsmanagerApp.business;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.com.newsmanagerApp.business.interfaces.IMessageBusiness;
import br.com.newsmanagerApp.vo.MessageVO;

@ApplicationScoped
public class MessageBusiness implements IMessageBusiness{


	private final List<MessageVO> messages =
            Collections.synchronizedList(new LinkedList<MessageVO>());
 
    @Override
    public void sendMessage(MessageVO msg) {
        messages.add(msg);
        msg.setDateSent(new Date());
    }
 
    @Override
    public MessageVO getFirstAfter(Date after) {
        if(messages.isEmpty())
            return null;
        if(after == null)
            return messages.get(0);
        for(MessageVO m : messages) {
            if(m.getDateSent().after(after))
                return m;
        }
        return null;
    }

}
