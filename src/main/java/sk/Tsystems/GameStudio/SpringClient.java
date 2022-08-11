package sk.Tsystems.GameStudio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sk.Tsystems.GameStudio.minesweeper.PlaygroundJPA;
import sk.Tsystems.GameStudio.minesweeper.consoleui.ConsoleUI;
import sk.Tsystems.GameStudio.service.*;

@SpringBootApplication
public class SpringClient {
    public static void main(String[] args) {

        SpringApplication.run(SpringClient.class);
    }

//    @Bean
//    public CommandLineRunner runnerJPA(PlaygroundJPA console){
//        return s->console.play();
//    }
    @Bean
    public PlaygroundJPA consoleJPA(){
        return new PlaygroundJPA();
    }

//    @Bean
//    public CommandLineRunner runner(ConsoleUI console){
//
//        return s -> console.play();
//    }
    @Bean
    public ConsoleUI console(){
        return new ConsoleUI();
    }

    @Bean
    public ScoreService scoreService(){
        return new ScoreServiceJPA();
    }


    @Bean
    public RatingService ratingService(){
        return new RatingServiceJPA();
    }
    @Bean
    public CommentService commentService(){
        return new CommentServiceJPA();
    }





}
