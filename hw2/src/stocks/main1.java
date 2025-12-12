package stocks;

public class main1 {
    public static void main(String[] args) throws Exception {
        stockViewer viewer = new stockViewer();
        Thread viewerThread = new Thread(viewer);
        viewerThread.start();
    }
}
