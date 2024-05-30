package bookstore.model.business;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "recommended_order",schema="book_store")
public class RecommendedOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_a_id")
    private Book bookA;

    @ManyToOne
    @JoinColumn(name = "book_b_id")
    private Book bookB;


    public Long getBookAId() {
        return bookA.getId();
    }
    public Long getBookBId() {
        return bookB.getId();
    }

}