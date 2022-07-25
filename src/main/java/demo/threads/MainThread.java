package demo.threads;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class MainThread {
    public static void main(String[] args) {
        Vertx vertex = Vertx.vertx();

        System.out.println("Number of processors:" + Runtime.getRuntime().availableProcessors());

        // Play with this number. you can see that vertxThreadCount won't be > two times Number of processors
        int instance = 50;

        vertex.deployVerticle(BasicVerticle::new, new DeploymentOptions().setInstances(instance))
                .onSuccess(p -> {
                    long vertxThreadCount = Thread.getAllStackTraces().keySet().stream()
                            .filter(f ->f.getName().startsWith("vert"))
                            .count();

                    System.out.println(String.format("all have been deployed. verticle count is: %s  thread count is %s", instance, vertxThreadCount));
                    System.out.println();
                    System.out.println("List of threads:");

                    Thread.getAllStackTraces().keySet().stream()
                            .filter( f-> f.getName().startsWith("vert"))
                            .forEach(System.out::println);
                })
                .onFailure( f-> System.out.println(f));
    }
}
