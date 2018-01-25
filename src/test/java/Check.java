import online.work.dao.Impl.ContactDaoImpl;
import online.work.model.ContactModel;
import online.work.service.impl.ContactServiceImpl;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by 84825 on 2018/1/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:applicationConetext-hibernate.xml")
public class Check {
    @Autowired
private ContactDaoImpl contactDaoImpl;

    @Test
public void fun2() {

        System.out.println(contactDaoImpl.getAllContacts(null));
                System.out.println("查询结束");
    }
}
