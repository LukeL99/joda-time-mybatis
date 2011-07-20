package org.joda.time.mybatis.handlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class DateTimeTypeHandler implements TypeHandler
{

    public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException
    {
        ps.setTimestamp(i, new Timestamp(((DateTime) parameter).getMillis()));
    }

    public Object getResult(ResultSet rs, String columnName) throws SQLException
    {
        Timestamp ts = rs.getTimestamp(columnName);
        if (ts != null)
        {
             return new DateTime(ts.getTime(), DateTimeZone.UTC);
        }
        else
        {
            return null;
        }
    }

    public Object getResult(CallableStatement cs, int columnIndex) throws SQLException
    {
        Timestamp ts = cs.getTimestamp(columnIndex);
        if (ts != null)
        {
             return new DateTime(ts.getTime(), DateTimeZone.UTC);
        }
        else
        {
            return null;
        }
    }

}
