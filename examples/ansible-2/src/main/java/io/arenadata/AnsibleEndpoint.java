package io.arenadata;

import org.apache.camel.Category;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;
import org.apache.camel.support.DefaultEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@UriEndpoint(firstVersion = "0.1.0", scheme = "ansible", title = "Ansible", syntax = "ansible:playbook",category = { Category.SYSTEM })
public class AnsibleEndpoint extends DefaultEndpoint {

    public static Logger logger = LoggerFactory.getLogger(AnsibleEndpoint.class);

    @UriPath
    @Metadata(required = true)
    private String playbook;
    @UriParam
    @Metadata(required = true)
    private String inventory;
    @UriParam
    private String tags;
    @UriParam(defaultValue = "5")
    private String forks;
    @UriParam
    @Metadata(defaultValue = "false", javaType = "java.lang.Boolean")
    private Boolean verbose;

    public AnsibleEndpoint(String uri, AnsibleComponent component) {
        super(uri, component);
    }

    public Producer createProducer() throws Exception {
        return new AnsibleProducer(this);
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        throw new UnsupportedOperationException("Consumer not supported for AnsibleEndpoint!");
    }

    public boolean isSingleton() {
        return true;
    }

    public String getPlaybookPath() {
        return this.playbook;
    }

    public String getInventory() {
        return this.inventory;
    }

    public String getTags() {
        return this.tags;
    }

    public String getForks() {
        return this.forks;
    }

    public boolean isVerbose() {
        return this.verbose.booleanValue();
    }
}