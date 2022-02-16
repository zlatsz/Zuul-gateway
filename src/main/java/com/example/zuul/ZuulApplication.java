package com.example.zuul;


import com.example.zuul.service.ErrorFilter;
import com.example.zuul.service.PostFilter;
import com.example.zuul.service.PreFilter;
import com.example.zuul.service.RouteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageChannel;


@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
@EnableBinding(ZuulApplication.Channels.class)
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

    @Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }

    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }

    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }

    @Bean
    public RouteFilter routeFilter() {
        return new RouteFilter();
    }


    public interface Channels {

        @Input
        MessageChannel input();

        @Output
        MessageChannel output();

    }

}