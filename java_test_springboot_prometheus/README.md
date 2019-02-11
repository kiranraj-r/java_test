Spring Boot with Prometheus/Grafana Monitoring
==============================================

This demo shows a REST server that is monitored by Prometheus and
has a nice Grafana dashboard.

Setup
=====

Docker Compose
--------------

Run `docker-compose up`. The necessary config is in `docker-compose.yml`.
Some daemon configuration is in this directory as well, see Docker Compose
configuration.

Springboot Prometheus endpoint
------------------------------

SpringBoot2 uses MicroMeter as facade to various metric exporter format.
It's supposed to be something like SLF4J for logging implementations like Logback or log4j.

Enable `management.endpoints.web.exposure.include: prometheus` in application.yml.

Then add the following Maven depenedencies:
    org.springframework.boot:spring-boot-starter-actuator
    io.micrometer:micrometer-registry-prometheus
    
Add a new Gauge, Counter etc. to the MeterRegistry as it's done in the constructor of Thermometer.

View
----

Finally start de.lathspell.test.springboot.Main and go to 
* `http://localhost:8080/`                      - Static index.html with links
* `http://localhost:8080/actuator/prometheus`   - Prometheus compatible metrics export
* `http://localhost:9090`                         Prometheus web interface
* `http://localhost:9090/graph?g0.range_input=1m&g0.expr=Temperature_C&g0.tab=0` - Prometheus graph for "Temperature"
* `http://localhost:3000/d/2cyXtdumk/weather-control?panelId=2&fullscreen&edit&orgId=1&tab=metrics` final result 

Caveats:
========

* The Grafana datasource URL must be `http://prometheus:9090` not
  `http://localhost:9090` or else `docker-proxy` won't find it.

* In Java don't mix up `Counter` from the `io.micrometer` and `io.prometheus` packages.

Links
=====

* https://finestructure.co/blog/2016/5/16/monitoring-with-prometheus-grafana-docker-part-1
* https://www.igorski.co/java/spring-boot/spring-boot-metrics-prometheus/
* https://github.com/vegasbrianc/prometheus
