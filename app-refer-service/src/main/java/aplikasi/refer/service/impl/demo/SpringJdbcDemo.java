package aplikasi.refer.service.impl.demo;

import aplikasi.refer.domain.User;
import aplikasi.refer.service.UserService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringJdbcDemo {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:refContext-impl.xml");
    UserService pstDaoSpring = (UserService) ctx.getBean("userServiceDao");

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringJdbcDemo j = new SpringJdbcDemo();
        j.checkUser();

    }
    
    public void checkUser() {
        User p = pstDaoSpring.findUserById(1);
        System.out.println("First : " + p.getFirstname());
        System.out.println("Last : " + p.getLastname());
    }
}
