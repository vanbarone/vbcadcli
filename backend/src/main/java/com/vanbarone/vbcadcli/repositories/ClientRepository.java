package com.vanbarone.vbcadcli.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vanbarone.vbcadcli.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
