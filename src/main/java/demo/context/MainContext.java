package demo.context;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class MainContext {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(ShowMeTheVerticleContext::new, new DeploymentOptions().setInstances(2));
    }
}
