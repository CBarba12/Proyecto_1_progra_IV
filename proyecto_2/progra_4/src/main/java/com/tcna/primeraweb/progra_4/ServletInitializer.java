package com.tcna.primeraweb.progra_4;

import com.tcna.primeraweb.progra_4.Progra4Application;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Progra4Application.class);
    }

}
