public class Storage {
    Object shapes[] = new Object[50];

    int index = 0;
    public boolean add(Object shape){
        if(shapes[index]==null || shapes[index] instanceof  NullShape){
            shapes[index] = shape;
        }
        index++;
        return true;
    }
    public boolean remove(int inputIndex){
        NullShape nullShape = new NullShape();
        System.out.println(shapes[inputIndex]);
        if(index==0){
            return false;
        }

        shapes[inputIndex] = nullShape;
        index--;
        return true;
    }
    public boolean deleteAll(){
        NullShape nullShape = new NullShape();
        for(int i=0;i<shapes.length;i++){
            shapes[i] = nullShape;
        }
        index=0;
        return true;
    }
}
