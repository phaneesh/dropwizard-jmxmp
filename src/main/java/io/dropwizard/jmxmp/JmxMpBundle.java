/*
 * Copyright 2016 Phaneesh Nagaraja <phaneesh.n@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.dropwizard.jmxmp;

import com.google.common.base.Preconditions;
import io.dropwizard.core.Configuration;
import io.dropwizard.core.ConfiguredBundle;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.jmxmp.managed.JmxMpServer;

/**
 * @author phaneesh
 */
public abstract class JmxMpBundle<T extends Configuration> implements ConfiguredBundle<T> {

    protected abstract int port();

    @Override
    public void initialize(Bootstrap<?> bootstrap) {

    }

    @Override
    public void run(T configuration, Environment environment) {

        Preconditions.checkArgument(port() != -1, "Invalid JMXMP port configuration!");
        environment.lifecycle().manage(
                JmxMpServer.builder()
                    .port(port())
                .build()
        );
    }
}
