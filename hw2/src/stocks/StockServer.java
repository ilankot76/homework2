package stocks;

import java.security.InvalidParameterException;

public class StockServer {
	
	private int microsoftValue = 220;
	private int appleValue = 110;
	private int googleValue = 1512;
	
	public enum Stock {
		MICROSOFT,
		APPLE,
		GOOGLE
	}	
	 public synchronized void updatestock(Stock stock, int Value) {
	    switch(stock) {
	        case MICROSOFT:
	            microsoftValue=Value;
	            break;
	        case APPLE:
	            appleValue = Value;
	            break;
	        case GOOGLE:
	            googleValue = Value;
	            break;
	        default:
	            throw new InvalidParameterException("no such stock type");
	    }
	}
	public synchronized int GetStock(Stock stock) {
		switch(stock) {
		case MICROSOFT:
			return microsoftValue;
		case APPLE:
			return appleValue;
		case GOOGLE:
			return googleValue;
		default:
			throw new InvalidParameterException("no such stock type");
		}
	}
	
}

