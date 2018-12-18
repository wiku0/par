package com.par.chat.server.Endpoints;

import com.par.chat.server.Endpoints.XmlConf.XmlServiceExporter;

import org.apache.xmlrpc.XmlRpcException;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class XmlRpcEndpoint {

    @Bean(name = "/xmlrpc")
    public XmlServiceExporter xmlServiceExporter() throws XmlRpcException {
        return new XmlServiceExporter();
    }
}
