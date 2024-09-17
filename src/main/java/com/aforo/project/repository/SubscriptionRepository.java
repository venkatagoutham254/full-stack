package com.aforo.project.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aforo.project.model.Subscription;
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {

}
