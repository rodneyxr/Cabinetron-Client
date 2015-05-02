package controller;

import java.util.ArrayList;

import view.SessionView;

public class SessionController {
	private static ArrayList<SessionView> sessionViews = new ArrayList<>();

	public static void registerView(SessionView view) {
		sessionViews.add(view);
		// System.out.println(view.getName() + " registered");
	}

	public void logout() {
		for (SessionView view : sessionViews) {
			if (view != null) {
				view.logout();
				// System.out.println(view.getName() + " hidden");
			}
		}
		sessionViews.clear();
	}
}
