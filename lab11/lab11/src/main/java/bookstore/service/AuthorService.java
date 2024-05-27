package bookstore.service;

import bookstore.dto.AuthorDTO;
import bookstore.model.business.Author;
import bookstore.repository.AuthorRepository;
import bookstore.repository.pagination.AuthorSortedAndPagedRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AuthorService {
    @Autowired
    AuthorSortedAndPagedRepository authorSortedAndPagedRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());
        author = authorRepository.save(author);
        authorDTO.setId(author.getId());
        return authorDTO;
    }
    public Page<Author> getAll(Integer pageNo, Integer pageSize, String sortBy,
                               String sortDirection, String filterBy, String filterValue) {

        Sort sort = SortOrder
                .getEnumByAlias(sortDirection)
                .apply(Sort.by(sortBy));

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        if (filterBy.equals("name")) {
            return authorSortedAndPagedRepository.findAllByNameContainsIgnoreCase(filterValue, pageable);
        }
        return authorSortedAndPagedRepository.findAll(pageable);
    }

    @AllArgsConstructor
    @Getter
    public enum SortOrder {
        ASCENDING("asc"),
        DESCENDING("desc");
        private String alias;

        public static SortOrder getEnumByAlias(String alias) {
            if (alias == null) {
                return SortOrder.ASCENDING;
            }
            return Arrays.stream(SortOrder.values())
                    .filter(sort -> sort.getAlias().equals(alias.toLowerCase()))
                    .findFirst()
                    .orElse(SortOrder.ASCENDING);
        }

        public Sort apply(Sort currentSort) {
            switch (this) {
                case ASCENDING: {
                    return currentSort.ascending();
                }
                case DESCENDING: {
                    return currentSort.descending();
                }
            }
            return currentSort.ascending();
        }
    }
}
