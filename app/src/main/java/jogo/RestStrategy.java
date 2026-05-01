package jogo;

public class RestStrategy implements BonfireStrategy{
    
    @Override
    public void execute(Hero explorer) {
        double life_increase = 0.3 * explorer.MAX_HEALTH;
        explorer.gainHealth(life_increase);
        System.out.println("Sua vida foi aumentada em 30%!");
    }

    @Override
    public String getDescription() {
        return "Descanso profundo: Recupere cerca de 30% da sua vida máxima";
    }

    @Override
    public int getInt() {
        return 1;
    }
}
