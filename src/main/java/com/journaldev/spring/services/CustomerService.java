package com.journaldev.spring.services;

import com.journaldev.spring.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    private static List<Customer> list = new ArrayList<>();
    static{
        list.add(new Customer(1, "Alex", "alex@gmail.com","Poland"));
        list.add(new Customer(2, "John", "john@gmail.com","USA"));
        list.add(new Customer(3, "Lulu", "lulu@gmail.com","Brazil"));
    }
    public List listAll(){
        return list;
    }

    public void creatCustomer(Customer customer){
        customer.setId(list.size() + 1);
        list.add(customer);
    }

    public Customer get(long id){
        for(Customer customer : list){
            if(customer.getId() == id){
                return customer;
            }
        }
        return null;
    }

    public void updateCustomer(Customer customer){
        for(Customer c : list){
            if(c.getId() == customer.getId()){
                c.setName(customer.getName());
                c.setEmail(customer.getEmail());
                c.setAddress(customer.getAddress());
                return;
            }
        }
    }

    public Customer delete(long id){
        for(Customer customer : list){
            if(customer.getId() == id){
                list.remove(customer);
                return customer;
            }
        }
        return null;
    }

    public List<Customer> findCustomer(String param){
        List<Customer> resultList = new ArrayList<>();
        for(Customer customer : list){
            if(customer.getName().equals(param)){
                resultList.add(customer);
            }
            if(customer.getEmail().equals(param)){
                resultList.add(customer);
            }
            if(customer.getAddress().equals(param)){
                resultList.add(customer);
            }
        }
        return resultList;
    }


}
