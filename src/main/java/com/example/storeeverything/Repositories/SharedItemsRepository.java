package com.example.storeeverything.Repositories;

import com.example.storeeverything.Entities.Item;
import com.example.storeeverything.Entities.SharedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SharedItemsRepository extends JpaRepository<SharedItem, Long> {

    List<SharedItem> findAllByUserId(Long id);


}
