package online.work.service.impl;

import online.work.dao.Impl.ContactDaoImpl;
import online.work.model.ContactModel;
import online.work.service.ContactService;
import online.work.util.Poi4Excel;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 84825 on 2018/1/24.
 */
@Service
public class ContactServiceImpl implements ContactService{
    @Autowired
    private ContactDaoImpl contactDaoImpl;

    @Override
    public Workbook exportContact(String fileName, List<ContactModel> contacts) {
        // excel格式是.xlsx
        String fileType = "xlsx";
        // 导出excel需要的数据格式
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        // 导出excel第一行标题数据
        ArrayList<String> listTitle = new ArrayList<String>();
        // 存放标题顺序
        listTitle.add("联系人名称");
        listTitle.add("邮箱");
        listTitle.add("电话");
        listTitle.add("地址");
        listTitle.add("职位");
        // 将标题数据放在excel数据
        list.add(listTitle);

        for (int i = 0; i < contacts.size(); i++) {
            // 存放excel内容数据
            ArrayList<String> listBody = new ArrayList<String>();
            // 存放数据顺序和存放标题顺序对应
            listBody.add(contacts.get(i).getName());
            listBody.add(contacts.get(i).getEmail());
            listBody.add(contacts.get(i).getMobile());
            listBody.add(contacts.get(i).getAddress());
            listBody.add(contacts.get(i).getPosition());
            list.add(listBody);
        }

        // 调用 Poi4Excel 公共类的导出方法
        try {
            return Poi4Excel.writer(fileName, fileType, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ContactModel> getAllContacts(ContactModel contactModel) {
        return contactDaoImpl.getAllContacts(contactModel);
    }
    public void importAndSaveContact(ArrayList<ArrayList<Object>> list) {
        for (int i = 1; i < list.size(); i++) {
            ContactModel contact = new ContactModel();
            contact.setName(list.get(i).get(0).toString());
            contact.setEmail(list.get(i).get(1).toString());
            contact.setMobile(list.get(i).get(2).toString());
            contact.setAddress(list.get(i).get(3).toString());
            contact.setPosition(list.get(i).get(4).toString());
            // 保存导入的Excel数据到数据库
            contactDaoImpl.saveContacts(contact);
        }
    }
}
