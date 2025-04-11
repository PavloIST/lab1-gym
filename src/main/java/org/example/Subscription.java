package org.example;

public class Subscription {
    private boolean activeSubscription;

    public Subscription() {
        this.activeSubscription = true;
    }

    public Subscription(boolean subscriptionInfo) {
        this.activeSubscription = subscriptionInfo;
    }

    public boolean isActiveSubscription() {
        return activeSubscription;
    }

    public void setActiveSubscription(boolean activeSubscription) {
        this.activeSubscription = activeSubscription;
    }
}
