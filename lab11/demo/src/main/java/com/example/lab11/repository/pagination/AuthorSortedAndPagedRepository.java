package com.example.lab11.repository.pagination;

import com.example.lab11.model.business.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorSortedAndPagedRepository extends PagingAndSortingRepository<Author, Long> {
    Page<Author> findAll(Pageable pageable);

    Page<Author> findAllByNameContainsIgnoreCase(String nameContains, Pageable pageable);
}
