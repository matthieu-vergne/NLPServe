package fr.matthieu.vergne.nlpserve;

import io.javalin.Javalin;

public class App {

	public static void main(String[] args) {
		Javalin app = Javalin.create().start(5555);
		registerRoutes(app);
		System.out.println("Listening on http://127.0.0.1:5555");
	}

	public static void registerRoutes(Javalin app) {
		PromptProcessor processor = new PromptProcessor();
		app.post("/agent/prompt", ctx -> ctx.result(processor.process(ctx.body())));
		app.get("/health", ctx -> ctx.status(200));
	}
}
