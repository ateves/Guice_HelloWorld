import com.google.inject.*;

// ---------------------------------------------
// Main Application File (All-in-One Guice Demo)
// ---------------------------------------------
public class GuiceExample {

    // -------------------------
    // 1. Service Abstraction
    // -------------------------
    public interface GreetingService {
        void greet();
    }

    // -------------------------
    // 2. Service Implementation
    // -------------------------
    public static class HelloGreetingService implements GreetingService {
        @Override
        public void greet() {
            System.out.println("Hello from Guice!");
        }
    }

    // -------------------------
    // 3. Guice Module (Bindings)
    // -------------------------
    public static class AppModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(GreetingService.class).to(HelloGreetingService.class);
        }
    }

    // -------------------------
    // 4. Application Logic
    // -------------------------
    public static class App {
        private final GreetingService greetingService;

        @Inject
        public App(GreetingService greetingService) {
            this.greetingService = greetingService;
        }

        public void run() {
            greetingService.greet();
        }
    }

    // -------------------------
    // 5. Main Entry Point
    // -------------------------
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppModule());
        App app = injector.getInstance(App.class);
        app.run();
    }
}
