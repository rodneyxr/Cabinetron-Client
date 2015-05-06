package controller;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

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

	public static String blobify(Serializable o) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = null;
		byte[] bytes;
		try {
			out = new ObjectOutputStream(bos);
			out.writeObject(o);
			bytes = bos.toByteArray();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} finally {
			try {
				if (out != null)
					out.close();
				bos.close();
			} catch (IOException ioe) {
				return null;
			}
		}
		return Base64.getEncoder().encodeToString(bytes);// new String(bytes);
	}

	public static Object deblobify(String blob) {
		byte[] bytes = Base64.getDecoder().decode(blob);
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInputStream in = null;
		Object o;
		try {
			in = new ObjectInputStream(bis);
			o = in.readObject();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		} finally {
			try {
				bis.close();
				if (in != null)
					in.close();
			} catch (IOException ioe) {
				return null;
			}
		}
		return o;
	}
}
