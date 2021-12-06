package io.arenadata;

import org.apache.camel.Exchange;
import org.apache.camel.support.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.exec.CommandLine;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class AnsibleProducer extends DefaultProducer {

    public static Logger logger = LoggerFactory.getLogger(DefaultProducer.class);


    private AnsibleEndpoint endpoint;

    public AnsibleProducer(AnsibleEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    protected ProcessBuilder getProcessBuilder() {
        List<String> commands = new LinkedList<String>();
        Collections.addAll(commands,
                "ansible-playbook",
                "-i",
                endpoint.getInventory(),
                "-f",
                endpoint.getForks(),
                endpoint.getPlaybookPath()
        );
        if (!endpoint.getTags().isEmpty()) {
            Collections.addAll(commands,
                    "--tags",
                    endpoint.getTags()
            );
        }
        if (endpoint.isVerbose()) {
            commands.add("--verbose");
        }
        return new ProcessBuilder(commands);
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        String playbook = "///Users/ekurginyan/Documents/arenadata/adcm_cluster_ads/ansible/cluster_host_prepare_etc.yaml";
        String inventory = "///Users/ekurginyan/Downloads/inventory.json";

        ProcessBuilder builder =
                // getProcessBuilder();
                new ProcessBuilder("ansible-playbook", "-i", inventory, playbook);
        Process process = builder.start();
        process.waitFor();

//        try (BufferedReader reader = new BufferedReader(
//                new InputStreamReader(process.getInputStream()))) {
//
//            String line;
//
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//
//        }
    }
}