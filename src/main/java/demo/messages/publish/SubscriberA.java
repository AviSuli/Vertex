package demo.messages.publish;

import demo.Util;
import demo.messages.AddressBook;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;

public class SubscriberA extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        Util.echo("hi from: " + this.getClass().getSimpleName());

        Vertx vertx = getVertx();
        EventBus eventBus = vertx.eventBus();
        eventBus.consumer(AddressBook.ADDRESS_2, this::onMessage);

    }

    void onMessage(Message<String> message){
        String self = this.getClass().getSimpleName();
        System.out.println(String.format("%s: received message into address:%s, message content is: %s", self, AddressBook.ADDRESS_2, message.body()));
    }
}
