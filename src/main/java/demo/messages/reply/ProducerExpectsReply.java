package demo.messages.reply;

import demo.Util;
import demo.messages.AddressBook;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;

public class ProducerExpectsReply extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        String self = this.getClass().getSimpleName();
        Util.echo("hi from: " + self);

        Vertx vertx = getVertx();
        EventBus eventBus = vertx.eventBus();

        vertx.createHttpServer().requestHandler(req -> {
            if (req.uri().equals("/reply")){
                System.out.println(String.format("%s: about to send message to address: %s", self, AddressBook.ADDRESS_3));
                System.out.println(String.format("%s: is expecting a reply back", self));

                eventBus.request(AddressBook.ADDRESS_3, "Hi from producer",this::processResponse);
                req.response().end();
            }
        }).listen(8084);
    }

    private void processResponse(AsyncResult<Message<String>> response){
        System.out.println(String.format("%s: received a response back: %s", this.getClass().getSimpleName(), response.result().body()));
    }
}
