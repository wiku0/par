package com.par.chat.server.Endpoints;


import com.caucho.hessian.server.HessianServlet;
import com.par.shared.Api.ChatApi;

import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianExporter;
import org.springframework.remoting.caucho.HessianServiceExporter;

import org.springframework.stereotype.Component;

@Component
public class HessianEndpoint extends HessianServlet {


    @Bean(name = "/hessian")
    public HessianExporter hessian(ChatApi chatApi){
        HessianServiceExporter serviceExporter = new HessianServiceExporter();
        serviceExporter.setService(chatApi);
        serviceExporter.setServiceInterface(ChatApi.class);
        return serviceExporter;
    }
}
