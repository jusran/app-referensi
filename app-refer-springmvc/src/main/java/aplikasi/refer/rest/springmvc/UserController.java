/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.refer.rest.springmvc;

import aplikasi.refer.domain.User;
import aplikasi.refer.service.UserService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Student-03
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:refContext-impl.xml");
    private static UserService service = (UserService) ctx.getBean("userServiceDao");

    @ExceptionHandler(IOException.class)
    public String handleIOException(IOException ex, HttpServletRequest request) {
        return ClassUtils.getShortName(ex.getClass());
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        ResponseEntity<User> entity = null;

        try {
            User p = service.findUserById(id);
            if (p != null) {
                entity = new ResponseEntity<User>(p, HttpStatus.OK);                
            } else {
                entity = new ResponseEntity<User>(HttpStatus.NOT_FOUND);
                return entity;                
            }
        } catch (Exception e) {
            entity = new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return entity;
    }
}
