package org.joda.time.mybatis.handlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;

public class LocalTimeTypeHandler implements TypeHandler
{

    public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException
    {

        LocalTime time = (LocalTime) parameter;
        if (time != null)
        {
            DateTime datetime = new DateTime(1970, 1, 1, time.getHourOfDay(), time.getMinuteOfHour(),
                    time.getSecondOfMinute(), 0);
            ps.setTime(i, new Time(datetime.toDate().getTime()));
        }
        else
        {
            ps.setTime(i, null);
        }
    }

    public Object getResult(ResultSet rs, String columnName) throws SQLException
    {
        Time time = rs.getTime(columnName);
        if (time != null)
        {
            return new LocalTime(time.getTime());
        }
        else
        {
            return null;
        }

    }

    public Object getResult(CallableStatement cs, int columnIndex) throws SQLException
    {
        Time time = cs.getTime(columnIndex);
        if (time != null)
        {
            return new LocalTime(time.getTime());
        }
        else
        {
            return null;
        }
    }

}
