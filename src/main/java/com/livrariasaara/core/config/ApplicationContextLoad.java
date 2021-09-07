package com.livrariasaara.core.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextLoad  implements ApplicationContextAware {
	
	/*
	 * todos os controles, services, repositories, modulos carregados em memoria
	 */

	@Autowired
	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

		ApplicationContextLoad.applicationContext = applicationContext;
		
	}
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	/*
	 * exp. como usar:
	 * 	Optional<User> authUser = ApplicationContextLoad.getApplicationContext()
					.getBean(UserRepository.class).findByUsername(user).orElse(null);
	 */

}
