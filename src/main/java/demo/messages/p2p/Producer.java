package demo.messages.p2p;

import demo.Util;
import demo.messages.AddressBook;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

public class Producer extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        String self = this.getClass().getSimpleName();
        Util.echo("hi from: " + self);

        Vertx vertx = getVertx();
        EventBus eventBus = vertx.eventBus();

        vertx.createHttpServer().requestHandler(req -> {
            if (req.uri().equals("/p2p")){
                System.out.println(String.format("%s: about to send message to address: %s", self, AddressBook.ADDRESS_1));
                eventBus.send(AddressBook.ADDRESS_1, "Hi from producer");
                req.response().end();
            }
        }).listen(8083);

    }
}
