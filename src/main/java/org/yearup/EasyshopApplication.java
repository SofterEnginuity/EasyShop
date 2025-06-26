package org.yearup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.yearup.data.mysql.MySqlCategoryDao;

@SpringBootApplication
@EnableMethodSecurity
public class EasyshopApplication
{

    public static void main(String[] args) {
        SpringApplication.run(EasyshopApplication.class, args);


    }


}
