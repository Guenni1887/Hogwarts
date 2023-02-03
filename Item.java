class Item {
    private String name;
    private int gewicht;

    public Item(String nameParameter, int gewichtParameter) {
        this.name = nameParameter;
        this.gewicht = gewichtParameter;
    }
    public String getName(){
        return this.name;
    }

    public int getGewicht(){
        return this.gewicht;
    }
}

class Book extends Item {
    private String topic;

    public Book(String nameParameter , String topicParameter , int gewichtParameter) {
        super(nameParameter , gewichtParameter);
        this.topic = topicParameter;
    }
    public String getTopic(){
       return  this.topic;
    }

    
}

class MagicalItem extends Item{
   
    public MagicalItem (String nameParameter , int gewichtParameter){
        super (nameParameter , gewichtParameter);
    }
  
}