# DonationAlerts Notify

* Libary for receiving alerts from the [DA](https://www.donationalerts.com/) on Java
* This Libary Using [socket.io-client](https://github.com/socketio/socket.io-client-java) and [jackson-databind](https://github.com/FasterXML/jackson-databind)

## Example Usage

```java
package ru.expram.danotify;

public class Test {

    public static void main(String[] args) {
        //https://www.donationalerts.com/dashboard/general-settings/account
        String token = "PaXLKJCOFcA43142V1iA";
        DonationAlertsNotify donationAlertsNotify = new DonationAlertsNotify(token);
        donationAlertsNotify.connect();
        donationAlertsNotify.setupEvent(donate -> {
            System.out.println(donate.message);
        });
    }
}

```
