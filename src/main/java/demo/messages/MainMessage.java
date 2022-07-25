package demo.messages;

import demo.messages.p2p.ConsumerA;
import demo.messages.p2p.ConsumerB;
import demo.messages.p2p.Producer;
import demo.messages.publish.Publisher;
import demo.messages.publish.SubscriberA;
import demo.messages.publish.SubscriberB;
import demo.messages.reply.ProducerExpectsReply;
import demo.messages.reply.ReplyingConsumer;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class MainMessage {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        DeploymentOptions deploymentOptions = new DeploymentOptions().setInstances(1);

        //p2p
        vertx.deployVerticle(ConsumerA::new, deploymentOptions);
        vertx.deployVerticle(ConsumerB::new, deploymentOptions);
        vertx.deployVerticle(Producer::new, deploymentOptions);

        //reply
        vertx.deployVerticle(ProducerExpectsReply::new, deploymentOptions);
        vertx.deployVerticle(ReplyingConsumer::new, deploymentOptions);

        //publish
        vertx.deployVerticle(SubscriberA::new, deploymentOptions);
        vertx.deployVerticle(SubscriberB::new, deploymentOptions);
        vertx.deployVerticle(Publisher::new, deploymentOptions);

    }
}
