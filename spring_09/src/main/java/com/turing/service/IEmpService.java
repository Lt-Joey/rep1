package com.turing.service;

import java.util.List;

import com.turing.entity.Emp;

public interface IEmpService {
	List<Emp> findAll();
	
	//��ѯ�û����Ƿ����
	boolean findByName(String name);
	
	//���
	int addEmp(Emp emp);
}
