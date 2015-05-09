package controller;

import java.util.ArrayList;

import view.SessionView;

public class SessionController {
	private static ArrayList<SessionView> sessionViews = new ArrayList<>();

	public static void registerView(SessionView view) {
		sessionViews.add(view);
	}

	public void logout() {
		for (SessionView view : sessionViews) {
			if (view != null) {
				view.logout();
			}
		}
		sessionViews.clear();
	}
}
