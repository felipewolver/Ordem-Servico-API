package com.os.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.os.api.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {

}
