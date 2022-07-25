package demo.threads;

import demo.Util;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

import demo.Util;


public class BasicVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromiose){
        Util.echo("Hi, I'm a basic verticle");
        startPromiose.complete();
    }



}
