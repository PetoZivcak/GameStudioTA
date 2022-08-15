package sk.Tsystems.GameStudio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
//import sk.Tsystems.GameStudio.minesweeper.PlaygroundJPA;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import sk.Tsystems.GameStudio.minesweeper.PlaygroundJPA;
import sk.Tsystems.GameStudio.minesweeper.consoleui.ConsoleUI;
import sk.Tsystems.GameStudio.service.*;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(type= FilterType.REGEX,pattern = "sk.Tsystems.GameStudio.server.*"))
public class SpringClient {
    public static void main(String[] args) {

        SpringApplication.run(SpringClient.class);
      // new SpringApplicationBuilder(SpringClient.class).web(WebApplicationType.NONE).run(args);
    }

//    @Bean
//    public CommandLineRunner runnerJPA(PlaygroundJPA console){
//        return s->console.play();
//    }
//    @Bean
//    public PlaygroundJPA consoleJPA(){
//        return new PlaygroundJPA();
//    }

//    @Bean
//    public CommandLineRunner runner(ConsoleUI console){
//
//        return s -> console.play();
//    }
//    @Bean
//    public ConsoleUI console(){
//        return new ConsoleUI();
//    }
@Bean
public CommandLineRunner runner(sk.Tsystems.GameStudio.stones.consoleui.ConsoleUI console){

    return s -> console.play();
}
    @Bean
    public sk.Tsystems.GameStudio.stones.consoleui.ConsoleUI console(){
        return new sk.Tsystems.GameStudio.stones.consoleui.ConsoleUI();
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
    @Bean
    public ServiceStudentGroupJPA serviceStudentGroupJPA(){return new ServiceStudentGroupJPA();}
    @Bean
    public ServiceStudentJPA serviceStudentJPA(){return new ServiceStudentJPA();}
    @Bean
    public PlayerService playerServiceJPA(){return new PlayerServiceJPA();}
    @Bean
    public CountryService countryServiceJPA(){return new CountryServiceJPA();}
    @Bean
    public OccupationService occupationServiceJPA(){return  new OccupationServiceJPA();}







}
