
package ohtu;

public class Player {
    private String name;
    private String nationality;
    private String team;
    private int goals;
    private int assists;
    private int penalties;
    private int games;

    public void setName(String name, String nationality, String team, 
    int goals, int assists, int games, int penalties) {
        this.name = name;
        this.nationality = nationality;
        this.team = team;
        this.goals = goals;
        this.assists = assists;
        this.games = games;
        this.penalties = penalties;
    }

    public String getName() {
        return name;
    }

    public int getAssists() {
        return assists;
    }

    public String getTeam() {
        return team;
    }

    public String getNationality() {
        return nationality;
    }

    public int getGoals() {
        return goals;
    }

    public int getPenalties() {
        return penalties;
    }

    public int getGames() {
        return games;
    }


    @Override
    public String toString() {
        return name + " team: "  + team + " goals: " + goals + " assists: " + assists;
    }
      
}
