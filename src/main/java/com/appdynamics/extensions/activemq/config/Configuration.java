/**
 * Copyright 2014 AppDynamics, Inc.
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
package com.appdynamics.extensions.activemq.config;

import com.appdynamics.extensions.util.metrics.MetricOverride;

public class Configuration {

	private Server[] servers;
	private int threadTimeout;
    private int numberOfThreads;
	private String metricPrefix;
	private MetricOverride[] metricOverrides;

	public Server[] getServers() {
		return servers;
	}

	public void setServers(Server[] servers) {
		this.servers = servers;
	}

	public int getThreadTimeout() {
		return threadTimeout;
	}

	public void setThreadTimeout(int threadTimeout) {
		this.threadTimeout = threadTimeout;
	}

	public int getNumberOfThreads() {
		return numberOfThreads;
	}

	public void setNumberOfThreads(int numberOfThreads) {
		this.numberOfThreads = numberOfThreads;
	}

	public String getMetricPrefix() {
		return metricPrefix;
	}

	public void setMetricPrefix(String metricPrefix) {
		this.metricPrefix = metricPrefix;
	}

	public MetricOverride[] getMetricOverrides() {
		return metricOverrides;
	}

	public void setMetricOverrides(MetricOverride[] metricOverrides) {
		this.metricOverrides = metricOverrides;
	}
}
