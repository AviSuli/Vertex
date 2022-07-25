package demo.messages.reply;

import demo.Util;
import demo.messages.AddressBook;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;

public class ReplyingConsumer extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        Util.echo("hi from: " + this.getClass().getSimpleName());

        Vertx vertx = getVertx();
        EventBus eventBus = vertx.eventBus();
        eventBus.consumer(AddressBook.ADDRESS_3, this::onMessage);

    }

    void onMessage(Message<String> message){
        String self = this.getClass().getSimpleName();
        System.out.println(String.format("%s: received message into address:%s, message content is: %s", self, AddressBook.ADDRESS_3, message.body()));
        System.out.println(String.format("%s: is about to reply back to address %s", self, message.replyAddress())); //  this address is created by Vertex

        message.reply("replying back");

    }
}
