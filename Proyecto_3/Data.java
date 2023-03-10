public class Data {
    //Datos miembro
    private String name;
    private String ID;
    private String office;
    private String course;
    private String fID;
    //constructor
    public Data(String name, String ID,String office,String course,String fID){
        this.name = name;
        this.ID = ID;
        this.office = office;
        this.course = course;
        this.fID = fID;
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
    public void setCourse(String course){
        this.course = course;
    }
    public void setFID(String fID){
        this.fID = fID;
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
    public String getCourse(){
        return course;
    }
    public String getFID(){
        return fID;
    }

    @Override
    public String toString(){
        return "Clase: " + getCourse() + " , con ID: " + getID() + " e ID de facultad: "+getFID() + " del profesor: " + getName() + " Con oficina en:" + getOffice();
    }
}
