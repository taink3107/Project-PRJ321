package day8;

public class Person {
    String name;
    Adress adress;

    public Person(String name, Adress adress) {
        this.name = name;
        this.adress = adress;
    }
    String getProvintPerson(){
        if(this != null){
            return this.adress.province.getProvinct();
        }
       return "";
    }
    public Person() {

    }
}
