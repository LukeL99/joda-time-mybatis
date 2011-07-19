package org.joda.time.mybatis.handlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalTime;

public class LocalTimeTypeHandler implements TypeHandler
{

    public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException
    {
        LocalTime time = (LocalTime)parameter;
        DateTime datetime = new DateTime(1970, 1, 1, time.getHourOfDay(), time.getMinuteOfHour(), time.getSecondOfMinute(), 0, DateTimeZone.UTC);
        ps.setTime(i, new Time(datetime.toDate().getTime()));
    }

    public Object getResult(ResultSet rs, String columnName) throws SQLException
    {
        return new LocalTime(rs.getTime(columnName).getTime());
    }

    public Object getResult(CallableStatement cs, int columnIndex) throws SQLException
    {
        return new LocalTime(cs.getTime(columnIndex).getTime());
    }

}
