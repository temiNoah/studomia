package com.studomia.studomia;

import com.studomia.studomia.configuration.securityConfig.AppConfig;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class StudomiaApplication {

	public static void main(String[] args)
	{
		  SpringApplication.run(StudomiaApplication.class, args);



	}


	public static String baseUrl; public static String path;


	@Bean
	ApplicationRunner applicationRunner(Environment environment) {
		return args -> {
			  baseUrl = environment.getProperty("app.baseUrl");
			 path = environment.getProperty("app.on.auth.success.redirect.path");



			init();
		};
	}

	@Bean
	public  AppConfig init()
	{
		AppConfig app =  new  AppConfig();
		app.setBaseUrl(baseUrl);
		app.setPath(path);

		return app;
	}

//	@Bean
//	private static AppConfig init(){
//		return  new AppConfig();
//	}

}
