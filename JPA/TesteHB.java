import org.hibernate.*;
 import org.hibernate.cfg.*;
   
 public class TesteHB {
   
       public static void main(String[] args) {
             endereco end = new endereco();
              
           SessionFactory factory = factory(end);         
           end.setCodigo(1);
           end.setRua("Av. Getúlio Vargas");
           end.setCidade("Feira de Santana");
           end.setEstado("BA");
           end.setCep("4419999");
            
         Session session = null;
         Transaction tx = null;
         try {
             session = factory.openSession();
             tx = session.beginTransaction();
             session.persist(end);
             tx.commit();
         } catch (Exception e) {
             if (tx != null) tx.rollback();
             System.out.println("Transação falhou : ");
             e.printStackTrace();
         } finally {
             session.close();
         }
       }
        
       private static SessionFactory factory(Object classe){
           Configuration  config = new Configuration().
           setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect").
           setProperty("hibernate.connection.driver_class", "org.postgresql.Driver").
           setProperty("hibernate.connection.url", 
            "jdbc:postgresql://www.sitio.com.br:5432/Endereco").
           setProperty("hibernate.connection.username", "postgres").
           setProperty("hibernate.connection.password", "123").
           setProperty("hibernate.show_sql", "true");
              
             config.addClass(((endereco)classe).getClass());
              
             return config.buildSessionFactory();
       }
 }
