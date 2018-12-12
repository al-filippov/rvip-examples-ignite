package ru.ulstu.is.ignite;

import org.apache.ignite.Ignite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class IgniteHelloWorld implements CommandLineRunner {
    @Autowired
    private Ignite ignite;

    public static void main(String[] args) {
        SpringApplication.run(IgniteHelloWorld.class, args);
    }

    @Override
    public void run(String... args) {
        while (true) {
            try {
                ignite.compute(ignite.cluster().forRemotes())
                        .run(() -> System.out.printf("[%s] Hello World!%n",
                                new SimpleDateFormat("yyyy.MM.dd HH:mm:ss")
                                        .format(new Date())));
                Thread.sleep(1000);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
