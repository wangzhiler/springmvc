package com.qaqa.springmvc.crud.handler;

import com.qaqa.springmvc.crud.dao.DepartmentDao;
import com.qaqa.springmvc.crud.dao.EmployeeDao;
import com.qaqa.springmvc.crud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

/**
 * Created by thinkpad on 2018/7/6.
 */

@Controller
public class EmployeeHandler {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;


    @ModelAttribute
    public void getEmployee(@RequestParam(value = "id", required = false) Integer id
            , Map<String, Object> map) {
        if (id != null) {
            map.put("employee", employeeDao.get(id));
        }
    }

    @RequestMapping(value = "/emp", method = RequestMethod.PUT)
    public String update(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public String input(@PathVariable("id") Integer id, Map<String, Object> map) {
        map.put("employee", employeeDao.get(id));
        map.put("departments", departmentDao.getDepartments());
        return "input";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
//    @ResponseBody()
    public String delete(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String save(@Valid Employee employee, BindingResult result
            , Map<String, Object> map) {
        System.out.println("save: " + employee);

        System.out.println("save........." + employee);

        if (result.getErrorCount() > 0) {
            System.out.println("出错了!");
            for (FieldError error : result.getFieldErrors()) {
                System.out.println(error.getField() + ":" + error.getDefaultMessage());
            }

            //若验证出错,则转向定值的页面
            map.put("departments", departmentDao.getDepartments());
            return "input";
        }
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @RequestMapping(value = "emp", method = RequestMethod.GET)
    public String input(Map<String, Object> map) {
        map.put("departments", departmentDao.getDepartments());
        map.put("employee", new Employee());
        return "input";
    }

    @RequestMapping("/emps")
    public String list(Map<String, Object> map) {
        map.put("employees", employeeDao.getAll());
        return "list";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("lastName");

    }
}
