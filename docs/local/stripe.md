# Local Stripe Webhooks

Prerequisite: Install Stripe Cli and stripe login 

The account service handles payment webhooks at:

```text
POST /account/webhooks/payment
```

With the supplied local port/context, forward Stripe events to:

```powershell
stripe listen --forward-to http://localhost:9050/account/webhooks/payment
```

The application reads `stripe.api.secret` and `stripe.webhook.secret` from configuration. Use the signing secret printed by Stripe CLI as the local webhook secret. Stripe CLI is required because Stripe's servers cannot call a localhost endpoint directly.

The checked-in Kubernetes `stripe-cli` manifest uses the in-cluster target `http://account-service:80/account/webhooks/payment`; do not use that address in host-local development.


Ref: [https://docs.stripe.com/cli/listen](https://docs.stripe.com/cli/listen)