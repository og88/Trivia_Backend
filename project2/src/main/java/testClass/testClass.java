package testClass;

<<<<<<< HEAD
=======
import java.io.FileNotFoundException;

>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241
import org.apache.log4j.Logger;

import com.revature.DAO.UserDAOImplementation;
import com.revature.models.User;
<<<<<<< HEAD
=======
import com.revature.services.UserServices;
>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241

public class testClass {
	final static Logger log = Logger.getLogger(UserDAOImplementation.class);
	static User user = new User();
	public static void main(String[] args) {

<<<<<<< HEAD
//		user.setUsername("testdef");
//		user.setPassword("test");
//		user.setEmail("testa@email.com");
//		user.setTempUsername("testdef");
//		
//		System.out.println("in testClass main");
//		log.info("main Success");
//		try {
//			System.out.println(UserServices.getUserServices().updateUser(user));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
=======
		user.setUsername("testdef");
		user.setPassword("test");
		user.setEmail("testa@email.com");
		user.setTempUsername("testdef");
		
		System.out.println("in testClass main");
		log.info("main Success");
		try {
			System.out.println(UserServices.getUserServices().updateUser(user));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241
		
		
		//System.out.println(UserServices.getUserServices().getAllUsers());
	}

}
