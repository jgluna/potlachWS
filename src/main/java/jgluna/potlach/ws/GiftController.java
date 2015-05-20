package jgluna.potlach.ws;

import jgluna.potlach.model.Gift;
import jgluna.potlach.model.GiftOrderType;
import jgluna.potlach.repository.GiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public
    @ResponseBody
    Gift addGift(@RequestBody Gift gift) {
        gift.setTitle(gift.getTitle().toUpperCase());
        return repository.save(gift);
    }

    @RequestMapping(value = basePath + "/{id}/report", method = RequestMethod.PUT)
    public
    @ResponseBody
    Gift reportGift(@PathVariable("id") long giftId) {
        Gift gift = repository.findOne(giftId);
        gift.setReportCount(gift.getReportCount() + 1);
        return repository.save(gift);
    }

    @RequestMapping(value = basePath + "/{id}/like", method = RequestMethod.PUT)
    public
    @ResponseBody
    Gift likeGift(@PathVariable("id") long giftId) {
        Gift gift = repository.findOne(giftId);
        gift.setTouchesCount(gift.getTouchesCount() + 1);
        return repository.save(gift);
    }

    @RequestMapping(value = basePath + "/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<Gift> getGift(@PathVariable("id") long giftId) {
        Gift gift = repository.findOne(giftId);
        if (gift == null) {
            return new ResponseEntity<Gift>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Gift>(gift, HttpStatus.OK);
    }

    @RequestMapping(value = basePath, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<ArrayList<Gift>> queryGifts(@RequestParam("allowreported") boolean allowReported,
                                               @RequestParam("orderfield") String orderField,
                                               @RequestParam("orderdirection") String orderDirection,
                                               @RequestParam("pagesize") int pageSize,
                                               @RequestParam("page") int pageNumber) {
        Sort sort = new Sort(Sort.Direction.fromString(orderDirection), GiftOrderType.fromString(orderField).getColumn());
        Pageable page = new PageRequest(pageNumber, pageSize, sort);
        ArrayList<Gift> queriedGifts = null;
        if (allowReported) {
            queriedGifts = repository.queryGifts(page);
        }
        queriedGifts = repository.queryNonReportedGifts(page);
        if (queriedGifts == null) {
            return new ResponseEntity<ArrayList<Gift>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ArrayList<Gift>>(queriedGifts, HttpStatus.OK);
    }

    @RequestMapping(value = basePath + "/queryByTitle", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<ArrayList<Gift>> getGiftsByTitle(@RequestParam("title") String title,
                                                    @RequestParam("allowreported") boolean allowReported,
                                                    @RequestParam("pagesize") int pageSize,
                                                    @RequestParam("page") int pageNumber) {
        Sort sort = new Sort(Sort.Direction.ASC, GiftOrderType.TITLE.getColumn());
        Pageable page = new PageRequest(pageNumber, pageSize, sort);
        ArrayList<Gift> queriedGifts;
        if (allowReported) {
            queriedGifts = repository.queryGiftsByTitle(title.toUpperCase(), page);
        } else {
            queriedGifts = repository.queryNonReportedGiftsByTitle(title, page);
        }
        if (queriedGifts == null) {
            return new ResponseEntity<ArrayList<Gift>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ArrayList<Gift>>(queriedGifts, HttpStatus.OK);
    }

    @RequestMapping(value = basePath + "/{id}", method = RequestMethod.DELETE)
    public void deleteGift(long giftId) {
        Gift gift = repository.findOne(giftId);
        repository.delete(gift);
    }
}
