package NuocUong;

public abstract class DrinkingWater  {
    private String idWater;
    private String nameWater;

    // Getter
    public String getIdWater() {
        return idWater;
    }
    public String getNameWater() {
        return nameWater;
    }

    // Setter
    public void setIdWater(String idWater) {
        this.idWater = idWater;
    }
    public void setNameWater(String nameWater) {
        this.nameWater = nameWater;
    }

    abstract void setInfo();
    abstract void displayInfo();
    abstract void modifyInfo();

}
