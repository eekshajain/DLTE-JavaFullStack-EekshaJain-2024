package com.employeedao.employee.services;

import com.employeedao.employee.entity.EmployeeAddress;
import com.employeedao.employee.entity.EmployeeBasicDetails;
import com.employeedao.employee.exception.EmployeeException;
import com.employeedao.employee.remotes.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

@Service
public class EmployeeImplementation implements EmployeeRepository {
    ResourceBundle resourceBundle= ResourceBundle.getBundle("employee");
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public EmployeeBasicDetails saveAll(EmployeeBasicDetails employee) {
        int acknowledgeBasic=jdbcTemplate.update("insert into EmployeeBasicDetails values(?,?,?,?,?,?)",
                new Object[]{
                        employee.getEmployeeID(),
                        employee.getFirstName(),
                        employee.getMiddleName(),
                        employee.getLastName(),
                        employee.getPhoneNumber(),
                        employee.getEmailID()
                });
        int acknowledgeTempAddress=jdbcTemplate.update("insert into EmployeeAddress(ADDRESS_ID,EMPLOYEE_ID,HOUSE_NAME,STREET_NAME,CITY_NAME,STATE_NAME,PIN_CODE,IS_TEMPORARY) values (address_seq.nextval,?,?,?,?,?,?,0)",
                new Object[]{
                        employee.getTemporaryEmployeeAddress().getHouseName(),
                        employee.getTemporaryEmployeeAddress().getHouseStreet(),
                        employee.getTemporaryEmployeeAddress().getCityName(),
                        employee.getTemporaryEmployeeAddress().getStateName(),
                        employee.getTemporaryEmployeeAddress().getPinCode()
                });

        int acknowledgePermAddress=jdbcTemplate.update("insert into EmployeeAddress(ADDRESS_ID,EMPLOYEE_ID,HOUSE_NAME,STREET_NAME,CITY_NAME,STATE_NAME,PIN_CODE,IS_TEMPORARY) values (address_seq.nextval,?,?,?,?,?,?,1)",
                new Object[]{
                        employee.getPermanentEmployeeAddress().getHouseName(),
                        employee.getPermanentEmployeeAddress().getHouseStreet(),
                        employee.getPermanentEmployeeAddress().getCityName(),
                        employee.getPermanentEmployeeAddress().getStateName(),
                        employee.getPermanentEmployeeAddress().getPinCode()
                });
        if(acknowledgeBasic==0) throw new EmployeeException(resourceBundle.getString("basic.details.insertion.failed"));
        if(acknowledgeTempAddress==0) throw new EmployeeException(resourceBundle.getString("temporary.address.insertion.failed"));
        if(acknowledgePermAddress==0) throw new EmployeeException(resourceBundle.getString("permanent.address.insertion.failed"));
        else return employee;
    }

    @Override
    public EmployeeBasicDetails displayRequired(int employeeID) {
     EmployeeBasicDetails employeeBasicDetails= jdbcTemplate.queryForObject("SELECT e.EMPLOYEE_ID, " +
             "e.FIRST_NAME, " +
             "e.MIDDLE_NAME, " +
             "e.LAST_NAME, " +
             "e.PHONE_NUMBER, " +
             "e.EMAIL_ID, " +
             "ta.HOUSE_NAME AS TEMP_HOUSE_NAME, " +
             "ta.STREET_NAME AS TEMP_STREET_NAME, " +
             "ta.CITY_NAME AS TEMP_CITY_NAME, " +
             "ta.STATE_NAME AS TEMP_STATE_NAME, " +
             "ta.PIN_CODE AS TEMP_PIN_CODE, " +
             "pa.HOUSE_NAME AS PERM_HOUSE_NAME, " +
             "pa.STREET_NAME AS PERM_STREET_NAME, " +
             "pa.CITY_NAME AS PERM_CITY_NAME, " +
             "pa.STATE_NAME AS PERM_STATE_NAME, " +
             "pa.PIN_CODE AS PERM_PIN_CODE " +
             "FROM EmployeeBasicDetails e " +
             "INNER JOIN EmployeeAddress ta ON e.EMPLOYEE_ID = ta.EMPLOYEE_ID AND ta.IS_TEMPORARY = 1 " +
             "INNER JOIN EmployeeAddress pa ON e.EMPLOYEE_ID = pa.EMPLOYEE_ID AND pa.IS_TEMPORARY = 0 " +
             "WHERE e.EMPLOYEE_ID=?",
             new Object[]{employeeID},
             new EmployeeRowMapper());
           if(employeeBasicDetails==null) throw new EmployeeException(resourceBundle.getString("no.employee"));
           else return employeeBasicDetails;
    }

    @Override
    public List<EmployeeBasicDetails> displayBasedOnPinCode(int pinCode) {
        List<EmployeeBasicDetails> employeeBasicDetails;
        employeeBasicDetails=jdbcTemplate.query("SELECT e.EMPLOYEE_ID, " +
                "e.FIRST_NAME, " +
                "e.MIDDLE_NAME, " +
                "e.LAST_NAME, " +
                "e.PHONE_NUMBER, " +
                "e.EMAIL_ID, " +
                "ta.HOUSE_NAME AS TEMP_HOUSE_NAME, " +
                "ta.STREET_NAME AS TEMP_STREET_NAME, " +
                "ta.CITY_NAME AS TEMP_CITY_NAME, " +
                "ta.STATE_NAME AS TEMP_STATE_NAME, " +
                "ta.PIN_CODE AS TEMP_PIN_CODE, " +
                "pa.HOUSE_NAME AS PERM_HOUSE_NAME, " +
                "pa.STREET_NAME AS PERM_STREET_NAME, " +
                "pa.CITY_NAME AS PERM_CITY_NAME, " +
                "pa.STATE_NAME AS PERM_STATE_NAME, " +
                "pa.PIN_CODE AS PERM_PIN_CODE " +
                "FROM EmployeeBasicDetails e " +
                "INNER JOIN EmployeeAddress ta ON e.EMPLOYEE_ID = ta.EMPLOYEE_ID AND ta.IS_TEMPORARY = 1 " +
                "INNER JOIN EmployeeAddress pa ON e.EMPLOYEE_ID = pa.EMPLOYEE_ID AND pa.IS_TEMPORARY = 0 " +
                "WHERE ta.PIN_CODE=? or pa.PIN_CODE=?",
                new Object[]{pinCode},
                new EmployeeRowMapper()
        );
        if (employeeBasicDetails.size()==0) throw new EmployeeException(resourceBundle.getString("no.details.based.on.pincode"));
        else return employeeBasicDetails;
    }

    @Override
    public List<EmployeeBasicDetails> displayAll() {
        List<EmployeeBasicDetails> employeeBasicDetails;
        employeeBasicDetails=jdbcTemplate.query("SELECT e.EMPLOYEE_ID, " +
                        "e.FIRST_NAME, " +
                        "e.MIDDLE_NAME, " +
                        "e.LAST_NAME, " +
                        "e.PHONE_NUMBER, " +
                        "e.EMAIL_ID, " +
                        "ta.HOUSE_NAME AS TEMP_HOUSE_NAME, " +
                        "ta.STREET_NAME AS TEMP_STREET_NAME, " +
                        "ta.CITY_NAME AS TEMP_CITY_NAME, " +
                        "ta.STATE_NAME AS TEMP_STATE_NAME, " +
                        "ta.PIN_CODE AS TEMP_PIN_CODE, " +
                        "pa.HOUSE_NAME AS PERM_HOUSE_NAME, " +
                        "pa.STREET_NAME AS PERM_STREET_NAME, " +
                        "pa.CITY_NAME AS PERM_CITY_NAME, " +
                        "pa.STATE_NAME AS PERM_STATE_NAME, " +
                        "pa.PIN_CODE AS PERM_PIN_CODE " +
                        "FROM EmployeeBasicDetails e " +
                        "INNER JOIN EmployeeAddress ta ON e.EMPLOYEE_ID = ta.EMPLOYEE_ID AND ta.IS_TEMPORARY = 1 " +
                        "INNER JOIN EmployeeAddress pa ON e.EMPLOYEE_ID = pa.EMPLOYEE_ID AND pa.IS_TEMPORARY = 0 ",
                new Object[]{},
                new EmployeeRowMapper()
        );
       // if(employeeBasicDetails.size()==0) throw new EmployeeException(resourceBundle.getString(""));
         return employeeBasicDetails;
    }

    @Override
    public boolean doesEmployeeExists(int empID) {
        EmployeeBasicDetails employee = jdbcTemplate.queryForObject(
                "SELECT ebd.employee_id FROM employeebasicdetails ebd WHERE ebd.employee_id = ?",
                new Object[]{empID},
                new EmployeeRowMapper()
        );
        return employee != null;

    }

    @Override
    public boolean deleteByID(int employeeID) {
        return false;
    }

    public class EmployeeRowMapper implements RowMapper<EmployeeBasicDetails> {

        @Override
        public EmployeeBasicDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
          EmployeeBasicDetails employeeBasicDetails=new EmployeeBasicDetails();
            EmployeeAddress tempAddress=new EmployeeAddress();
            EmployeeAddress permAddress=new EmployeeAddress();
          employeeBasicDetails.setEmployeeID(rs.getInt(1));
          employeeBasicDetails.setFirstName(rs.getString(2));
          employeeBasicDetails.setMiddleName(rs.getString(3));
          employeeBasicDetails.setLastName(rs.getString(4));
          employeeBasicDetails.setLastName(rs.getString(5));
          employeeBasicDetails.setPhoneNumber(rs.getLong(6));
          employeeBasicDetails.setEmailID(rs.getString(7));
          tempAddress.setHouseName(rs.getString(8));
          tempAddress.setHouseStreet(rs.getString(9));
          tempAddress.setCityName(rs.getString(10));
          tempAddress.setStateName(rs.getString(11));
          tempAddress.setPinCode(rs.getInt(12));
          permAddress.setHouseName(rs.getString(13));
          permAddress.setHouseStreet(rs.getString(14));
          permAddress.setCityName(rs.getString(15));
          permAddress.setStateName(rs.getString(16));
          permAddress.setPinCode(rs.getInt(17));
          employeeBasicDetails.setTemporaryEmployeeAddress(tempAddress);
          return employeeBasicDetails;
        }
    }
}
