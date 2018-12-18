package com.par.chat.server.Endpoints.XmlConf;

import com.par.chat.server.Impl.ChatApiImpl;
import com.par.shared.Api.ChatApi;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.XmlRpcServletServer;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class XmlServiceExporter implements HttpRequestHandler {
    XmlRpcServletServer servletServer;

    public XmlServiceExporter() throws XmlRpcException {
        this.servletServer = new XmlRpcServletServer();

        PropertyHandlerMapping propertyHandlerMapping = new PropertyHandlerMapping();

        propertyHandlerMapping.addHandler(ChatApi.class.getName(), ChatApiImpl.class);

        servletServer.setHandlerMapping(propertyHandlerMapping);

        XmlRpcServerConfigImpl config = new XmlRpcServerConfigImpl();
        config.setEnabledForExtensions(true);
        config.setEnabledForExceptions(true);
        servletServer.setConfig(config);

    }

    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        servletServer.execute(httpServletRequest, httpServletResponse);
    }
}
