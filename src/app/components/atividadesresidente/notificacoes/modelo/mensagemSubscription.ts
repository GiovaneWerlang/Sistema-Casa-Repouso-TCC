export class MensagemSubscription{
    endpoint:string;
    p256dh:string;
    auth:string;

    constructor(endpoint:string,p256dh:string, auth:string){
        this.endpoint = endpoint;
        this.p256dh = p256dh;
        this.auth = auth;
    }
}