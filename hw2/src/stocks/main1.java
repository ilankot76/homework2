package stocks;

public class main1 {
    public static void main(String[] args) throws Exception {

        final StockServer server = new StockServer();

        stockViewer viewer1 = new stockViewer("Viewer1", StockServer.Stock.GOOGLE);
         stockViewer viewer2 = new stockViewer("Viewer2", StockServer.Stock.APPLE);
          stockViewer viewer3 = new stockViewer("Viewer3", StockServer.Stock.MICROSOFT);

        Thread viewerThread1 = new Thread(viewer1);
        Thread viewerThread2 = new Thread(viewer2);
        Thread viewerThread3 = new Thread(viewer3);

        viewerThread1.start();
        viewerThread2.start();
        viewerThread3.start();
    }
}
