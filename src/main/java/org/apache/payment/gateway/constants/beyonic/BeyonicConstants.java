package org.apache.payment.gateway.constants.beyonic;

public class BeyonicConstants {

    public static final String API_ENDPOINT = "https://app.beyonic.com/api/";

    public static final String COLLECTION_REQUEST_API_ENDPOINT = "https://app.beyonic.com/api/collectionrequests";  //Collection Requests allow you to notify a customer to send funds to you

    public static final String COLLECTION_API_ENDPOINT = "https://app.beyonic.com/api/collections"; //Beyonic uses the term “Collections” for payments you receive (or collect) from mobile subscribers

    public static final String PAYMENT_API_ENDPOINT = "https://app.beyonic.com/api/payments";   // to send payments

    public static final String ACCOUNTS_API_ENDPOINT = "https://app.beyonic.com/api/accounts";

    public static final String TRANSACTIONS_API_ENDPOINT = "https://app.beyonic.com/api/transactions";

    public static final String CONTACTS_API_ENDPOINT = "https://app.beyonic.com/api/contacts";

    public static final String EVENTS_ENDPOINT = "https://app.beyonic.com/api/events";

    public static final String WEBHOOKS_ENDPOINT = "https://app.beyonic.com/api/webhook";

    public static final String API_TOKEN = "bb38b84f38ff77abfd373c8a59761591216e2e46";  // Don't use - this will be set in the vendor record

}
