package com.example.storeeverything.Repositories;

import com.example.storeeverything.Entities.LinkedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkedItemRepository extends JpaRepository<LinkedItem, Long> {

    LinkedItem findLinkedItemBySourceId(Long id);
}
