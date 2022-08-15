package sk.Tsystems.GameStudio.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import sk.Tsystems.GameStudio.SpringClient;
import sk.Tsystems.GameStudio.entity.Score;
import sk.Tsystems.GameStudio.minesweeper.consoleui.ConsoleUI;
import sk.Tsystems.GameStudio.service.*;

import java.util.List;

@SpringBootApplication
@EntityScan(basePackages = "sk.Tsystems.GameStudio.entity")
public class GameStudioSrerver {


    public static void main(String[] args) {

        //SpringApplication.run(SpringClient.class);
        SpringApplication.run(GameStudioSrerver.class);
    }
    @Bean
    public ScoreService scoreService(){
        return new ScoreServiceJPA();
    }
    @Bean
    public ScoreService scoreService2(){
        return new ScoreServiceJDBC();
    }
    @Bean
    public CommentService commentService(){
        return new CommentServiceJPA();
    }








}
