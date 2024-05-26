package bookstore.controller;

import bookstore.dto.AuthorDTO;
import bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/authors")
public class AuthorController {
    @Autowired
    AuthorService authorService;
    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") Integer pageNo,
                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                    @RequestParam(defaultValue = "id") String sortBy,
                                    @RequestParam(defaultValue = "desc") String sortDirection,
                                    @RequestParam(defaultValue = "null") String filterBy,
                                    @RequestParam(defaultValue = "null") String filterValue
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authorService.getAll(pageNo, pageSize, sortBy, sortDirection, filterBy, filterValue));
    }
    @PostMapping
    public AuthorDTO createAuthor(@RequestBody AuthorDTO authorDTO) {
        return authorService.createAuthor(authorDTO);
    }
}
