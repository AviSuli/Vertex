package demo.context;

import io.vertx.core.*;

public class ShowMeTheVerticleContext extends AbstractVerticle {

    @Override
    public void start(Promise<Void> promise) throws Exception {
        Vertx vertx = getVertx();
        Context context = vertx.getOrCreateContext();

        System.out.println(context.config());
        System.out.println(context.isEventLoopContext());
        System.out.println(context.isWorkerContext());
        System.out.println(context.getInstanceCount());

        promise.complete();
    }
}
