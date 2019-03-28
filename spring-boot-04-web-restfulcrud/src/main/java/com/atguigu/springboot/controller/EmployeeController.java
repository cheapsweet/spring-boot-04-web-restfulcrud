package com.atguigu.springboot.controller;

import com.atguigu.springboot.dao.DepartmentDao;
import com.atguigu.springboot.dao.EmployeeDao;
import com.atguigu.springboot.entities.Department;
import com.atguigu.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @GetMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps", employees);

        return "emp/list";
    }

    // 来到员工添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model) {
        // 来到添加页面，查出所有的部门
        Collection<Department> departments = departmentDao.getDepartments();
        //System.out.println(" [打印departments] ");
        //System.out.println(departments.toString());
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    // 员工添加
    // springMVC自动将请求参数和入参对象的属性进行一一绑定，
    // 要求请求参数的名字和javabean的属性一致
    @PostMapping("/emp")
    public String addEmp(Employee employee) {

        // 来到员工列表页面

        System.out.println("保存的员工信息：" + employee);
        employeeDao.save(employee);

        // redirect: 表示重定向到一个地址 /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/emps";
    }

}
