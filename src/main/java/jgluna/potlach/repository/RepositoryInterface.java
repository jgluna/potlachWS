package jgluna.potlach.repository;

import jgluna.potlach.model.Gift;

import java.util.ArrayList;

public interface RepositoryInterface {

    Gift saveGift(Gift gift);

    Gift findGiftById(long giftId);

    Gift updateGift(Gift gift);

    ArrayList<Gift> queryGifts(int limit, boolean allowReported);

}
