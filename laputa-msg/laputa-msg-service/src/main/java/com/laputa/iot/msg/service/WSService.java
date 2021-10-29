package com.laputa.iot.msg.service;

public interface WSService {

    void notifyFrontend(final String message);

    void notifyUser(final String id, final String message);
}
