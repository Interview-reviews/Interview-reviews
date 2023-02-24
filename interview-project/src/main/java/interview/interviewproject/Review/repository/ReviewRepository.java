package interview.interviewproject.Review.repository;

import interview.interviewproject.Review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Modifying
    @Query("update Review r set r.view = r.view + 1 where r.id = :post_id")
    void updateView(Long post_id);

    @Modifying
    @Query("update Review r set r.likes = r.likes + 1 where r.id = :post_id")
    void plus_Like(Long post_id);

    @Modifying
    @Query("update Review r set r.likes = r.likes - 1 where r.id = :post_id")
    void minus_Like(Long post_id);

    List<Review> findByTitleContaining(String keyword);

//    List<Review> findAllByOrderByView_numDesc(`);
//
//    List<Review> findAllByOrderByLikes_numDesc();

}
