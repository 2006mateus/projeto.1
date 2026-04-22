package jogo;

public class EnergyStrategy implements BonfireStrategy {

    @Override
    public void execute(Hero explorer) {
        double energyIncrease = (1.1 * explorer.getMaxEnergy());
        explorer.setMaxEnergy(energyIncrease);
        System.out.println("Sua energia maxima foi aumentada em 10%!");
    }

    @Override
    public String getDescription() {
        return "Polimento: Aumente sua energia máxima em 10%";
    }
}
