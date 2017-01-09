package com.example;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface MyEntityRepository extends PagingAndSortingRepository<MyEntity, String> {
}
