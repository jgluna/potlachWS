package jgluna.potlach.ws;

import jgluna.potlach.model.Gift;
import jgluna.potlach.model.GiftOrderType;
import jgluna.potlach.repository.GiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class GiftController {
    private final GiftRepository repository;

    private final String basePath = "/gift";

    @Autowired
    public GiftController(GiftRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = basePath, method = RequestMethod.POST)
    public Gift addGift(@RequestBody Gift gift) {
        return repository.save(gift);
    }

    @RequestMapping(value = basePath + "/{id}/report", method = RequestMethod.POST)
    public int reportGift(@PathVariable("id") long giftId) {
        Gift gift = repository.findOne(giftId);
        gift.setReportCount(gift.getReportCount() + 1);
        Gift updatedGift = repository.save(gift);
        return updatedGift.getReportCount();
    }

    @RequestMapping(value = basePath + "/{id}/like", method = RequestMethod.POST)
    public boolean likeGift(@PathVariable("id") long giftId) {
        Gift gift = repository.findOne(giftId);
        gift.setTouchesCount(gift.getTouchesCount() + 1);
        Gift updatedGift = repository.save(gift);
        return updatedGift != null;
    }

    @RequestMapping(value = basePath + "/{id}", method = RequestMethod.GET)
    public Gift getGift(@PathVariable("id") long giftId) {
        return repository.findOne(giftId);
    }

    @RequestMapping(value = basePath, method = RequestMethod.GET)
    public ArrayList<Gift> queryGifts(@RequestParam("allowreported") boolean allowReported,
                                      @RequestParam("order") String orderType,
                                      @RequestParam("pagesize") int pageSize,
                                      @RequestParam("page") int pageNumber) {
        Sort sort = new Sort(Sort.Direction.ASC, GiftOrderType.fromString(orderType).getColumn());
        Pageable page = new PageRequest(pageNumber, pageSize, sort);
        if (allowReported) {
            return repository.queryGifts(page);
        }
        return repository.queryNonReportedGifts(page);
    }

    @RequestMapping(value = basePath + "/queryByTitle", method = RequestMethod.GET)
    public ArrayList<Gift> getGiftsByTitle(@RequestParam("title") String title,
                                           @RequestParam("allowreported") boolean allowReported,
                                           @RequestParam("order") String orderType,
                                           @RequestParam("pagesize") int pageSize,
                                           @RequestParam("page") int pageNumber) {
        Sort sort = new Sort(Sort.Direction.ASC, GiftOrderType.fromString(orderType).getColumn());
        Pageable page = new PageRequest(pageNumber, pageSize, sort);
        if (allowReported) {
            return repository.queryGiftsByTitle(title, page);
        }
        return repository.queryNonReportedGiftsByTitle(title, page);
    }

    @RequestMapping(value = basePath + "/{id}", method = RequestMethod.DELETE)
    public void deleteGift(long giftId) {
        Gift gift = repository.findOne(giftId);
        repository.delete(gift);
    }
}
