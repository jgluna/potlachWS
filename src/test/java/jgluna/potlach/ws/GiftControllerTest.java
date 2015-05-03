package jgluna.potlach.ws;

import jgluna.potlach.model.Gift;
import jgluna.potlach.repository.RepositoryInterface;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GiftControllerTest {

    private static GiftController giftController;
    private static int limitGifts = 10;
    private static Gift newGift;
    private static Gift savedGift1;
    private static Gift savedGift2;

    @BeforeClass
    public static void setUp() throws Exception {

        newGift = new Gift();
        newGift.setCreationDate(new Date());
        newGift.setImageURL("some url");
        newGift.setParentGift(null);
        newGift.setText("some text");
        newGift.setTitle("some tittle");
        newGift.setUser(null);

        savedGift1 = new Gift();
        savedGift1.setCreationDate(new Date());
        savedGift1.setImageURL("some url");
        savedGift1.setParentGift(null);
        savedGift1.setText("some text");
        savedGift1.setTitle("some tittle");
        savedGift1.setUser(null);
        savedGift1.setId(1);

        savedGift2 = new Gift();
        savedGift2.setCreationDate(new Date());
        savedGift2.setImageURL("some url");
        savedGift2.setParentGift(null);
        savedGift2.setText("some text");
        savedGift2.setTitle("some tittle");
        savedGift2.setUser(null);
        savedGift2.setId(2);

        Gift reportedGift1 = new Gift();
        reportedGift1.setCreationDate(new Date());
        reportedGift1.setImageURL("some url");
        reportedGift1.setParentGift(null);
        reportedGift1.setText("some text");
        reportedGift1.setTitle("some tittle");
        reportedGift1.setUser(null);
        reportedGift1.setId(3);
        reportedGift1.setReportCount(1);

        Gift reportedGift2 = new Gift();
        reportedGift2.setCreationDate(new Date());
        reportedGift2.setImageURL("some url");
        reportedGift2.setParentGift(null);
        reportedGift2.setText("some text");
        reportedGift2.setTitle("some tittle");
        reportedGift2.setUser(null);
        reportedGift2.setId(3);
        reportedGift2.setReportCount(1);

        ArrayList<Gift> reportedGifts = new ArrayList<Gift>();
        reportedGifts.add(savedGift1);
        reportedGifts.add(savedGift2);

        ArrayList<Gift> savedGifts = new ArrayList<Gift>();
        savedGifts.add(savedGift1);
        savedGifts.add(savedGift2);
        savedGifts.add(reportedGift1);
        savedGifts.add(reportedGift2);

        RepositoryInterface repo = mock(RepositoryInterface.class);
        when(repo.saveGift(newGift)).thenReturn(savedGift1);
        when(repo.findGiftById(1)).thenReturn(savedGift1);
        when(repo.findGiftById(2)).thenReturn(savedGift2);
        when(repo.queryGifts(limitGifts, true)).thenReturn(savedGifts);
        when(repo.updateGift(savedGift1)).thenReturn(savedGift1);
        when(repo.updateGift(savedGift2)).thenReturn(savedGift2);
        when(repo.queryGifts(limitGifts, true)).thenReturn(savedGifts);
        when(repo.queryGifts(limitGifts, false)).thenReturn(reportedGifts);

        giftController = new GiftController(repo);

    }

    @AfterClass
    public static void tearDown() throws Exception {

    }

    @Test
    public void addGift() {
        Gift responseGift = giftController.addGift(newGift);
        assertNotNull("responseGift is null", responseGift);
        assertTrue(responseGift.getId() > 0);
    }

    @Test
    public void getGift() {
        Gift gift = giftController.getGift(1);
        assertNotNull(gift);
    }

    @Test
    public void getUserLimitedGifts() {
        ArrayList<Gift> gifts = giftController.getGifts(limitGifts);
        assertNotNull(gifts);
        assertThat(gifts, is(not(empty())));
        assertThat(gifts.size(), is(lessThanOrEqualTo(limitGifts)));
    }

    @Test
    public void reportGift() {
        int reportCount = giftController.reportGift(savedGift1);
        assertThat(reportCount, is(greaterThanOrEqualTo(1)));
    }

    @Test
    public void likeGift() {
        boolean response = giftController.likeGift(savedGift2);
        assertTrue(response);
    }

    @Test
    public void getNonReportedGifts() {
        ArrayList<Gift> gifts = giftController.getNonReportedGifts(limitGifts);
        assertNotNull(gifts);
        assertThat(gifts, hasItem(Matchers.<Gift>hasProperty("reportCount", is(0))));
    }
}