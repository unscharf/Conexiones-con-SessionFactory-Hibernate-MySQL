/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package loginhibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Blutharsch
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory=null;
    
    public static SessionFactory conexion(String login, String Contraseña, String Puerto, String IP) {
        try {
            try {
                Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:" + Puerto + "/pruebaconnect", login, Contraseña);
                conexion.close();
            } catch (SQLException e) {
                return null;
            }
            sessionFactory = (SessionFactory) new Configuration()
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                    .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:" + Puerto + "/pruebaconnect")
                    .setProperty("hibernate.connection.username", login)
                    .setProperty("hibernate.connection.password", Contraseña)
                    .addResource("POJO/Prueba.hbm.xml")
                    .buildSessionFactory();
                    /*ORM*/
                  
                    

        } catch (HibernateException ex) {

            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        return sessionFactory;
    }
}
