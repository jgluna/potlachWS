package jgluna.potlach.repository;

import jgluna.potlach.model.Gift;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

//TODO agregar queries, sino spring-boot revienta
@Repository
public interface GiftRepository extends CrudRepository<Gift, Long> {

    ArrayList<Gift> queryGifts(int limit, boolean allowReported);

    ArrayList<Gift> queryGiftsOrderByTouched(int limit, boolean allowReported);

    ArrayList<Gift> queryGiftsByTitle(int limit, String partialTitle, boolean allowReported);
}
