package com.icss.hr.analyze.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.icss.hr.analyze.dao.AnaDao;
import com.icss.hr.analyze.dto.DeptEmpCount;
import com.icss.hr.analyze.dto.DeptSal;

/**
 * 数据分析业务
 * 
 * @author Administrator
 *
 */
public class AnaService {

	private AnaDao dao = new AnaDao();

	public List<DeptEmpCount> queryEmpCount() throws SQLException {

		return dao.queryEmpCount();
	}

	public List<Map<String, Object>> queryJobAvgSal() throws SQLException {

		return dao.queryJobAvgSal();
	}

	public List<DeptSal> queryDeptSal() throws SQLException {

		return dao.queryDeptSal();
	}

}