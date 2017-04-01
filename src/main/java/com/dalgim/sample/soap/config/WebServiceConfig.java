package com.dalgim.sample.soap.config;

import com.dalgim.namespace.personservice.PersonEndpoint;
import com.dalgim.namespace.personservice.PersonRegistry;
import com.dalgim.sample.soap.endpoint.PersonRegistryImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import javax.xml.ws.Endpoint;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dalgim on 26.03.2017.
 */
@Configuration
public class WebServiceConfig {

    private static final String SERVLET_URL_PATH = "/api";
    private static final String SERVICE_URL_PARH = "/PersonSoapService_1.0";

    @Bean
    public ServletRegistrationBean cxfServlet() {
        return new ServletRegistrationBean(new CXFServlet(), SERVLET_URL_PATH + "/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public PersonRegistry personRegistry() {
        return new PersonRegistryImpl();
    }

    @Bean
    public PersonEndpoint personEndpoint() {
        return new PersonEndpoint();
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), personEndpoint());
        endpoint.setServiceName(personEndpoint().getServiceName());
        endpoint.setWsdlLocation(personEndpoint().getWSDLDocumentLocation().toString());
        endpoint.publish(SERVICE_URL_PARH);
        return endpoint;
    }
}
