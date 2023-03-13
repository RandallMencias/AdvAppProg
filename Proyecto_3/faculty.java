public class faculty {
    //Datos miembro
    private String name;
    private String ID;
    private String office;

    //constructor
    public faculty(String ID,String name,String office){
        this.name = name;
        this.ID = ID;
        this.office = office;

    }
    //setters y getters
    public void setName(String name){
        this.name = name;
    }
    public void setID(String ID){
        this.ID = ID;
    }
    public void setOffice(String office){
        this.office = office;
    }

    //getters
    public String getName(){
        return name;
    }
    public String getID(){
        return ID;
    }
    public String getOffice(){
        return office;
    }


    @Override
    public String toString(){
        return "Profesor: " + getName() + "; Oficina: " + getOffice() +";ID: " + getID();
    }
}
