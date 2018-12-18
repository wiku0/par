package com.par.client;

import com.caucho.burlap.client.BurlapProxyFactory;

import com.caucho.hessian.client.HessianProxyFactory;
import com.par.client.xmlCon.XmlRpc;
import com.par.shared.Api.ChatApi;
import sun.rmi.runtime.Log;

import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApiWrapper {
    private static Logger logger = Logger.getLogger(ApiWrapper.class.getName());
    String url = "http://localhost:8080/";
//String url = "http://10.7.58.65:8080/";
    ChatApi chatApiHessian = (ChatApi) new HessianProxyFactory().create(ChatApi.class, url + "hessian");
    ChatApi chatApiBurlap = (ChatApi) new BurlapProxyFactory().create(ChatApi.class, url + "burlap");
    ChatApi chatApiXml = new XmlRpc(url+"xmlrpc").getXmlRpcApi();

    public ApiWrapper() throws MalformedURLException {
    }

    public ChatApi getApi(Protocol protocol) throws Exception {
        logger.log(Level.INFO, "Currently protocol: " + protocol);
        switch (protocol) {
            case XML:
                return chatApiXml ;
            case BURLAP:
                return chatApiBurlap;
            case HESSIAN:
                return chatApiHessian;

        }
        throw new Exception("Wrong Protocol");
    }

}
