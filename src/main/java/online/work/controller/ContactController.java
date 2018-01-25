package online.work.controller;

import com.alibaba.fastjson.JSONObject;
import online.work.model.ContactModel;
import online.work.service.ContactService;

import online.work.util.Poi4Excel;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by 84825 on 2018/1/24.
 */
@Controller
@RequestMapping(value = "index")
public class ContactController {

    @Autowired
    private ContactService contactService;
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "to_contact", method = {RequestMethod.POST, RequestMethod.GET})
    public String toContact(Model model) {
        model.addAttribute("contacts",contactService.getAllContacts(null));

        return "contact";
    }

    /**
     * 导出
     * @param model
     * @param fileName
     * @param response
     */
    @RequestMapping(value = "/export/{fileName}", method = RequestMethod.GET)
    public void exportContact(Model model, @PathVariable String fileName, HttpServletResponse response) {
        try {
            System.out.println("开始导出表格");
            // 获取所有需要导出的联系人信息
            List<ContactModel> contacts = contactService.getAllContacts(null);
            // 将联系人列表数据转换成工作文档对象
            Workbook workbook = contactService.exportContact(fileName, contacts);
            // 设置发送到客户端的响应的内容类型
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            // 为了解决中文名称乱码问题
            String finalFileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
            response.setHeader("Content-disposition", "attachment;filename=" + finalFileName + ".xlsx");
            // 获取输出流
            OutputStream ouputStream = new BufferedOutputStream(response.getOutputStream());
            // 下载文件(写输出流)
            workbook.write(ouputStream);
            // 刷新流
            ouputStream.flush();
            // 关闭流
            ouputStream.close();
            System.out.println("导出表格结束");
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            // 抛出异常
            throw new RuntimeException(e);
        }
    }

    /**
     * 导入
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/import")
    @ResponseBody
    public String importContact(Model model, MultipartRequest request) {
        System.out.println("开始导入");
        JSONObject jsonObject=new JSONObject();
        ResponseData responseData = new ResponseData();
        try {
            // 获取导入的文件
            MultipartFile file = request.getFile("excelFile");
            // 文件名称
            String filename = file.getOriginalFilename();
            // 文件类型
            String type = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
            // 利用 Poi4Excel 获取excel导入的内容列表
            ArrayList<ArrayList<Object>> contactList = Poi4Excel.read(file.getInputStream(), type);
            // 保存excel中联系人信息
            contactService.importAndSaveContact(contactList);
            System.out.println("导入成功");
        } catch (Exception e) {
            // 异常处理
            logger.error(e.getMessage(), e);
            System.out.println("发生异常");
            System.out.println(e.getMessage());
            responseData.setError(e.getMessage());
        }
        // 返回处理结果（json 格式）
        jsonObject.put("responseData",responseData);
        return jsonObject.toString();
    }
}
