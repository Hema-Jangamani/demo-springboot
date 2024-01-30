package com.example.demo.service;

import com.example.demo.model.Address;
import com.example.demo.model.Employee;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressService addressService;

    @Transactional
    public Employee addEmployee(Employee employee) throws Exception{
        Employee employeeSavedToDB = this.employeeRepository.save(employee);

        Address address = null;
        address.setId(123L);
        address.setAddress("Varanasi");
        address.setEmployee(employee);

        this.addressService.addAddress(address);
        return employeeSavedToDB;
    }
}
