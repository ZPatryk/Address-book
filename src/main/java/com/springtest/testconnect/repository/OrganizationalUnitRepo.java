package com.springtest.testconnect.repository;

import com.springtest.testconnect.Entity.OrganizationalUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationalUnitRepo extends JpaRepository<OrganizationalUnit, Long> {
}
