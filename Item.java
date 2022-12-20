class Item {
    private String name;

    public Item(String nameParameter) {
        this.name = nameParameter;
    }
    public String getName(){
        return this.name;
    }
}

class Book extends Item {
    private String topic;

    public Book(String nameParameter , String topicParameter) {
        super(nameParameter);
        this.topic = topicParameter;
    }
    public String getTopic(){
       return  this.topic;
    }

    
}