package controller;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.JList;

public class ControllerUtils {

	public static Object getDoubleClickedItemInList(MouseEvent e) {
		if (!(e.getSource() instanceof JList)) // assert the source is of type JList
			return null;

		JList<?> list = (JList<?>) e.getSource();
		if (e.getClickCount() == 2) { // if double-click
			Rectangle r = list.getCellBounds(0, list.getLastVisibleIndex()); // bound the list
			if (r != null && r.contains(e.getPoint())) { // forces mouse to be over a part
				return list.getSelectedValue();
			}
		}

		return null;
	}

}
