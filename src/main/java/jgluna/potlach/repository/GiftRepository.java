package jgluna.potlach.repository;

import jgluna.potlach.model.Gift;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface GiftRepository extends Repository<Gift, Long> {

    void delete(Gift deleted);

    Gift findOne(Long id);

    Gift save(Gift persisted);

    @Query("select g from Gift g where g.reportCount=0")
    ArrayList<Gift> queryNonReportedGifts(Pageable pageable);

    @Query("select g from Gift g")
    ArrayList<Gift> queryGifts(Pageable pageable);

    @Query("select g from Gift g where g.title like :title%")
    ArrayList<Gift> queryGiftsByTitle(@Param("title") String partialTitle, Pageable pageable);

    @Query("select g from Gift g where g.title like :title% and g.reportCount=0")
    ArrayList<Gift> queryNonReportedGiftsByTitle(@Param("title") String partialTitle, Pageable pageable);
}
