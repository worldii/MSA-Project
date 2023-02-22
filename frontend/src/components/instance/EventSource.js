let instance;
let eventSource;
let subscribeUrl = "http://localhost:8071/subscribe";

class singletonEventSource {
    constructor() {
        if (instance) {
            return instance
        }
        instance = this
    }

    subscribe(accessToken) {
        eventSource = new EventSource(subscribeUrl + "?pk=" + parseInt(accessToken));
        console.log(eventSource)
        return eventSource;
    }

    getEventSource(){
        return eventSource;
    }
}

const EventSourceObject = Object.freeze(singletonEventSource);
export default EventSourceObject;