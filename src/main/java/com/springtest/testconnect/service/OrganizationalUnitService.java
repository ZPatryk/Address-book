package com.springtest.testconnect.service;

import com.springtest.testconnect.Entity.OrganizationalUnit;
import com.springtest.testconnect.repository.OrganizationalUnitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrganizationalUnitService {

    @Autowired
    private OrganizationalUnitRepo unitRepo;

    public List<OrganizationalUnit> getAllUnits(){
        return unitRepo.findAll();
    }


    //Metoda wyświetlająca wybraną jednostkę organizacyjna
    public String getUnitNameById(Long unitId) {
        OrganizationalUnit unit = unitRepo.findById(unitId).orElse(null);
        return unit != null ? unit.getUnitName() : null;
    }

}
