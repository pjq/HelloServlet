
package net.impjq.twitter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;

import net.impjq.base.GetUserTimeline;

/**
 * Get Twitter UserTimeline
 * 
 * @author pjq
 */
public class GetUserTimelineImpl extends GetUserTimeline {

    /**
	 * 
	 */
    private static final long serialVersionUID = -492624368602865034L;

    @Override
    protected void getTimeline() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doGet(req, resp);

        if (isFromWeb()) {
            printHeader();
        }

        ResponseList<Status> responseList = null;
        try {
            responseList = createTwitterInstance().getHomeTimeline();
            if (isFromWeb()) {
                out.println("<br>");
            }
            out.println("\n=========GetHomeTimeline Response:========\n");
            if (isFromWeb()) {
                out.println("<br>");
            }
            printResponseList(responseList);

            responseList = createTwitterInstance().getUserTimeline();
            if (isFromWeb()) {
                out.println("<br>");
            }
            out.println("\n========GetUserTimeline Response:========\n");
            if (isFromWeb()) {
                out.println("<br>");
            }
            printResponseList(responseList);

        } catch (TwitterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            out.println(e.getMessage());
        }

        if (isFromWeb()) {
            printFooter();
        }
    }
}
