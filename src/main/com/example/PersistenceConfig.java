package main.com.example;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
public class PersistenceConfig {
	private Map<String, String> properties = new HashMap<>();

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
		properties.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/pyjioh");
		properties.put("javax.persistence.jdbc.user", "root");
		properties.put("javax.persistence.jdbc.password", "mysql");
		properties.put("spring.jpa.hibernate.ddl-auto", "update");
		properties.put("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		emf.setPackagesToScan("main.com.example");
		emf.setJpaPropertyMap(properties);
		emf.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
		return emf;
	}
	
	@Bean
	public EntityManager entityManager(LocalContainerEntityManagerFactoryBean emf){
		return emf.getNativeEntityManagerFactory().createEntityManager();
	} 

}
