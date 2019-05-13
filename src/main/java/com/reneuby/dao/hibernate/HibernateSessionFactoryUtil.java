package com.reneuby.dao.hibernate;

import com.reneuby.domain.Coefficients;
import com.reneuby.domain.Equation;
import com.reneuby.domain.Roots;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class HibernateSessionFactoryUtil {
    private SessionFactory sessionFactory;

    public HibernateSessionFactoryUtil() {
        getSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Coefficients.class);
                configuration.addAnnotatedClass(Roots.class);
                configuration.addAnnotatedClass(Equation.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("HibernateSessionFactoryUtil Исключение!\n" + e);
            }
        }
        return sessionFactory;
    }
}
