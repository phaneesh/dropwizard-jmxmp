package io.dropwizard.jmxmp.managed;

import io.dropwizard.lifecycle.Managed;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import javax.management.MBeanServer;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Objects;

@Slf4j
public class JmxMpServer implements Managed {

    private int port;

    private JMXConnectorServer cs;

    @Builder
    public JmxMpServer(final int port) {
        this.port = port;
    }

    @Override
    public void start() {
        try {
            log.info("Starting JMXMP server on port: {}", port);
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            HashMap<String, ?> env = new HashMap<>();
            JMXServiceURL url = new JMXServiceURL("jmxmp", null, port);
            cs = JMXConnectorServerFactory.newJMXConnectorServer(url, env, mbs);
            cs.start();
            log.info("Started JMXMP server on port: {} successfully", port);
        } catch(Exception e) {
            log.error("Error starting JMXMP server on port: {}", port, e);
        }
    }

    @Override
    public void stop() throws Exception {
        log.info("Stopping JMXMP server");
        if(Objects.nonNull(cs)) {
            cs.stop();
        }
        log.info("Stopped JMXMP server successfully");
    }
}
