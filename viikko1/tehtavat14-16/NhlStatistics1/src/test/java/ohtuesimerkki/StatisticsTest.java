/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author pekka
 */
public class StatisticsTest {
    
    
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka k‰ytt‰‰ "stubia"
        stats = new Statistics(readerStub);
    }  
    
    @Test
    public void searchTest() {
        Player searchResult = stats.search("Semenko");
        String name = searchResult.getName();
        int goals = searchResult.getGoals();
        int assists = searchResult.getAssists();
        int points = searchResult.getPoints();
        
        Assert.assertEquals(searchResult.getName(), name);
        Assert.assertEquals(searchResult.getGoals(), goals);
        Assert.assertEquals(searchResult.getAssists(), assists);
        Assert.assertEquals(searchResult.getPoints(), points);
    }
    
    @Test
    public void teamCorrect() {
        List<Player> teamResult = stats.team("EDM");
        
        List<Player> actualTeam = new ArrayList<>();
        actualTeam.add(new Player("Semenko", "EDM", 4, 12));
        actualTeam.add(new Player("Kurri",   "EDM", 37, 53));
        actualTeam.add(new Player("Gretzky", "EDM", 35, 89));
        
        Assert.assertEquals(teamResult.get(0).getTeam(), actualTeam.get(0).getTeam());
        Assert.assertEquals(teamResult.get(1).getTeam(), actualTeam.get(1).getTeam());
        Assert.assertEquals(teamResult.get(2).getTeam(), actualTeam.get(2).getTeam());
    }
    
    @Test
    public void topScorerCorrect() {
        List<Player> topScorerResult = stats.topScorers(2);
        
        List<Player> actualTopScorer = new ArrayList<>();
        
        actualTopScorer.add(new Player("Gretzky", "EDM", 35, 89));
        actualTopScorer.add(new Player("Lemieux", "PIT", 45, 54));
        
        Assert.assertEquals(actualTopScorer.get(0).getGoals(), topScorerResult.get(0).getGoals());
        Assert.assertEquals(actualTopScorer.get(1).getGoals(), topScorerResult.get(1).getGoals());
    }
    
}
