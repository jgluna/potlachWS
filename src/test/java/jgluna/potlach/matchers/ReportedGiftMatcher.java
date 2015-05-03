package jgluna.potlach.matchers;

import jgluna.potlach.model.Gift;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class ReportedGiftMatcher extends TypeSafeMatcher<Gift> {

    private final boolean isReported;

    @Override
    protected boolean matchesSafely(Gift gift) {
        if (isReported) {
            return gift.getReportCount() > 0;
        }
        return gift.getReportCount() == 0;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(" gift.getReportCount() should return ").appendText(isReported ? ">0" : "0");
    }

    public ReportedGiftMatcher(boolean isReported) {
        this.isReported = isReported;
    }

}
