package com.cemetiere.weblab.config;

import com.cemetiere.weblab.beans.Checker;
import com.cemetiere.weblab.beans.FigureCollector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Beans {
    @Bean
    @Scope("singleton")
    public Checker globalChecker(){
        return new Checker();
    }

    @Bean
    @Scope("singleton")
    public FigureCollector globalFigureCollector(){
        return new FigureCollector();
    }

}
