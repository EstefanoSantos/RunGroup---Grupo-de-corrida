package com.estefanosantos.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estefanosantos.club.models.Event;

public interface EventRepository extends JpaRepository<Event, Long>{

}
