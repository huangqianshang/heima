package com.hqs.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;


import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {
    private static DataSource ds;

    static {
        try {
            //加载配置文件
            Properties pro = new Properties ();
            //获取流
            InputStream in = JDBCUtil.class.getClassLoader ().getResourceAsStream ("druid_1.properties");
            //装载
            pro.load (in);
            //创建
            ds = DruidDataSourceFactory.createDataSource (pro);
        } catch (Exception e) {
            e.printStackTrace ( );
        }

    }

    /**
     * 获取连接
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection ();
    }

    /**
     * 获取DruidDataSource对象
     */
    public static DataSource getDs() {
        return ds;
    }
}
