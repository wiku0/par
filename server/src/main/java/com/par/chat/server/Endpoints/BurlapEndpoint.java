package com.par.chat.server.Endpoints;

import com.caucho.burlap.server.BurlapServlet;
import com.par.chat.server.Impl.ChatApiImpl;
import com.par.shared.Api.ChatApi;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.BurlapExporter;
import org.springframework.remoting.caucho.BurlapServiceExporter;
import org.springframework.stereotype.Component;

@Component
public class BurlapEndpoint extends BurlapServlet {


    @Bean(name = "/burlap")
    public BurlapExporter burlap(ChatApi chatApi){
        BurlapServiceExporter serviceExporter = new BurlapServiceExporter();
        serviceExporter.setService(chatApi);
        serviceExporter.setServiceInterface(ChatApi.class);
        return serviceExporter;
    }
}
