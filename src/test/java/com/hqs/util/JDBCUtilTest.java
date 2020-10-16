package com.hqs.util;

import java.sql.SQLException;

import org.junit.Test;

public class JDBCUtilTest {
	@Test
	public void s() {
		try {
			
			System.out.println(JDBCUtil.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
