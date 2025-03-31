package org.example;

import org.example.data.InstrumentFileRepository;
import org.example.data.InstrumentJdbcClientRepository;
import org.example.data.InstrumentRepository;
import org.example.domain.InstrumentService;
import org.example.ui.ConsoleIO;
import org.example.ui.Controller;
import org.example.ui.TextIO;
import org.example.ui.View;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.simple.JdbcClient;
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);



//
//        TextIO io = new ConsoleIO();
//
//        View view = new View(io);
//
////        InstrumentRepository repository = new InstrumentFileRepository("./data/instruments_prod.csv");
//        JdbcClient client = DataHelper.getJdbcClient();
//
//        InstrumentRepository repository = new InstrumentJdbcClientRepository(client);
//
//        InstrumentService service = new InstrumentService(repository);
//
//        Controller controller = new Controller(view, service);
//
//        controller.run();
    }
}