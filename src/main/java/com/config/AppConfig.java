package com.config;

import com.controller.FlightController;
import com.controller.PassengerController;
import com.controller.PlaneController;
import com.dao.FlightDAO;
import com.dao.PassengerDAO;
import com.dao.PlaneDAO;
import com.dao.impl.FlightDAOImpl;
import com.dao.impl.PassengerDAOImpl;
import com.dao.impl.PlaneDAOImpl;
import com.service.FlightService;
import com.service.PassengerService;
import com.service.PlaneService;
import com.service.impl.FlightServiceImpl;
import com.service.impl.PassengerServiceImpl;
import com.service.impl.PlaneServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;


@EnableWebMvc
@Configuration
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"com"});

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        return em;
    }

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@gromcode-lessons.cnrx1jkycv8d.us-east-2.rds.amazonaws.com:1521:ORCL");
        dataSource.setUsername("main");
        dataSource.setPassword("asol1998");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }


    @Bean
    public FlightDAO flightDAO() {
        return new FlightDAOImpl();
    }

    @Bean
    public PassengerDAO passengerDAO() {
        return new PassengerDAOImpl();
    }

    @Bean
    public PlaneDAO planeDAO() {
        return new PlaneDAOImpl();
    }


    @Bean
    public FlightService flightService(FlightDAO flightDAO) {
        return new FlightServiceImpl(flightDAO);
    }

    @Bean
    public PassengerService passengerService(PassengerDAO passengerDAO) {
        return new PassengerServiceImpl(passengerDAO);
    }

    @Bean
    public PlaneService planeService(PlaneDAO planeDAO) {
        return new PlaneServiceImpl(planeDAO);
    }


    @Bean
    public FlightController flightController(FlightService flightService) {
        return new FlightController(flightService);
    }

    @Bean
    public PassengerController passengerController(PassengerService passengerService) {
        return new PassengerController(passengerService);
    }

    @Bean
    public PlaneController planeController(PlaneService planeService) {
        return new PlaneController(planeService);
    }

}
