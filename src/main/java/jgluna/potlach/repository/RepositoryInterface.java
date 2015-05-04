package jgluna.potlach.repository;

import jgluna.potlach.model.Gift;
import jgluna.potlach.model.User;

import java.util.ArrayList;

public interface RepositoryInterface {

    Gift saveGift(Gift gift);

    Gift findGiftById(long giftId);

    Gift updateGift(Gift gift);

    ArrayList<Gift> queryGifts(int limit, boolean allowReported);

    User saveUser(User user);

    User findUserByEmail(String email);

    User updateUser(User user);

    ArrayList<Gift> queryGiftsOrderByTouched(int limit, boolean allowReported);

    ArrayList<Gift> queryGiftsByTitle(int limit, String partialTitle, boolean allowReported);

    ArrayList<User> queryTopUsers(int limit);

}
