package stocks;

import java.util.Random;

public class StockUpdateThread implements Runnable{

private final StockServer server;
private int max=500;
private int min=100;

    public StockUpdateThread(StockServer server) {this.server = server;}
        
        @Override
        public void run(){
         Random rand = new Random();
            for (int i = 0; i < 10; i++) {
              int newPrice=rand.nextInt((max - min) + 1) + min;
            server.updatestock(StockServer.Stock.MICROSOFT, newPrice);
            newPrice=rand.nextInt((max - min) + 1) + min;
            server.updatestock(StockServer.Stock.APPLE, newPrice);
            newPrice=rand.nextInt((max - min) + 1) + min;
            server.updatestock(StockServer.Stock.GOOGLE, newPrice);

        try {Thread.sleep(2000);}
        catch (InterruptedException e) { e.printStackTrace();}     
    }
 
}


}
