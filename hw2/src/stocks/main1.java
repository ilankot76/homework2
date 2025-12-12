package stocks;

public class main1 {
    public static void main(String[] args) throws Exception {

        StockServer server = new StockServer();
        StockUpdateThread stockUpdater = new StockUpdateThread(server);
        Thread stockUpdateThread = new Thread(stockUpdater);

         final stockViewer viewer1 = new stockViewer("nami tan", StockServer.Stock.GOOGLE,server);
         final stockViewer viewer2 = new stockViewer("tim sruli", StockServer.Stock.APPLE,server);
         final stockViewer viewer3 = new stockViewer("sima didas", StockServer.Stock.MICROSOFT,server);

        final Thread viewerThread1 = new Thread(viewer1);
        final Thread viewerThread2 = new Thread(viewer2);
        final Thread viewerThread3 = new Thread(viewer3);

        stockUpdateThread.start();
        viewerThread1.start();
        viewerThread2.start();
        viewerThread3.start();
    }
}
