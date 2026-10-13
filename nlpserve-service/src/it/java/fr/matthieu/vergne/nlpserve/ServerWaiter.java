package fr.matthieu.vergne.nlpserve;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class ServerWaiter {

	private static final ServerWaiter INSTANCE = new ServerWaiter("http://127.0.0.1:5555");

	private static final long TIMEOUT_MS = 12_000;
	private static final long SLEEP_MS = 200;
	private static final int CONNECT_TIMEOUT_MS = 100;
	private static final int READ_TIMEOUT_MS = 100;
	private final String baseUrl;

	private ServerWaiter(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public static ServerWaiter instance() {
		return INSTANCE;
	}

	public void waitUntilReady() throws Exception {
		long start = System.currentTimeMillis();
		while (System.currentTimeMillis() - start < TIMEOUT_MS) {
			try {
				URL url = URI.create(baseUrl + "/health").toURL();
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setConnectTimeout(CONNECT_TIMEOUT_MS);
				conn.setReadTimeout(READ_TIMEOUT_MS);
				int code = conn.getResponseCode();
				conn.disconnect();
				if (code == 200) {
					return;
				}
			} catch (Exception ignored) {
				Thread.sleep(SLEEP_MS);
			}
		}
		throw new AssertionError("Server did not become ready in time");
	}
}
