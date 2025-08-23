package enums;

public enum ShippingStatus {
	PENDING,         // Order has been placed but not yet processed
    SHIPPED,         // Order has been handed over to the carrier
    CANCELLED       // Order was cancelled before shipping
}
