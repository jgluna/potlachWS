package jgluna.potlach.ws;

import jgluna.potlach.model.Gift;
import jgluna.potlach.repository.GiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class GiftController {
    private final GiftRepository repository;

    @Autowired
    public GiftController(GiftRepository repository) {
        this.repository = repository;
    }

    public Gift addGift(Gift gift) {
        return repository.save(gift);
    }

    public int reportGift(Gift gift) {
        gift.setReportCount(gift.getReportCount() + 1);
        Gift updatedGift = repository.save(gift);
        return updatedGift.getReportCount();
    }

    public boolean likeGift(Gift gift) {
        gift.setTouchesCount(gift.getTouchesCount() + 1);
        Gift updatedGift = repository.save(gift);
        return updatedGift != null;
    }

    public Gift getGift(long giftId) {
        return repository.findOne(giftId);
    }

    public ArrayList<Gift> getGifts(int limitGifts) {
        return repository.queryGifts(limitGifts, true);
    }

    public ArrayList<Gift> getNonReportedGifts(int limitGifts) {
        return repository.queryGifts(limitGifts, false);
    }

    public ArrayList<Gift> getGiftsByTitle(String title, int limit, boolean allowReported) {
        return repository.queryGiftsByTitle(limit, title, allowReported);
    }

    public ArrayList<Gift> getTopGifts(int limit, boolean allowReported) {
        return repository.queryGiftsOrderByTouched(limit, allowReported);
    }
}
