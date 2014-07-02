
package art;

import java.io.Serializable;


public class custPoint implements Serializable{
    
int x;
int y;
int length;
String color;

    public void custPoint(int x,int y,int length,String color){
        this.x=x;
        this.y=y;
        this.length=length;
        this.color=color;
        
    }
}
