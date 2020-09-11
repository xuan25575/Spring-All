package com.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.pojo.Account;

@Controller
public class IndexController {
	
	@RequestMapping("/account")
	public String index(Model m) {
		List<Account> list = new ArrayList<Account>();
		list.add(new Account("huang_2", "黄小2", "admin", "超级管理员", "13322222222"));
		list.add(new Account("Laohei", "老黑", "33234", "管理员", "13322222222"));
		list.add(new Account("hanke","汉客","33323","运维人员","15633338888"));
		list.add(new Account("Lisa", "丽萨", "lisahao", "清算人员", "15633338888"));
        m.addAttribute("accountList",list);
        return "account";
	}
}
