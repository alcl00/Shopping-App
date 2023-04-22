package com.shop.shoppingapp.contents;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentsRepository extends JpaRepository<Contents, ContentsId>{

	List<Contents> findByOrdersId(Long orderID);
}
