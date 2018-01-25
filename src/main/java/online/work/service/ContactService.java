package online.work.service;

import online.work.model.ContactModel;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 84825 on 2018/1/24.
 */
public interface ContactService {
     Workbook exportContact(String fileName, List<ContactModel> contacts);
     List<ContactModel> getAllContacts(ContactModel contactModel);
     public void importAndSaveContact(ArrayList<ArrayList<Object>> list);
}
