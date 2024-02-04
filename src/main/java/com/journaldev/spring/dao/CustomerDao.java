package com.journaldev.spring.dao;

import com.journaldev.spring.model.Customer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerDao {
    JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public int save(Customer c){
        String sql="insert into customers(name,email,address) values('"+c.getName()+"','"+c.getEmail()+"','"+c.getAddress()+"')";
        return template.update(sql);
    }
    public int update(Customer c){
        String sql="update customers set name='"+c.getName()+"', email='"+c.getEmail()+"',address='"+c.getAddress()+"' where id="+c.getId()+"";
        return template.update(sql);
    }
    public int delete(int id){
        String sql="delete from customers where id="+id+"";
        return template.update(sql);
    }
    public Customer getCustomerById(int id){
        String sql="select * from customers where id=?";
        return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Customer>(Customer.class));
    }
    public List<Customer> getCustomers(){
        return template.query("select * from customers",new RowMapper<Customer>(){
            public Customer mapRow(ResultSet rs, int row) throws SQLException {
                Customer c = new Customer();
                c.setId(rs.getInt(1));
                c.setName(rs.getString(2));
                c.setEmail(rs.getString(3));
                c.setAddress(rs.getString(4));
                return c;
            }
        });
    }
    public List<Customer> getCustomersByParameter(String param){
        return template.query("select * from customers where " + param +"= ", new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
                Customer c = new Customer();
                c.setId(resultSet.getInt(1));
                c.setName(resultSet.getString(2));
                c.setEmail(resultSet.getString(3));
                c.setAddress(resultSet.getString(4));
                return c;
            }
        });
    }

}
