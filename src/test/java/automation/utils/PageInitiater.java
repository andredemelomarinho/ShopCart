package automation.utils;

import java.util.ArrayList;
import java.util.List;

import automation.utils.PageListener;

public class PageInitiater {
	private List<PageListener> listeners = new ArrayList<PageListener>();

    public void addListener(PageListener toAdd) {
        listeners.add(toAdd);
    }

    public void closeWindow() {
        // Notify everybody that may be interested.
        for (PageListener hl : listeners)
            hl.windowClosed();
    }

}
