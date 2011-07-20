package org.joda.time.mybatis.handlers;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

public class LocalDateTypeHandler implements TypeHandler
{

    public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException
    {
        LocalDate date = (LocalDate) parameter;
        ps.setDate(i, new Date(date.toDateTimeAtStartOfDay().toDate().getTime()));
    }

    public Object getResult(ResultSet rs, String columnName) throws SQLException
    {
        Date date = rs.getDate(columnName);
        if (date != null)
        {
            return new LocalDate(date.getTime(), DateTimeZone.UTC);
        }
        else
        {
            return null;
        }
    }

    public Object getResult(CallableStatement cs, int columnIndex) throws SQLException
    {
        Date date = cs.getDate(columnIndex);
        if (date != null)
        {
            return new LocalDate(date.getTime(), DateTimeZone.UTC);
        }
        else
        {
            return null;
        }
    }

}
