package jgluna.potlach.ws;

import jgluna.potlach.model.Gift;
import jgluna.potlach.repository.RepositoryInterface;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class GiftController {
    private final RepositoryInterface repository;

    public GiftController(RepositoryInterface repository) {
        this.repository = repository;
    }

    public Gift addGift(Gift gift) {
        return repository.saveGift(gift);
    }

    public int reportGift(Gift gift) {
        gift.setReportCount(gift.getReportCount() + 1);
        Gift updatedGift = repository.updateGift(gift);
        return updatedGift.getReportCount();
    }

    public boolean likeGift(Gift gift) {
        gift.setTouchesCount(gift.getTouchesCount() + 1);
        Gift updatedGift = repository.updateGift(gift);
        return updatedGift != null;
    }

    public Gift getGift(long giftId) {
        return repository.findGiftById(giftId);
    }

    public ArrayList<Gift> getGifts(int limitGifts) {
        return repository.queryGifts(limitGifts, true);
    }

    public ArrayList<Gift> getNonReportedGifts(int limitGifts) {
        return repository.queryGifts(limitGifts, false);
    }
}
