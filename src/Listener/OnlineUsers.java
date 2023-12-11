package Listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class OnlineUsers implements HttpSessionListener {
    public static int counter;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
        OnlineUsers.counter++;
        HttpSession session=se.getSession();
        System.out.println("sessionCreated执行，OnlineUsers.counter="+OnlineUsers.counter+",SessionID="+session.getId());

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
        OnlineUsers.counter--;
        System.out.println("sessionDestroyed执行，OnlineUsers.counter="+OnlineUsers.counter);
    }
}
