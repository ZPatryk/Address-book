package com.springtest.testconnect.service;
import com.springtest.testconnect.repository.OrganizationalUnitRepo;
import com.springtest.testconnect.Entity.OrganizationalUnit;
import com.springtest.testconnect.Entity.User;
import com.springtest.testconnect.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    @Autowired
    private OrganizationalUnitRepo organizationalUnitRepo;

    // Metoda grupująca użytkowników według jednostek organizacyjnych, posortowanych alfabetycznie
    public Map<OrganizationalUnit, List<User>> getAllUsersGroupedByUnit() {
        List<User> allUsers = userRepo.findAll();
        Map<OrganizationalUnit, List<User>> usersByUnit = new LinkedHashMap<>(); // LinkedHashMap zachowa kolejność dodawania jednostek organizacyjnych

        // Sortowanie jednostek organizacyjnych alfabetycznie na podstawie ich nazw
        List<OrganizationalUnit> sortedUnits = organizationalUnitRepo.findAll();
        sortedUnits.sort(Comparator.comparing(OrganizationalUnit::getUnitName));

        for (User user : allUsers) {
            if (user.getUnitId() != null) {
                OrganizationalUnit unit = organizationalUnitRepo.findById(user.getUnitId()).orElse(null);
                if (unit != null && sortedUnits.contains(unit)) {
                    usersByUnit.computeIfAbsent(unit, k -> new ArrayList<>()).add(user);
                }
            }
        }

        return usersByUnit;
    }

    // Metoda zwracająca listę użytkowników bez przypisanych jednostek organizacyjnych
    public List<User> getUsersWithoutUnit() {
        List<User> allUsers = userRepo.findAll();
        List<User> usersWithoutUnit = new ArrayList<>();

        for (User user : allUsers) {
            if (user.getUnitId() == null) {
                usersWithoutUnit.add(user);
            }
        }

        return usersWithoutUnit;
    }


    // Metoda zwracająca użytkowników dla określonej jednostki organizacyjnej
    public List<User> getUsersByUnitId(Long unitId) {
        return userRepo.findByUnitId(unitId);
    }

    // Metoda zwracająca użytkowników po wpisaniu imienia lub zazwiska
    public List<User>searchUsersByFname(String query){
        return userRepo.findByFnameContainingIgnoreCase(query);
    }



}


    /*// Metoda zwracająca użytwoników tylko z wybranej jednostki organizacyjnej
    public List<User> getOnlyOneUnit(){
        List<User> OneUnit =userRepo.findByUnitId(1L);
        return OneUnit;
    }

    public List<User> getOnlyTwoUnit(){
        List<User> TwoUnit =userRepo.findByUnitId(2L);
        return TwoUnit;
    }*/

