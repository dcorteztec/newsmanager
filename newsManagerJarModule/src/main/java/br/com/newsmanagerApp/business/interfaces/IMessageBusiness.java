package br.com.newsmanagerApp.business.interfaces;

import java.util.Date;

import br.com.newsmanagerApp.vo.MessageVO;

public interface IMessageBusiness {

	 void sendMessage(MessageVO msg);
	 
	 MessageVO getFirstAfter(Date after);
}
