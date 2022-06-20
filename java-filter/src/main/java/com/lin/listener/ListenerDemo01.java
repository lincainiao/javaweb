package com.lin.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

// 统计网站在线人数
public class ListenerDemo01 implements HttpSessionListener {
    // 创建session监听
    // 一旦创建session，就会触发该事件
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
        System.out.println(httpSessionEvent.getSession().getId());
        Integer onlineCount = (Integer) servletContext.getAttribute("OnlineCount");
        if (onlineCount == null) {
            onlineCount = Integer.valueOf(1);
        }else {
            int count = onlineCount.intValue();
            onlineCount = Integer.valueOf(count+1);
        }
        servletContext.setAttribute("OnlineCount",onlineCount);
    }

    // 销毁session监听
    // 一旦session销毁，就会触发该事件
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
        Integer onlineCount = (Integer) servletContext.getAttribute("OnlineCount");
        if (onlineCount == null) {
            onlineCount = Integer.valueOf(1);
        }else {
            int count = onlineCount.intValue();
            onlineCount = Integer.valueOf(count--);
        }
        servletContext.setAttribute("OnlineCount",onlineCount);

    }

    /*
    * session的销毁
    * 手动销毁:httpSessionEvent.getSession().invalidate();
    * 自动销毁，在xml中配置session的过期时间
    * */
}
