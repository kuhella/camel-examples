package io.arenadata;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.spi.annotations.Component;
import org.apache.camel.support.DefaultComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Component("ansible")
public class AnsibleComponent extends DefaultComponent {

    private static Logger logger = LoggerFactory.getLogger(AnsibleComponent.class);

    public AnsibleComponent() {

    }

    public AnsibleComponent(CamelContext context) {
        super(context);
    }

    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        AnsibleEndpoint endpoint = new AnsibleEndpoint(uri, this);
        setProperties(endpoint, parameters);
        logger.debug("AnsibleComponent create endpoint");
        return endpoint;
    }

}
