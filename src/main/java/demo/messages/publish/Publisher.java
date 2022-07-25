package demo.messages.publish;

import demo.Util;
import demo.messages.AddressBook;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

public class Publisher extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        String self = this.getClass().getSimpleName();
        Util.echo("hi from: " + self);

        Vertx vertx = getVertx();
        EventBus eventBus = vertx.eventBus();

        vertx.createHttpServer().requestHandler(req -> {
            if (req.uri().equals("/publish")){
                System.out.println(String.format("%s: about to publish message to address: %s", self, AddressBook.ADDRESS_2));
                eventBus.publish(AddressBook.ADDRESS_2, "Hi from publisher");
                req.response().end();
            }
        }).listen(8082);
    }
}
