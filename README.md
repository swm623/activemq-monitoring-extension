# AppDynamics ActiveMQ Monitoring Extension

This extension works only with the standalone machine agent.

##Use Case

ActiveMQ is an open source, JMS 1.1 compliant, message-oriented middleware (MOM) from the Apache Software Foundation that provides high-availability, performance, scalability, reliability and security for enterprise messaging. 
The ActiveMQ Monitoring extension collects metrics from an ActiveMQ messaging server and uploads them to the AppDynamics Metric Browser. 

## Prerequisites ##

JMX must be enabled in ActiveMQ Messaging server for this extension to gather metrics. To enable, please see [these instructions](http://activemq.apache.org/jmx.html).

## Troubleshooting steps ##
Before configuring the extension, please make sure to run the below steps to check if the set up is correct.

1. Telnet into your activemq server from the box where the extension is deployed.
       telnet <hostname> <port>

       <port> - It is the jmxremote.port specified.
        <hostname> - IP address

    If telnet works, it confirm the access to the activemq server.


2. Start jconsole. Jconsole comes as a utitlity with installed jdk. After giving the correct host and port , check if activemq
mbean shows up.


##Installation

1. Run 'mvn clean install' from the activemq-monitoring-extension directory and find the ActiveMQMonitor.zip in the "target" folder.
2. Unzip as "ActiveMQMonitor" and copy the "ActiveMQMonitor" directory to `<MACHINE_AGENT_HOME>/monitors`


## Configuration ##

Note : Please make sure to not use tab (\t) while editing yaml files. You may want to validate the yaml file using a [yaml validator](http://yamllint.com/)

1. Configure the ActiveMQ instances by editing the config.yml file in `<MACHINE_AGENT_HOME>/monitors/ActiveMQMonitor/`.
2. Configure the yaml file

   For eg.
   ```
        #This will create this metric in all the tiers, under this path
        metricPrefix: Custom Metrics|ActiveMQ

        #This will create it in specific Tier. Replace <TIER_NAME>
        #metricPrefix: Server|Component:<TIER_NAME>|Custom Metrics|ActiveMQ

        # List of ActiveMQ Servers
        servers:
          - host: "localhost"
            port: 1099
            username: ""
            password: ""
            displayName: "localhost"


        # number of concurrent tasks.
        # This doesn't need to be changed unless many servers are configured
        numberOfThreads: 10


        # The configuration of different metrics from various mbeans of activemq server
        # Does not need to be changed.
        #
        mbeans:
          - objectName: "org.apache.activemq:type=Broker,brokerName=*"
            metrics:
              include:
                - StorePercentUsage: "StorePercentUsage"
                - TempPercentUsage: "TempPercentUsage"
                - MemoryPercentUsage: "MemoryPercentUsage"
                - TotalConnectionsCount: "TotalConnectionsCount"
                - TotalConsumerCount: "TotalConsumerCount"
                - TotalDequeueCount: "TotalDequeueCount"
                - TotalEnqueueCount: "TotalEnqueueCount"
                - TotalMessageCount: "TotalMessageCount"
                - TotalProducerCount: "TotalProducerCount"

          - objectName: "org.apache.activemq:type=Broker,brokerName=*,destinationType=Queue,destinationName=*"
            metrics:
              include:
                - AverageEnqueueTime: "AverageEnqueueTime"
                - ConsumerCount: "ConsumerCount"
                - ProducerCount: "ProducerCount"
                - MaxEnqueueTime: "MaxEnqueueTime"
                - MinEnqueueTime: "MinEnqueueTime"
                - MemoryPercentUsage: "MemoryPercentUsage"
                - QueueSize: "QueueSize"
                - DequeueCount: "DequeueCount"
                - DispatchCount: "DispatchCount"
                - EnqueueCount: "EnqueueCount"
                - ExpiredCount: "ExpiredCount"
                - InFlightCount: "InFlightCount"
                - AverageMessageSize: "AverageMessageSize"

          - objectName: "org.apache.activemq:type=Broker,brokerName=*,destinationType=Topic,destinationName=*"
            metrics:
              include:
                - AverageEnqueueTime: "AverageEnqueueTime"
                - ConsumerCount: "ConsumerCount"
                - ProducerCount: "ProducerCount"
                - MaxEnqueueTime: "MaxEnqueueTime"
                - MinEnqueueTime: "MinEnqueueTime"
                - MemoryPercentUsage: "MemoryPercentUsage"
                - QueueSize: "QueueSize"
                - DequeueCount: "DequeueCount"
                - DispatchCount: "DispatchCount"
                - EnqueueCount: "EnqueueCount"
                - ExpiredCount: "ExpiredCount"
                - InFlightCount: "InFlightCount"
                - AverageMessageSize: "AverageMessageSize"

   ```

The objectNames mentioned in the above yaml may not match your environment exactly. Please use jconsole to extract the objectName and configure it
accordingly in the config.yaml. 



4. Configure the path to the config.yml file by editing the <task-arguments> in the monitor.xml file in the `<MACHINE_AGENT_HOME>/monitors/ActiveMQMonitor/` directory. Below is the sample

     ```
     <task-arguments>
         <!-- config file-->
         <argument name="config-file" is-required="true" default-value="monitors/ActiveMQMonitor/config.yml" />
          ....
     </task-arguments>
    ```

## Metrics

The following are the metrics reported to the controller

In addition to the above metrics, we also add a metric called "Metrics Collection Successful" with a value 0 when an error occurs and 1 when the metrics collection is successful.

Note : By default, a Machine agent or a AppServer agent can send a fixed number of metrics to the controller. To change this limit, please follow the instructions mentioned [here](http://docs.appdynamics.com/display/PRO14S/Metrics+Limits).
For eg.  
```    
    java -Dappdynamics.agent.maxMetrics=2500 -jar machineagent.jar
```

## Custom Dashboard
![](https://raw.github.com/Appdynamics/activemq-monitoring-extension/master/ActiveMQDashboard.png)

##Contributing

Always feel free to fork and contribute any changes directly here on GitHub.

##Community

Find out more in the [AppSphere](http://appsphere.appdynamics.com/t5/eXchange/ActiveMQ-Monitoring-Extension/idi-p/5717) community.

##Support

For any questions or feature request, please contact [AppDynamics Support](mailto:help@appdynamics.com).


