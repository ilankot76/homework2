package stocks;

import java.util.Random;

public class stockViewer implements Runnable {
private String name;
private StockServer.Stock ID;

public  stockViewer(String name, StockServer.Stock ID){
    this.name = name;
    this.ID = ID;
}

    @Override
    public void run(){
        System.out.println("Stock Viewer is running.");
        for(int i=0; i<10; i++) {
            System.out.println("Name: " + name + ", "+ "Stock ID: " + ID + ", " + "Stock Value: " + new StockServer().GetStock(ID));
            int temp= 1000 + new Random().nextInt(2000);
             try {
           Thread.sleep(temp);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
        }
    }
    
}
