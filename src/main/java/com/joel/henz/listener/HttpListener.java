package com.joel.henz.listener;

import java.util.Date;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

public class HttpListener implements HttpSessionListener, HttpSessionAttributeListener{
	
	public HttpListener() {
		
	}
	
	private static Logger logger = Logger.getLogger(HttpListener.class);

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		logger.info("new session attribute added for session with id "+event.getSession().getId()+". Attribute key / value: "+event.getName()+" / "+event.getValue());
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		logger.info("New Session was created at: "+new Date(se.getSession().getCreationTime())+"; session id = "+se.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		logger.info("Session destroyed at: "+new Date(se.getSession().getCreationTime())+"; session id = "+se.getSession().getId());
		
	}

}
