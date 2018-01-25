package online.work.dao.Impl;

import online.work.dao.BaseDao;
import online.work.model.ContactModel;
import org.hibernate.Query;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 84825 on 2018/1/24.
 */
@Repository
public class ContactDaoImpl {
    @Autowired
    private BaseDao baseDao;

    @Autowired
    private  SessionFactory sessionFactory;
    public List<ContactModel> getAllContacts(ContactModel contactModel){
        return (List<ContactModel>)baseDao.getHibernateTemplate().find("from ContactModel");
    }
    public void saveContacts(ContactModel contactModel){
        baseDao.getHibernateTemplate().saveOrUpdate(contactModel);
    }
}
