package org.joda.time.mybatis.handlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class DateTimeTypeHandler implements TypeHandler
{

    public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException
    {
        ps.setTimestamp(i, new java.sql.Timestamp(((DateTime) parameter).toDateTime(DateTimeZone.UTC).toDate()
                .getTime()));
    }

    public Object getResult(ResultSet rs, String columnName) throws SQLException
    {
        return new DateTime(rs.getTimestamp(columnName).getTime()).withZoneRetainFields(DateTimeZone.UTC);
    }

    public Object getResult(CallableStatement cs, int columnIndex) throws SQLException
    {
        return new DateTime(cs.getTimestamp(columnIndex).getTime()).withZoneRetainFields(DateTimeZone.UTC);
    }

}
