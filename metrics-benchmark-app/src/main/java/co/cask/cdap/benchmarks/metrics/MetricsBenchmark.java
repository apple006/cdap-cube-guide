/*
 * Copyright © 2014 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package co.cask.cdap.benchmarks.metrics;

import co.cask.cdap.api.app.AbstractApplication;
import co.cask.cdap.api.data.schema.UnsupportedTypeException;
import co.cask.cdap.api.dataset.lib.ObjectStores;
import co.cask.cdap.benchmarks.metrics.emitter.EmitterConfig;

/**
 * Application for benchmarking metrics.
 *
 * <p>
 *   {@link MetricsEmitter}: emits metrics.
 *   {@link BenchmarkService}: set/get emitter configs and get benchmark report/results
 * </p>
 */
public class MetricsBenchmark extends AbstractApplication {

  @Override
  public void configure() {
    setName("MetricsBenchmark");
    setDescription("A metrics benchmark application");
    addWorker(new MetricsEmitter());
    addService(new BenchmarkService());

    try {
      ObjectStores.createObjectStore(getConfigurer(), "configs", EmitterConfig.class);
    } catch (UnsupportedTypeException e) {
      throw new RuntimeException(e);
    }
  }
}
